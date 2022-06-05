package com.ktg.mes.report.controller;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.ktg.common.constant.UserConstants;
import com.ktg.mes.report.utils.FormatSuffixEnum;
import com.ktg.mes.report.utils.JasperContext;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.print.PrintService;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(path = "/jasper-report")
@RestController
public class JasperReportController {


    @Resource
    private DataSource dataSource;

    @Resource
    private JasperContext jasperContext;

    /**
     * 导出文件
     * 支持pdf、html、png预览
     * 支持导出pdf、word、excel、html、png到用户指定目录
     * http://localhost:2222/jasper-report/export/pdf/inline/report3/sfdf
     */
    @RequestMapping(path = "/export/{fileType}/{openType}/{sourceName}/{fileName}", method = RequestMethod.GET)
    public void export(@PathVariable("fileType") final String fileType,
                       @PathVariable("openType") final String openType,
                       @PathVariable("sourceName") final String sourceName,
                       @PathVariable("fileName") final String fileName,
                       @RequestParam(required = false) Map<String, Object> query){
        FormatSuffixEnum suffixEnum = FormatSuffixEnum.getValue(fileType);
        if (null == suffixEnum) {
            return;
        }
        if (!jasperContext.setFormatSuffix(suffixEnum)) {
           return;
        }
        JasperPrint jasperPrint = jasperContext.read(query, sourceName);
        jasperContext.setFormatSuffix(suffixEnum);
        jasperContext.executeExport(jasperPrint,openType,fileName,fileType);
    }

    /**
     * 预览并浏览器自动弹出打印
     */
    @RequestMapping(path = "/previewPrint/{reportName}", method = RequestMethod.GET)
    public void previewPrint(@PathVariable("reportName") final String reportName,
                             @RequestParam(required = false) Map<String, Object> parameters,
                             HttpServletResponse response, HttpServletRequest request)
            throws SQLException, JRException, IOException {

        parameters = parameters == null ? new HashMap<>() : parameters;        //获取文件流
        ClassPathResource resource = new ClassPathResource(UserConstants.REPORT_JASPER_PATH + reportName + ".jasper");
        InputStream jasperStream = resource.getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        parameters.put("VendorName","测试厂商");
        //byte[] pdfStream = JasperRunManager.runReportToPdf(jasperReport, parameters, dataSource.getConnection());
        byte[] pdfStream = JasperRunManager.runReportToPdf(jasperReport, parameters, new JREmptyDataSource());
        PdfReader reader = new PdfReader(pdfStream);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(pdfStream.length);
        try {
            // 给pdf加上脚本实现自动打印
            StringBuffer script = new StringBuffer();
            script.append("this.print({bUI:false,bSilent:true,bShrinkToFit:false});");
            PdfStamper stamp = new PdfStamper(reader, bos);
            stamp.setViewerPreferences(PdfWriter.HideMenubar
                    | PdfWriter.HideToolbar | PdfWriter.HideWindowUI);
            stamp.addJavaScript(script.toString());
            stamp.close();
        } catch (Exception e) {
        }
        // 输出pdf
        byte[] bytes = bos.toByteArray();
        if (bytes != null && bytes.length > 0) {
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            ServletOutputStream ouputStream = response.getOutputStream();
            try {
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
            } finally {
                if (ouputStream != null) {
                    try {
                        ouputStream.close();
                    } catch (IOException ex) {
                    }
                }
            }
        }
    }

    /**
     * 连接打印机打印
     */
    @RequestMapping(path = "/print/{sourceName}", method = RequestMethod.GET)
    public void print( @PathVariable("sourceName") final String sourceName,
                        @RequestParam(required = false) Map<String, Object> query){
        JasperPrint jasperPrint = jasperContext.read(query, sourceName);
        //设置打印方向 LANDSCAPE横向  PORTRAIT竖向
        jasperPrint.setOrientation(OrientationEnum.PORTRAIT);
        //withPrintDialog表示在打印的时候是否显示打印机设置对话框
        try {
            JasperPrintManager.printReport(jasperPrint, false);// 改true报错
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置指定打印机打印
     */
    @RequestMapping(path = "/printByPrinterName/{sourceName}", method = RequestMethod.GET)
    public void printByPrinterName( @PathVariable("sourceName") final String sourceName,
                        @RequestParam(required = false) Map<String, Object> query) throws JRException {
        JasperPrint jasperPrint = jasperContext.read(query, sourceName);
        //设置打印方向 LANDSCAPE横向  PORTRAIT竖向
        jasperPrint.setOrientation(OrientationEnum.PORTRAIT);
        //withPrintDialog表示在打印的时候是否显示打印机设置对话框
        JasperPrintManager.printReport(jasperPrint, false);// 改true报错
        PrintService[] pss = PrinterJob.lookupPrintServices();
        PrintService ps = null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < pss.length; i++) {
            String sps = pss[i].toString();
            sb.append(sps + "\n");
            //如果打印机名称相同
            if (sps.equalsIgnoreCase("Win32 Printer : Adobe PDF")) {// 可变参数，可让前端传参
                ps = pss[i];
            }
        }
        JRAbstractExporter je = new JRPrintServiceExporter();
        je.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        //设置指定打印机
        je.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, ps);
        je.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, false);
        je.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, false);
        je.exportReport();
    }
}
