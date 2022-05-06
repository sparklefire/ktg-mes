package com.ktg.system.strategy;

import com.ktg.common.core.domain.entity.SysAutoCodePart;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Order(1)
public class PartTypeNowDateHandler implements PartTypeTemplate {

    @Override
    public String partHandle(SysAutoCodePart sysAutoCodePart) {
        String formartDate = sysAutoCodePart.getDateFormart();
        return DateTimeFormatter.ofPattern(formartDate).format(LocalDateTime.now());
    }
}
