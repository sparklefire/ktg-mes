package com.ktg.mes.md.service.impl;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.domain.TreeSelect;
import com.ktg.common.core.domain.entity.ItemType;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.md.mapper.ItemTypeMapper;
import com.ktg.mes.md.service.IItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemTypeServiceImpl implements IItemTypeService {

    @Autowired
    private ItemTypeMapper itemTypeMapper;

    @Override
    public List<ItemType> selectItemTypeList(ItemType itemType) {
        return itemTypeMapper.selectItemTypeList(itemType);
    }

    @Override
    public ItemType selectItemTypeById(Long itemTypeId) {
        return itemTypeMapper.selectItemTypeById(itemTypeId);
    }

    @Override
    public List<TreeSelect> buildTreeSelect(List<ItemType> list) {
        List<ItemType> itemTypes = buildTree(list);
        return itemTypes.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    private List<ItemType> buildTree(List<ItemType> itemTypes){
        List<ItemType> returnList = new ArrayList<ItemType>();
        List<Long> tempList = new ArrayList<Long>();
        for(ItemType it : itemTypes){
            tempList.add(it.getItemTypeId());
        }

        for(ItemType it : itemTypes){
            if(!tempList.contains(it.getParentTypeId())){
                recursionFn(itemTypes,it);
                returnList.add(it);
            }
        }
        if(returnList.isEmpty()){
            returnList = itemTypes;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<ItemType> list, ItemType t)
    {
        // 得到子节点列表
        List<ItemType> childList = getChildList(list, t);
        t.setChildren(childList);
        for (ItemType tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<ItemType> getChildList(List<ItemType> list, ItemType t)
    {
        List<ItemType> tlist = new ArrayList<ItemType>();
        Iterator<ItemType> it = list.iterator();
        while (it.hasNext())
        {
            ItemType n = (ItemType) it.next();
            if (StringUtils.isNotNull(n.getParentTypeId()) && n.getParentTypeId().longValue() == t.getItemTypeId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<ItemType> list, ItemType t)
    {
        return getChildList(list, t).size() > 0;
    }


    @Override
    public String checkItemTypeCodeUnique(ItemType itemType) {
        ItemType itemType1 =itemTypeMapper.checkItemTypeCodeUnique(itemType.getItemTypeCode(),itemType.getParentTypeId());
        Long itemTypeId = itemType.getItemTypeId() ==null? -1L:itemType.getItemTypeId();
        if(StringUtils.isNotNull(itemType1)&& itemTypeId.longValue() != itemType1.getItemTypeId().longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkItemTypeNameUnique(ItemType itemType) {
        ItemType itemType1 =itemTypeMapper.checkItemTypeNameUnique(itemType.getItemTypeName(),itemType.getParentTypeId());
        Long itemTypeId = itemType.getItemTypeId() ==null? -1L:itemType.getItemTypeId();
        if(StringUtils.isNotNull(itemType1)&& itemTypeId.longValue() != itemType1.getItemTypeId().longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public Integer insertItemType(ItemType itemType) {
        if(itemType.getParentTypeId()!= null){
            ItemType parent = itemTypeMapper.selectItemTypeById(itemType.getParentTypeId());
            if(StringUtils.isNotNull(parent)){
                itemType.setAncestors(parent.getAncestors()+","+parent.getItemTypeId());
            }
        }
        return itemTypeMapper.insertItemType(itemType);
    }

    @Override
    public Integer updateItemType(ItemType itemType) {
        return itemTypeMapper.updateItemType(itemType);
    }

    @Override
    public Integer removeItemType(Long itemTypeId) {
        return itemTypeMapper.deleteItemTypeById(itemTypeId);
    }

    @Override
    public boolean checkHasChild(Long itemTypeId) {
        int num =itemTypeMapper.hasChildByItemTypeId(itemTypeId);
        return num >0 ? true:false;
    }

    @Override
    public boolean checkHasItem(Long itemTypeId) {
        int num =itemTypeMapper.hasItemByItemTypeId(itemTypeId);
        return num >0 ? true:false;
    }
}
