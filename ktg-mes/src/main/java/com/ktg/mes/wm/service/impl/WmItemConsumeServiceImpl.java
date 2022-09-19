package com.ktg.mes.wm.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
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
}
