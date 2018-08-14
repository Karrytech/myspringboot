package com.ccm.bi.task.dao;

import com.ccm.bi.task.pojo.BiVisitIncomeMonth;

import java.sql.SQLException;
import java.util.List;

//@Repository(value="BiVisitIncomeMonth")
public interface BiVisitIncomeMonthDAO {
	
	void saveVisitIncome(BiVisitIncomeMonth income) throws SQLException;
    
    List<BiVisitIncomeMonth> findVisitIncomeNativeSQL(String sql);
}
