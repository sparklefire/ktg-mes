package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmRtVendorLine;

/**
 * 供应商退货行Service接口
 * 
 * @author yinjinlu
 * @date 2022-06-13
 */
public interface IWmRtVendorLineService 
{
    /**
     * 查询供应商退货行
     * 
     * @param lineId 供应商退货行主键
     * @return 供应商退货行
     */
    public WmRtVendorLine selectWmRtVendorLineByLineId(Long lineId);

    /**
     * 查询供应商退货行列表
     * 
     * @param wmRtVendorLine 供应商退货行
     * @return 供应商退货行集合
     */
    public List<WmRtVendorLine> selectWmRtVendorLineList(WmRtVendorLine wmRtVendorLine);

    /**
     * 新增供应商退货行
     * 
     * @param wmRtVendorLine 供应商退货行
     * @return 结果
     */
    public int insertWmRtVendorLine(WmRtVendorLine wmRtVendorLine);

    /**
     * 修改供应商退货行
     * 
     * @param wmRtVendorLine 供应商退货行
     * @return 结果
     */
    public int updateWmRtVendorLine(WmRtVendorLine wmRtVendorLine);

    /**
     * 批量删除供应商退货行
     * 
     * @param lineIds 需要删除的供应商退货行主键集合
     * @return 结果
     */
    public int deleteWmRtVendorLineByLineIds(Long[] lineIds);

    /**
     * 删除供应商退货行信息
     * 
     * @param lineId 供应商退货行主键
     * @return 结果
     */
    public int deleteWmRtVendorLineByLineId(Long lineId);

    public int deleteByRtId(Long rtId);
}
