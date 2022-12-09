package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmSn;

/**
 * SN码Service接口
 * 
 * @author yinjinlu
 * @date 2022-12-08
 */
public interface IWmSnService 
{
    /**
     * 查询SN码
     * 
     * @param snId SN码主键
     * @return SN码
     */
    public WmSn selectWmSnBySnId(Long snId);

    /**
     * 查询SN码列表
     * 
     * @param wmSn SN码
     * @return SN码集合
     */
    public List<WmSn> selectWmSnList(WmSn wmSn);

    /**
     * 查询SN展示列表
     * @param wmSn
     * @return
     */
    public List<WmSn> selectSnList(WmSn wmSn);

    /**
     * 新增SN码
     * 
     * @param wmSn SN码
     * @return 结果
     */
    public int insertWmSn(WmSn wmSn);

    /**
     * 修改SN码
     * 
     * @param wmSn SN码
     * @return 结果
     */
    public int updateWmSn(WmSn wmSn);

    /**
     * 批量删除SN码
     * 
     * @param snIds 需要删除的SN码主键集合
     * @return 结果
     */
    public int deleteWmSnBySnIds(Long[] snIds);

    /**
     * 删除SN码信息
     * 
     * @param snId SN码主键
     * @return 结果
     */
    public int deleteWmSnBySnId(Long snId);
}
