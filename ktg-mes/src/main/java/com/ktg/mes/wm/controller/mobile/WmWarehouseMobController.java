package com.ktg.mes.wm.controller.mobile;

import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.mes.wm.service.IWmWarehouseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("仓库存储位置信息")
@RestController
@RequestMapping("/mobile/wm/warehouse")
public class WmWarehouseMobController extends BaseController {

    @Autowired
    private IWmWarehouseService wmWarehouseService;

    /**
     * 查询树型的列表
     * @return
     */
    @GetMapping("/getTreeList")
    public AjaxResult getTreeList(){
        return AjaxResult.success(wmWarehouseService.getTreeList());
    }
}
