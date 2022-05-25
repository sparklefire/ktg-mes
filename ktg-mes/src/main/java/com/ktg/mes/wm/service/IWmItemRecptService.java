package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmItemRecpt;
import com.ktg.mes.wm.domain.tx.ItemRecptTxBean;

/**
 * 物料入库单Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-22
 */
public interface IWmItemRecptService 
{
    /**
     * 查询物料入库单
     * 
     * @param recptId 物料入库单主键
     * @return 物料入库单
     */
    public WmItemRecpt selectWmItemRecptByRecptId(Long recptId);

    /**
     * 查询物料入库单列表
     * 
     * @param wmItemRecpt 物料入库单
     * @return 物料入库单集合
     */
    public List<WmItemRecpt> selectWmItemRecptList(WmItemRecpt wmItemRecpt);

    /**
     * 检查入库单号是否重复
     * @param wmItemRecpt
     * @return
     */
    public String checkRecptCodeUnique(WmItemRecpt wmItemRecpt);

    /**
     * 新增物料入库单
     * 
     * @param wmItemRecpt 物料入库单
     * @return 结果
     */
    public int insertWmItemRecpt(WmItemRecpt wmItemRecpt);

    /**
     * 修改物料入库单
     * 
     * @param wmItemRecpt 物料入库单
     * @return 结果
     */
    public int updateWmItemRecpt(WmItemRecpt wmItemRecpt);

    /**
     * 批量删除物料入库单
     * 
     * @param recptIds 需要删除的物料入库单主键集合
     * @return 结果
     */
    public int deleteWmItemRecptByRecptIds(Long[] recptIds);

    /**
     * 删除物料入库单信息
     * 
     * @param recptId 物料入库单主键
     * @return 结果
     */
    public int deleteWmItemRecptByRecptId(Long recptId);


    /**
     * 组装当前入库单对应的库存事务传入bean
     * @param receptId
     * @return
     */
    public List<ItemRecptTxBean> getTxBeans(Long receptId);


}
