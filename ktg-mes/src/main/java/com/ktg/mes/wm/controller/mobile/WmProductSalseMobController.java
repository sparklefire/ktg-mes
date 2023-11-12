package com.ktg.mes.wm.controller.mobile;

import cn.hutool.core.collection.CollectionUtil;
import com.ktg.common.annotation.Log;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.StringUtils;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.mes.md.domain.MdClient;
import com.ktg.mes.md.service.IMdClientService;
import com.ktg.mes.wm.domain.*;
import com.ktg.mes.wm.domain.tx.ProductSalseTxBean;
import com.ktg.mes.wm.service.*;
import com.ktg.system.strategy.AutoCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api("产品销售出库")
@RestController
@RequestMapping("/mobile/wm/productsalse")
public class WmProductSalseMobController extends BaseController {
    @Autowired
    private IWmProductSalseService wmProductSalseService;

    @Autowired
    private IWmProductSalseLineService wmProductSalseLineService;

    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IWmStorageLocationService wmStorageLocationService;

    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

    @Autowired
    private IStorageCoreService storageCoreService;

    @Autowired
    private IMdClientService mdClientService;

    @Autowired
    private AutoCodeUtil autoCodeUtil;

    /**
     * 查询销售出库单列表
     */
    @ApiOperation("查询销售出库单清单接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:productsalse:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmProductSalse wmProductSalse)
    {
        startPage();
        List<WmProductSalse> list = wmProductSalseService.selectWmProductSalseList(wmProductSalse);
        return getDataTable(list);
    }


    /**
     * 获取销售出库单详细信息
     */
    @ApiOperation("获取销售出库单明细信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:productsalse:query')")
    @GetMapping(value = "/{salseId}")
    public AjaxResult getInfo(@PathVariable("salseId") Long salseId)
    {
        return AjaxResult.success(wmProductSalseService.selectWmProductSalseBySalseId(salseId));
    }

    /**
     * 新增销售出库单
     */
    @ApiOperation("新增销售出库单基本信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:productsalse:add')")
    @Log(title = "销售出库单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmProductSalse wmProductSalse)
    {
        if(StringUtils.isNotNull(wmProductSalse.getSalseCode())){
            if(UserConstants.NOT_UNIQUE.equals(wmProductSalseService.checkUnique(wmProductSalse))){
                return AjaxResult.error("出库单编号已存在！");
            }
        }else {
            wmProductSalse.setSalseCode(autoCodeUtil.genSerialCode(UserConstants.PRODUCTSALSE_CODE,""));
        }

        if(StringUtils.isNotNull(wmProductSalse.getClientId())){
            MdClient client = mdClientService.selectMdClientByClientId(wmProductSalse.getClientId());
            wmProductSalse.setClientCode(client.getClientCode());
            wmProductSalse.setClientName(client.getClientName());
            wmProductSalse.setClientNick(client.getClientNick());
        }

        wmProductSalse.setCreateBy(getUsername());
        wmProductSalseService.insertWmProductSalse(wmProductSalse);
        return AjaxResult.success(wmProductSalse);
    }

    /**
     * 修改销售出库单
     */
    @ApiOperation("编辑销售出库单基本信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:productsalse:edit')")
    @Log(title = "销售出库单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmProductSalse wmProductSalse)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmProductSalseService.checkUnique(wmProductSalse))){
            return AjaxResult.error("出库单编号已存在！");
        }

        if(StringUtils.isNotNull(wmProductSalse.getClientId())){
            MdClient client = mdClientService.selectMdClientByClientId(wmProductSalse.getClientId());
            wmProductSalse.setClientCode(client.getClientCode());
            wmProductSalse.setClientName(client.getClientName());
            wmProductSalse.setClientNick(client.getClientNick());
        }

        return toAjax(wmProductSalseService.updateWmProductSalse(wmProductSalse));
    }

    /**
     * 删除销售出库单
     */
    @ApiOperation("删除销售出库单信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:productsalse:remove')")
    @Log(title = "销售出库单", businessType = BusinessType.DELETE)
    @Transactional
    @DeleteMapping("/{salseIds}")
    public AjaxResult remove(@PathVariable Long[] salseIds)
    {
        for (Long salseId: salseIds
        ) {
            WmProductSalse salse = wmProductSalseService.selectWmProductSalseBySalseId(salseId);
            if(!UserConstants.ORDER_STATUS_PREPARE.equals(salse.getStatus())){
                return AjaxResult.error("只能删除草稿状态的单据!");
            }

            wmProductSalseLineService.deleteBySalseId(salseId);
        }
        return toAjax(wmProductSalseService.deleteWmProductSalseBySalseIds(salseIds));
    }

    /**
     * 执行出库
     * @return
     */
    @ApiOperation("执行销售出库接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:productsalse:edit')")
    @Log(title = "销售出库单", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping("/{salseId}")
    public AjaxResult execute(@PathVariable Long salseId){
        WmProductSalse salse = wmProductSalseService.selectWmProductSalseBySalseId(salseId);

        WmProductSalseLine param = new WmProductSalseLine();
        param.setSalseId(salseId);
        List<WmProductSalseLine> lines = wmProductSalseLineService.selectWmProductSalseLineList(param);
        if(CollectionUtil.isEmpty(lines)){
            return AjaxResult.error("出库物资不能为空");
        }

        List<ProductSalseTxBean> beans = wmProductSalseService.getTxBeans(salseId);
        storageCoreService.processProductSalse(beans);

        salse.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        wmProductSalseService.updateWmProductSalse(salse);
        return AjaxResult.success();
    }
}
