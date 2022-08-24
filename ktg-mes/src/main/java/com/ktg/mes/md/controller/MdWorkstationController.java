package com.ktg.mes.md.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
import com.ktg.mes.md.domain.MdWorkshop;
import com.ktg.mes.md.service.*;
import com.ktg.mes.pro.domain.ProProcess;
import com.ktg.mes.pro.service.IProProcessService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ktg.common.annotation.Log;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.enums.BusinessType;
import com.ktg.mes.md.domain.MdWorkstation;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 工作站Controller
 * 
 * @author yinjinlu
 * @date 2022-05-10
 */
@RestController
@RequestMapping("/mes/md/workstation")
public class MdWorkstationController extends BaseController
{
    @Autowired
    private IMdWorkstationService mdWorkstationService;

    @Autowired
    private IMdWorkstationMachineService mdWorkstationMachineService;

    @Autowired
    private IMdWorkstationToolService mdWorkstationToolService;

    @Autowired
    private IMdWorkstationWorkerService mdWorkstationWorkerService;

    @Autowired
    private IProProcessService proProcessService;

    @Autowired
    private IMdWorkshopService mdWorkshopService;

    /**
     * 查询工作站列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstation:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdWorkstation mdWorkstation)
    {
        startPage();
        List<MdWorkstation> list = mdWorkstationService.selectMdWorkstationList(mdWorkstation);
        return getDataTable(list);
    }

    /**
     * 导出工作站列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstation:export')")
    @Log(title = "工作站", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdWorkstation mdWorkstation)
    {
        List<MdWorkstation> list = mdWorkstationService.selectMdWorkstationList(mdWorkstation);
        ExcelUtil<MdWorkstation> util = new ExcelUtil<MdWorkstation>(MdWorkstation.class);
        util.exportExcel(response, list, "工作站数据");
    }

    /**
     * 获取工作站详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstation:query')")
    @GetMapping(value = "/{workstationId}")
    public AjaxResult getInfo(@PathVariable("workstationId") Long workstationId)
    {
        return AjaxResult.success(mdWorkstationService.selectMdWorkstationByWorkstationId(workstationId));
    }

    /**
     * 新增工作站
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstation:add')")
    @Log(title = "工作站", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdWorkstation mdWorkstation)
    {
        if(UserConstants.NOT_UNIQUE.equals(mdWorkstationService.checkWorkStationCodeUnique(mdWorkstation))){
            return AjaxResult.error("工作站编码已存在！");
        }
        if(UserConstants.NOT_UNIQUE.equals(mdWorkstationService.checkWorkStationNameUnique(mdWorkstation))){
            return AjaxResult.error("工作站名称已存在！");
        }
        ProProcess process = proProcessService.selectProProcessByProcessId(mdWorkstation.getProcessId());
        mdWorkstation.setProcessCode(process.getProcessCode());
        mdWorkstation.setProcessName(process.getProcessName());

        MdWorkshop workshop = mdWorkshopService.selectMdWorkshopByWorkshopId(mdWorkstation.getWorkshopId());
        mdWorkstation.setWorkshopCode(workshop.getWorkshopCode());
        mdWorkstation.setWorkshopName(workshop.getWorkshopName());
        return toAjax(mdWorkstationService.insertMdWorkstation(mdWorkstation));
    }

    /**
     * 修改工作站
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstation:edit')")
    @Log(title = "工作站", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdWorkstation mdWorkstation)
    {
        if(UserConstants.NOT_UNIQUE.equals(mdWorkstationService.checkWorkStationCodeUnique(mdWorkstation))){
            return AjaxResult.error("工作站编码已存在！");
        }
        if(UserConstants.NOT_UNIQUE.equals(mdWorkstationService.checkWorkStationNameUnique(mdWorkstation))){
            return AjaxResult.error("工作站名称已存在！");
        }
        ProProcess process = proProcessService.selectProProcessByProcessId(mdWorkstation.getProcessId());
        mdWorkstation.setProcessCode(process.getProcessCode());
        mdWorkstation.setProcessName(process.getProcessName());

        MdWorkshop workshop = mdWorkshopService.selectMdWorkshopByWorkshopId(mdWorkstation.getWorkshopId());
        mdWorkstation.setWorkshopCode(workshop.getWorkshopCode());
        mdWorkstation.setWorkshopName(workshop.getWorkshopName());
        return toAjax(mdWorkstationService.updateMdWorkstation(mdWorkstation));
    }

    /**
     * 删除工作站
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstation:remove')")
    @Log(title = "工作站", businessType = BusinessType.DELETE)
    @Transactional
	@DeleteMapping("/{workstationIds}")
    public AjaxResult remove(@PathVariable Long[] workstationIds)
    {
        for (Long workstationId: workstationIds
             ) {
            mdWorkstationMachineService.deleteByWorkstationId(workstationId);
            mdWorkstationToolService.deleteByWorkstationId(workstationId);
            mdWorkstationWorkerService.deleteByWorkstationId(workstationId);
        }
        return toAjax(mdWorkstationService.deleteMdWorkstationByWorkstationIds(workstationIds));
    }
}
