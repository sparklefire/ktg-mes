package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmProductRecptLine;

/**
 * 产品入库记录行Service接口
 * 
 * @author yinjinlu
 * @date 2022-09-22
 */
public interface IWmProductRecptLineService 
{
    /**
     * 查询产品入库记录行
     * 
     * @param lineId 产品入库记录行主键
     * @return 产品入库记录行
     */
    public WmProductRecptLine selectWmProductRecptLineByLineId(Long lineId);

    /**
     * 查询产品入库记录行列表
     * 
     * @param wmProductRecptLine 产品入库记录行
     * @return 产品入库记录行集合
     */
    public List<WmProductRecptLine> selectWmProductRecptLineList(WmProductRecptLine wmProductRecptLine);

    /**
     * 新增产品入库记录行
     * 
     * @param wmProductRecptLine 产品入库记录行
     * @return 结果
     */
    public int insertWmProductRecptLine(WmProductRecptLine wmProductRecptLine);

    /**
     * 修改产品入库记录行
     * 
     * @param wmProductRecptLine 产品入库记录行
     * @return 结果
     */
    public int updateWmProductRecptLine(WmProductRecptLine wmProductRecptLine);

    /**
     * 批量删除产品入库记录行
     * 
     * @param lineIds 需要删除的产品入库记录行主键集合
     * @return 结果
     */
    public int deleteWmProductRecptLineByLineIds(Long[] lineIds);

    /**
     * 删除产品入库记录行信息
     * 
     * @param lineId 产品入库记录行主键
     * @return 结果
     */
    public int deleteWmProductRecptLineByLineId(Long lineId);

    /**
     * 根据入库单ID删除所有行
     * @param recptId
     * @return
     */
    public int deleteByRecptId(Long recptId);
}
