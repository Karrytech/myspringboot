package com.ccm.mzjhapp.base.util;

import com.ccm.mzjhapp.base.vo.DtmjReportInfo;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class DocUtil {
	
	
	public static List<DtmjReportInfo> getAnalysisCreateReport(String f, String patientId) {
		List<DtmjReportInfo> listReportInfo = new ArrayList<>();
		boolean flag = false;
		try {
			if (f.length() > 0) {
				String name= "";
				String sex = "";
				String age = "";
				String code = "";
				String msc = "";
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(new InputSource(new StringReader(f)));
				NodeList nl = doc.getElementsByTagName("res");
				for (int i = 0; i < nl.getLength(); i++) {
					if (doc.getElementsByTagName("zchar1").item(i).getFirstChild() != null) {
						if (doc.getElementsByTagName("zchar1").item(i).getFirstChild().getNodeValue() != null 
								&& !"".equals(doc.getElementsByTagName("zchar1").item(i).getFirstChild().getNodeValue())) {
							code = doc.getElementsByTagName("zchar1").item(i).getFirstChild().getNodeValue();
							if(code.equals("0")) {
								flag = true;
							}
						}
					}
					if (doc.getElementsByTagName("zchar2").item(i).getFirstChild() != null) {
						if (doc.getElementsByTagName("zchar2").item(i).getFirstChild().getNodeValue() != null 
								&& !"".equals(doc.getElementsByTagName("zchar2").item(i).getFirstChild().getNodeValue())) {
							msc = doc.getElementsByTagName("zchar2").item(i).getFirstChild().getNodeValue();
						}
					}
					if (doc.getElementsByTagName("patient_name").item(i).getFirstChild() != null) {
						if (doc.getElementsByTagName("patient_name").item(i).getFirstChild().getNodeValue() != null
								&& !"".equals(doc.getElementsByTagName("patient_name").item(i).getFirstChild().getNodeValue())) {
							name = doc.getElementsByTagName("patient_name").item(i).getFirstChild().getNodeValue();
							
							
						} 
					}
					if (doc.getElementsByTagName("sex").item(i).getFirstChild() != null) {
						if (doc.getElementsByTagName("sex").item(i).getFirstChild().getNodeValue() != null
								&& !"".equals(doc.getElementsByTagName("sex").item(i).getFirstChild().getNodeValue())) {
							sex = doc.getElementsByTagName("sex").item(i).getFirstChild().getNodeValue();
						}
					} 
					if (doc.getElementsByTagName("age").item(i).getFirstChild() != null) {
						if (doc.getElementsByTagName("age").item(i).getFirstChild().getNodeValue() != null 
								&& !"".equals(doc.getElementsByTagName("age").item(i).getFirstChild().getNodeValue())) {
							age = doc.getElementsByTagName("age").item(i).getFirstChild().getNodeValue();
						}
					}
					
					if(flag) {
						NodeList n2 = doc.getElementsByTagName("reportInfo");
						for (int j = 0; j < n2.getLength(); j++) {
							DtmjReportInfo rtif = new DtmjReportInfo();
							rtif.setPatient_no(patientId);
							rtif.setPatient_name(name);
							rtif.setAge(age);
							rtif.setSex(sex);
							rtif.setZchar1(code);
							rtif.setZchar2(msc);
							if (doc.getElementsByTagName("visit_time").item(j).getFirstChild() != null) {
								if (doc.getElementsByTagName("visit_time").item(j).getFirstChild().getNodeValue() != null
										&& !"".equals(doc.getElementsByTagName("visit_time").item(j).getFirstChild().getNodeValue())) {
									rtif.setVisit_time(doc.getElementsByTagName("visit_time").item(j).getFirstChild().getNodeValue());
									
								} 
							}
							if (doc.getElementsByTagName("dept_name").item(j).getFirstChild() != null) {
								if (doc.getElementsByTagName("dept_name").item(j).getFirstChild().getNodeValue() != null
										&& !"".equals(doc.getElementsByTagName("dept_name").item(j).getFirstChild().getNodeValue())) {
									rtif.setDept_name(doc.getElementsByTagName("dept_name").item(j).getFirstChild().getNodeValue());
									
								} 
							}
							if (doc.getElementsByTagName("doctor_name").item(j).getFirstChild() != null) {
								if (doc.getElementsByTagName("doctor_name").item(j).getFirstChild().getNodeValue() != null
										&& !"".equals(doc.getElementsByTagName("doctor_name").item(j).getFirstChild().getNodeValue())) {
									rtif.setDoctor_name(doc.getElementsByTagName("doctor_name").item(j).getFirstChild().getNodeValue());
									
								} 
							}
							if (doc.getElementsByTagName("check_type").item(j).getFirstChild() != null) {
								if (doc.getElementsByTagName("check_type").item(j).getFirstChild().getNodeValue() != null
										&& !"".equals(doc.getElementsByTagName("check_type").item(j).getFirstChild().getNodeValue())) {
									rtif.setCheck_type(doc.getElementsByTagName("check_type").item(j).getFirstChild().getNodeValue());
									
								} 
							}
							if (doc.getElementsByTagName("type").item(j).getFirstChild() != null) {
								if (doc.getElementsByTagName("type").item(j).getFirstChild().getNodeValue() != null
										&& !"".equals(doc.getElementsByTagName("type").item(j).getFirstChild().getNodeValue())) {
									rtif.setType(doc.getElementsByTagName("type").item(j).getFirstChild().getNodeValue());
									
								} 
							}
							
							if (doc.getElementsByTagName("url").item(j).getFirstChild() != null) {
								if (doc.getElementsByTagName("url").item(j).getFirstChild().getNodeValue() != null
										&& !"".equals(doc.getElementsByTagName("url").item(j).getFirstChild().getNodeValue())) {
									rtif.setUrl(doc.getElementsByTagName("url").item(j).getFirstChild().getNodeValue());
									
								} 
							}
							if (doc.getElementsByTagName("is_have").item(j).getFirstChild() != null) {
								if (doc.getElementsByTagName("is_have").item(j).getFirstChild().getNodeValue() != null
										&& !"".equals(doc.getElementsByTagName("is_have").item(j).getFirstChild().getNodeValue())) {
									rtif.setIs_have(doc.getElementsByTagName("is_have").item(j).getFirstChild().getNodeValue());
									
								} 
							}
							if (doc.getElementsByTagName("zchar3").item(j).getFirstChild() != null) {
								if (doc.getElementsByTagName("zchar3").item(j).getFirstChild().getNodeValue() != null
										&& !"".equals(doc.getElementsByTagName("zchar3").item(j).getFirstChild().getNodeValue())) {
									rtif.setZchar3(doc.getElementsByTagName("zchar3").item(j).getFirstChild().getNodeValue());
									
								} 
							}
							if (doc.getElementsByTagName("zchar4").item(j).getFirstChild() != null) {
								if (doc.getElementsByTagName("zchar4").item(j).getFirstChild().getNodeValue() != null
										&& !"".equals(doc.getElementsByTagName("zchar4").item(j).getFirstChild().getNodeValue())) {
									rtif.setZchar4(doc.getElementsByTagName("zchar4").item(j).getFirstChild().getNodeValue());
									
								} 
							}
							
							
							listReportInfo.add(rtif);
						}
						
					}else {
						DtmjReportInfo rtif = new DtmjReportInfo();
						rtif.setPatient_no(patientId);
						rtif.setPatient_name(name);
						rtif.setAge(age);
						rtif.setSex(sex);
						rtif.setZchar1(code);
						rtif.setZchar2(msc);
						listReportInfo.add(rtif);
					}
					
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listReportInfo;
		
	}

}
