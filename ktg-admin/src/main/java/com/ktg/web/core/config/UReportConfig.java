package com.ktg.web.core.config;

import com.bstek.ureport.definition.datasource.BuildinDatasource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@EnableAutoConfiguration
@Configuration
public class UReportConfig implements BuildinDatasource {

    @Resource
    DataSource dataSource;

    /**
     * 返回数据源的名称
     * @return
     */
    @Override
    public String name() {
        return "内置数据源";
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("连接数据源失败！");
        }
        return null;
    }
}

