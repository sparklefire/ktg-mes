package com.ktg.mes.md.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.md.domain.MdWorkstation;
import com.ktg.mes.md.service.IMdWorkstationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ktg.mes.md.domain.MdWorkstationMachine;
import com.ktg.mes.md.service.IMdWorkstationMachineService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 设备资源Controller
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
@RestController
@RequestMapping("/mes/md/workstationmachine")
public class MdWorkstationMachineController extends BaseController
{
    @Autowired
    private IMdWorkstationMachineService mdWorkstationMachineService;

    @Autowired
    private IMdWorkstationService mdWorkstationService;

    /**
     * 查询设备资源列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationmachine:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdWorkstationMachine mdWorkstationMachine)
    {
        startPage();
        List<MdWorkstationMachine> list = mdWorkstationMachineService.selectMdWorkstationMachineList(mdWorkstationMachine);
        return getDataTable(list);
    }

    /**
     * 导出设备资源列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationmachine:export')")
    @Log(title = "设备资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdWorkstationMachine mdWorkstationMachine)
    {
        List<MdWorkstationMachine> list = mdWorkstationMachineService.selectMdWorkstationMachineList(mdWorkstationMachine);
        ExcelUtil<MdWorkstationMachine> util = new ExcelUtil<MdWorkstationMachine>(MdWorkstationMachine.class);
        util.exportExcel(response, list, "设备资源数据");
    }

    /**
     * 获取设备资源详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationmachine:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(mdWorkstationMachineService.selectMdWorkstationMachineByRecordId(recordId));
    }

    /**
     * 新增设备资源
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationmachine:add')")
    @Log(title = "设备资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdWorkstationMachine mdWorkstationMachine)
    {
        MdWorkstationMachine machine = mdWorkstationMachineService.checkMachineryExists(mdWorkstationMachine);
        if(StringUtils.isNotNull(machine)){
            MdWorkstation workstation = mdWorkstationService.selectMdWorkstationByWorkstationId(machine.getWorkstationId());
            return AjaxResult.error("设备已分配至工作站:"+workstation.getWorkstationName());
        }
        return toAjax(mdWorkstationMachineService.insertMdWorkstationMachine(mdWorkstationMachine));
    }

    /**
     * 修改设备资源
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationmachine:edit')")
    @Log(title = "设备资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdWorkstationMachine mdWorkstationMachine)
    {
        return toAjax(mdWorkstationMachineService.updateMdWorkstationMachine(mdWorkstationMachine));
    }

    /**
     * 删除设备资源
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationmachine:remove')")
    @Log(title = "设备资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(mdWorkstationMachineService.deleteMdWorkstationMachineByRecordIds(recordIds));
    }
}
