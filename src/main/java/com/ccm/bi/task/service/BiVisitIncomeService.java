package com.ccm.bi.task.service;

import com.ccm.bi.task.pojo.BiVisitIncomeMonth;

import java.sql.SQLException;
import java.util.List;


public interface BiVisitIncomeService {
	
	void saveVisitIncomeMonth(BiVisitIncomeMonth income) throws SQLException;
    
    List<BiVisitIncomeMonth> findVisitIncomeMonthNativeSQL(String flag, String start, String end);
    
}
