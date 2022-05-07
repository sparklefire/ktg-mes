package com.ktg.mes.md.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.md.mapper.MdWorkshopMapper;
import com.ktg.mes.md.domain.MdWorkshop;
import com.ktg.mes.md.service.IMdWorkshopService;

/**
 * 车间Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-07
 */
@Service
public class MdWorkshopServiceImpl implements IMdWorkshopService 
{
    @Autowired
    private MdWorkshopMapper mdWorkshopMapper;

    /**
     * 查询车间
     * 
     * @param workshopId 车间主键
     * @return 车间
     */
    @Override
    public MdWorkshop selectMdWorkshopByWorkshopId(Long workshopId)
    {
        return mdWorkshopMapper.selectMdWorkshopByWorkshopId(workshopId);
    }

    /**
     * 查询车间列表
     * 
     * @param mdWorkshop 车间
     * @return 车间
     */
    @Override
    public List<MdWorkshop> selectMdWorkshopList(MdWorkshop mdWorkshop)
    {
        return mdWorkshopMapper.selectMdWorkshopList(mdWorkshop);
    }

    @Override
    public String checkWorkshopCodeUnique(MdWorkshop mdWorkshop) {
        MdWorkshop workshop = mdWorkshopMapper.checkWorkshopCodeUnique(mdWorkshop);
        Long workshopId = mdWorkshop.getWorkshopId()==null?-1L:mdWorkshop.getWorkshopId();
        if(StringUtils.isNotNull(workshop) && workshop.getWorkshopId().longValue() != workshopId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkWorkshopNameUnique(MdWorkshop mdWorkshop) {
        MdWorkshop workshop = mdWorkshopMapper.checkWorkshopNameUnique(mdWorkshop);
        Long workshopId = mdWorkshop.getWorkshopId()==null?-1L:mdWorkshop.getWorkshopId();
        if(StringUtils.isNotNull(workshop) && workshop.getWorkshopId().longValue() != workshopId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增车间
     * 
     * @param mdWorkshop 车间
     * @return 结果
     */
    @Override
    public int insertMdWorkshop(MdWorkshop mdWorkshop)
    {
        mdWorkshop.setCreateTime(DateUtils.getNowDate());
        return mdWorkshopMapper.insertMdWorkshop(mdWorkshop);
    }

    /**
     * 修改车间
     * 
     * @param mdWorkshop 车间
     * @return 结果
     */
    @Override
    public int updateMdWorkshop(MdWorkshop mdWorkshop)
    {
        mdWorkshop.setUpdateTime(DateUtils.getNowDate());
        return mdWorkshopMapper.updateMdWorkshop(mdWorkshop);
    }

    /**
     * 批量删除车间
     * 
     * @param workshopIds 需要删除的车间主键
     * @return 结果
     */
    @Override
    public int deleteMdWorkshopByWorkshopIds(Long[] workshopIds)
    {
        return mdWorkshopMapper.deleteMdWorkshopByWorkshopIds(workshopIds);
    }

    /**
     * 删除车间信息
     * 
     * @param workshopId 车间主键
     * @return 结果
     */
    @Override
    public int deleteMdWorkshopByWorkshopId(Long workshopId)
    {
        return mdWorkshopMapper.deleteMdWorkshopByWorkshopId(workshopId);
    }
}
