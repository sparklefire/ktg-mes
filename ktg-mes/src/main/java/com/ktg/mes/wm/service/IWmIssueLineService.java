package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmIssueLine;

/**
 * 生产领料单行Service接口
 * 
 * @author yinjinlu
 * @date 2022-07-14
 */
public interface IWmIssueLineService 
{
    /**
     * 查询生产领料单行
     * 
     * @param lineId 生产领料单行主键
     * @return 生产领料单行
     */
    public WmIssueLine selectWmIssueLineByLineId(Long lineId);

    /**
     * 查询生产领料单行列表
     * 
     * @param wmIssueLine 生产领料单行
     * @return 生产领料单行集合
     */
    public List<WmIssueLine> selectWmIssueLineList(WmIssueLine wmIssueLine);

    /**
     * 新增生产领料单行
     * 
     * @param wmIssueLine 生产领料单行
     * @return 结果
     */
    public int insertWmIssueLine(WmIssueLine wmIssueLine);

    /**
     * 修改生产领料单行
     * 
     * @param wmIssueLine 生产领料单行
     * @return 结果
     */
    public int updateWmIssueLine(WmIssueLine wmIssueLine);

    /**
     * 批量删除生产领料单行
     * 
     * @param lineIds 需要删除的生产领料单行主键集合
     * @return 结果
     */
    public int deleteWmIssueLineByLineIds(Long[] lineIds);

    /**
     * 删除生产领料单行信息
     * 
     * @param lineId 生产领料单行主键
     * @return 结果
     */
    public int deleteWmIssueLineByLineId(Long lineId);

    public int deleteByIssueHeaderId(Long issueId);

}
