package com.ktg.mes.md.controller;

import com.ktg.mes.aspect.BarcodeGen;
import com.ktg.mes.md.service.IMdItemService;
import com.ktg.common.annotation.Log;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.domain.entity.ItemType;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.md.domain.MdItem;
import com.ktg.mes.md.service.IItemTypeService;
import com.ktg.mes.wm.utils.WmBarCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mes/md/mditem")
public class MdItemController extends BaseController {

    @Autowired
    private IMdItemService mdItemService;

    @Autowired
    private IItemTypeService iItemTypeService;

    @Autowired
    private WmBarCodeUtil barcodeUtil;

    /**
     * 列表查询
     * @param mdItem
     * @return
     */
    @PreAuthorize("@ss.hasPermi('mes:md:mditem:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdItem mdItem){
        startPage();
        List<MdItem> list = mdItemService.selectMdItemList(mdItem);
        return getDataTable(list);
    }

    /**
     * 主键查询
     * @param itemId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('mes:md:mditem:query')")
    @GetMapping(value = "/{itemId}")
    public AjaxResult getInfo(@PathVariable Long itemId){
        return AjaxResult.success(mdItemService.selectMdItemById(itemId));
    }

    /**
     * 新增
     * @param mdItem
     * @return
     */
    @PreAuthorize("@ss.hasPermi('mes:md:mditem:add')")
    @Log(title = "物料管理",businessType = BusinessType.INSERT)
    @BarcodeGen(barcodeType = UserConstants.BARCODE_TYPE_ITEM)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody MdItem mdItem){
        if(UserConstants.NOT_UNIQUE.equals(mdItemService.checkItemCodeUnique(mdItem))){
            return AjaxResult.error("新增物料"+mdItem.getItemCode()+"失败，物料编码已存在");
        }
        if(UserConstants.NOT_UNIQUE.equals(mdItemService.checkItemNameUnique(mdItem))){
            return AjaxResult.error("新增物料"+mdItem.getItemCode()+"失败，物料名称已存在");
        }

        ItemType type =iItemTypeService.selectItemTypeById(mdItem.getItemTypeId());
        if(StringUtils.isNotNull(type)){
            mdItem.setItemTypeCode(type.getItemTypeCode());
            mdItem.setItemTypeName(type.getItemTypeName());
            mdItem.setItemOrProduct(type.getItemOrProduct());
        }
        mdItem.setCreateBy(getUsername());
        mdItemService.insertMdItem(mdItem);
        barcodeUtil.generateBarCode(UserConstants.BARCODE_TYPE_ITEM,mdItem.getItemId(),mdItem.getItemCode(), mdItem.getItemName());
        return AjaxResult.success(mdItem.getItemId());
    }

    /**
     * 更新
     * @param mdItem
     * @return
     */
    @PreAuthorize("@ss.hasPermi('mes:md:mditem:edit')")
    @Log(title = "物料管理",businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody MdItem mdItem){
        if(UserConstants.NOT_UNIQUE.equals(mdItemService.checkItemCodeUnique(mdItem))){
            return AjaxResult.error("新增物料"+mdItem.getItemCode()+"失败，物料编码已存在");
        }
        if(UserConstants.NOT_UNIQUE.equals(mdItemService.checkItemNameUnique(mdItem))){
            return AjaxResult.error("新增物料"+mdItem.getItemCode()+"失败，物料名称已存在");
        }
        ItemType type =iItemTypeService.selectItemTypeById(mdItem.getItemTypeId());
        if(StringUtils.isNotNull(type)){
            mdItem.setItemTypeCode(type.getItemTypeCode());
            mdItem.setItemTypeName(type.getItemTypeName());
            mdItem.setItemOrProduct(type.getItemOrProduct());
        }
        if(StringUtils.isNotNull(mdItem.getSafeStockFlag())&& "N".equals(mdItem.getSafeStockFlag())){
            mdItem.setMinStock(0D);
            mdItem.setMaxStock(0D);
        }

        mdItem.setUpdateBy(getUsername());
        return toAjax(mdItemService.updateMdItem(mdItem));
    }

    @PreAuthorize("@ss.hasPermi('mes:md:mditem:remove')")
    @Log(title = "物料管理",businessType = BusinessType.DELETE)
    @DeleteMapping("/{itemIds}")
    public AjaxResult remove(@PathVariable Long[] itemIds){
        return toAjax(mdItemService.deleteByItemIds(itemIds));
    }


}
