package com.ktg.mes.pro.mapper;

import java.util.List;
import com.ktg.mes.pro.domain.ProTransOrder;

/**
 * 流转单Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-07-24
 */
public interface ProTransOrderMapper 
{
    /**
     * 查询流转单
     * 
     * @param transOrderId 流转单主键
     * @return 流转单
     */
    public ProTransOrder selectProTransOrderByTransOrderId(Long transOrderId);

    /**
     * 查询流转单列表
     * 
     * @param proTransOrder 流转单
     * @return 流转单集合
     */
    public List<ProTransOrder> selectProTransOrderList(ProTransOrder proTransOrder);

    /**
     * 新增流转单
     * 
     * @param proTransOrder 流转单
     * @return 结果
     */
    public int insertProTransOrder(ProTransOrder proTransOrder);

    /**
     * 修改流转单
     * 
     * @param proTransOrder 流转单
     * @return 结果
     */
    public int updateProTransOrder(ProTransOrder proTransOrder);

    /**
     * 删除流转单
     * 
     * @param transOrderId 流转单主键
     * @return 结果
     */
    public int deleteProTransOrderByTransOrderId(Long transOrderId);

    /**
     * 批量删除流转单
     * 
     * @param transOrderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProTransOrderByTransOrderIds(Long[] transOrderIds);
}
