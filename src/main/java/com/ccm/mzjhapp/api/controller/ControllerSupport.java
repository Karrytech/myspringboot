package com.ccm.mzjhapp.api.controller;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/17 10:23
 * @Description:
 */
public class ControllerSupport {

    private static ControllerSupport instance = new ControllerSupport(){};

    private ControllerSupport(){
    }

    public static ControllerSupport getInstance(){
        return instance;
    }

    public static String buildReportParam(String patientID, String startTime, String endTime) throws IOException {
        DocumentFactory factory = new DocumentFactory();
        Document document = factory.createDocument();
        Element root = document.addElement("req");

        Element id_type = root.addElement("patient_no");
        id_type.setText(!StringUtils.isEmpty(patientID) ? patientID : "");

        Element start_time = root.addElement("start_time");
        start_time.setText(!StringUtils.isEmpty(startTime) ? startTime : "");

        Element end_time = root.addElement("end_time");
        end_time.setText(!StringUtils.isEmpty(endTime) ? endTime : "");

        StringWriter out = new StringWriter();
        XMLWriter xw = new XMLWriter(out, new OutputFormat(" ", true, "UTF-8"));
        xw.write(document);

        return out.toString();
    }
}
