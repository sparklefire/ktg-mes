package com.ktg.mes.wm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.wm.domain.WmBarcode;
import com.ktg.mes.wm.service.IWmBarcodeService;
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
import com.ktg.mes.wm.domain.WmPackage;
import com.ktg.mes.wm.service.IWmPackageService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 装箱单Controller
 * 
 * @author yinjinlu
 * @date 2022-10-10
 */
@RestController
@RequestMapping("/mes/wm/package")
public class WmPackageController extends BaseController
{
    @Autowired
    private IWmPackageService wmPackageService;

    @Autowired
    private IWmBarcodeService wmBarcodeService;

    /**
     * 查询装箱单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:package:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmPackage wmPackage)
    {
        startPage();
        List<WmPackage> list = wmPackageService.selectWmPackageList(wmPackage);
        return getDataTable(list);
    }

    /**
     * 导出装箱单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:package:export')")
    @Log(title = "装箱单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmPackage wmPackage)
    {
        List<WmPackage> list = wmPackageService.selectWmPackageList(wmPackage);
        ExcelUtil<WmPackage> util = new ExcelUtil<WmPackage>(WmPackage.class);
        util.exportExcel(response, list, "装箱单数据");
    }

    /**
     * 获取装箱单详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:package:query')")
    @GetMapping(value = "/{packageId}")
    public AjaxResult getInfo(@PathVariable("packageId") Long packageId)
    {
        return AjaxResult.success(wmPackageService.selectWmPackageByPackageId(packageId));
    }

    /**
     * 新增装箱单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:package:add')")
    @Log(title = "装箱单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmPackage wmPackage)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmPackageService.checkPackgeCodeUnique(wmPackage))){
            return AjaxResult.error("装箱单编号已存在!");
        }
        if(wmPackage.getParentId() !=null){
            WmPackage parentPackage = wmPackageService.selectWmPackageByPackageId(wmPackage.getParentId());
            if(StringUtils.isNotNull(parentPackage)){
                wmPackage.setAncestors(parentPackage.getAncestors()+","+parentPackage.getPackageId());
            }
        }

        int ret =wmPackageService.insertWmPackage(wmPackage);

        //装箱单保存成功就自动生成对应的箱条码
        WmBarcode wmBarcode = new WmBarcode();
        wmBarcode.setBussinessId(wmPackage.getPackageId());
        wmBarcode.setBussinessCode(wmPackage.getPackageCode());
        wmBarcode.setBussinessName(wmPackage.getClientName());
        wmBarcode.setBarcodeType(UserConstants.BARCODE_TYPE_PACKAGE);//类型设置为箱条码
        wmBarcode.setBarcodeFormart(UserConstants.QR_CODE);//设置为二维码
        wmBarcode.setBarcodeContent(""+UserConstants.BARCODE_TYPE_PACKAGE+"-"+wmPackage.getPackageCode());
        String path =wmBarcodeService.generateBarcode(wmBarcode);
        wmBarcode.setBarcodeUrl(path);
        wmBarcode.setCreateBy(getUsername());
        wmBarcodeService.insertWmBarcode(wmBarcode);

        //将条码的URL更新上去
        wmPackage.setBarcodeId(wmBarcode.getBarcodeId());
        wmPackage.setBarcodeContent(wmBarcode.getBarcodeContent());
        wmPackage.setBarcodeUrl(path);
        wmPackageService.updateWmPackage(wmPackage);
        return toAjax(ret);
    }

    /**
     * 修改装箱单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:package:edit')")
    @Log(title = "装箱单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmPackage wmPackage)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmPackageService.checkPackgeCodeUnique(wmPackage))){
            return AjaxResult.error("装箱单编号已存在!");
        }
        return toAjax(wmPackageService.updateWmPackage(wmPackage));
    }

    /**
     * 添加子箱
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:package:edit')")
    @Log(title = "装箱单", businessType = BusinessType.UPDATE)
    @PutMapping("/addsub")
    public AjaxResult addSubPackage(@RequestBody WmPackage wmPackage){
        //不能添加自己
        if(wmPackage.getPackageId().longValue() == wmPackage.getParentId().longValue()){
            return AjaxResult.error("不能添加自己为子箱！");
        }

        //已经有父箱的不能再次添加
        WmPackage subPackage = wmPackageService.selectWmPackageByPackageId(wmPackage.getPackageId());
        if(!"0".equals(subPackage.getAncestors())){
            return AjaxResult.error("当前子箱已经有外箱包装！");
        }

        //更新当前子箱的父箱列表
        WmPackage parentPackage = wmPackageService.selectWmPackageByPackageId(wmPackage.getParentId());
        if(StringUtils.isNotNull(parentPackage)){
            wmPackage.setAncestors(parentPackage.getAncestors()+","+parentPackage.getPackageId());
        }

        return toAjax(wmPackageService.updateWmPackage(wmPackage));
    }


    /**
     * 删除装箱单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:package:remove')")
    @Log(title = "装箱单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{packageIds}")
    public AjaxResult remove(@PathVariable Long[] packageIds)
    {
        return toAjax(wmPackageService.deleteWmPackageByPackageIds(packageIds));
    }
}
