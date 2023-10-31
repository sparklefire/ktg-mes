package com.ktg.mes.wm.mapper;

import java.util.List;
import com.ktg.mes.wm.domain.WmOutsourceRecpt;
import com.ktg.mes.wm.domain.tx.OutsourceRecptTxBean;

/**
 * 外协入库单Mapper接口
 * 
 * @author yinjinlu
 * @date 2023-10-30
 */
public interface WmOutsourceRecptMapper 
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
     * 删除外协入库单
     * 
     * @param recptId 外协入库单主键
     * @return 结果
     */
    public int deleteWmOutsourceRecptByRecptId(Long recptId);

    /**
     * 批量删除外协入库单
     * 
     * @param recptIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWmOutsourceRecptByRecptIds(Long[] recptIds);

    public List<OutsourceRecptTxBean> getTxBeans(Long recptId);
}
