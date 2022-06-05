package com.ktg.mes.report.utils.impl;

import com.ktg.mes.report.utils.JasperReportExport;
import com.ktg.mes.report.utils.OpenTypeEnum;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleDocxExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: create by libin
 * @version: v1.0
 * @description: com.yingyinqi.jasperreport.handler
 * @date:2020/4/15
 */
@Service
public class ExportWord extends JasperReportExport {
    @Override
    public void initOpenType(OpenTypeEnum openType, String fileName, String fileType) {
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment; filename=file.doc");
    }

    @Override
    public void executEexport(JasperPrint jasperPrint) throws IOException, JRException {
        JRDocxExporter exporter = new JRDocxExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setConfiguration(new SimpleDocxExporterConfiguration());
        OutputStream outputStream = response.getOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();
    }
}
