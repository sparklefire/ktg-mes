package com.ktg.mes.report.utils;

import com.ktg.common.constant.UserConstants;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.base.JRBaseReport;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Map;

public class ReportHelper {

    public static void initReport(JasperReport report,String type){

        if(UserConstants.REPORT_EXCEL_TYPE.equals(type))try{
            Field margin = JRBaseReport.class.getDeclaredField("leftMargin");
            margin.setAccessible(true);
            margin.setInt(report, 0);
            margin = JRBaseReport.class.getDeclaredField("topMargin");
            margin.setAccessible(true);
            margin.setInt(report, 0);
            margin = JRBaseReport.class.getDeclaredField("bottomMargin");
            margin.setAccessible(true);
            margin.setInt(report, 0);
            Field pageHeight = JRBaseReport.class.getDeclaredField("pageHeight");
            pageHeight.setAccessible(true);
            pageHeight.setInt(report, 2147483647);
        }catch (Exception e){

        }
    }

    public static void showHtml(String fileName, String reportFile, HttpServletRequest request, HttpServletResponse response, Map params, JRDataSource dataSource)throws JRException,IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        JRAbstractExporter exporter = null;//getJRExporter("HTML");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile, params, dataSource);
        request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);

        PrintWriter out = response.getWriter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
        //exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);



    }


}
