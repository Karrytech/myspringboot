package com.ccm.bi.task.task;

import com.ccm.bi.task.pojo.BiVisitIncomeMonth;
import com.ccm.bi.task.pojo.CreateSequenceKey;
import com.ccm.bi.task.service.BiVisitIncomeService;
import com.ccm.bi.task.util.CihJdbcUtils;
import com.ccm.bi.task.util.DtmzJdbcUtils;
import com.ccm.bi.task.util.PromedJdbcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;
import java.util.TimeZone;
@Component
public class IncomeMonthQuartzJob {
	
	@Autowired
	private BiVisitIncomeService biVisitIncomeService;
	Logger log = LoggerFactory.getLogger(IncomeMonthQuartzJob.class);
	public void timeTask() {

		log.info("医院收入月统计数据同步接口任务开始。。。。。"+new Timestamp(System.currentTimeMillis()));
		
		String startTime = getFirstDayOfLastMonth();
		String endTime = getFirstDayOfMonth();
		
		System.out.println("上月开始日：" + startTime);
		System.out.println("本月开始日：" + endTime);
		
		StringBuilder strSQL = buildSql(startTime, endTime);
		
		this.datongRun( strSQL);
		this.shRun(strSQL);
		this.cihRun( strSQL);
		
		log.info("医院收入月统计数据同步接口任务结束。。。。。"+new Timestamp(System.currentTimeMillis()));
	}
	
	public boolean synByHand (Set<String> HosSet,String startTime, String endTime){
		StringBuilder strSQL = buildSql(startTime, endTime);
		if (HosSet.contains("DTMZ")) {
			this.datongRun( strSQL);
		}
		if (HosSet.contains("PROMED")) {
			this.shRun(strSQL);
		}
		if (HosSet.contains("CIH")) {
			this.cihRun( strSQL);
		}
		
		return true;
	}

	private StringBuilder buildSql(String startTime, String endTime) {
		StringBuilder strSQL = new StringBuilder();
		
		//SQL准备
		strSQL.append(" SELECT X.A NAME, X.REVENUE_CENTRE_CODE, X.REVENUE_CENTRE_DESC, SUM(X.B) NUM");
		strSQL.append(" FROM (SELECT '' || PGCOMMON.FXGETCODEDESC(V.PATIENT_TYPE) AS A,");
		strSQL.append(" RM.REVENUE_CENTRE_CODE,");
		strSQL.append(" RM.REVENUE_CENTRE_DESC,");
		strSQL.append(" SUM(PAT.TXN_AMOUNT - PAT.DISCOUNT_AMOUNT - PACKAGE_DISCOUNT_AMOUNT) AS B");
		strSQL.append(" FROM PATIENTACCOUNTTXN PAT,");
		strSQL.append(" CHARGE C,");
		strSQL.append(" PATIENTACCOUNT PA,");
		strSQL.append(" VISIT V,");
		strSQL.append(" REVENUECENTREMSTR RM");
		strSQL.append(" WHERE PAT.CHARGE_STATUS = 'CTTNOR'");
		strSQL.append(" AND C.PATIENTACCOUNTTXN_ID = PAT.PATIENTACCOUNTTXN_ID(+)");
		strSQL.append(" AND PA.PATIENTACCOUNT_ID = PAT.PATIENTACCOUNT_ID");
		strSQL.append(" AND V.VISIT_ID = PA.VISIT_ID");
		strSQL.append(" AND RM.REVENUECENTREMSTR_ID = C.CHARGE_REVENUECENTREMSTR_ID(+)");
		strSQL.append(" AND PAT.ENTERED_DATETIME > TO_DATE('"+startTime+"', 'YYYYMMDD')");
		strSQL.append(" AND PAT.ENTERED_DATETIME < TO_DATE('"+endTime+"', 'YYYYMMDD')");
		strSQL.append(" GROUP BY RM.REVENUE_CENTRE_DESC,");
		strSQL.append(" V.PATIENT_TYPE,");
		strSQL.append(" RM.REVENUE_CENTRE_CODE,");
		strSQL.append(" RM.REVENUE_CENTRE_DESC");
		strSQL.append(" UNION ALL");
		strSQL.append(" SELECT '' || PGCOMMON.FXGETCODEDESC(W.CODE_CAT || W.CODE_ABBR) AS,");
		strSQL.append(" Z.REVENUE_CENTRE_CODE,");
		strSQL.append(" Z.REVENUE_CENTRE_DESC,");
		strSQL.append(" 0 AS B");
		strSQL.append(" FROM (SELECT * FROM CODEMSTR CM");
		strSQL.append(" WHERE CM.CODE_CAT = 'PTY'");
		strSQL.append(" AND CM.DEFUNCT_IND = 'N') W,");
		strSQL.append(" (SELECT * FROM REVENUECENTREMSTR RM WHERE RM.DEFUNCT_IND = 'N') Z) X");
		strSQL.append(" GROUP BY X.A, X.REVENUE_CENTRE_CODE, X.REVENUE_CENTRE_DESC");
		strSQL.append(" ORDER BY X.A, X.REVENUE_CENTRE_CODE");
		
		log.info(strSQL.toString());
		return strSQL;
	}

