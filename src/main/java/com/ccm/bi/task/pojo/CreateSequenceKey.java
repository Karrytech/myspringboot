package com.ccm.bi.task.pojo;

import java.text.SimpleDateFormat;

/**   
 *    
 * 项目名称：management   
 * 类名称：ProcessTools   
 * 类描述：   处理类 
 * @version    
 *    
 */
public class CreateSequenceKey
{
	
	/**
	 * 获取唯一主键ID
	 *
	 * @return
	 */
	public static String getIndexNum(String flag)
	{
	  String indexCode="";
	  return indexCode;
	}
	
	public static String getIndexNumBy(String flag)
	{
	    Long num = System.currentTimeMillis();
	    SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
		String str=flag+bartDateFormat.format(num);
	  return str;
	}
}


 