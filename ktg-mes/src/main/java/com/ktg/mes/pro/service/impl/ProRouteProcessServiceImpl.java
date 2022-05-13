package com.ktg.mes.pro.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.pro.mapper.ProRouteProcessMapper;
import com.ktg.mes.pro.domain.ProRouteProcess;
import com.ktg.mes.pro.service.IProRouteProcessService;

/**
 * 工艺组成Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-13
 */
@Service
public class ProRouteProcessServiceImpl implements IProRouteProcessService 
{
    @Autowired
    private ProRouteProcessMapper proRouteProcessMapper;

    /**
     * 查询工艺组成
     * 
     * @param recordId 工艺组成主键
     * @return 工艺组成
     */
    @Override
    public ProRouteProcess selectProRouteProcessByRecordId(Long recordId)
    {
        return proRouteProcessMapper.selectProRouteProcessByRecordId(recordId);
    }

    /**
     * 查询工艺组成列表
     * 
     * @param proRouteProcess 工艺组成
     * @return 工艺组成
     */
    @Override
    public List<ProRouteProcess> selectProRouteProcessList(ProRouteProcess proRouteProcess)
    {
        return proRouteProcessMapper.selectProRouteProcessList(proRouteProcess);
    }

    @Override
    public String checkOrderNumExists(ProRouteProcess proRouteProcess) {
        ProRouteProcess process = proRouteProcessMapper.checkOrderNumExists(proRouteProcess);
        Long recordId = proRouteProcess.getRecordId()==null?-1L:proRouteProcess.getRecordId();
        if(StringUtils.isNotNull(process) && process.getRecordId().longValue() != recordId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkProcessExists(ProRouteProcess proRouteProcess) {
        ProRouteProcess process = proRouteProcessMapper.checkProcessExists(proRouteProcess);
        Long recordId = proRouteProcess.getRecordId()==null?-1L:proRouteProcess.getRecordId();
        if(StringUtils.isNotNull(process) && process.getRecordId().longValue() != recordId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public ProRouteProcess findPreProcess(ProRouteProcess proRouteProcess) {
        return proRouteProcessMapper.findPreProcess(proRouteProcess);
    }

    @Override
    public ProRouteProcess findNextProcess(ProRouteProcess proRouteProcess) {
        return proRouteProcessMapper.findNextProcess(proRouteProcess);
    }

    /**
     * 新增工艺组成
     * 
     * @param proRouteProcess 工艺组成
     * @return 结果
     */
    @Override
    public int insertProRouteProcess(ProRouteProcess proRouteProcess)
    {
        proRouteProcess.setCreateTime(DateUtils.getNowDate());
        return proRouteProcessMapper.insertProRouteProcess(proRouteProcess);
    }

    /**
     * 修改工艺组成
     * 
     * @param proRouteProcess 工艺组成
     * @return 结果
     */
    @Override
    public int updateProRouteProcess(ProRouteProcess proRouteProcess)
    {
        proRouteProcess.setUpdateTime(DateUtils.getNowDate());
        return proRouteProcessMapper.updateProRouteProcess(proRouteProcess);
    }

    /**
     * 批量删除工艺组成
     * 
     * @param recordIds 需要删除的工艺组成主键
     * @return 结果
     */
    @Override
    public int deleteProRouteProcessByRecordIds(Long[] recordIds)
    {
        return proRouteProcessMapper.deleteProRouteProcessByRecordIds(recordIds);
    }

    /**
     * 删除工艺组成信息
     * 
     * @param recordId 工艺组成主键
     * @return 结果
     */
    @Override
    public int deleteProRouteProcessByRecordId(Long recordId)
    {
        return proRouteProcessMapper.deleteProRouteProcessByRecordId(recordId);
    }
}
