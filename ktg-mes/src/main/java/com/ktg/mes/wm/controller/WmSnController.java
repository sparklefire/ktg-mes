package com.ktg.mes.wm.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import com.ktg.common.constant.UserConstants;
import com.ktg.system.strategy.AutoCodeUtil;
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
import com.ktg.mes.wm.domain.WmSn;
import com.ktg.mes.wm.service.IWmSnService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * SN码Controller
 * 
 * @author yinjinlu
 * @date 2022-12-08
 */
@RestController
@RequestMapping("/mes/wm/sn")
public class WmSnController extends BaseController
{
    @Autowired
    private IWmSnService wmSnService;

    @Autowired
    private AutoCodeUtil autoCodeUtil;

    /**
     * 查询SN码列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:sn:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmSn wmSn)
    {
        startPage();
        List<WmSn> list = wmSnService.selectWmSnList(wmSn);
        return getDataTable(list);
    }

    /**
     * 查询SN码列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:sn:list')")
    @GetMapping("/listSn")
    public TableDataInfo listSn(WmSn wmSn)
    {
        startPage();
        List<WmSn> list = wmSnService.selectSnList(wmSn);
        return getDataTable(list);
    }

    /**
     * 导出SN码列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:sn:export')")
    @Log(title = "SN码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmSn wmSn)
    {
        List<WmSn> list = wmSnService.selectWmSnList(wmSn);
        ExcelUtil<WmSn> util = new ExcelUtil<WmSn>(WmSn.class);
        util.exportExcel(response, list, "SN码数据");
    }

    /**
     * 获取SN码详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:sn:query')")
    @GetMapping(value = "/{snId}")
    public AjaxResult getInfo(@PathVariable("snId") Long snId)
    {
        return AjaxResult.success(wmSnService.selectWmSnBySnId(snId));
    }

    /**
     * 新增SN码
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:sn:add')")
    @Log(title = "SN码", businessType = BusinessType.INSERT)
    @Transactional
    @PostMapping
    public AjaxResult add(@RequestBody WmSn wmSn)
    {
        Date genDate = DateUtil.date();
        wmSn.setGenDate(genDate);
        String SNCode= null;
        if(wmSn.getSnNum()>0){
            for(int i=0;i<wmSn.getSnNum();i++){
                SNCode = autoCodeUtil.genSerialCode(UserConstants.SN_CODE,wmSn.getItemCode());
                wmSn.setSnCode(SNCode);
                wmSnService.insertWmSn(wmSn);
            }
        }
        return AjaxResult.success();
    }

    /**
     * 修改SN码
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:sn:edit')")
    @Log(title = "SN码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmSn wmSn)
    {
        return toAjax(wmSnService.updateWmSn(wmSn));
    }

    /**
     * 删除SN码
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:sn:remove')")
    @Log(title = "SN码", businessType = BusinessType.DELETE)
	@DeleteMapping("/{snIds}")
    public AjaxResult remove(@PathVariable Long[] snIds)
    {
        return toAjax(wmSnService.deleteWmSnBySnIds(snIds));
    }
}
