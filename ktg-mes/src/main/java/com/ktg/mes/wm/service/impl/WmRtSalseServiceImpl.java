package com.ktg.mes.wm.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.wm.domain.tx.RtSalseTxBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmRtSalseMapper;
import com.ktg.mes.wm.domain.WmRtSalse;
import com.ktg.mes.wm.service.IWmRtSalseService;

/**
 * 产品销售退货单Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-10-06
 */
@Service
public class WmRtSalseServiceImpl implements IWmRtSalseService 
{
    @Autowired
    private WmRtSalseMapper wmRtSalseMapper;

    /**
     * 查询产品销售退货单
     * 
     * @param rtId 产品销售退货单主键
     * @return 产品销售退货单
     */
    @Override
    public WmRtSalse selectWmRtSalseByRtId(Long rtId)
    {
        return wmRtSalseMapper.selectWmRtSalseByRtId(rtId);
    }

    /**
     * 查询产品销售退货单列表
     * 
     * @param wmRtSalse 产品销售退货单
     * @return 产品销售退货单
     */
    @Override
    public List<WmRtSalse> selectWmRtSalseList(WmRtSalse wmRtSalse)
    {
        return wmRtSalseMapper.selectWmRtSalseList(wmRtSalse);
    }

    @Override
    public List<RtSalseTxBean> getTxBeans(Long rtId) {
        return wmRtSalseMapper.getTxBeans(rtId);
    }

    @Override
    public String checkUnique(WmRtSalse wmRtSalse) {
        WmRtSalse salse = wmRtSalseMapper.checkUnique(wmRtSalse);
        Long rtId = wmRtSalse.getRtId() == null? -1L: wmRtSalse.getRtId();
        if(StringUtils.isNotNull(salse) && rtId.longValue() != salse.getRtId().longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增产品销售退货单
     * 
     * @param wmRtSalse 产品销售退货单
     * @return 结果
     */
    @Override
    public int insertWmRtSalse(WmRtSalse wmRtSalse)
    {
        wmRtSalse.setCreateTime(DateUtils.getNowDate());
        return wmRtSalseMapper.insertWmRtSalse(wmRtSalse);
    }

    /**
     * 修改产品销售退货单
     * 
     * @param wmRtSalse 产品销售退货单
     * @return 结果
     */
    @Override
    public int updateWmRtSalse(WmRtSalse wmRtSalse)
    {
        wmRtSalse.setUpdateTime(DateUtils.getNowDate());
        return wmRtSalseMapper.updateWmRtSalse(wmRtSalse);
    }

    /**
     * 批量删除产品销售退货单
     * 
     * @param rtIds 需要删除的产品销售退货单主键
     * @return 结果
     */
    @Override
    public int deleteWmRtSalseByRtIds(Long[] rtIds)
    {
        return wmRtSalseMapper.deleteWmRtSalseByRtIds(rtIds);
    }

    /**
     * 删除产品销售退货单信息
     * 
     * @param rtId 产品销售退货单主键
     * @return 结果
     */
    @Override
    public int deleteWmRtSalseByRtId(Long rtId)
    {
        return wmRtSalseMapper.deleteWmRtSalseByRtId(rtId);
    }
}
