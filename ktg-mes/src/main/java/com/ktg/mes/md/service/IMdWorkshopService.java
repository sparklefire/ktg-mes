package com.ktg.mes.md.service;

import java.util.List;
import com.ktg.mes.md.domain.MdWorkshop;

/**
 * 车间Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-07
 */
public interface IMdWorkshopService 
{
    /**
     * 查询车间
     * 
     * @param workshopId 车间主键
     * @return 车间
     */
    public MdWorkshop selectMdWorkshopByWorkshopId(Long workshopId);

    /**
     * 查询车间列表
     * 
     * @param mdWorkshop 车间
     * @return 车间集合
     */
    public List<MdWorkshop> selectMdWorkshopList(MdWorkshop mdWorkshop);

    public String checkWorkshopCodeUnique(MdWorkshop mdWorkshop);

    public String checkWorkshopNameUnique(MdWorkshop mdWorkshop);

    /**
     * 新增车间
     * 
     * @param mdWorkshop 车间
     * @return 结果
     */
    public int insertMdWorkshop(MdWorkshop mdWorkshop);

    /**
     * 修改车间
     * 
     * @param mdWorkshop 车间
     * @return 结果
     */
    public int updateMdWorkshop(MdWorkshop mdWorkshop);

    /**
     * 批量删除车间
     * 
     * @param workshopIds 需要删除的车间主键集合
     * @return 结果
     */
    public int deleteMdWorkshopByWorkshopIds(Long[] workshopIds);

    /**
     * 删除车间信息
     * 
     * @param workshopId 车间主键
     * @return 结果
     */
    public int deleteMdWorkshopByWorkshopId(Long workshopId);
}
