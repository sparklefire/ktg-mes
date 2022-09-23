package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmProductRecpt;
import com.ktg.mes.wm.domain.tx.ProductRecptTxBean;

/**
 * 产品入库录Service接口
 * 
 * @author yinjinlu
 * @date 2022-09-22
 */
public interface IWmProductRecptService 
{
    /**
     * 查询产品入库录
     * 
     * @param recptId 产品入库录主键
     * @return 产品入库录
     */
    public WmProductRecpt selectWmProductRecptByRecptId(Long recptId);

    /**
     * 查询产品入库录列表
     * 
     * @param wmProductRecpt 产品入库录
     * @return 产品入库录集合
     */
    public List<WmProductRecpt> selectWmProductRecptList(WmProductRecpt wmProductRecpt);


    /**
     * 检查编码唯一性
     * @param wmProductRecpt
     * @return
     */
    public String checkUnique(WmProductRecpt wmProductRecpt);

    /**
     * 新增产品入库录
     * 
     * @param wmProductRecpt 产品入库录
     * @return 结果
     */
    public int insertWmProductRecpt(WmProductRecpt wmProductRecpt);

    /**
     * 修改产品入库录
     * 
     * @param wmProductRecpt 产品入库录
     * @return 结果
     */
    public int updateWmProductRecpt(WmProductRecpt wmProductRecpt);

    /**
     * 批量删除产品入库录
     * 
     * @param recptIds 需要删除的产品入库录主键集合
     * @return 结果
     */
    public int deleteWmProductRecptByRecptIds(Long[] recptIds);

    /**
     * 删除产品入库录信息
     * 
     * @param recptId 产品入库录主键
     * @return 结果
     */
    public int deleteWmProductRecptByRecptId(Long recptId);

    public List<ProductRecptTxBean> getTxBean(Long recptId);
}
