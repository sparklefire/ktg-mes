package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmOutsourceRecpt;
import com.ktg.mes.wm.domain.tx.OutsourceRecptTxBean;

/**
 * 外协入库单Service接口
 * 
 * @author yinjinlu
 * @date 2023-10-30
 */
public interface IWmOutsourceRecptService 
{
    /**
     * 查询外协入库单
     * 
     * @param recptId 外协入库单主键
     * @return 外协入库单
     */
    public WmOutsourceRecpt selectWmOutsourceRecptByRecptId(Long recptId);

    /**
     * 查询外协入库单列表
     * 
     * @param wmOutsourceRecpt 外协入库单
     * @return 外协入库单集合
     */
    public List<WmOutsourceRecpt> selectWmOutsourceRecptList(WmOutsourceRecpt wmOutsourceRecpt);

    /**
     * 新增外协入库单
     * 
     * @param wmOutsourceRecpt 外协入库单
     * @return 结果
     */
    public int insertWmOutsourceRecpt(WmOutsourceRecpt wmOutsourceRecpt);

    /**
     * 修改外协入库单
     * 
     * @param wmOutsourceRecpt 外协入库单
     * @return 结果
     */
    public int updateWmOutsourceRecpt(WmOutsourceRecpt wmOutsourceRecpt);

    /**
     * 批量删除外协入库单
     * 
     * @param recptIds 需要删除的外协入库单主键集合
     * @return 结果
     */
    public int deleteWmOutsourceRecptByRecptIds(Long[] recptIds);

    /**
     * 删除外协入库单信息
     * 
     * @param recptId 外协入库单主键
     * @return 结果
     */
    public int deleteWmOutsourceRecptByRecptId(Long recptId);

    /**
     * 获取执行入库的TxBeans
     * @param recptId
     * @return
     */
    public List<OutsourceRecptTxBean> getTxBeans(Long recptId);
}
