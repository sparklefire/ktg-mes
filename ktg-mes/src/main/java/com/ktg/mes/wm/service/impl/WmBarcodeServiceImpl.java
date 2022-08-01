package com.ktg.mes.wm.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmBarcodeMapper;
import com.ktg.mes.wm.domain.WmBarcode;
import com.ktg.mes.wm.service.IWmBarcodeService;

/**
 * 条码清单Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-08-01
 */
@Service
public class WmBarcodeServiceImpl implements IWmBarcodeService 
{
    @Autowired
    private WmBarcodeMapper wmBarcodeMapper;

    /**
     * 查询条码清单
     * 
     * @param barcodeId 条码清单主键
     * @return 条码清单
     */
    @Override
    public WmBarcode selectWmBarcodeByBarcodeId(Long barcodeId)
    {
        return wmBarcodeMapper.selectWmBarcodeByBarcodeId(barcodeId);
    }

    /**
     * 查询条码清单列表
     * 
     * @param wmBarcode 条码清单
     * @return 条码清单
     */
    @Override
    public List<WmBarcode> selectWmBarcodeList(WmBarcode wmBarcode)
    {
        return wmBarcodeMapper.selectWmBarcodeList(wmBarcode);
    }

    /**
     * 新增条码清单
     * 
     * @param wmBarcode 条码清单
     * @return 结果
     */
    @Override
    public int insertWmBarcode(WmBarcode wmBarcode)
    {
        wmBarcode.setCreateTime(DateUtils.getNowDate());
        return wmBarcodeMapper.insertWmBarcode(wmBarcode);
    }

    /**
     * 修改条码清单
     * 
     * @param wmBarcode 条码清单
     * @return 结果
     */
    @Override
    public int updateWmBarcode(WmBarcode wmBarcode)
    {
        wmBarcode.setUpdateTime(DateUtils.getNowDate());
        return wmBarcodeMapper.updateWmBarcode(wmBarcode);
    }

    /**
     * 批量删除条码清单
     * 
     * @param barcodeIds 需要删除的条码清单主键
     * @return 结果
     */
    @Override
    public int deleteWmBarcodeByBarcodeIds(Long[] barcodeIds)
    {
        return wmBarcodeMapper.deleteWmBarcodeByBarcodeIds(barcodeIds);
    }

    /**
     * 删除条码清单信息
     * 
     * @param barcodeId 条码清单主键
     * @return 结果
     */
    @Override
    public int deleteWmBarcodeByBarcodeId(Long barcodeId)
    {
        return wmBarcodeMapper.deleteWmBarcodeByBarcodeId(barcodeId);
    }
}
