package com.ktg.mes.md.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.md.mapper.MdVendorMapper;
import com.ktg.mes.md.domain.MdVendor;
import com.ktg.mes.md.service.IMdVendorService;

/**
 * 供应商Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-06
 */
@Service
public class MdVendorServiceImpl implements IMdVendorService 
{
    @Autowired
    private MdVendorMapper mdVendorMapper;

    /**
     * 查询供应商
     * 
     * @param vendorId 供应商主键
     * @return 供应商
     */
    @Override
    public MdVendor selectMdVendorByVendorId(Long vendorId)
    {
        return mdVendorMapper.selectMdVendorByVendorId(vendorId);
    }

    /**
     * 查询供应商列表
     * 
     * @param mdVendor 供应商
     * @return 供应商
     */
    @Override
    public List<MdVendor> selectMdVendorList(MdVendor mdVendor)
    {
        return mdVendorMapper.selectMdVendorList(mdVendor);
    }

    @Override
    public String checkVendorCodeUnique(MdVendor mdVendor) {
        MdVendor vendor = mdVendorMapper.checkVendorCodeUnique(mdVendor);
        Long vendorId = mdVendor.getVendorId()==null?-1L:mdVendor.getVendorId();
        if(StringUtils.isNotNull(vendor) && vendor.getVendorId().longValue() !=vendorId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkVendorNameUnique(MdVendor mdVendor) {
        MdVendor vendor = mdVendorMapper.checkVendorNameUnique(mdVendor);
        Long vendorId = mdVendor.getVendorId()==null?-1L:mdVendor.getVendorId();
        if(StringUtils.isNotNull(vendor) && vendor.getVendorId().longValue() !=vendorId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkVendorNickUnique(MdVendor mdVendor) {
        MdVendor vendor = mdVendorMapper.checkVendorNickUnique(mdVendor);
        Long vendorId = mdVendor.getVendorId()==null?-1L:mdVendor.getVendorId();
        if(StringUtils.isNotNull(vendor) && vendor.getVendorId().longValue() !=vendorId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增供应商
     * 
     * @param mdVendor 供应商
     * @return 结果
     */
    @Override
    public int insertMdVendor(MdVendor mdVendor)
    {
        mdVendor.setCreateTime(DateUtils.getNowDate());
        return mdVendorMapper.insertMdVendor(mdVendor);
    }

    /**
     * 修改供应商
     * 
     * @param mdVendor 供应商
     * @return 结果
     */
    @Override
    public int updateMdVendor(MdVendor mdVendor)
    {
        mdVendor.setUpdateTime(DateUtils.getNowDate());
        return mdVendorMapper.updateMdVendor(mdVendor);
    }

    /**
     * 批量删除供应商
     * 
     * @param vendorIds 需要删除的供应商主键
     * @return 结果
     */
    @Override
    public int deleteMdVendorByVendorIds(Long[] vendorIds)
    {
        return mdVendorMapper.deleteMdVendorByVendorIds(vendorIds);
    }

    /**
     * 删除供应商信息
     * 
     * @param vendorId 供应商主键
     * @return 结果
     */
    @Override
    public int deleteMdVendorByVendorId(Long vendorId)
    {
        return mdVendorMapper.deleteMdVendorByVendorId(vendorId);
    }
}
