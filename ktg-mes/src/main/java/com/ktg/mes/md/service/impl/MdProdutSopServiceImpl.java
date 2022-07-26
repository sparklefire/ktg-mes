package com.ktg.mes.md.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.md.mapper.MdProdutSopMapper;
import com.ktg.mes.md.domain.MdProdutSop;
import com.ktg.mes.md.service.IMdProdutSopService;

/**
 * 产品SOPService业务层处理
 * 
 * @author yinjinlu
 * @date 2022-07-26
 */
@Service
public class MdProdutSopServiceImpl implements IMdProdutSopService 
{
    @Autowired
    private MdProdutSopMapper mdProdutSopMapper;

    /**
     * 查询产品SOP
     * 
     * @param sopId 产品SOP主键
     * @return 产品SOP
     */
    @Override
    public MdProdutSop selectMdProdutSopBySopId(Long sopId)
    {
        return mdProdutSopMapper.selectMdProdutSopBySopId(sopId);
    }

    /**
     * 查询产品SOP列表
     * 
     * @param mdProdutSop 产品SOP
     * @return 产品SOP
     */
    @Override
    public List<MdProdutSop> selectMdProdutSopList(MdProdutSop mdProdutSop)
    {
        return mdProdutSopMapper.selectMdProdutSopList(mdProdutSop);
    }

    /**
     * 新增产品SOP
     * 
     * @param mdProdutSop 产品SOP
     * @return 结果
     */
    @Override
    public int insertMdProdutSop(MdProdutSop mdProdutSop)
    {
        mdProdutSop.setCreateTime(DateUtils.getNowDate());
        return mdProdutSopMapper.insertMdProdutSop(mdProdutSop);
    }

    /**
     * 修改产品SOP
     * 
     * @param mdProdutSop 产品SOP
     * @return 结果
     */
    @Override
    public int updateMdProdutSop(MdProdutSop mdProdutSop)
    {
        mdProdutSop.setUpdateTime(DateUtils.getNowDate());
        return mdProdutSopMapper.updateMdProdutSop(mdProdutSop);
    }

    /**
     * 批量删除产品SOP
     * 
     * @param sopIds 需要删除的产品SOP主键
     * @return 结果
     */
    @Override
    public int deleteMdProdutSopBySopIds(Long[] sopIds)
    {
        return mdProdutSopMapper.deleteMdProdutSopBySopIds(sopIds);
    }

    /**
     * 删除产品SOP信息
     * 
     * @param sopId 产品SOP主键
     * @return 结果
     */
    @Override
    public int deleteMdProdutSopBySopId(Long sopId)
    {
        return mdProdutSopMapper.deleteMdProdutSopBySopId(sopId);
    }
}
