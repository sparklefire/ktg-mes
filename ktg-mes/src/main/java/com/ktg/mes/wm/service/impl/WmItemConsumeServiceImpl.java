package com.ktg.mes.wm.service.impl;

import java.util.Date;
import java.util.List;

import cn.hutool.core.collection.CollectionUtil;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.mes.md.domain.MdWorkstation;
import com.ktg.mes.md.mapper.MdWorkstationMapper;
import com.ktg.mes.pro.domain.*;
import com.ktg.mes.pro.mapper.*;
import com.ktg.mes.wm.domain.WmItemConsumeLine;
import com.ktg.mes.wm.domain.tx.ItemConsumeTxBean;
import com.ktg.mes.wm.mapper.WmItemConsumeLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmItemConsumeMapper;
import com.ktg.mes.wm.domain.WmItemConsume;
import com.ktg.mes.wm.service.IWmItemConsumeService;

/**
 * 物料消耗记录Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-09-19
 */
@Service
public class WmItemConsumeServiceImpl implements IWmItemConsumeService 
{
    @Autowired
    private WmItemConsumeMapper wmItemConsumeMapper;

    @Autowired
    private WmItemConsumeLineMapper wmItemConsumeLineMapper;

    @Autowired
    private ProWorkorderMapper proWorkorderMapper;

    @Autowired
    private ProTaskMapper proTaskMapper;

    @Autowired
    private MdWorkstationMapper mdWorkstationMapper;

    @Autowired
    private ProProcessMapper proProcessMapper;

    @Autowired
    private ProRouteMapper proRouteMapper;

    @Autowired
    private ProRouteProductBomMapper proRouteProductBomMapper;

    /**
     * 查询物料消耗记录
     * 
     * @param recordId 物料消耗记录主键
     * @return 物料消耗记录
     */
    @Override
    public WmItemConsume selectWmItemConsumeByRecordId(Long recordId)
    {
        return wmItemConsumeMapper.selectWmItemConsumeByRecordId(recordId);
    }

    /**
     * 查询物料消耗记录列表
     * 
     * @param wmItemConsume 物料消耗记录
     * @return 物料消耗记录
     */
    @Override
    public List<WmItemConsume> selectWmItemConsumeList(WmItemConsume wmItemConsume)
    {
        return wmItemConsumeMapper.selectWmItemConsumeList(wmItemConsume);
    }

    /**
     * 新增物料消耗记录
     * 
     * @param wmItemConsume 物料消耗记录
     * @return 结果
     */
    @Override
    public int insertWmItemConsume(WmItemConsume wmItemConsume)
    {
        wmItemConsume.setCreateTime(DateUtils.getNowDate());
        return wmItemConsumeMapper.insertWmItemConsume(wmItemConsume);
    }

    /**
     * 修改物料消耗记录
     * 
     * @param wmItemConsume 物料消耗记录
     * @return 结果
     */
    @Override
    public int updateWmItemConsume(WmItemConsume wmItemConsume)
    {
        wmItemConsume.setUpdateTime(DateUtils.getNowDate());
        return wmItemConsumeMapper.updateWmItemConsume(wmItemConsume);
    }

    /**
     * 批量删除物料消耗记录
     * 
     * @param recordIds 需要删除的物料消耗记录主键
     * @return 结果
     */
    @Override
    public int deleteWmItemConsumeByRecordIds(Long[] recordIds)
    {
        return wmItemConsumeMapper.deleteWmItemConsumeByRecordIds(recordIds);
    }

    /**
     * 删除物料消耗记录信息
     * 
     * @param recordId 物料消耗记录主键
     * @return 结果
     */
    @Override
    public int deleteWmItemConsumeByRecordId(Long recordId)
    {
        return wmItemConsumeMapper.deleteWmItemConsumeByRecordId(recordId);
    }


    @Override
    public WmItemConsume generateItemConsume(ProFeedback feedback) {
        ProWorkorder workorder = proWorkorderMapper.selectProWorkorderByWorkorderId(feedback.getWorkorderId());
        MdWorkstation workstation = mdWorkstationMapper.selectMdWorkstationByWorkstationId(feedback.getWorkstationId());
        ProProcess process = proProcessMapper.selectProProcessByProcessId(workstation.getProcessId());
        ProTask task = proTaskMapper.selectProTaskByTaskId(feedback.getTaskId());
        ProRoute route = proRouteMapper.getRouteByProductId(feedback.getItemId());

        //生成消耗单头信息
        WmItemConsume itemConsume = new WmItemConsume();
        itemConsume.setWorkorderId(feedback.getWorkorderId());
        itemConsume.setWorkorderCode(workorder.getWorkorderCode());
        itemConsume.setWorkorderName(workorder.getWorkorderName());

        itemConsume.setWorkstationId(feedback.getWorkstationId());
        itemConsume.setWorkstationCode(workstation.getWorkstationCode());
        itemConsume.setWorkstationName(workstation.getWorkstationName());

        itemConsume.setTaskId(feedback.getTaskId());
        itemConsume.setTaskCode(task.getTaskCode());
        itemConsume.setTaskName(task.getTaskName());

        itemConsume.setProcessId(process.getProcessId());
        itemConsume.setProcessCode(process.getProcessCode());
        itemConsume.setProcessName(process.getProcessName());

        itemConsume.setConsumeDate(new Date());
        itemConsume.setStatus(UserConstants.ORDER_STATUS_PREPARE);
        wmItemConsumeMapper.insertWmItemConsume(itemConsume);

        //生成行信息
        //先获取当前生产的产品在此道工序中配置的物料BOM
        ProRouteProductBom param = new ProRouteProductBom();
        param.setProductId(feedback.getItemId());
        param.setRouteId(route.getRouteId());
        List<ProRouteProductBom> boms = proRouteProductBomMapper.selectProRouteProductBomList(param);
        if(CollectionUtil.isNotEmpty(boms)){
            for (ProRouteProductBom bom: boms
                 ) {
                WmItemConsumeLine line = new WmItemConsumeLine();
                line.setRecordId(itemConsume.getRecordId());
                line.setItemId(bom.getItemId());
                line.setItemCode(bom.getItemCode());
                line.setItemName(bom.getItemName());
                line.setSpecification(bom.getSpecification());
                line.setUnitOfMeasure(bom.getUnitOfMeasure());
                line.setQuantityConsume(bom.getQuantity().multiply(feedback.getQuantity()));
                line.setBatchCode(workorder.getBatchCode());
                wmItemConsumeLineMapper.insertWmItemConsumeLine(line);
            }
        }else {
            return  null; //如果本道工序没有配置BOM物料，则直接返回空
        }

        return itemConsume;
    }

    @Override
    public List<ItemConsumeTxBean> getTxBeans(Long recordId) {
        return wmItemConsumeMapper.getTxBeans(recordId);
    }
}
