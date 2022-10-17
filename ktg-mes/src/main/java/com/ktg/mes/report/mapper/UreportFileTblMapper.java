package com.ktg.mes.report.mapper;

import com.ktg.mes.report.domain.UreportFileTbl;

import java.util.List;

/**
 * 报表管理Mapper接口
 * 
 * @author yanshikui
 * @date 2022-10-07
 */
public interface UreportFileTblMapper 
{
    /**
     * 查询报表管理
     * 
     * @param id 报表管理主键
     * @return 报表管理
     */
    public UreportFileTbl selectUreportFileTblById(Long id);

    /**
     * 查询报表管理列表
     * 
     * @param ureportFileTbl 报表管理
     * @return 报表管理集合
     */
    public List<UreportFileTbl> selectUreportFileTblList(UreportFileTbl ureportFileTbl);

    /**
     * 新增报表管理
     * 
     * @param ureportFileTbl 报表管理
     * @return 结果
     */
    public int insertUreportFileTbl(UreportFileTbl ureportFileTbl);

    /**
     * 修改报表管理
     * 
     * @param ureportFileTbl 报表管理
     * @return 结果
     */
    public int updateUreportFileTbl(UreportFileTbl ureportFileTbl);

    /**
     * 删除报表管理
     * 
     * @param id 报表管理主键
     * @return 结果
     */
    public int deleteUreportFileTblById(Long id);

    /**
     * 批量删除报表管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUreportFileTblByIds(Long[] ids);
}
