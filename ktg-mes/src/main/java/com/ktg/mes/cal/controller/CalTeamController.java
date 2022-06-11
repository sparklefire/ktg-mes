package com.ktg.mes.cal.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.mes.cal.service.ICalTeamMemberService;
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
import com.ktg.mes.cal.domain.CalTeam;
import com.ktg.mes.cal.service.ICalTeamService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 班组Controller
 * 
 * @author yinjinlu
 * @date 2022-06-05
 */
@RestController
@RequestMapping("/mes/cal/team")
public class CalTeamController extends BaseController
{
    @Autowired
    private ICalTeamService calTeamService;

    @Autowired
    private ICalTeamMemberService calTeamMemberService;

    /**
     * 查询班组列表
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:team:list')")
    @GetMapping("/list")
    public TableDataInfo list(CalTeam calTeam)
    {
        startPage();
        List<CalTeam> list = calTeamService.selectCalTeamList(calTeam);
        return getDataTable(list);
    }

    /**
     * 查询所有班组列表
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:team:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(){
        CalTeam  calTeam= new CalTeam();
        List<CalTeam> list = calTeamService.selectCalTeamList(calTeam);
        return AjaxResult.success(list);
    }

    /**
     * 导出班组列表
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:team:export')")
    @Log(title = "班组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CalTeam calTeam)
    {
        List<CalTeam> list = calTeamService.selectCalTeamList(calTeam);
        ExcelUtil<CalTeam> util = new ExcelUtil<CalTeam>(CalTeam.class);
        util.exportExcel(response, list, "班组数据");
    }

    /**
     * 获取班组详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:team:query')")
    @GetMapping(value = "/{teamId}")
    public AjaxResult getInfo(@PathVariable("teamId") Long teamId)
    {
        return AjaxResult.success(calTeamService.selectCalTeamByTeamId(teamId));
    }

    /**
     * 新增班组
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:team:add')")
    @Log(title = "班组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CalTeam calTeam)
    {
        return toAjax(calTeamService.insertCalTeam(calTeam));
    }

    /**
     * 修改班组
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:team:edit')")
    @Log(title = "班组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CalTeam calTeam)
    {
        return toAjax(calTeamService.updateCalTeam(calTeam));
    }

    /**
     * 删除班组
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:team:remove')")
    @Log(title = "班组", businessType = BusinessType.DELETE)
    @Transactional
	@DeleteMapping("/{teamIds}")
    public AjaxResult remove(@PathVariable Long[] teamIds)
    {
        for (Long teamId:teamIds
             ) {
            calTeamMemberService.deleteByTeamId(teamId);
        }
        return toAjax(calTeamService.deleteCalTeamByTeamIds(teamIds));
    }
}
