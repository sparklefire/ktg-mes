package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmRtSalse;
import com.ktg.mes.wm.domain.tx.RtSalseTxBean;

/**
 * 产品销售退货单Service接口
 * 
 * @author yinjinlu
 * @date 2022-10-06
 */
public interface IWmRtSalseService 
{
    /**
     * 查询产品销售退货单
     * 
     * @param rtId 产品销售退货单主键
     * @return 产品销售退货单
     */
    public WmRtSalse selectWmRtSalseByRtId(Long rtId);

    /**
     * 查询产品销售退货单列表
     * 
     * @param wmRtSalse 产品销售退货单
     * @return 产品销售退货单集合
     */
    public List<WmRtSalse> selectWmRtSalseList(WmRtSalse wmRtSalse);


    /**
     *
     * @param rtId
     * @return
     */
    public List<RtSalseTxBean> getTxBeans(Long rtId);

    /**
     * 检查编号唯一性
     * @return
     */
    public String checkUnique(WmRtSalse wmRtSalse);

    /**
     * 新增产品销售退货单
     * 
     * @param wmRtSalse 产品销售退货单
     * @return 结果
     */
    public int insertWmRtSalse(WmRtSalse wmRtSalse);

    /**
     * 修改产品销售退货单
     * 
     * @param wmRtSalse 产品销售退货单
     * @return 结果
     */
    public int updateWmRtSalse(WmRtSalse wmRtSalse);

    /**
     * 批量删除产品销售退货单
     * 
     * @param rtIds 需要删除的产品销售退货单主键集合
     * @return 结果
     */
    public int deleteWmRtSalseByRtIds(Long[] rtIds);

    /**
     * 删除产品销售退货单信息
     * 
     * @param rtId 产品销售退货单主键
     * @return 结果
     */
    public int deleteWmRtSalseByRtId(Long rtId);
}
