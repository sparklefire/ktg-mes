package com.ktg.mes.wm.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.wm.domain.tx.RtIssueTxBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmRtIssueMapper;
import com.ktg.mes.wm.domain.WmRtIssue;
import com.ktg.mes.wm.service.IWmRtIssueService;

/**
 * 生产退料单头Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-09-15
 */
@Service
public class WmRtIssueServiceImpl implements IWmRtIssueService 
{
    @Autowired
    private WmRtIssueMapper wmRtIssueMapper;

    /**
     * 查询生产退料单头
     * 
     * @param rtId 生产退料单头主键
     * @return 生产退料单头
     */
    @Override
    public WmRtIssue selectWmRtIssueByRtId(Long rtId)
    {
        return wmRtIssueMapper.selectWmRtIssueByRtId(rtId);
    }

    /**
     * 查询生产退料单头列表
     * 
     * @param wmRtIssue 生产退料单头
     * @return 生产退料单头
     */
    @Override
    public List<WmRtIssue> selectWmRtIssueList(WmRtIssue wmRtIssue)
    {
        return wmRtIssueMapper.selectWmRtIssueList(wmRtIssue);
    }

    @Override
    public String checkUnique(WmRtIssue wmRtIssue) {
       WmRtIssue issue = wmRtIssueMapper.checkUnique(wmRtIssue);
       Long rtId = wmRtIssue.getRtId() == null? -1L: wmRtIssue.getRtId();
       if(StringUtils.isNotNull(issue) && issue.getRtId().longValue() != rtId.longValue()){
           return UserConstants.NOT_UNIQUE;
       }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增生产退料单头
     * 
     * @param wmRtIssue 生产退料单头
     * @return 结果
     */
    @Override
    public int insertWmRtIssue(WmRtIssue wmRtIssue)
    {
        wmRtIssue.setCreateTime(DateUtils.getNowDate());
        return wmRtIssueMapper.insertWmRtIssue(wmRtIssue);
    }

    /**
     * 修改生产退料单头
     * 
     * @param wmRtIssue 生产退料单头
     * @return 结果
     */
    @Override
    public int updateWmRtIssue(WmRtIssue wmRtIssue)
    {
        wmRtIssue.setUpdateTime(DateUtils.getNowDate());
        return wmRtIssueMapper.updateWmRtIssue(wmRtIssue);
    }

    /**
     * 批量删除生产退料单头
     * 
     * @param rtIds 需要删除的生产退料单头主键
     * @return 结果
     */
    @Override
    public int deleteWmRtIssueByRtIds(Long[] rtIds)
    {
        return wmRtIssueMapper.deleteWmRtIssueByRtIds(rtIds);
    }

    /**
     * 删除生产退料单头信息
     * 
     * @param rtId 生产退料单头主键
     * @return 结果
     */
    @Override
    public int deleteWmRtIssueByRtId(Long rtId)
    {
        return wmRtIssueMapper.deleteWmRtIssueByRtId(rtId);
    }

    @Override
    public List<RtIssueTxBean> getTxBeans(Long rtId) {
        return wmRtIssueMapper.getTxBeans(rtId);
    }
}
