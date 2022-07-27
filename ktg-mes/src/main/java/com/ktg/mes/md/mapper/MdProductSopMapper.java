package com.ktg.mes.md.mapper;

import java.util.List;
import com.ktg.mes.md.domain.MdProductSop;

/**
 * 产品SOPMapper接口
 * 
 * @author yinjinlu
 * @date 2022-07-26
 */
public interface MdProductSopMapper
{
    /**
     * 查询产品SOP
     * 
     * @param sopId 产品SOP主键
     * @return 产品SOP
     */
    public MdProductSop selectMdProductSopBySopId(Long sopId);

    /**
     * 查询产品SOP列表
     * 
     * @param mdProductSop 产品SOP
     * @return 产品SOP集合
     */
    public List<MdProductSop> selectMdProductSopList(MdProductSop mdProductSop);

    /**
     * 新增产品SOP
     * 
     * @param mdProductSop 产品SOP
     * @return 结果
     */
    public int insertMdProductSop(MdProductSop mdProductSop);

    /**
     * 修改产品SOP
     * 
     * @param mdProductSop 产品SOP
     * @return 结果
     */
    public int updateMdProductSop(MdProductSop mdProductSop);

    /**
     * 删除产品SOP
     * 
     * @param sopId 产品SOP主键
     * @return 结果
     */
    public int deleteMdProductSopBySopId(Long sopId);

    /**
     * 批量删除产品SOP
     * 
     * @param sopIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMdProductSopBySopIds(Long[] sopIds);
}
