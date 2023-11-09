package com.ktg.system.domain.vo;

import java.util.Map;

/**
 * WebSocket 来自 Neuron 的 VO
 *
 * @author qiushile <qiushile@sina.com>
 * @date 2023/11/7
 */
public class NeuronVo {

    private String node;

    private String group;

    private Long timestamp;

    private Map values;

    private Map errors;

    private Map metas;

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Map getValues() {
        return values;
    }

    public void setValues(Map values) {
        this.values = values;
    }

    public Map getErrors() {
        return errors;
    }

    public void setErrors(Map errors) {
        this.errors = errors;
    }

    public Map getMetas() {
        return metas;
    }

    public void setMetas(Map metas) {
        this.metas = metas;
    }
}
