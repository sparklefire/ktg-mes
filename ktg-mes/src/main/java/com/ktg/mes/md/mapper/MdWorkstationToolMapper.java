package com.ktg.mes.md.mapper;

import java.util.List;
import com.ktg.mes.md.domain.MdWorkstationTool;

/**
 * 工装夹具资源Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
public interface MdWorkstationToolMapper 
{
    /**
     * 查询工装夹具资源
     * 
     * @param recordId 工装夹具资源主键
     * @return 工装夹具资源
     */
    public MdWorkstationTool selectMdWorkstationToolByRecordId(Long recordId);

    /**
     * 查询工装夹具资源列表
     * 
     * @param mdWorkstationTool 工装夹具资源
     * @return 工装夹具资源集合
     */
    public List<MdWorkstationTool> selectMdWorkstationToolList(MdWorkstationTool mdWorkstationTool);

    public MdWorkstationTool checkToolTypeExists(MdWorkstationTool mdWorkstationTool);

    /**
     * 新增工装夹具资源
     * 
     * @param mdWorkstationTool 工装夹具资源
     * @return 结果
     */
    public int insertMdWorkstationTool(MdWorkstationTool mdWorkstationTool);

    /**
     * 修改工装夹具资源
     * 
     * @param mdWorkstationTool 工装夹具资源
     * @return 结果
     */
    public int updateMdWorkstationTool(MdWorkstationTool mdWorkstationTool);

    /**
     * 删除工装夹具资源
     * 
     * @param recordId 工装夹具资源主键
     * @return 结果
     */
    public int deleteMdWorkstationToolByRecordId(Long recordId);

    /**
     * 批量删除工装夹具资源
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMdWorkstationToolByRecordIds(Long[] recordIds);

    /**
     * 根据工作站ID删除对应的工装夹具信息
     * @param workstationId
     * @return
     */
    public int deleteByWorkstationId(Long workstationId);
}
