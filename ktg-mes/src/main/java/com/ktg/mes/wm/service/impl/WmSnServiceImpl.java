package com.ktg.mes.wm.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmSnMapper;
import com.ktg.mes.wm.domain.WmSn;
import com.ktg.mes.wm.service.IWmSnService;

/**
 * SN码Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-12-08
 */
@Service
public class WmSnServiceImpl implements IWmSnService 
{
    @Autowired
    private WmSnMapper wmSnMapper;

    /**
     * 查询SN码
     * 
     * @param snId SN码主键
     * @return SN码
     */
    @Override
    public WmSn selectWmSnBySnId(Long snId)
    {
        return wmSnMapper.selectWmSnBySnId(snId);
    }

    /**
     * 查询SN码列表
     * 
     * @param wmSn SN码
     * @return SN码
     */
    @Override
    public List<WmSn> selectWmSnList(WmSn wmSn)
    {
        return wmSnMapper.selectWmSnList(wmSn);
    }

    @Override
    public List<WmSn> selectSnList(WmSn wmSn) {
        return wmSnMapper.selectSnList(wmSn);
    }

    /**
     * 新增SN码
     * 
     * @param wmSn SN码
     * @return 结果
     */
    @Override
    public int insertWmSn(WmSn wmSn)
    {
        wmSn.setCreateTime(DateUtils.getNowDate());
        return wmSnMapper.insertWmSn(wmSn);
    }

    /**
     * 修改SN码
     * 
     * @param wmSn SN码
     * @return 结果
     */
    @Override
    public int updateWmSn(WmSn wmSn)
    {
        wmSn.setUpdateTime(DateUtils.getNowDate());
        return wmSnMapper.updateWmSn(wmSn);
    }

    /**
     * 批量删除SN码
     * 
     * @param snIds 需要删除的SN码主键
     * @return 结果
     */
    @Override
    public int deleteWmSnBySnIds(Long[] snIds)
    {
        return wmSnMapper.deleteWmSnBySnIds(snIds);
    }

    /**
     * 删除SN码信息
     * 
     * @param snId SN码主键
     * @return 结果
     */
    @Override
    public int deleteWmSnBySnId(Long snId)
    {
        return wmSnMapper.deleteWmSnBySnId(snId);
    }
}
