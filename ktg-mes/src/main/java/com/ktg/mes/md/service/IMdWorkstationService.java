package com.ktg.mes.md.service;

import java.util.List;
import com.ktg.mes.md.domain.MdWorkstation;

/**
 * 工作站Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-10
 */
public interface IMdWorkstationService 
{
    /**
     * 查询工作站
     * 
     * @param workstationId 工作站主键
     * @return 工作站
     */
    public MdWorkstation selectMdWorkstationByWorkstationId(Long workstationId);

    /**
     * 查询工作站列表
     * 
     * @param mdWorkstation 工作站
     * @return 工作站集合
     */
    public List<MdWorkstation> selectMdWorkstationList(MdWorkstation mdWorkstation);

    /**
     * 检查编码是否存在
     * @param mdWorkstation
     * @return
     */
    public String checkWorkStationCodeUnique(MdWorkstation mdWorkstation);

    /**
     * 检查名称是否存在
     * @param mdWorkstation
     * @return
     */
    public String checkWorkStationNameUnique(MdWorkstation mdWorkstation);

    /**
     * 新增工作站
     * 
     * @param mdWorkstation 工作站
     * @return 结果
     */
    public int insertMdWorkstation(MdWorkstation mdWorkstation);

    /**
     * 修改工作站
     * 
     * @param mdWorkstation 工作站
     * @return 结果
     */
    public int updateMdWorkstation(MdWorkstation mdWorkstation);

    /**
     * 批量删除工作站
     * 
     * @param workstationIds 需要删除的工作站主键集合
     * @return 结果
     */
    public int deleteMdWorkstationByWorkstationIds(Long[] workstationIds);

    /**
     * 删除工作站信息
     * 
     * @param workstationId 工作站主键
     * @return 结果
     */
    public int deleteMdWorkstationByWorkstationId(Long workstationId);
}
