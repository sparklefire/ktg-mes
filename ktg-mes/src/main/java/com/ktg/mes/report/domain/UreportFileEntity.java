package com.ktg.mes.report.domain;

import com.ktg.common.core.domain.BaseEntity;

import java.util.Arrays;
import java.util.Date;

/**
 *  Ureport文件 实体类
 * @author yanshikui
 * @version 2022年10月7日
 *
 */

public class UreportFileEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private byte[] content;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UreportFileEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content=" + Arrays.toString(content) +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
