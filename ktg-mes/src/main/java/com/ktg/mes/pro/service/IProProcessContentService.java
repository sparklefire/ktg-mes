package com.ktg.mes.pro.service;

import java.util.List;
import com.ktg.mes.pro.domain.ProProcessContent;

/**
 * 生产工序内容Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
public interface IProProcessContentService 
{
    /**
     * 查询生产工序内容
     * 
     * @param contentId 生产工序内容主键
     * @return 生产工序内容
     */
    public ProProcessContent selectProProcessContentByContentId(Long contentId);

    /**
     * 查询生产工序内容列表
     * 
     * @param proProcessContent 生产工序内容
     * @return 生产工序内容集合
     */
    public List<ProProcessContent> selectProProcessContentList(ProProcessContent proProcessContent);

    /**
     * 新增生产工序内容
     * 
     * @param proProcessContent 生产工序内容
     * @return 结果
     */
    public int insertProProcessContent(ProProcessContent proProcessContent);

    /**
     * 修改生产工序内容
     * 
     * @param proProcessContent 生产工序内容
     * @return 结果
     */
    public int updateProProcessContent(ProProcessContent proProcessContent);

    /**
     * 批量删除生产工序内容
     * 
     * @param contentIds 需要删除的生产工序内容主键集合
     * @return 结果
     */
    public int deleteProProcessContentByContentIds(Long[] contentIds);

    /**
     * 删除生产工序内容信息
     * 
     * @param contentId 生产工序内容主键
     * @return 结果
     */
    public int deleteProProcessContentByContentId(Long contentId);
}
