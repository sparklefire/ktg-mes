package com.ktg.mes.md.mapper;

import java.util.List;
import com.ktg.mes.md.domain.MdVendor;

/**
 * 供应商Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-05-06
 */
public interface MdVendorMapper 
{
    /**
     * 查询供应商
     * 
     * @param vendorId 供应商主键
     * @return 供应商
     */
    public MdVendor selectMdVendorByVendorId(Long vendorId);

    /**
     * 查询供应商列表
     * 
     * @param mdVendor 供应商
     * @return 供应商集合
     */
    public List<MdVendor> selectMdVendorList(MdVendor mdVendor);

    public MdVendor checkVendorCodeUnique(MdVendor mdVendor);
    public MdVendor checkVendorNameUnique(MdVendor mdVendor);
    public MdVendor checkVendorNickUnique(MdVendor mdVendor);

    /**
     * 新增供应商
     * 
     * @param mdVendor 供应商
     * @return 结果
     */
    public int insertMdVendor(MdVendor mdVendor);

    /**
     * 修改供应商
     * 
     * @param mdVendor 供应商
     * @return 结果
     */
    public int updateMdVendor(MdVendor mdVendor);

    /**
     * 删除供应商
     * 
     * @param vendorId 供应商主键
     * @return 结果
     */
    public int deleteMdVendorByVendorId(Long vendorId);

    /**
     * 批量删除供应商
     * 
     * @param vendorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMdVendorByVendorIds(Long[] vendorIds);
}
