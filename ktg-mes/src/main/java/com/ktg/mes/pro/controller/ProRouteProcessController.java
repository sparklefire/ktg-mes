package com.ktg.mes.pro.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
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
import com.ktg.mes.pro.domain.ProRouteProcess;
import com.ktg.mes.pro.service.IProRouteProcessService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 工艺组成Controller
 * 
 * @author yinjinlu
 * @date 2022-05-13
 */
@RestController
@RequestMapping("/mes/pro/routeprocess")
public class ProRouteProcessController extends BaseController
{
    @Autowired
    private IProRouteProcessService proRouteProcessService;

    /**
     * 查询工艺组成列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:routeprocess:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProRouteProcess proRouteProcess)
    {
        startPage();
        List<ProRouteProcess> list = proRouteProcessService.selectProRouteProcessList(proRouteProcess);
        return getDataTable(list);
    }

    /**
     * 导出工艺组成列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:routeprocess:export')")
    @Log(title = "工艺组成", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProRouteProcess proRouteProcess)
    {
        List<ProRouteProcess> list = proRouteProcessService.selectProRouteProcessList(proRouteProcess);
        ExcelUtil<ProRouteProcess> util = new ExcelUtil<ProRouteProcess>(ProRouteProcess.class);
        util.exportExcel(response, list, "工艺组成数据");
    }

    /**
     * 获取工艺组成详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:routeprocess:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(proRouteProcessService.selectProRouteProcessByRecordId(recordId));
    }

    /**
     * 新增工艺组成
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:routeprocess:add')")
    @Log(title = "工艺组成", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProRouteProcess proRouteProcess)
    {
        if(UserConstants.NOT_UNIQUE.equals(proRouteProcessService.checkOrderNumExists(proRouteProcess))){
            return AjaxResult.error("序号已存在！");
        }
        return toAjax(proRouteProcessService.insertProRouteProcess(proRouteProcess));
    }

    /**
     * 修改工艺组成
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:routeprocess:edit')")
    @Log(title = "工艺组成", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProRouteProcess proRouteProcess)
    {
        if(UserConstants.NOT_UNIQUE.equals(proRouteProcessService.checkOrderNumExists(proRouteProcess))){
            return AjaxResult.error("序号已存在！");
        }
        return toAjax(proRouteProcessService.updateProRouteProcess(proRouteProcess));
    }

    /**
     * 删除工艺组成
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:routeprocess:remove')")
    @Log(title = "工艺组成", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(proRouteProcessService.deleteProRouteProcessByRecordIds(recordIds));
    }
}
