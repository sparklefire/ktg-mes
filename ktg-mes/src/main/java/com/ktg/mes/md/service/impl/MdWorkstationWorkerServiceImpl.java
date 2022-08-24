package com.ktg.mes.md.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.md.mapper.MdWorkstationWorkerMapper;
import com.ktg.mes.md.domain.MdWorkstationWorker;
import com.ktg.mes.md.service.IMdWorkstationWorkerService;

/**
 * 人力资源Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
@Service
public class MdWorkstationWorkerServiceImpl implements IMdWorkstationWorkerService 
{
    @Autowired
    private MdWorkstationWorkerMapper mdWorkstationWorkerMapper;

    /**
     * 查询人力资源
     * 
     * @param recordId 人力资源主键
     * @return 人力资源
     */
    @Override
    public MdWorkstationWorker selectMdWorkstationWorkerByRecordId(Long recordId)
    {
        return mdWorkstationWorkerMapper.selectMdWorkstationWorkerByRecordId(recordId);
    }

    /**
     * 查询人力资源列表
     * 
     * @param mdWorkstationWorker 人力资源
     * @return 人力资源
     */
    @Override
    public List<MdWorkstationWorker> selectMdWorkstationWorkerList(MdWorkstationWorker mdWorkstationWorker)
    {
        return mdWorkstationWorkerMapper.selectMdWorkstationWorkerList(mdWorkstationWorker);
    }

    @Override
    public String checkPostExist(MdWorkstationWorker mdWorkstationWorker) {
        MdWorkstationWorker post = mdWorkstationWorkerMapper.checkPostExist(mdWorkstationWorker);
        Long recordId = mdWorkstationWorker.getRecordId()==null?-1L:mdWorkstationWorker.getRecordId();
        if(StringUtils.isNotNull(post) && post.getRecordId().longValue() != recordId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增人力资源
     * 
     * @param mdWorkstationWorker 人力资源
     * @return 结果
     */
    @Override
    public int insertMdWorkstationWorker(MdWorkstationWorker mdWorkstationWorker)
    {
        mdWorkstationWorker.setCreateTime(DateUtils.getNowDate());
        return mdWorkstationWorkerMapper.insertMdWorkstationWorker(mdWorkstationWorker);
    }

    /**
     * 修改人力资源
     * 
     * @param mdWorkstationWorker 人力资源
     * @return 结果
     */
    @Override
    public int updateMdWorkstationWorker(MdWorkstationWorker mdWorkstationWorker)
    {
        mdWorkstationWorker.setUpdateTime(DateUtils.getNowDate());
        return mdWorkstationWorkerMapper.updateMdWorkstationWorker(mdWorkstationWorker);
    }

    /**
     * 批量删除人力资源
     * 
     * @param recordIds 需要删除的人力资源主键
     * @return 结果
     */
    @Override
    public int deleteMdWorkstationWorkerByRecordIds(Long[] recordIds)
    {
        return mdWorkstationWorkerMapper.deleteMdWorkstationWorkerByRecordIds(recordIds);
    }

    /**
     * 删除人力资源信息
     * 
     * @param recordId 人力资源主键
     * @return 结果
     */
    @Override
    public int deleteMdWorkstationWorkerByRecordId(Long recordId)
    {
        return mdWorkstationWorkerMapper.deleteMdWorkstationWorkerByRecordId(recordId);
    }

    @Override
    public int deleteByWorkstationId(Long workstationId) {
        return mdWorkstationWorkerMapper.deleteByWorkstationId(workstationId);
    }
}
