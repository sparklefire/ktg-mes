package com.ktg.mes.pro.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.pro.mapper.ProProcessContentMapper;
import com.ktg.mes.pro.domain.ProProcessContent;
import com.ktg.mes.pro.service.IProProcessContentService;

/**
 * 生产工序内容Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
@Service
public class ProProcessContentServiceImpl implements IProProcessContentService 
{
    @Autowired
    private ProProcessContentMapper proProcessContentMapper;

    /**
     * 查询生产工序内容
     * 
     * @param contentId 生产工序内容主键
     * @return 生产工序内容
     */
    @Override
    public ProProcessContent selectProProcessContentByContentId(Long contentId)
    {
        return proProcessContentMapper.selectProProcessContentByContentId(contentId);
    }

    /**
     * 查询生产工序内容列表
     * 
     * @param proProcessContent 生产工序内容
     * @return 生产工序内容
     */
    @Override
    public List<ProProcessContent> selectProProcessContentList(ProProcessContent proProcessContent)
    {
        return proProcessContentMapper.selectProProcessContentList(proProcessContent);
    }

    /**
     * 新增生产工序内容
     * 
     * @param proProcessContent 生产工序内容
     * @return 结果
     */
    @Override
    public int insertProProcessContent(ProProcessContent proProcessContent)
    {
        proProcessContent.setCreateTime(DateUtils.getNowDate());
        return proProcessContentMapper.insertProProcessContent(proProcessContent);
    }

    /**
     * 修改生产工序内容
     * 
     * @param proProcessContent 生产工序内容
     * @return 结果
     */
    @Override
    public int updateProProcessContent(ProProcessContent proProcessContent)
    {
        proProcessContent.setUpdateTime(DateUtils.getNowDate());
        return proProcessContentMapper.updateProProcessContent(proProcessContent);
    }

    /**
     * 批量删除生产工序内容
     * 
     * @param contentIds 需要删除的生产工序内容主键
     * @return 结果
     */
    @Override
    public int deleteProProcessContentByContentIds(Long[] contentIds)
    {
        return proProcessContentMapper.deleteProProcessContentByContentIds(contentIds);
    }

    /**
     * 删除生产工序内容信息
     * 
     * @param contentId 生产工序内容主键
     * @return 结果
     */
    @Override
    public int deleteProProcessContentByContentId(Long contentId)
    {
        return proProcessContentMapper.deleteProProcessContentByContentId(contentId);
    }
}