	private void shRun(StringBuilder strSQL) {
		log.info("PROMED医院收入月统计数据同步接口任务开始。。。。。"+new Timestamp(System.currentTimeMillis()));
		Connection connp = PromedJdbcUtils.getConnection();//百瑞HIS数据源
		String flag = "PROMED";
		execute(strSQL, connp, flag);
		log.info("PROMED医院收入月统计数据同步接口任务结束。。。。。"+new Timestamp(System.currentTimeMillis()));
	}

	private void cihRun(StringBuilder strSQL) {
		log.info("CIH医院收入月统计数据同步接口任务开始。。。。。"+new Timestamp(System.currentTimeMillis()));
		Connection connc = CihJdbcUtils.getConnection();//新加坡HIS数据源
		String flag = "CIH";
		execute(strSQL, connc, flag);
		log.info("CIH医院收入月统计数据同步接口任务结束。。。。。"+new Timestamp(System.currentTimeMillis()));
	}
	
	private void datongRun(StringBuilder strSQL) {
		log.info("DTMZ医院收入月统计数据同步接口任务开始。。。。。"+new Timestamp(System.currentTimeMillis()));
		Connection connd = DtmzJdbcUtils.getConnection();
		String flag = "DTMZ";
		execute(strSQL, connd, flag);
		log.info("DTMZ医院收入月统计数据同步接口任务结束。。。。。"+new Timestamp(System.currentTimeMillis()));
	}

	private void execute(StringBuilder strSQL, Connection connd, String flag) {

		log.info("医院收入月统计数据_execute"+new Timestamp(System.currentTimeMillis()));
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String curDate = s.format(c.getTime());  //当前日期
		
		String startTime = getFirstDayOfLastMonth();
		String endTime = getFirstDayOfMonth();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connd.createStatement();
			rs = stmt.executeQuery(strSQL.toString());
			while(rs.next()) {
				BiVisitIncomeMonth b = new BiVisitIncomeMonth();
				String id = CreateSequenceKey.getIndexNum(flag);
				b.setInId(id);
	            b.setPatientType(nullToString(rs.getString("NAME")));//患者类型
	            b.setIcode(nullToString(rs.getString("REVENUE_CENTRE_CODE")));//科室code
	            b.setIdesc(nullToString(rs.getString("REVENUE_CENTRE_DESC")));//科室desc
	            b.setBincome(new BigDecimal(nullToString(rs.getString("NUM"))));//总数
	            b.setInDatetime(startTime);
	            b.setInDatetimeEnd(endTime);
	            
	            b.setFlag(flag);
	            b.setDeleteFlag("0");
	            b.setSycDateTime(curDate);//当前日期
	            b.setTimes(1);
	            
	            biVisitIncomeService.saveVisitIncomeMonth(b);
			}
			
			
		} catch (SQLException e) {
			log.error(e.getMessage());
		}finally {
			try{
                if(rs != null){
                    rs.close();
                    rs = null;
                }
                if(stmt != null){
                    stmt.close();
                    stmt = null;
                }
                
                DtmzJdbcUtils.close(connd);
            }catch(Exception e){
                e.printStackTrace();
            }
		}
	}
	
	 /**
     * 空值转换
     * @param iOption
     * @return
     */
    private String nullToString(String iOption){
        if(iOption == null){
            iOption = "";
        }else{
            iOption = iOption.trim();
        }
        return iOption;
    }//end of method nullToString
	
	public static void main(String[] args) {
		  	new IncomeMonthQuartzJob().timeTask();
		  
	}

	/**
	* 获得上月第一天
	* @return
	*/
	public static String getFirstDayOfLastMonth(){
        Calendar cal = Calendar.getInstance();
        //获取月最小天数
        cal.add(Calendar.MONTH, -1);
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

	/**
	* 获得上月最后一天
	* @return
	*/
	public static String getFirstDayOfMonth(){
        Calendar cal = Calendar.getInstance();
        //获取上月最大天数
        int lastDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

}
