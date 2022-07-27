package com.ktg.mes.md.mapper;

import java.util.List;
import com.ktg.mes.md.domain.MdProdutSop;

/**
 * 产品SOPMapper接口
 * 
 * @author yinjinlu
 * @date 2022-07-26
 */
public interface MdProdutSopMapper 
{
    /**
     * 查询产品SOP
     * 
     * @param sopId 产品SOP主键
     * @return 产品SOP
     */
    public MdProdutSop selectMdProdutSopBySopId(Long sopId);

    /**
     * 查询产品SOP列表
     * 
     * @param mdProdutSop 产品SOP
     * @return 产品SOP集合
     */
    public List<MdProdutSop> selectMdProdutSopList(MdProdutSop mdProdutSop);

    /**
     * 新增产品SOP
     * 
     * @param mdProdutSop 产品SOP
     * @return 结果
     */
    public int insertMdProdutSop(MdProdutSop mdProdutSop);

    /**
     * 修改产品SOP
     * 
     * @param mdProdutSop 产品SOP
     * @return 结果
     */
    public int updateMdProdutSop(MdProdutSop mdProdutSop);

    /**
     * 删除产品SOP
     * 
     * @param sopId 产品SOP主键
     * @return 结果
     */
    public int deleteMdProdutSopBySopId(Long sopId);

    /**
     * 批量删除产品SOP
     * 
     * @param sopIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMdProdutSopBySopIds(Long[] sopIds);
}
