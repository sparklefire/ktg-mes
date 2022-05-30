package com.ktg.mes.wm.mapper;

import java.util.List;
import com.ktg.mes.wm.domain.WmItemRecpt;
import com.ktg.mes.wm.domain.tx.ItemRecptTxBean;

/**
 * 物料入库单Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-05-22
 */
public interface WmItemRecptMapper 
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

    public WmItemRecpt checkRecptCodeUnique(WmItemRecpt wmItemRecpt);


    public List<ItemRecptTxBean> getTxBeans(Long recptId);

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
     * 删除物料入库单
     * 
     * @param recptId 物料入库单主键
     * @return 结果
     */
    public int deleteWmItemRecptByRecptId(Long recptId);

    /**
     * 批量删除物料入库单
     * 
     * @param recptIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWmItemRecptByRecptIds(Long[] recptIds);
}
