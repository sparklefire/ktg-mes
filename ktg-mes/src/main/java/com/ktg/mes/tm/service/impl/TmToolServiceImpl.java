package com.ktg.mes.tm.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.tm.mapper.TmToolMapper;
import com.ktg.mes.tm.domain.TmTool;
import com.ktg.mes.tm.service.ITmToolService;

/**
 * 工装夹具清单Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-11
 */
@Service
public class TmToolServiceImpl implements ITmToolService 
{
    @Autowired
    private TmToolMapper tmToolMapper;

    /**
     * 查询工装夹具清单
     * 
     * @param toolId 工装夹具清单主键
     * @return 工装夹具清单
     */
    @Override
    public TmTool selectTmToolByToolId(Long toolId)
    {
        return tmToolMapper.selectTmToolByToolId(toolId);
    }

    /**
     * 查询工装夹具清单列表
     * 
     * @param tmTool 工装夹具清单
     * @return 工装夹具清单
     */
    @Override
    public List<TmTool> selectTmToolList(TmTool tmTool)
    {
        return tmToolMapper.selectTmToolList(tmTool);
    }

    @Override
    public String checkToolCodeUnique(TmTool tmTool) {
        if(StringUtils.isNotNull(tmTool.getToolCode())){
            TmTool tool = tmToolMapper.checkToolCodeUnique(tmTool);
            Long toolId = tmTool.getToolId()==null?-1L:tmTool.getToolId();
            if(StringUtils.isNotNull(tool) && tool.getToolId().longValue()!=toolId.longValue()){
                return UserConstants.NOT_UNIQUE;
            }
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增工装夹具清单
     * 
     * @param tmTool 工装夹具清单
     * @return 结果
     */
    @Override
    public int insertTmTool(TmTool tmTool)
    {
        tmTool.setCreateTime(DateUtils.getNowDate());
        return tmToolMapper.insertTmTool(tmTool);
    }

    /**
     * 修改工装夹具清单
     * 
     * @param tmTool 工装夹具清单
     * @return 结果
     */
    @Override
    public int updateTmTool(TmTool tmTool)
    {
        tmTool.setUpdateTime(DateUtils.getNowDate());
        return tmToolMapper.updateTmTool(tmTool);
    }

    /**
     * 批量删除工装夹具清单
     * 
     * @param toolIds 需要删除的工装夹具清单主键
     * @return 结果
     */
    @Override
    public int deleteTmToolByToolIds(Long[] toolIds)
    {
        return tmToolMapper.deleteTmToolByToolIds(toolIds);
    }

    /**
     * 删除工装夹具清单信息
     * 
     * @param toolId 工装夹具清单主键
     * @return 结果
     */
    @Override
    public int deleteTmToolByToolId(Long toolId)
    {
        return tmToolMapper.deleteTmToolByToolId(toolId);
    }
}
