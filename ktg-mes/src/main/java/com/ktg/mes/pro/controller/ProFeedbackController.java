package com.ktg.mes.pro.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollectionUtil;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.md.domain.MdWorkstation;
import com.ktg.mes.md.service.IMdWorkstationService;
import com.ktg.mes.pro.domain.*;
import com.ktg.mes.pro.service.IProRouteProcessService;
import com.ktg.mes.pro.service.IProTaskService;
import com.ktg.mes.pro.service.IProWorkorderService;
import com.ktg.mes.wm.domain.WmItemConsume;
import com.ktg.mes.wm.domain.WmProductProduce;
import com.ktg.mes.wm.domain.tx.ItemConsumeTxBean;
import com.ktg.mes.wm.domain.tx.ProductProductTxBean;
import com.ktg.mes.wm.service.IStorageCoreService;
import com.ktg.mes.wm.service.IWmItemConsumeService;
import com.ktg.mes.wm.service.IWmProductProduceService;
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
import com.ktg.mes.pro.service.IProFeedbackService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 生产报工记录Controller
 * 
 * @author yinjinlu
 * @date 2022-07-10
 */
@RestController
@RequestMapping("/mes/pro/feedback")
public class ProFeedbackController extends BaseController
{
    @Autowired
    private IProFeedbackService proFeedbackService;

    @Autowired
    private IProTaskService proTaskService;

    @Autowired
    private IProRouteProcessService proRouteProcessService;

    @Autowired
    private IProWorkorderService proWorkorderService;

    @Autowired
    private IMdWorkstationService mdWorkstationService;

    @Autowired
    private IWmItemConsumeService wmItemConsumeService;

    @Autowired
    private IWmProductProduceService wmProductProduceService;

    @Autowired
    private IStorageCoreService storageCoreService;

