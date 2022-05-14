package com.ktg.mes.pro.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.dv.domain.DvMachineryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.pro.mapper.ProWorkorderMapper;
import com.ktg.mes.pro.domain.ProWorkorder;
import com.ktg.mes.pro.service.IProWorkorderService;

/**
 * 生产工单Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-09
 */
@Service
public class ProWorkorderServiceImpl implements IProWorkorderService 
{
    @Autowired
    private ProWorkorderMapper proWorkorderMapper;

    /**
     * 查询生产工单
     * 
     * @param workorderId 生产工单主键
     * @return 生产工单
     */
    @Override
    public ProWorkorder selectProWorkorderByWorkorderId(Long workorderId)
    {
        return proWorkorderMapper.selectProWorkorderByWorkorderId(workorderId);
    }

    /**
     * 查询生产工单列表
     * 
     * @param proWorkorder 生产工单
     * @return 生产工单
     */
    @Override
    public List<ProWorkorder> selectProWorkorderList(ProWorkorder proWorkorder)
    {
        return proWorkorderMapper.selectProWorkorderList(proWorkorder);
    }

    @Override
    public String checkWorkorderCodeUnique(ProWorkorder proWorkorder) {
        ProWorkorder workorder = proWorkorderMapper.checkWorkorderCodeUnique(proWorkorder);
        Long workorderId = proWorkorder.getWorkorderId() == null? -1L: proWorkorder.getWorkorderId();
        if(StringUtils.isNotNull(workorder) && workorder.getWorkorderId().longValue() != workorderId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }


    /**
     * 新增生产工单
     * 
     * @param proWorkorder 生产工单
     * @return 结果
     */
    @Override
    public int insertProWorkorder(ProWorkorder proWorkorder)
    {
        if(proWorkorder.getParentId()!= null){
            ProWorkorder parent = proWorkorderMapper.selectProWorkorderByWorkorderId(proWorkorder.getParentId());
            if(StringUtils.isNotNull(parent)){
                proWorkorder.setAncestors(parent.getAncestors()+","+parent.getParentId());
            }
        }

        proWorkorder.setCreateTime(DateUtils.getNowDate());
        return proWorkorderMapper.insertProWorkorder(proWorkorder);
    }

    /**
     * 修改生产工单
     * 
     * @param proWorkorder 生产工单
     * @return 结果
     */
    @Override
    public int updateProWorkorder(ProWorkorder proWorkorder)
    {
        proWorkorder.setUpdateTime(DateUtils.getNowDate());
        return proWorkorderMapper.updateProWorkorder(proWorkorder);
    }

    /**
     * 批量删除生产工单
     * 
     * @param workorderIds 需要删除的生产工单主键
     * @return 结果
     */
    @Override
    public int deleteProWorkorderByWorkorderIds(Long[] workorderIds)
    {
        return proWorkorderMapper.deleteProWorkorderByWorkorderIds(workorderIds);
    }

    /**
     * 删除生产工单信息
     * 
     * @param workorderId 生产工单主键
     * @return 结果
     */
    @Override
    public int deleteProWorkorderByWorkorderId(Long workorderId)
    {
        return proWorkorderMapper.deleteProWorkorderByWorkorderId(workorderId);
    }
}
