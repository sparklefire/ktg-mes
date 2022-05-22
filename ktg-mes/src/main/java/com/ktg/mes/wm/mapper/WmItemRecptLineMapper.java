package com.ktg.mes.wm.mapper;

import java.util.List;
import com.ktg.mes.wm.domain.WmItemRecptLine;

/**
 * 物料入库单行Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-05-22
 */
public interface WmItemRecptLineMapper 
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
     * 删除物料入库单行
     * 
     * @param lineId 物料入库单行主键
     * @return 结果
     */
    public int deleteWmItemRecptLineByLineId(Long lineId);

    /**
     * 批量删除物料入库单行
     * 
     * @param lineIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWmItemRecptLineByLineIds(Long[] lineIds);

    public int deleteByRecptId(Long recptId);
}