    /**
     * 查询生产报工记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProFeedback proFeedback)
    {
        startPage();
        List<ProFeedback> list = proFeedbackService.selectProFeedbackList(proFeedback);
        return getDataTable(list);
    }

    /**
     * 导出生产报工记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:export')")
    @Log(title = "生产报工记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProFeedback proFeedback)
    {
        List<ProFeedback> list = proFeedbackService.selectProFeedbackList(proFeedback);
        ExcelUtil<ProFeedback> util = new ExcelUtil<ProFeedback>(ProFeedback.class);
        util.exportExcel(response, list, "生产报工记录数据");
    }

    /**
     * 获取生产报工记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(proFeedbackService.selectProFeedbackByRecordId(recordId));
    }

    /**
     * 新增生产报工记录
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:add')")
    @Log(title = "生产报工记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProFeedback proFeedback)
    {
        MdWorkstation workstation = mdWorkstationService.selectMdWorkstationByWorkstationId(proFeedback.getWorkstationId());
        if(StringUtils.isNotNull(workstation)){
            proFeedback.setProcessId(workstation.getProcessId());
            proFeedback.setProcessCode(workstation.getProcessCode());
            proFeedback.setProcessName(workstation.getProcessName());
        }else {
            return AjaxResult.error("当前生产任务对应的工作站不存在！");
        }
        //检查对应的工艺路线和工序配置
        if(StringUtils.isNotNull(proFeedback.getRouteId())&& StringUtils.isNotNull(proFeedback.getProcessId())){
            ProRouteProcess param = new ProRouteProcess();
            param.setRouteId(proFeedback.getRouteId());
            param.setProcessId(proFeedback.getProcessId());
            List<ProRouteProcess> processes = proRouteProcessService.selectProRouteProcessList(param);
            if(CollectionUtil.isEmpty(processes)){
                return AjaxResult.error("未找到生产任务对应的工艺工序配置！");
            }
        }else {
            return AjaxResult.error("当前生产任务对应的工艺工序配置无效！");
        }

        //检查数量
        if(UserConstants.YES.equals(proFeedback.getIsCheck())){
            if(!StringUtils.isNotNull(proFeedback.getQuantityUncheck())){
                return AjaxResult.error("请输入待检测数量!");
            }
        }else {
            if(!StringUtils.isNotNull(proFeedback.getQuantityQualified()) || !StringUtils.isNotNull(proFeedback.getQuantityUnquanlified())){
                return AjaxResult.error("请输入合格品和不良品数量！");
            }
        }

        proFeedback.setCreateBy(getUsername());
        return toAjax(proFeedbackService.insertProFeedback(proFeedback));
    }

    /**
     * 修改生产报工记录
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:edit')")
    @Log(title = "生产报工记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProFeedback proFeedback)
    {
        MdWorkstation workstation = mdWorkstationService.selectMdWorkstationByWorkstationId(proFeedback.getWorkstationId());
        if(StringUtils.isNotNull(workstation)){
            proFeedback.setProcessId(workstation.getProcessId());
            proFeedback.setProcessCode(workstation.getProcessCode());
            proFeedback.setProcessName(workstation.getProcessName());
        }else {
            return AjaxResult.error("当前生产任务对应的工作站不存在！");
        }

        //检查对应的工艺路线和工序配置
        if(StringUtils.isNotNull(proFeedback.getRouteId())&& StringUtils.isNotNull(proFeedback.getProcessId())){
            ProRouteProcess param = new ProRouteProcess();
            param.setRouteId(proFeedback.getRouteId());
            param.setProcessId(proFeedback.getProcessId());
            List<ProRouteProcess> processes = proRouteProcessService.selectProRouteProcessList(param);
            if(CollectionUtil.isEmpty(processes)){
                return AjaxResult.error("未找到生产任务对应的工艺工序配置！");
            }
        }else {
            return AjaxResult.error("当前生产任务对应的工艺工序配置无效！");
        }

        //检查数量
        if(UserConstants.YES.equals(proFeedback.getIsCheck())){
            if(!StringUtils.isNotNull(proFeedback.getQuantityUncheck())){
                return AjaxResult.error("当前工作站报工需要经过质检确认，请输入待检测数量!");
            }
        }else {
            if(!StringUtils.isNotNull(proFeedback.getQuantityQualified()) || !StringUtils.isNotNull(proFeedback.getQuantityUnquanlified())){
                return AjaxResult.error("请输入合格品和不良品数量！");
            }
        }
        return toAjax(proFeedbackService.updateProFeedback(proFeedback));
    }

    /**
     * 删除生产报工记录
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:remove')")
    @Log(title = "生产报工记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(proFeedbackService.deleteProFeedbackByRecordIds(recordIds));
    }

    /**
     * 执行报工
     * 1.更新生产任务和生产工单的进度
     * 2.物料消耗
     * 3.产品产出
     * @param recordId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:edit')")
    @Log(title = "生产报工执行", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping("/{recordId}")
    public AjaxResult execute(@PathVariable("recordId") Long recordId){

        if(!StringUtils.isNotNull(recordId)){
            return AjaxResult.error("请先保存单据");
        }

        ProFeedback feedback= proFeedbackService.selectProFeedbackByRecordId(recordId);
        if(feedback.getQuantityFeedback().compareTo(BigDecimal.ZERO) !=1){
            return AjaxResult.error("报工数量必须大于0");
        }

        ProWorkorder workorder = proWorkorderService.selectProWorkorderByWorkorderId(feedback.getWorkorderId());

        ProTask task = proTaskService.selectProTaskByTaskId(feedback.getTaskId());

        //判断当前生产任务的状态，如果已经完成则不能再报工
        if(UserConstants.ORDER_STATUS_FINISHED.equals(task.getStatus())){
            return AjaxResult.error("当前生产工单的状态为已完成，不能继续报工，请刷新生产任务列表！");
        }

        //仍旧有待检数量时不能执行
        if(StringUtils.isNotNull(feedback.getQuantityUncheck()) && feedback.getQuantityUncheck().compareTo(BigDecimal.ZERO) >0){
            return  AjaxResult.error("当前报工单未完成检验（待检数量大于0），无法执行报工！");
        }

        //更新生产任务的生产数量
        BigDecimal quantityProduced,quantityQuanlify,quantityUnquanlify;
        quantityQuanlify = task.getQuantityQuanlify()==null? new BigDecimal(0):task.getQuantityQuanlify();
        quantityUnquanlify = task.getQuantityUnquanlify() ==null? new BigDecimal(0):task.getQuantityUnquanlify();
        quantityProduced = task.getQuantityProduced()==null? new BigDecimal(0):task.getQuantityProduced();
        task.setQuantityProduced(quantityProduced.add(feedback.getQuantityFeedback()));
        task.setQuantityQuanlify(quantityQuanlify.add(feedback.getQuantityQualified()));
        task.setQuantityUnquanlify(quantityUnquanlify.add(feedback.getQuantityUnquanlified()));

        proTaskService.updateProTask(task);

        //如果是关键工序，则更新当前工单的已生产数量，进行产品产出动作
        if(proRouteProcessService.checkKeyProcess(feedback)){
            //更新生产工单的生产数量
            BigDecimal produced = workorder.getQuantityProduced() == null?new BigDecimal(0):workorder.getQuantityProduced();
            BigDecimal feedBackQuantity = feedback.getQuantityFeedback() ==null?new BigDecimal(0):feedback.getQuantityFeedback();
            workorder.setQuantityProduced( produced.add(feedBackQuantity));
            proWorkorderService.updateProWorkorder(workorder);

            //生成产品产出记录单
            WmProductProduce productRecord = wmProductProduceService.generateProductProduce(feedback);
            //执行产品产出入线边库
            executeProductProduce(productRecord);
        }

        //根据当前工序的物料BOM配置，进行物料消耗
        //先生成消耗单
        WmItemConsume itemConsume = wmItemConsumeService.generateItemConsume(feedback);
        if(StringUtils.isNotNull(itemConsume)){
            //再执行库存消耗动作
            executeItemConsume(itemConsume);
        }

        //更新报工单的状态
        feedback.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        proFeedbackService.updateProFeedback(feedback);
        return AjaxResult.success();
    }

    /**
     * 执行产品产出入线边库动作
     * @param record
     */
    private void executeProductProduce(WmProductProduce record){
        List<ProductProductTxBean> beans = wmProductProduceService.getTxBeans(record.getRecordId());
        storageCoreService.processProductProduce(beans);
        record.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        wmProductProduceService.updateWmProductProduce(record);
    }

    /**
     * 执行物料消耗库存动作
     * @param record
     */
    private void executeItemConsume(WmItemConsume record){
        //需要在此处进行分批次领料的线边库扣减
        List<ItemConsumeTxBean> beans = wmItemConsumeService.getTxBeans(record.getRecordId());
        storageCoreService.processItemConsume(beans);
        record.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        wmItemConsumeService.updateWmItemConsume(record);
    }

}
