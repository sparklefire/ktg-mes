package com.ktg.system.strategy;

import com.ktg.common.core.domain.entity.SysAutoCodePart;

public interface PartTypeTemplate {
    /**
     * 分段的处理规则
     * @param sysAutoCodePart
     * @return
     */
    String partHandle(SysAutoCodePart sysAutoCodePart);
}
