package com.ktg.system.service;

import com.ktg.common.core.domain.entity.SysAutoCodeResult;

import java.util.List;

public interface IAutoCodeResultService {

    /**
     * 根据条件查询当前的记录表中是否有满足指定条件的记录
     * gen_date条件要以like 'xxx%' 方式查询
     * @param sysAutoCodeResult
     * @return
     */
    public List<SysAutoCodeResult> list(SysAutoCodeResult sysAutoCodeResult);

    public void saveAutoCodeResult(SysAutoCodeResult sysAutoCodeResult);

    public void updateAutoCodeResult(SysAutoCodeResult sysAutoCodeResult);
}
