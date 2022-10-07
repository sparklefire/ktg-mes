package com.ktg.mes.wm.mapper;

import java.util.List;
import com.ktg.mes.wm.domain.WmRtSalseLine;

/**
 * 产品销售退货行Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-10-06
 */
public interface WmRtSalseLineMapper 
{
    /**
     * 查询产品销售退货行
     * 
     * @param lineId 产品销售退货行主键
     * @return 产品销售退货行
     */
    public WmRtSalseLine selectWmRtSalseLineByLineId(Long lineId);

    /**
     * 查询产品销售退货行列表
     * 
     * @param wmRtSalseLine 产品销售退货行
     * @return 产品销售退货行集合
     */
    public List<WmRtSalseLine> selectWmRtSalseLineList(WmRtSalseLine wmRtSalseLine);

    /**
     * 新增产品销售退货行
     * 
     * @param wmRtSalseLine 产品销售退货行
     * @return 结果
     */
    public int insertWmRtSalseLine(WmRtSalseLine wmRtSalseLine);

    /**
     * 修改产品销售退货行
     * 
     * @param wmRtSalseLine 产品销售退货行
     * @return 结果
     */
    public int updateWmRtSalseLine(WmRtSalseLine wmRtSalseLine);

    /**
     * 删除产品销售退货行
     * 
     * @param lineId 产品销售退货行主键
     * @return 结果
     */
    public int deleteWmRtSalseLineByLineId(Long lineId);

    /**
     * 批量删除产品销售退货行
     * 
     * @param lineIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWmRtSalseLineByLineIds(Long[] lineIds);

    /**
     * 根据退货单ID删除所有行
     * @param rtId
     * @return
     */
    public int deleteByRtId(Long rtId);
}
