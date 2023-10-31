package com.ktg.mes.md.mapper;

import java.util.List;
import com.ktg.mes.md.domain.MdProductSip;

/**
 * 产品SIPMapper接口
 * 
 * @author yinjinlu
 * @date 2023-10-31
 */
public interface MdProductSipMapper 
{
    /**
     * 查询产品SIP
     * 
     * @param sipId 产品SIP主键
     * @return 产品SIP
     */
    public MdProductSip selectMdProductSipBySipId(Long sipId);

    /**
     * 查询产品SIP列表
     * 
     * @param mdProductSip 产品SIP
     * @return 产品SIP集合
     */
    public List<MdProductSip> selectMdProductSipList(MdProductSip mdProductSip);

    /**
     * 新增产品SIP
     * 
     * @param mdProductSip 产品SIP
     * @return 结果
     */
    public int insertMdProductSip(MdProductSip mdProductSip);

    /**
     * 修改产品SIP
     * 
     * @param mdProductSip 产品SIP
     * @return 结果
     */
    public int updateMdProductSip(MdProductSip mdProductSip);

    /**
     * 删除产品SIP
     * 
     * @param sipId 产品SIP主键
     * @return 结果
     */
    public int deleteMdProductSipBySipId(Long sipId);

    /**
     * 批量删除产品SIP
     * 
     * @param sipIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMdProductSipBySipIds(Long[] sipIds);
}
