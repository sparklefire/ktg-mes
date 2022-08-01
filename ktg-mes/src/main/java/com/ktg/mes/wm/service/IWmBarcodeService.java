package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmBarcode;

/**
 * 条码清单Service接口
 * 
 * @author yinjinlu
 * @date 2022-08-01
 */
public interface IWmBarcodeService 
{
    /**
     * 查询条码清单
     * 
     * @param barcodeId 条码清单主键
     * @return 条码清单
     */
    public WmBarcode selectWmBarcodeByBarcodeId(Long barcodeId);

    /**
     * 查询条码清单列表
     * 
     * @param wmBarcode 条码清单
     * @return 条码清单集合
     */
    public List<WmBarcode> selectWmBarcodeList(WmBarcode wmBarcode);

    /**
     * 新增条码清单
     * 
     * @param wmBarcode 条码清单
     * @return 结果
     */
    public int insertWmBarcode(WmBarcode wmBarcode);

    /**
     * 修改条码清单
     * 
     * @param wmBarcode 条码清单
     * @return 结果
     */
    public int updateWmBarcode(WmBarcode wmBarcode);

    /**
     * 批量删除条码清单
     * 
     * @param barcodeIds 需要删除的条码清单主键集合
     * @return 结果
     */
    public int deleteWmBarcodeByBarcodeIds(Long[] barcodeIds);

    /**
     * 删除条码清单信息
     * 
     * @param barcodeId 条码清单主键
     * @return 结果
     */
    public int deleteWmBarcodeByBarcodeId(Long barcodeId);
}
