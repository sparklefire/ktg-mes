package com.ktg.mes.report.service;

import com.ktg.mes.report.domain.UreportFileTbl;

import java.util.List;

/**
 * 报表管理Service接口
 * 
 * @author yanshikui
 * @date 2022-10-07
 */
public interface IUreportFileTblService 
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
     * 批量删除报表管理
     * 
     * @param ids 需要删除的报表管理主键集合
     * @return 结果
     */
    public int deleteUreportFileTblByIds(Long[] ids);

    /**
     * 删除报表管理信息
     * 
     * @param id 报表管理主键
     * @return 结果
     */
    public int deleteUreportFileTblById(Long id);
}
