package com.ccm.bi.task.dao;

import com.ccm.bi.task.pojo.BiVisitIncomeMonth;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class BiVisitIncomeMonthDAOImpl /*extends BaseDao<BiVisitIncomeMonth>*/ implements
		BiVisitIncomeMonthDAO {

	/*@Override*/
	public void saveVisitIncome(BiVisitIncomeMonth income) throws SQLException {
//		super.save(income);

	}

	/*@Override*/
	public List<BiVisitIncomeMonth> findVisitIncomeNativeSQL(String sql) {
		/*Query query =  super.getEntityManager().createNativeQuery(sql);
		return query ==null?null:query.getResultList();*/
		return null;
	}



}
