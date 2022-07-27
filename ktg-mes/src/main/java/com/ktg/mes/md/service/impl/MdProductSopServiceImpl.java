package com.ktg.mes.md.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.md.mapper.MdProductSopMapper;
import com.ktg.mes.md.domain.MdProductSop;
import com.ktg.mes.md.service.IMdProductSopService;

/**
 * 产品SOPService业务层处理
 * 
 * @author yinjinlu
 * @date 2022-07-26
 */
@Service
public class MdProductSopServiceImpl implements IMdProductSopService
{
    @Autowired
    private MdProductSopMapper mdProductSopMapper;

    /**
     * 查询产品SOP
     * 
     * @param sopId 产品SOP主键
     * @return 产品SOP
     */
    @Override
    public MdProductSop selectMdProductSopBySopId(Long sopId)
    {
        return mdProductSopMapper.selectMdProductSopBySopId(sopId);
    }

    /**
     * 查询产品SOP列表
     * 
     * @param mdProdutSop 产品SOP
     * @return 产品SOP
     */
    @Override
    public List<MdProductSop> selectMdProductSopList(MdProductSop mdProdutSop)
    {
        return mdProductSopMapper.selectMdProductSopList(mdProdutSop);
    }

    /**
     * 新增产品SOP
     * 
     * @param mdProdutSop 产品SOP
     * @return 结果
     */
    @Override
    public int insertMdProductSop(MdProductSop mdProdutSop)
    {
        mdProdutSop.setCreateTime(DateUtils.getNowDate());
        return mdProductSopMapper.insertMdProductSop(mdProdutSop);
    }

    /**
     * 修改产品SOP
     * 
     * @param mdProdutSop 产品SOP
     * @return 结果
     */
    @Override
    public int updateMdProductSop(MdProductSop mdProdutSop)
    {
        mdProdutSop.setUpdateTime(DateUtils.getNowDate());
        return mdProductSopMapper.updateMdProductSop(mdProdutSop);
    }

    /**
     * 批量删除产品SOP
     * 
     * @param sopIds 需要删除的产品SOP主键
     * @return 结果
     */
    @Override
    public int deleteMdProductSopBySopIds(Long[] sopIds)
    {
        return mdProductSopMapper.deleteMdProductSopBySopIds(sopIds);
    }

    /**
     * 删除产品SOP信息
     * 
     * @param sopId 产品SOP主键
     * @return 结果
     */
    @Override
    public int deleteMdProductSopBySopId(Long sopId)
    {
        return mdProductSopMapper.deleteMdProductSopBySopId(sopId);
    }
}
