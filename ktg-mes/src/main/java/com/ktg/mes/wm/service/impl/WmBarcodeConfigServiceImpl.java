package com.ktg.mes.wm.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmBarcodeConfigMapper;
import com.ktg.mes.wm.domain.WmBarcodeConfig;
import com.ktg.mes.wm.service.IWmBarcodeConfigService;
import org.springframework.util.CollectionUtils;

/**
 * 条码配置Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-10-22
 */
@Service
public class WmBarcodeConfigServiceImpl implements IWmBarcodeConfigService 
{
    @Autowired
    private WmBarcodeConfigMapper wmBarcodeConfigMapper;

    /**
     * 查询条码配置
     * 
     * @param configId 条码配置主键
     * @return 条码配置
     */
    @Override
    public WmBarcodeConfig selectWmBarcodeConfigByConfigId(Long configId)
    {
        return wmBarcodeConfigMapper.selectWmBarcodeConfigByConfigId(configId);
    }

    /**
     * 查询条码配置列表
     * 
     * @param wmBarcodeConfig 条码配置
     * @return 条码配置
     */
    @Override
    public List<WmBarcodeConfig> selectWmBarcodeConfigList(WmBarcodeConfig wmBarcodeConfig)
    {
        return wmBarcodeConfigMapper.selectWmBarcodeConfigList(wmBarcodeConfig);
    }

    /**
     * 新增条码配置
     * 
     * @param wmBarcodeConfig 条码配置
     * @return 结果
     */
    @Override
    public int insertWmBarcodeConfig(WmBarcodeConfig wmBarcodeConfig)
    {
        wmBarcodeConfig.setCreateTime(DateUtils.getNowDate());
        return wmBarcodeConfigMapper.insertWmBarcodeConfig(wmBarcodeConfig);
    }

    /**
     * 修改条码配置
     * 
     * @param wmBarcodeConfig 条码配置
     * @return 结果
     */
    @Override
    public int updateWmBarcodeConfig(WmBarcodeConfig wmBarcodeConfig)
    {
        wmBarcodeConfig.setUpdateTime(DateUtils.getNowDate());
        return wmBarcodeConfigMapper.updateWmBarcodeConfig(wmBarcodeConfig);
    }

    /**
     * 批量删除条码配置
     * 
     * @param configIds 需要删除的条码配置主键
     * @return 结果
     */
    @Override
    public int deleteWmBarcodeConfigByConfigIds(Long[] configIds)
    {
        return wmBarcodeConfigMapper.deleteWmBarcodeConfigByConfigIds(configIds);
    }

    /**
     * 删除条码配置信息
     * 
     * @param configId 条码配置主键
     * @return 结果
     */
    @Override
    public int deleteWmBarcodeConfigByConfigId(Long configId)
    {
        return wmBarcodeConfigMapper.deleteWmBarcodeConfigByConfigId(configId);
    }

    /**
     * 判断某种类型的业务是否需要自动生成赋码
     * @param barcodeType
     * @return
     */
    @Override
    public boolean isAutoGen(String barcodeType) {
        WmBarcodeConfig param = new WmBarcodeConfig();
        param.setBarcodeType(barcodeType);
        List<WmBarcodeConfig> configs = wmBarcodeConfigMapper.selectWmBarcodeConfigList(param);
        if(!CollectionUtils.isEmpty(configs)){
            return UserConstants.YES.equals(configs.get(0).getAutoGenFlag())?true:false;
        }
        return false;
    }
}
