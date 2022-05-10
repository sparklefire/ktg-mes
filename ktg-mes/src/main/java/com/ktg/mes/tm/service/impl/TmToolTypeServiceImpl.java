package com.ktg.mes.tm.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.tm.mapper.TmToolTypeMapper;
import com.ktg.mes.tm.domain.TmToolType;
import com.ktg.mes.tm.service.ITmToolTypeService;

/**
 * 工装夹具类型Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-10
 */
@Service
public class TmToolTypeServiceImpl implements ITmToolTypeService 
{
    @Autowired
    private TmToolTypeMapper tmToolTypeMapper;

    /**
     * 查询工装夹具类型
     * 
     * @param toolTypeId 工装夹具类型主键
     * @return 工装夹具类型
     */
    @Override
    public TmToolType selectTmToolTypeByToolTypeId(Long toolTypeId)
    {
        return tmToolTypeMapper.selectTmToolTypeByToolTypeId(toolTypeId);
    }

    /**
     * 查询工装夹具类型列表
     * 
     * @param tmToolType 工装夹具类型
     * @return 工装夹具类型
     */
    @Override
    public List<TmToolType> selectTmToolTypeList(TmToolType tmToolType)
    {
        return tmToolTypeMapper.selectTmToolTypeList(tmToolType);
    }

    @Override
    public String checkToolTypeCodeUnique(TmToolType tmToolType) {
        TmToolType toolType =tmToolTypeMapper.checkToolTypeCodeUnique(tmToolType);
        Long toolTypeId = tmToolType.getToolTypeId()==null?-1L:tmToolType.getToolTypeId();
        if(StringUtils.isNotNull(toolType) && toolType.getToolTypeId().longValue() != toolTypeId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkToolTypeNameUnique(TmToolType tmToolType) {
        TmToolType toolType =tmToolTypeMapper.checkToolTypeNameUnique(tmToolType);
        Long toolTypeId = tmToolType.getToolTypeId()==null?-1L:tmToolType.getToolTypeId();
        if(StringUtils.isNotNull(toolType) && toolType.getToolTypeId().longValue() != toolTypeId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增工装夹具类型
     * 
     * @param tmToolType 工装夹具类型
     * @return 结果
     */
    @Override
    public int insertTmToolType(TmToolType tmToolType)
    {
        tmToolType.setCreateTime(DateUtils.getNowDate());
        return tmToolTypeMapper.insertTmToolType(tmToolType);
    }

    /**
     * 修改工装夹具类型
     * 
     * @param tmToolType 工装夹具类型
     * @return 结果
     */
    @Override
    public int updateTmToolType(TmToolType tmToolType)
    {
        tmToolType.setUpdateTime(DateUtils.getNowDate());
        return tmToolTypeMapper.updateTmToolType(tmToolType);
    }

    /**
     * 批量删除工装夹具类型
     * 
     * @param toolTypeIds 需要删除的工装夹具类型主键
     * @return 结果
     */
    @Override
    public int deleteTmToolTypeByToolTypeIds(Long[] toolTypeIds)
    {
        return tmToolTypeMapper.deleteTmToolTypeByToolTypeIds(toolTypeIds);
    }

    /**
     * 删除工装夹具类型信息
     * 
     * @param toolTypeId 工装夹具类型主键
     * @return 结果
     */
    @Override
    public int deleteTmToolTypeByToolTypeId(Long toolTypeId)
    {
        return tmToolTypeMapper.deleteTmToolTypeByToolTypeId(toolTypeId);
    }
}
