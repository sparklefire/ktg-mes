package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmItemRecptLine;

/**
 * 物料入库单行Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-22
 */
public interface IWmItemRecptLineService 
{
    /**
     * 查询物料入库单行
     * 
     * @param lineId 物料入库单行主键
     * @return 物料入库单行
     */
    public WmItemRecptLine selectWmItemRecptLineByLineId(Long lineId);

    /**
     * 查询物料入库单行列表
     * 
     * @param wmItemRecptLine 物料入库单行
     * @return 物料入库单行集合
     */
    public List<WmItemRecptLine> selectWmItemRecptLineList(WmItemRecptLine wmItemRecptLine);

    /**
     * 新增物料入库单行
     * 
     * @param wmItemRecptLine 物料入库单行
     * @return 结果
     */
    public int insertWmItemRecptLine(WmItemRecptLine wmItemRecptLine);

    /**
     * 修改物料入库单行
     * 
     * @param wmItemRecptLine 物料入库单行
     * @return 结果
     */
    public int updateWmItemRecptLine(WmItemRecptLine wmItemRecptLine);

    /**
     * 批量删除物料入库单行
     * 
     * @param lineIds 需要删除的物料入库单行主键集合
     * @return 结果
     */
    public int deleteWmItemRecptLineByLineIds(Long[] lineIds);

    /**
     * 删除物料入库单行信息
     * 
     * @param lineId 物料入库单行主键
     * @return 结果
     */
    public int deleteWmItemRecptLineByLineId(Long lineId);

    /**
     * 删除物料入库单下所有行信息
     * @param recptId
     * @return
     */
    public int deleteByRecptId(Long recptId);
}
