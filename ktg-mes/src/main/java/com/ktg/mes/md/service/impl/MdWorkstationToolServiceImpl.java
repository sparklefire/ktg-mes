package com.ktg.mes.md.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.md.mapper.MdWorkstationToolMapper;
import com.ktg.mes.md.domain.MdWorkstationTool;
import com.ktg.mes.md.service.IMdWorkstationToolService;

/**
 * 工装夹具资源Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
@Service
public class MdWorkstationToolServiceImpl implements IMdWorkstationToolService
{
    @Autowired
    private MdWorkstationToolMapper mdWorkstationToolMapper;

    /**
     * 查询工装夹具资源
     * 
     * @param recordId 工装夹具资源主键
     * @return 工装夹具资源
     */
    @Override
    public MdWorkstationTool selectMdWorkstationToolByRecordId(Long recordId)
    {
        return mdWorkstationToolMapper.selectMdWorkstationToolByRecordId(recordId);
    }

    /**
     * 查询工装夹具资源列表
     * 
     * @param mdWorkstationTool 工装夹具资源
     * @return 工装夹具资源
     */
    @Override
    public List<MdWorkstationTool> selectMdWorkstationToolList(MdWorkstationTool mdWorkstationTool)
    {
        return mdWorkstationToolMapper.selectMdWorkstationToolList(mdWorkstationTool);
    }

    @Override
    public String checkToolTypeExists(MdWorkstationTool mdWorkstationTool) {
        MdWorkstationTool workstationTool = mdWorkstationToolMapper.checkToolTypeExists(mdWorkstationTool);
        Long workstationToolId = mdWorkstationTool.getRecordId()==null?-1L:mdWorkstationTool.getRecordId();
        if(StringUtils.isNotNull(workstationTool)&&workstationTool.getRecordId().longValue()!=workstationToolId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增工装夹具资源
     * 
     * @param mdWorkstationTool 工装夹具资源
     * @return 结果
     */
    @Override
    public int insertMdWorkstationTool(MdWorkstationTool mdWorkstationTool)
    {
        mdWorkstationTool.setCreateTime(DateUtils.getNowDate());
        return mdWorkstationToolMapper.insertMdWorkstationTool(mdWorkstationTool);
    }

    /**
     * 修改工装夹具资源
     * 
     * @param mdWorkstationTool 工装夹具资源
     * @return 结果
     */
    @Override
    public int updateMdWorkstationTool(MdWorkstationTool mdWorkstationTool)
    {
        mdWorkstationTool.setUpdateTime(DateUtils.getNowDate());
        return mdWorkstationToolMapper.updateMdWorkstationTool(mdWorkstationTool);
    }

    /**
     * 批量删除工装夹具资源
     * 
     * @param recordIds 需要删除的工装夹具资源主键
     * @return 结果
     */
    @Override
    public int deleteMdWorkstationToolByRecordIds(Long[] recordIds)
    {
        return mdWorkstationToolMapper.deleteMdWorkstationToolByRecordIds(recordIds);
    }

    /**
     * 删除工装夹具资源信息
     * 
     * @param recordId 工装夹具资源主键
     * @return 结果
     */
    @Override
    public int deleteMdWorkstationToolByRecordId(Long recordId)
    {
        return mdWorkstationToolMapper.deleteMdWorkstationToolByRecordId(recordId);
    }

    @Override
    public int deleteByWorkstationId(Long workstationId) {
        return mdWorkstationToolMapper.deleteByWorkstationId(workstationId);
    }
}
