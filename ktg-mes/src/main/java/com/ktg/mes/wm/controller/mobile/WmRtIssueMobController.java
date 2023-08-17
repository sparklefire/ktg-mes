package com.ktg.mes.wm.controller.mobile;

import cn.hutool.core.collection.CollUtil;
import com.ktg.common.annotation.Log;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.StringUtils;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.mes.pro.domain.ProWorkorder;
import com.ktg.mes.pro.service.IProWorkorderService;
import com.ktg.mes.wm.domain.*;
import com.ktg.mes.wm.domain.tx.RtIssueTxBean;
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

@Api("生产退料")
@RestController
@RequestMapping("/mobile/wm/rtissue")
public class WmRtIssueMobController extends BaseController {

    @Autowired
    private IWmRtIssueService wmRtIssueService;

    @Autowired
    private IWmRtIssueLineService wmRtIssueLineService;

    @Autowired
    private IStorageCoreService storageCoreService;

    @Autowired
    private IProWorkorderService proWorkorderService;

    @Autowired
    private AutoCodeUtil autoCodeUtil;

    /**
     * 查询生产退料单头列表
     */
    @ApiOperation("查询生产退料单清单接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmRtIssue wmRtIssue)
    {
        startPage();
        List<WmRtIssue> list = wmRtIssueService.selectWmRtIssueList(wmRtIssue);
        return getDataTable(list);
    }


    /**
     * 获取生产退料单头详细信息
     */
    @ApiOperation("获取生产退料单详情接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:query')")
    @GetMapping(value = "/{rtId}")
    public AjaxResult getInfo(@PathVariable("rtId") Long rtId)
    {
        return AjaxResult.success(wmRtIssueService.selectWmRtIssueByRtId(rtId));
    }

    /**
     * 新增生产退料单头
     */
    @ApiOperation("新增生产退料单基本信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:add')")
    @Log(title = "生产退料单头", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmRtIssue wmRtIssue)
    {
        if(StringUtils.isNotNull(wmRtIssue.getRtCode())){
            if(UserConstants.NOT_UNIQUE.equals(wmRtIssueService.checkUnique(wmRtIssue))){
                return AjaxResult.error("退料单编号已存在");
            }
        }else {
            wmRtIssue.setRtCode(autoCodeUtil.genSerialCode(UserConstants.RTISSUE_CODE,""));
        }


        if(StringUtils.isNotNull(wmRtIssue.getWorkorderId())){
            ProWorkorder workorder = proWorkorderService.selectProWorkorderByWorkorderId(wmRtIssue.getWorkorderId());
            wmRtIssue.setWorkorderCode(workorder.getWorkorderCode());
        }

        wmRtIssue.setCreateBy(getUsername());
        wmRtIssueService.insertWmRtIssue(wmRtIssue);
        return AjaxResult.success(wmRtIssue);
    }

    /**
     * 修改生产退料单头
     */
    @ApiOperation("修改生产退料单基本信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:edit')")
    @Log(title = "生产退料单头", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmRtIssue wmRtIssue)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmRtIssueService.checkUnique(wmRtIssue))){
            return AjaxResult.error("退料单编号已存在");
        }

        if(StringUtils.isNotNull(wmRtIssue.getWorkorderId())){
            ProWorkorder workorder = proWorkorderService.selectProWorkorderByWorkorderId(wmRtIssue.getWorkorderId());
            wmRtIssue.setWorkorderCode(workorder.getWorkorderCode());
        }

        return toAjax(wmRtIssueService.updateWmRtIssue(wmRtIssue));
    }

    /**
     * 删除生产退料单头
     */
    @ApiOperation("删除生产退料单接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:remove')")
    @Log(title = "生产退料单头", businessType = BusinessType.DELETE)
    @Transactional
    @DeleteMapping("/{rtIds}")
    public AjaxResult remove(@PathVariable Long[] rtIds)
    {
        for (Long rtId: rtIds
        ) {
            WmRtIssue header = wmRtIssueService.selectWmRtIssueByRtId(rtId);
            if(!UserConstants.ORDER_STATUS_PREPARE.equals(header.getStatus())){
                return AjaxResult.error("只能删除草稿状态的单据!");
            }

            wmRtIssueLineService.deleteByRtId(rtId);
        }
        return toAjax(wmRtIssueService.deleteWmRtIssueByRtIds(rtIds));
    }

    /**
     * 执行退料
     * @param rtId
     * @return
     */
    @ApiOperation("执行生产退料接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:edit')")
    @Log(title = "生产退料单头", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping("/{rtId}")
    public AjaxResult execute(@PathVariable Long rtId){
        WmRtIssue rtIssue = wmRtIssueService.selectWmRtIssueByRtId(rtId);

        //单据有效性
        if(!StringUtils.isNotNull(rtIssue)){
            return AjaxResult.error("无效单据");
        }

        //先检查单据状态
        if(UserConstants.ORDER_STATUS_FINISHED.equals(rtIssue.getStatus())){
            return AjaxResult.error("当前单据已提交!");
        }

        //检查行信息
        WmRtIssueLine param = new WmRtIssueLine();
        param.setRtId(rtId);
        List<WmRtIssueLine> lines = wmRtIssueLineService.selectWmRtIssueLineList(param);
        if(CollUtil.isEmpty(lines)){
            return AjaxResult.error("请选择要退料的物资");
        }

        List<RtIssueTxBean> beans = wmRtIssueService.getTxBeans(rtId);

        //执行生产退料
        storageCoreService.processRtIssue(beans);


        rtIssue.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        wmRtIssueService.updateWmRtIssue(rtIssue);
        return AjaxResult.success();
    }

}
