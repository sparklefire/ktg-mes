package com.ktg.mes.wm.service;

import com.ktg.mes.wm.domain.WmItemRecptLine;
import com.ktg.mes.wm.domain.tx.ItemRecptTxBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStorageCoreService {

    /**
     * 处理物料入库单
     * @param lines
     */
    public void processItemRecpt(List<ItemRecptTxBean> lines);


}
