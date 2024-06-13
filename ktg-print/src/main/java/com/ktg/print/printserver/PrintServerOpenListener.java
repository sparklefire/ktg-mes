package com.ktg.print.printserver;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PrintServerOpenListener implements ApplicationRunner {

    @Resource
    private PrinterServer printerServer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        printerServer.start();
    }

}
