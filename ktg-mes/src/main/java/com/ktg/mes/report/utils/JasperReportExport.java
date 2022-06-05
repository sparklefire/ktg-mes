package com.ktg.mes.report.utils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: create by libin
 * @version: v1.0
 * @description: pl.piomin.jasperreport.handler
 * @date:2020/4/15
 */
public abstract class JasperReportExport {
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected HttpServletRequest request;

    public final void execute(JasperPrint jasperPrint, String openType, String fileName,String fileType){
        OpenTypeEnum openTypeEnum = OpenTypeEnum.getValue(openType);
        if (openTypeEnum==null) {
            openTypeEnum=OpenTypeEnum.inline;
        }
        initOpenType(openTypeEnum,fileName,fileType);
        try {
            executEexport(jasperPrint);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
    public abstract void initOpenType(OpenTypeEnum openType, String fileName,String fileType);
    /**
     * 导出
     */
    public abstract void executEexport(JasperPrint jasperPrint) throws IOException, JRException;

}
