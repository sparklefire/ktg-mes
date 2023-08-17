package com.ktg.mes.wm.controller.mobile;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.ktg.common.annotation.Log;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.pro.domain.ProWorkorder;
import com.ktg.mes.pro.service.IProWorkorderService;
import com.ktg.mes.wm.domain.*;
import com.ktg.mes.wm.domain.tx.IssueTxBean;
import com.ktg.mes.wm.service.*;
import com.ktg.system.strategy.AutoCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("生产领料")
@RestController
@RequestMapping("/mobile/wm/issueheader")
public class WmIssueMobController extends BaseController {
    @Autowired
    private IWmIssueHeaderService wmIssueHeaderService;

    @Autowired
    private IWmIssueLineService wmIssueLineService;

    @Autowired
    private IStorageCoreService storageCoreService;

    @Autowired
    private IProWorkorderService proWorkorderService;

    @Autowired
    private AutoCodeUtil autoCodeUtil;

    /**
     * 查询生产领料单头列表
     */
    @ApiOperation("查询生产领料单清单接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:issueheader:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmIssueHeader wmIssueHeader)
    {
        startPage();
        List<WmIssueHeader> list = wmIssueHeaderService.selectWmIssueHeaderList(wmIssueHeader);
        return getDataTable(list);
    }



    /**
     * 获取生产领料单头详细信息
     */
    @ApiOperation("获取生产领料单详情信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:issueheader:query')")
    @GetMapping(value = "/{issueId}")
    public AjaxResult getInfo(@PathVariable("issueId") Long issueId)
    {
        return AjaxResult.success(wmIssueHeaderService.selectWmIssueHeaderByIssueId(issueId));
    }

    /**
     * 新增生产领料单头
     */
    @ApiOperation("新增生产领料单基本信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:issueheader:add')")
    @Log(title = "生产领料单头", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmIssueHeader wmIssueHeader)
    {
        if(StringUtils.isNotNull(wmIssueHeader.getIssueCode())){
            if(UserConstants.NOT_UNIQUE.equals(wmIssueHeaderService.checkIssueCodeUnique(wmIssueHeader))){
                return  AjaxResult.error("单据编号已存在！");
            }
        }else {
            wmIssueHeader.setIssueCode(autoCodeUtil.genSerialCode(UserConstants.ISSUE_CODE,""));
        }

        //根据领料单上的生产工单初始化客户信息
        if(StringUtils.isNotNull(wmIssueHeader.getWorkorderId())){
            ProWorkorder workorder = proWorkorderService.selectProWorkorderByWorkorderId(wmIssueHeader.getWorkorderId());
            wmIssueHeader.setClientId(workorder.getClientId());
            wmIssueHeader.setClientCode(workorder.getClientCode());
            wmIssueHeader.setClientName(workorder.getClientName());
        }

        wmIssueHeader.setCreateBy(getUsername());
        wmIssueHeaderService.insertWmIssueHeader(wmIssueHeader);
        return AjaxResult.success(wmIssueHeader);
    }

    /**
     * 修改生产领料单头
     */
    @ApiOperation("修改生产领料单基本信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:issueheader:edit')")
    @Log(title = "生产领料单头", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmIssueHeader wmIssueHeader)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmIssueHeaderService.checkIssueCodeUnique(wmIssueHeader))){
            return AjaxResult.error("领料单编号已存在");
        }

        //根据领料单上的生产工单初始化客户信息
        if(StringUtils.isNotNull(wmIssueHeader.getWorkorderId())){
            ProWorkorder workorder = proWorkorderService.selectProWorkorderByWorkorderId(wmIssueHeader.getWorkorderId());
            wmIssueHeader.setClientId(workorder.getClientId());
            wmIssueHeader.setClientCode(workorder.getClientCode());
            wmIssueHeader.setClientName(workorder.getClientName());
        }

        return toAjax(wmIssueHeaderService.updateWmIssueHeader(wmIssueHeader));
    }

    /**
     * 删除生产领料单头
     */
    @ApiOperation("删除生产领料单信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:issueheader:remove')")
    @Log(title = "生产领料单头", businessType = BusinessType.DELETE)
    @Transactional
    @DeleteMapping("/{issueIds}")
    public AjaxResult remove(@PathVariable Long[] issueIds)
    {
        for (long issueId:issueIds
        ) {
            WmIssueHeader header = wmIssueHeaderService.selectWmIssueHeaderByIssueId(issueId);
            if(!UserConstants.ORDER_STATUS_PREPARE.equals(header.getStatus())){
                return AjaxResult.error("只能删除草稿状态的单据!");
            }

            wmIssueLineService.deleteByIssueHeaderId(issueId);
        }

        return toAjax(wmIssueHeaderService.deleteWmIssueHeaderByIssueIds(issueIds));
    }

    /**
     * 执行出库
     * @return
     */
    @ApiOperation("执行生产领出接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:issueheader:edit')")
    @Log(title = "生产领料单头", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping("/{issueId}")
    public AjaxResult execute(@PathVariable Long issueId){

        WmIssueHeader header = wmIssueHeaderService.selectWmIssueHeaderByIssueId(issueId);

        //单据有效性
        if(!StringUtils.isNotNull(header)){
            return AjaxResult.error("无效单据");
        }

        //先检查单据状态
        if(UserConstants.ORDER_STATUS_FINISHED.equals(header.getStatus())){
            return AjaxResult.error("当前单据已提交!");
        }

        //检查行数量
        WmIssueLine param = new WmIssueLine();
        param.setIssueId(issueId);
        List<WmIssueLine> lines = wmIssueLineService.selectWmIssueLineList(param);
        if(CollUtil.isEmpty(lines)){
            return AjaxResult.error("请指定领出的物资");
        }

        List<IssueTxBean> beans = wmIssueHeaderService.getTxBeans(issueId);

        //调用库存核心
        storageCoreService.processIssue(beans);

        //更新单据状态
        header.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        wmIssueHeaderService.updateWmIssueHeader(header);
        return AjaxResult.success();
    }
}
