package com.ktg.mes.md.service;

import java.util.List;
import com.ktg.mes.md.domain.MdProductSop;

/**
 * 产品SOPService接口
 * 
 * @author yinjinlu
 * @date 2022-07-26
 */
public interface IMdProductSopService
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
     * @param mdProdutSop 产品SOP
     * @return 产品SOP集合
     */
    public List<MdProductSop> selectMdProductSopList(MdProductSop mdProductSop);

    /**
     * 新增产品SOP
     * 
     * @param mdProdutSop 产品SOP
     * @return 结果
     */
    public int insertMdProductSop(MdProductSop mdProductSop);

    /**
     * 修改产品SOP
     * 
     * @param mdProdutSop 产品SOP
     * @return 结果
     */
    public int updateMdProductSop(MdProductSop mdProductSop);

    /**
     * 批量删除产品SOP
     * 
     * @param sopIds 需要删除的产品SOP主键集合
     * @return 结果
     */
    public int deleteMdProductSopBySopIds(Long[] sopIds);

    /**
     * 删除产品SOP信息
     * 
     * @param sopId 产品SOP主键
     * @return 结果
     */
    public int deleteMdProductSopBySopId(Long sopId);
}
