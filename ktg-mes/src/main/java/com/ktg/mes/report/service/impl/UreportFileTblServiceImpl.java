package com.ktg.mes.report.service.impl;

import com.ktg.common.utils.DateUtils;
import com.ktg.mes.report.domain.UreportFileTbl;
import com.ktg.mes.report.mapper.UreportFileTblMapper;
import com.ktg.mes.report.service.IUreportFileTblService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报表管理Service业务层处理
 *
 * @author yanshikui
 * @date 2022-10-07
 */
@Service
public class UreportFileTblServiceImpl implements IUreportFileTblService
{
    @Autowired
    private UreportFileTblMapper ureportFileTblMapper;

    /**
     * 查询报表管理
     *
     * @param id 报表管理主键
     * @return 报表管理
     */
    @Override
    public UreportFileTbl selectUreportFileTblById(Long id)
    {
        return ureportFileTblMapper.selectUreportFileTblById(id);
    }

    /**
     * 查询报表管理列表
     *
     * @param ureportFileTbl 报表管理
     * @return 报表管理
     */
    @Override
    public List<UreportFileTbl> selectUreportFileTblList(UreportFileTbl ureportFileTbl)
    {
        return ureportFileTblMapper.selectUreportFileTblList(ureportFileTbl);
    }

    /**
     * 新增报表管理
     *
     * @param ureportFileTbl 报表管理
     * @return 结果
     */
    @Override
    public int insertUreportFileTbl(UreportFileTbl ureportFileTbl)
    {
        ureportFileTbl.setCreateTime(DateUtils.getNowDate());
        return ureportFileTblMapper.insertUreportFileTbl(ureportFileTbl);
    }

    /**
     * 修改报表管理
     *
     * @param ureportFileTbl 报表管理
     * @return 结果
     */
    @Override
    public int updateUreportFileTbl(UreportFileTbl ureportFileTbl)
    {
        ureportFileTbl.setUpdateTime(DateUtils.getNowDate());
        return ureportFileTblMapper.updateUreportFileTbl(ureportFileTbl);
    }

    /**
     * 批量删除报表管理
     *
     * @param ids 需要删除的报表管理主键
     * @return 结果
     */
    @Override
    public int deleteUreportFileTblByIds(Long[] ids)
    {
        return ureportFileTblMapper.deleteUreportFileTblByIds(ids);
    }

    /**
     * 删除报表管理信息
     *
     * @param id 报表管理主键
     * @return 结果
     */
    @Override
    public int deleteUreportFileTblById(Long id)
    {
        return ureportFileTblMapper.deleteUreportFileTblById(id);
    }
}
