package com.ktg.mes.wm.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.wm.domain.tx.RtVendorTxBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmRtVendorMapper;
import com.ktg.mes.wm.domain.WmRtVendor;
import com.ktg.mes.wm.service.IWmRtVendorService;

/**
 * 供应商退货Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-06-13
 */
@Service
public class WmRtVendorServiceImpl implements IWmRtVendorService 
{
    @Autowired
    private WmRtVendorMapper wmRtVendorMapper;

    /**
     * 查询供应商退货
     * 
     * @param rtId 供应商退货主键
     * @return 供应商退货
     */
    @Override
    public WmRtVendor selectWmRtVendorByRtId(Long rtId)
    {
        return wmRtVendorMapper.selectWmRtVendorByRtId(rtId);
    }

    /**
     * 查询供应商退货列表
     * 
     * @param wmRtVendor 供应商退货
     * @return 供应商退货
     */
    @Override
    public List<WmRtVendor> selectWmRtVendorList(WmRtVendor wmRtVendor)
    {
        return wmRtVendorMapper.selectWmRtVendorList(wmRtVendor);
    }

    @Override
    public String checkCodeUnique(WmRtVendor wmRtVendor) {
        WmRtVendor rt = wmRtVendorMapper.checkCodeUnique(wmRtVendor);
        Long rtId = wmRtVendor.getRtId() ==null?-1L:wmRtVendor.getRtId();
        if(StringUtils.isNotNull(rt) && rt.getRtId().longValue() != rtId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增供应商退货
     * 
     * @param wmRtVendor 供应商退货
     * @return 结果
     */
    @Override
    public int insertWmRtVendor(WmRtVendor wmRtVendor)
    {
        wmRtVendor.setCreateTime(DateUtils.getNowDate());
        return wmRtVendorMapper.insertWmRtVendor(wmRtVendor);
    }

    /**
     * 修改供应商退货
     * 
     * @param wmRtVendor 供应商退货
     * @return 结果
     */
    @Override
    public int updateWmRtVendor(WmRtVendor wmRtVendor)
    {
        wmRtVendor.setUpdateTime(DateUtils.getNowDate());
        return wmRtVendorMapper.updateWmRtVendor(wmRtVendor);
    }

    /**
     * 批量删除供应商退货
     * 
     * @param rtIds 需要删除的供应商退货主键
     * @return 结果
     */
    @Override
    public int deleteWmRtVendorByRtIds(Long[] rtIds)
    {
        return wmRtVendorMapper.deleteWmRtVendorByRtIds(rtIds);
    }

    /**
     * 删除供应商退货信息
     * 
     * @param rtId 供应商退货主键
     * @return 结果
     */
    @Override
    public int deleteWmRtVendorByRtId(Long rtId)
    {
        return wmRtVendorMapper.deleteWmRtVendorByRtId(rtId);
    }

    @Override
    public List<RtVendorTxBean> getTxBeans(Long rtId) {
        return wmRtVendorMapper.getTxBeans(rtId);
    }
}
