package com.ktg.mes.md.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
import com.ktg.mes.wm.utils.WmBarCodeUtil;
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
import com.ktg.mes.md.domain.MdClient;
import com.ktg.mes.md.service.IMdClientService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 客户Controller
 * 
 * @author yinjinlu
 * @date 2022-05-06
 */
@RestController
@RequestMapping("/mes/md/client")
public class MdClientController extends BaseController
{
    @Autowired
    private IMdClientService mdClientService;

    @Autowired
    private WmBarCodeUtil barCodeUtil;

    /**
     * 查询客户列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:client:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdClient mdClient)
    {
        startPage();
        List<MdClient> list = mdClientService.selectMdClientList(mdClient);
        return getDataTable(list);
    }

    /**
     * 导出客户列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:client:export')")
    @Log(title = "客户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdClient mdClient)
    {
        List<MdClient> list = mdClientService.selectMdClientList(mdClient);
        ExcelUtil<MdClient> util = new ExcelUtil<MdClient>(MdClient.class);
        util.exportExcel(response, list, "客户数据");
    }

    /**
     * 获取客户详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:client:query')")
    @GetMapping(value = "/{clientId}")
    public AjaxResult getInfo(@PathVariable("clientId") Long clientId)
    {
        return AjaxResult.success(mdClientService.selectMdClientByClientId(clientId));
    }

    /**
     * 新增客户
     */
    @PreAuthorize("@ss.hasPermi('mes:md:client:add')")
    @Log(title = "客户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdClient mdClient)
    {
        if(UserConstants.NOT_UNIQUE.equals(mdClientService.checkClientCodeUnique(mdClient))){
            return AjaxResult.error("客户编码已存在！");
        }

        if(UserConstants.NOT_UNIQUE.equals(mdClientService.checkClientNameUnique(mdClient))){
            return AjaxResult.error("客户名称已存在！");
        }

        if(UserConstants.NOT_UNIQUE.equals(mdClientService.checkClientNickUnique(mdClient))){
            return AjaxResult.error("客户简称已存在！");
        }

        mdClientService.insertMdClient(mdClient);
        barCodeUtil.generateBarCode(UserConstants.BARCODE_TYPE_CLIENT,mdClient.getClientId(),mdClient.getClientCode(),mdClient.getClientName());

        return AjaxResult.success(mdClient.getClientId());
    }

    /**
     * 修改客户
     */
    @PreAuthorize("@ss.hasPermi('mes:md:client:edit')")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdClient mdClient)
    {
        if(UserConstants.NOT_UNIQUE.equals(mdClientService.checkClientCodeUnique(mdClient))){
            return AjaxResult.error("客户编码已存在！");
        }

        if(UserConstants.NOT_UNIQUE.equals(mdClientService.checkClientNameUnique(mdClient))){
            return AjaxResult.error("客户名称已存在！");
        }

        if(UserConstants.NOT_UNIQUE.equals(mdClientService.checkClientNickUnique(mdClient))){
            return AjaxResult.error("客户简称已存在！");
        }
        return toAjax(mdClientService.updateMdClient(mdClient));
    }

    /**
     * 删除客户
     */
    @PreAuthorize("@ss.hasPermi('mes:md:client:remove')")
    @Log(title = "客户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{clientIds}")
    public AjaxResult remove(@PathVariable Long[] clientIds)
    {
        return toAjax(mdClientService.deleteMdClientByClientIds(clientIds));
    }
}
