package com.ktg.mes.md.controller.mobile;

import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.mes.md.domain.MdVendor;
import com.ktg.mes.md.service.IMdVendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("供应商信息")
@RestController
@RequestMapping("/mobile/md/vendor")
public class MdVendorMobController extends BaseController {

    @Autowired
    private IMdVendorService mdVendorService;

    /**
     * 查询供应商列表
     */
    @ApiOperation("查询供应商清单（分页）")
    @PreAuthorize("@ss.hasPermi('mes:md:vendor:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdVendor mdVendor)
    {
        startPage();
        List<MdVendor> list = mdVendorService.selectMdVendorList(mdVendor);
        return getDataTable(list);
    }
}
