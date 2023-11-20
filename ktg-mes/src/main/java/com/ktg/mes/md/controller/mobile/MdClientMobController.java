package com.ktg.mes.md.controller.mobile;

import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.mes.md.domain.MdClient;
import com.ktg.mes.md.service.IMdClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("客户信息")
@RestController
@RequestMapping("/mobile/md/client")
public class MdClientMobController extends BaseController {
    @Autowired
    private IMdClientService mdClientService;

    /**
     * 查询客户列表
     */
    @ApiOperation("查询客户清单（分页）")
    @GetMapping("/list")
    public TableDataInfo list(MdClient mdClient)
    {
        startPage();
        List<MdClient> list = mdClientService.selectMdClientList(mdClient);
        return getDataTable(list);
    }

}
