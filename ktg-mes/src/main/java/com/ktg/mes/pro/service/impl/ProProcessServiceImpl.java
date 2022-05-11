package com.ktg.mes.pro.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.pro.mapper.ProProcessMapper;
import com.ktg.mes.pro.domain.ProProcess;
import com.ktg.mes.pro.service.IProProcessService;

/**
 * 生产工序Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-11
 */
@Service
public class ProProcessServiceImpl implements IProProcessService 
{
    @Autowired
    private ProProcessMapper proProcessMapper;

    /**
     * 查询生产工序
     * 
     * @param processId 生产工序主键
     * @return 生产工序
     */
    @Override
    public ProProcess selectProProcessByProcessId(Long processId)
    {
        return proProcessMapper.selectProProcessByProcessId(processId);
    }

    /**
     * 查询生产工序列表
     * 
     * @param proProcess 生产工序
     * @return 生产工序
     */
    @Override
    public List<ProProcess> selectProProcessList(ProProcess proProcess)
    {
        return proProcessMapper.selectProProcessList(proProcess);
    }

    /**
     * 检查工序编码是否唯一
     * @param proProcess
     * @return
     */
    @Override
    public String checkProcessCodeUnique(ProProcess proProcess) {
        ProProcess process = proProcessMapper.checkProcessCodeUnique(proProcess);
        Long processId = proProcess.getProcessId()==null?-1L:proProcess.getProcessId();
        if(StringUtils.isNotNull(process) && process.getProcessId().longValue() != processId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }


    /**
     * 检查工序名称是否唯一
     * @param proProcess
     * @return
     */
    @Override
    public String checkProcessNameUnique(ProProcess proProcess) {
        ProProcess process = proProcessMapper.checkProcessNameUnique(proProcess);
        Long processId = proProcess.getProcessId()==null?-1L:proProcess.getProcessId();
        if(StringUtils.isNotNull(process) && process.getProcessId().longValue() != processId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增生产工序
     * 
     * @param proProcess 生产工序
     * @return 结果
     */
    @Override
    public int insertProProcess(ProProcess proProcess)
    {
        proProcess.setCreateTime(DateUtils.getNowDate());
        return proProcessMapper.insertProProcess(proProcess);
    }

    /**
     * 修改生产工序
     * 
     * @param proProcess 生产工序
     * @return 结果
     */
    @Override
    public int updateProProcess(ProProcess proProcess)
    {
        proProcess.setUpdateTime(DateUtils.getNowDate());
        return proProcessMapper.updateProProcess(proProcess);
    }

    /**
     * 批量删除生产工序
     * 
     * @param processIds 需要删除的生产工序主键
     * @return 结果
     */
    @Override
    public int deleteProProcessByProcessIds(Long[] processIds)
    {
        return proProcessMapper.deleteProProcessByProcessIds(processIds);
    }

    /**
     * 删除生产工序信息
     * 
     * @param processId 生产工序主键
     * @return 结果
     */
    @Override
    public int deleteProProcessByProcessId(Long processId)
    {
        return proProcessMapper.deleteProProcessByProcessId(processId);
    }
}
