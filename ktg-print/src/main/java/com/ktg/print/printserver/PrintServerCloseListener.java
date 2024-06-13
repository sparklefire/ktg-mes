package com.ktg.print.printserver;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PrintServerCloseListener implements DisposableBean {

    @Resource
    private PrinterServer printerServer;

    @Override
    public void destroy() throws Exception {
        printerServer.stop();
    }
}
