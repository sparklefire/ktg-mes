package com.ktg.mes.md.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.md.mapper.MdWorkstationMapper;
import com.ktg.mes.md.domain.MdWorkstation;
import com.ktg.mes.md.service.IMdWorkstationService;

/**
 * 工作站Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-10
 */
@Service
public class MdWorkstationServiceImpl implements IMdWorkstationService 
{
    @Autowired
    private MdWorkstationMapper mdWorkstationMapper;

    /**
     * 查询工作站
     * 
     * @param workstationId 工作站主键
     * @return 工作站
     */
    @Override
    public MdWorkstation selectMdWorkstationByWorkstationId(Long workstationId)
    {
        return mdWorkstationMapper.selectMdWorkstationByWorkstationId(workstationId);
    }

    /**
     * 查询工作站列表
     * 
     * @param mdWorkstation 工作站
     * @return 工作站
     */
    @Override
    public List<MdWorkstation> selectMdWorkstationList(MdWorkstation mdWorkstation)
    {
        return mdWorkstationMapper.selectMdWorkstationList(mdWorkstation);
    }

    @Override
    public String checkWorkStationCodeUnique(MdWorkstation mdWorkstation) {
        MdWorkstation workstation = mdWorkstationMapper.checkWorkStationCodeUnique(mdWorkstation);
        Long workstationId = mdWorkstation.getWorkstationId()==null? -1L:mdWorkstation.getWorkstationId();
        if(StringUtils.isNotNull(workstation) && workstation.getWorkstationId().longValue() != workstationId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkWorkStationNameUnique(MdWorkstation mdWorkstation) {
        MdWorkstation workstation = mdWorkstationMapper.checkWorkStationNameUnique(mdWorkstation);
        Long workstationId = mdWorkstation.getWorkstationId()==null? -1L:mdWorkstation.getWorkstationId();
        if(StringUtils.isNotNull(workstation) && workstation.getWorkstationId().longValue() != workstationId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增工作站
     * 
     * @param mdWorkstation 工作站
     * @return 结果
     */
    @Override
    public int insertMdWorkstation(MdWorkstation mdWorkstation)
    {
        mdWorkstation.setCreateTime(DateUtils.getNowDate());
        return mdWorkstationMapper.insertMdWorkstation(mdWorkstation);
    }

    /**
     * 修改工作站
     * 
     * @param mdWorkstation 工作站
     * @return 结果
     */
    @Override
    public int updateMdWorkstation(MdWorkstation mdWorkstation)
    {
        mdWorkstation.setUpdateTime(DateUtils.getNowDate());
        return mdWorkstationMapper.updateMdWorkstation(mdWorkstation);
    }

    /**
     * 批量删除工作站
     * 
     * @param workstationIds 需要删除的工作站主键
     * @return 结果
     */
    @Override
    public int deleteMdWorkstationByWorkstationIds(Long[] workstationIds)
    {
        return mdWorkstationMapper.deleteMdWorkstationByWorkstationIds(workstationIds);
    }

    /**
     * 删除工作站信息
     * 
     * @param workstationId 工作站主键
     * @return 结果
     */
    @Override
    public int deleteMdWorkstationByWorkstationId(Long workstationId)
    {
        return mdWorkstationMapper.deleteMdWorkstationByWorkstationId(workstationId);
    }
}
