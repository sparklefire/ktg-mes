package com.ktg.mes.dv.service.impl;

import com.ktg.common.utils.DateUtils;
import com.ktg.mes.dv.domain.DvMachinery;
import com.ktg.mes.dv.mapper.DvMachineryMapper;
import com.ktg.mes.dv.service.IDvMachineryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-08
 */
@Service
public class DvMachineryServiceImpl implements IDvMachineryService 
{
    @Autowired
    private DvMachineryMapper dvMachineryMapper;

    /**
     * 查询设备
     * 
     * @param machineryId 设备主键
     * @return 设备
     */
    @Override
    public DvMachinery selectDvMachineryByMachineryId(Long machineryId)
    {
        return dvMachineryMapper.selectDvMachineryByMachineryId(machineryId);
    }

    /**
     * 查询设备列表
     * 
     * @param dvMachinery 设备
     * @return 设备
     */
    @Override
    public List<DvMachinery> selectDvMachineryList(DvMachinery dvMachinery)
    {
        return dvMachineryMapper.selectDvMachineryList(dvMachinery);
    }

    /**
     * 新增设备
     * 
     * @param dvMachinery 设备
     * @return 结果
     */
    @Override
    public int insertDvMachinery(DvMachinery dvMachinery)
    {
        dvMachinery.setCreateTime(DateUtils.getNowDate());
        return dvMachineryMapper.insertDvMachinery(dvMachinery);
    }

    /**
     * 修改设备
     * 
     * @param dvMachinery 设备
     * @return 结果
     */
    @Override
    public int updateDvMachinery(DvMachinery dvMachinery)
    {
        dvMachinery.setUpdateTime(DateUtils.getNowDate());
        return dvMachineryMapper.updateDvMachinery(dvMachinery);
    }

    /**
     * 批量删除设备
     * 
     * @param machineryIds 需要删除的设备主键
     * @return 结果
     */
    @Override
    public int deleteDvMachineryByMachineryIds(Long[] machineryIds)
    {
        return dvMachineryMapper.deleteDvMachineryByMachineryIds(machineryIds);
    }

    /**
     * 删除设备信息
     * 
     * @param machineryId 设备主键
     * @return 结果
     */
    @Override
    public int deleteDvMachineryByMachineryId(Long machineryId)
    {
        return dvMachineryMapper.deleteDvMachineryByMachineryId(machineryId);
    }

    /**
     * 依据上传的文件更新或插入设备信息
     */
    @Override
    public String importMachinery(List<DvMachinery> machineryList, Boolean isUpdateSupport, String operName) {
        if (machineryList == null || machineryList.isEmpty()) {
            return "导入数据为空";
        }
        int successCount = 0;
        int failureCount = 0;
        for (DvMachinery machinery : machineryList) {
            // 去除空格
            String machineryCode = machinery.getMachineryCode().trim();
            DvMachinery existing = dvMachineryMapper.selectByMachineryCode(machineryCode);
            if (existing != null) {
                if (isUpdateSupport) {
                    // 更新数据
                    machinery.setMachineryId(existing.getMachineryId()); // 确保使用现有 ID 进行更新
                    dvMachineryMapper.updateDvMachinery(machinery);
                    successCount++;
                } else {
                    // 不更新数据
                    failureCount++;
                }
            } else {
                // 新增数据
                dvMachineryMapper.insertDvMachinery(machinery);
                successCount++;
            }
        }
        return String.format("操作用户：%s，导入完成，成功 %d 条，失败 %d 条。", operName, successCount, failureCount);
    }


}
