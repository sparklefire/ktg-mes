package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmRtIssue;
import com.ktg.mes.wm.domain.tx.RtIssueTxBean;

/**
 * 生产退料单头Service接口
 * 
 * @author yinjinlu
 * @date 2022-09-15
 */
public interface IWmRtIssueService 
{
    /**
     * 查询生产退料单头
     * 
     * @param rtId 生产退料单头主键
     * @return 生产退料单头
     */
    public WmRtIssue selectWmRtIssueByRtId(Long rtId);

    /**
     * 查询生产退料单头列表
     * 
     * @param wmRtIssue 生产退料单头
     * @return 生产退料单头集合
     */
    public List<WmRtIssue> selectWmRtIssueList(WmRtIssue wmRtIssue);

    /**
     * 检查编号是否重复
     * @param wmRtIssue
     * @return
     */
    public String checkUnique(WmRtIssue wmRtIssue);

    /**
     * 新增生产退料单头
     * 
     * @param wmRtIssue 生产退料单头
     * @return 结果
     */
    public int insertWmRtIssue(WmRtIssue wmRtIssue);

    /**
     * 修改生产退料单头
     * 
     * @param wmRtIssue 生产退料单头
     * @return 结果
     */
    public int updateWmRtIssue(WmRtIssue wmRtIssue);

    /**
     * 批量删除生产退料单头
     * 
     * @param rtIds 需要删除的生产退料单头主键集合
     * @return 结果
     */
    public int deleteWmRtIssueByRtIds(Long[] rtIds);

    /**
     * 删除生产退料单头信息
     * 
     * @param rtId 生产退料单头主键
     * @return 结果
     */
    public int deleteWmRtIssueByRtId(Long rtId);

    /**
     *
     * @param rtId
     * @return
     */
    public List<RtIssueTxBean> getTxBeans(Long rtId);

}
