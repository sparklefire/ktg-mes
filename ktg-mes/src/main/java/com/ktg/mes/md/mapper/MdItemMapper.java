package com.ktg.mes.md.mapper;

import com.ktg.mes.md.domain.MdItem;

import java.util.List;

public interface MdItemMapper {
    /**
     * 根据条件查询物料编码
     * @param mdItem
     * @return
     */
    public List<MdItem> selectMdItemList(MdItem mdItem);

    /**
     * 查询所有物料编码
     * @return
     */
    public List<MdItem> selectMdItemAll();


    /**
     * 根据物料ID查询物料
     * @param itemId
     * @return
     */
    public MdItem selectMdItemById(Long itemId);

    /**
     * 检查物料编码是否唯一
     * @param mdItem
     * @return
     */
    public MdItem  checkItemCodeUnique(MdItem mdItem);

    /**
     * 检查物料名称是否唯一
     * @param mdItem
     * @return
     */
    public MdItem  checkItemNameUnique(MdItem mdItem);

    /**
     * 新增物料
     * @param mdItem
     * @return
     */
    public int insertMdItem(MdItem mdItem);

    /**
     * 更新物料
     * @param mdItem
     * @return
     */
    public int updateMdItem(MdItem mdItem);

    /**
     * 根据ID删除物料
     * @param itemId
     * @return
     */
    public int deleteMdItemById(Long itemId);

    /**
     * 批量删除物料
     * @param itemIds
     * @return
     */
    public int deleteMdItemByIds(Long[] itemIds);

}
