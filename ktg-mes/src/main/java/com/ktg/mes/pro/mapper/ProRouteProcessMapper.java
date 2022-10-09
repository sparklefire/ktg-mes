package com.ktg.mes.pro.mapper;

import java.util.List;
import com.ktg.mes.pro.domain.ProRouteProcess;
import org.springframework.security.core.parameters.P;

/**
 * 工艺组成Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-05-13
 */
public interface ProRouteProcessMapper 
{
    /**
     * 查询工艺组成
     * 
     * @param recordId 工艺组成主键
     * @return 工艺组成
     */
    public ProRouteProcess selectProRouteProcessByRecordId(Long recordId);

    /**
     * 查询工艺组成列表
     * 
     * @param proRouteProcess 工艺组成
     * @return 工艺组成集合
     */
    public List<ProRouteProcess> selectProRouteProcessList(ProRouteProcess proRouteProcess);

    public ProRouteProcess checkOrderNumExists(ProRouteProcess proRouteProcess);
    public ProRouteProcess checkProcessExists(ProRouteProcess proRouteProcess);
    public ProRouteProcess checkUpdateFlagUnique(ProRouteProcess proRouteProcess);

    public ProRouteProcess findPreProcess(ProRouteProcess proRouteProcess);

    public ProRouteProcess findNextProcess(ProRouteProcess proRouteProcess);
    /**
     * 新增工艺组成
     * 
     * @param proRouteProcess 工艺组成
     * @return 结果
     */
    public int insertProRouteProcess(ProRouteProcess proRouteProcess);

    /**
     * 修改工艺组成
     * 
     * @param proRouteProcess 工艺组成
     * @return 结果
     */
    public int updateProRouteProcess(ProRouteProcess proRouteProcess);

    /**
     * 删除工艺组成
     * 
     * @param recordId 工艺组成主键
     * @return 结果
     */
    public int deleteProRouteProcessByRecordId(Long recordId);

    /**
     * 批量删除工艺组成
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProRouteProcessByRecordIds(Long[] recordIds);

    /**
     * 根据工艺路线ID删除所有工序配置
     * @param routeId
     * @return
     */
    public int deleteByRouteId(Long routeId);
}
