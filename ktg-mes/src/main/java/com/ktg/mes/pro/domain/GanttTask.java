package com.ktg.mes.pro.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ktg.common.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class GanttTask {
    private static final long serialVersionUID = 1L;

    private List<GanttData> data;

    private List<GanttLink> links;

    public List<GanttData> getData() {
        return data;
    }

    public void setData(List<GanttData> data) {
        this.data = data;
    }

    public List<GanttLink> getLinks() {
        return links;
    }

    public void setLinks(List<GanttLink> links) {
        this.links = links;
    }
}
