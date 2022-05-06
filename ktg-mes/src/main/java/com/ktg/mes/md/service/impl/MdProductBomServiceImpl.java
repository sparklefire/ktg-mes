package com.ktg.mes.md.service.impl;

import java.util.List;

import com.ktg.mes.md.service.IMdProductBomService;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.md.mapper.MdProductBomMapper;
import com.ktg.mes.md.domain.MdProductBom;

/**
 * 产品BOM关系Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-05
 */
@Service
public class MdProductBomServiceImpl implements IMdProductBomService
{
    @Autowired
    private MdProductBomMapper mdProductBomMapper;

    /**
     * 查询产品BOM关系
     * 
     * @param bomId 产品BOM关系主键
     * @return 产品BOM关系
     */
    @Override
    public MdProductBom selectMdProductBomByBomId(Long bomId)
    {
        return mdProductBomMapper.selectMdProductBomByBomId(bomId);
    }

    /**
     * 查询产品BOM关系列表
     * 
     * @param mdProductBom 产品BOM关系
     * @return 产品BOM关系
     */
    @Override
    public List<MdProductBom> selectMdProductBomList(MdProductBom mdProductBom)
    {
        return mdProductBomMapper.selectMdProductBomList(mdProductBom);
    }

    @Override
    public String checkBomExist(MdProductBom mdProductBom) {
        MdProductBom bom = mdProductBomMapper.checkBomExist(mdProductBom);
        Long bomId = mdProductBom.getBomId()==null?-1L:mdProductBom.getBomId();
        if(StringUtils.isNotNull(bom) && bom.getBomId().longValue() != bomId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增产品BOM关系
     * 
     * @param mdProductBom 产品BOM关系
     * @return 结果
     */
    @Override
    public int insertMdProductBom(MdProductBom mdProductBom)
    {
        mdProductBom.setCreateTime(DateUtils.getNowDate());
        return mdProductBomMapper.insertMdProductBom(mdProductBom);
    }

    /**
     * 修改产品BOM关系
     * 
     * @param mdProductBom 产品BOM关系
     * @return 结果
     */
    @Override
    public int updateMdProductBom(MdProductBom mdProductBom)
    {
        mdProductBom.setUpdateTime(DateUtils.getNowDate());
        return mdProductBomMapper.updateMdProductBom(mdProductBom);
    }

    /**
     * 批量删除产品BOM关系
     * 
     * @param bomIds 需要删除的产品BOM关系主键
     * @return 结果
     */
    @Override
    public int deleteMdProductBomByBomIds(Long[] bomIds)
    {
        return mdProductBomMapper.deleteMdProductBomByBomIds(bomIds);
    }

    /**
     * 删除产品BOM关系信息
     * 
     * @param bomId 产品BOM关系主键
     * @return 结果
     */
    @Override
    public int deleteMdProductBomByBomId(Long bomId)
    {
        return mdProductBomMapper.deleteMdProductBomByBomId(bomId);
    }
}
