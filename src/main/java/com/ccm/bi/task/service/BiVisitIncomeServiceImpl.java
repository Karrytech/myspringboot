package com.ccm.bi.task.service;

import com.ccm.bi.task.dao.BiVisitIncomeMonthDAO;
import com.ccm.bi.task.pojo.BiVisitIncomeMonth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@Component
public class BiVisitIncomeServiceImpl implements BiVisitIncomeService {
	@Autowired
	private BiVisitIncomeMonthDAO biVisitIncomeMonthDAO;
	
	private static final Logger log = LoggerFactory.getLogger(BiVisitIncomeServiceImpl.class);


	@Override
	public void saveVisitIncomeMonth(BiVisitIncomeMonth income)
			throws SQLException {
		try{
			biVisitIncomeMonthDAO.saveVisitIncome(income);
		}catch(Exception e){
			log.error(e.getMessage());
		}
	}

	@Override
	public List<BiVisitIncomeMonth> findVisitIncomeMonthNativeSQL(String flag,
			String start, String end) {

		String sql = "  SELECT PATIENT_TYPE, SUM(BINCOME) AS C"
	               + "  FROM BI_VISIT_INCOME_MONTH"
	               + "  WHERE FLAG = '"+flag+"'"
	               + "  AND IN_DATETIME BETWEEN '"+start+"' AND '"+end+"'"
	               + "  GROUP BY PATIENT_TYPE"
	               + "  UNION ALL"
	               + "  SELECT 'C' PATIENT_TYPE, SUM(BINCOME) AS C"
	               + "  FROM BI_VISIT_INCOME_MONTH"
	               + "  WHERE FLAG = '"+flag+"'"
	               + "  AND IN_DATETIME BETWEEN '"+start+"' AND '"+end+"'";
		       log.info(sql);
		return biVisitIncomeMonthDAO.findVisitIncomeNativeSQL(sql);
	}

}
