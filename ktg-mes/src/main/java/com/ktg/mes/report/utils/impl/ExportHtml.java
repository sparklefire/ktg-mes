package com.ktg.mes.report.utils.impl;


import com.ktg.mes.report.utils.JasperReportExport;
import com.ktg.mes.report.utils.OpenTypeEnum;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author: create by libin
 * @version: v1.0
 * @description: com.yingyinqi.jasperreport.handler
 * @date:2020/4/15
 */
@Service
public class ExportHtml extends JasperReportExport {

    @Override
    public void initOpenType(OpenTypeEnum openType, String fileName, String fileType) {
        if (openType.equals(OpenTypeEnum.attachment)) {
            response.setContentType("text/html; charset=utf-8");
            response.setHeader("Content-disposition", "attachment; filename=file.html");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "No-cache");
            response.setDateHeader("Expires", 0);
        }
    }

    @Override
    public void executEexport(JasperPrint jasperPrint) throws IOException, JRException {
        HtmlExporter exporter = new HtmlExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getOutputStream()));
        exporter.setConfiguration(new SimpleHtmlExporterConfiguration());
        exporter.exportReport();
    }
}

