package com.ktg.mes.md.service;

import com.ktg.common.core.domain.TreeSelect;
import com.ktg.common.core.domain.entity.ItemType;

import java.util.List;

public interface IItemTypeService {

    public List<ItemType> selectItemTypeList(ItemType itemType);

    public ItemType selectItemTypeById(Long itemTypeId);

    public List<TreeSelect> buildTreeSelect(List<ItemType> list);

    public String checkItemTypeCodeUnique(ItemType itemType);

    public String checkItemTypeNameUnique(ItemType itemType);

    public Integer insertItemType(ItemType itemType);

    public Integer updateItemType(ItemType itemType);

    public Integer removeItemType(Long itemTypeId);

    public boolean checkHasChild(Long itemTypeId);

    public boolean checkHasItem(Long itemTypeId);
}
