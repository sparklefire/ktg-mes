package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmRtVendor;
import com.ktg.mes.wm.domain.tx.RtVendorTxBean;

/**
 * 供应商退货Service接口
 * 
 * @author yinjinlu
 * @date 2022-06-13
 */
public interface IWmRtVendorService 
{
    /**
     * 查询供应商退货
     * 
     * @param rtId 供应商退货主键
     * @return 供应商退货
     */
    public WmRtVendor selectWmRtVendorByRtId(Long rtId);

    /**
     * 查询供应商退货列表
     * 
     * @param wmRtVendor 供应商退货
     * @return 供应商退货集合
     */
    public List<WmRtVendor> selectWmRtVendorList(WmRtVendor wmRtVendor);

    /**
     * 检查供应商退货单编码是否唯一
     * @param wmRtVendor
     * @return
     */
    public String checkCodeUnique(WmRtVendor wmRtVendor);

    /**
     * 新增供应商退货
     * 
     * @param wmRtVendor 供应商退货
     * @return 结果
     */
    public int insertWmRtVendor(WmRtVendor wmRtVendor);

    /**
     * 修改供应商退货
     * 
     * @param wmRtVendor 供应商退货
     * @return 结果
     */
    public int updateWmRtVendor(WmRtVendor wmRtVendor);

    /**
     * 批量删除供应商退货
     * 
     * @param rtIds 需要删除的供应商退货主键集合
     * @return 结果
     */
    public int deleteWmRtVendorByRtIds(Long[] rtIds);

    /**
     * 删除供应商退货信息
     * 
     * @param rtId 供应商退货主键
     * @return 结果
     */
    public int deleteWmRtVendorByRtId(Long rtId);

    /**
     * 获取库存事务bean
     * @param rtId
     * @return
     */
    public List<RtVendorTxBean> getTxBeans(Long rtId);

}
