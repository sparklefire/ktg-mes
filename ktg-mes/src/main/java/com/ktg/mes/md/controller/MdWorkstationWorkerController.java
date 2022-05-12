package com.ktg.mes.md.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.StringUtils;
import com.ktg.system.domain.SysPost;
import com.ktg.system.service.ISysPostService;
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
import com.ktg.mes.md.domain.MdWorkstationWorker;
import com.ktg.mes.md.service.IMdWorkstationWorkerService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 人力资源Controller
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
@RestController
@RequestMapping("/mes/md/workstationworker")
public class MdWorkstationWorkerController extends BaseController
{
    @Autowired
    private IMdWorkstationWorkerService mdWorkstationWorkerService;

    @Autowired
    private ISysPostService sysPostService;

    /**
     * 查询人力资源列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationworker:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdWorkstationWorker mdWorkstationWorker)
    {
        startPage();
        List<MdWorkstationWorker> list = mdWorkstationWorkerService.selectMdWorkstationWorkerList(mdWorkstationWorker);
        return getDataTable(list);
    }

    /**
     * 导出人力资源列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationworker:export')")
    @Log(title = "人力资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdWorkstationWorker mdWorkstationWorker)
    {
        List<MdWorkstationWorker> list = mdWorkstationWorkerService.selectMdWorkstationWorkerList(mdWorkstationWorker);
        ExcelUtil<MdWorkstationWorker> util = new ExcelUtil<MdWorkstationWorker>(MdWorkstationWorker.class);
        util.exportExcel(response, list, "人力资源数据");
    }

    /**
     * 获取人力资源详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationworker:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(mdWorkstationWorkerService.selectMdWorkstationWorkerByRecordId(recordId));
    }

    /**
     * 新增人力资源
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationworker:add')")
    @Log(title = "人力资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdWorkstationWorker mdWorkstationWorker)
    {
        SysPost post = sysPostService.selectPostById(mdWorkstationWorker.getPostId());
        mdWorkstationWorker.setPostCode(post.getPostCode());
        mdWorkstationWorker.setPostName(post.getPostName());
        if(UserConstants.NOT_UNIQUE.equals(mdWorkstationWorkerService.checkPostExist(mdWorkstationWorker))){
            return AjaxResult.error("此岗位已经添加！");
        }
        return toAjax(mdWorkstationWorkerService.insertMdWorkstationWorker(mdWorkstationWorker));
    }

    /**
     * 修改人力资源
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationworker:edit')")
    @Log(title = "人力资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdWorkstationWorker mdWorkstationWorker)
    {
        SysPost post = sysPostService.selectPostById(mdWorkstationWorker.getPostId());
        mdWorkstationWorker.setPostCode(post.getPostCode());
        mdWorkstationWorker.setPostName(post.getPostName());
        if(UserConstants.NOT_UNIQUE.equals(mdWorkstationWorkerService.checkPostExist(mdWorkstationWorker))){
            return AjaxResult.error("此岗位已经添加！");
        }
        return toAjax(mdWorkstationWorkerService.updateMdWorkstationWorker(mdWorkstationWorker));
    }

    /**
     * 删除人力资源
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationworker:remove')")
    @Log(title = "人力资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(mdWorkstationWorkerService.deleteMdWorkstationWorkerByRecordIds(recordIds));
    }
}
