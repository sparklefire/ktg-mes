package com.ktg.mes.wm.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import com.ktg.mes.wm.domain.tx.IssueTxBean;
import com.ktg.mes.wm.domain.tx.OutsourceIssueTxBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmOutsourceIssueMapper;
import com.ktg.mes.wm.domain.WmOutsourceIssue;
import com.ktg.mes.wm.service.IWmOutsourceIssueService;

/**
 * 外协领料单头Service业务层处理
 *
 * @author yinjinlu
 * @date 2023-10-30
 */
@Service
public class WmOutsourceIssueServiceImpl implements IWmOutsourceIssueService {

    @Autowired
    private WmOutsourceIssueMapper wmOutsourceIssueMapper;

    /**
     * 查询外协领料单头
     *
     * @param issueId 外协领料单头主键
     * @return 外协领料单头
     */
    @Override
    public WmOutsourceIssue selectWmOutsourceIssueByIssueId(Long issueId) {
        return wmOutsourceIssueMapper.selectWmOutsourceIssueByIssueId(issueId);
    }

    /**
     * 查询外协领料单头列表
     *
     * @param wmOutsourceIssue 外协领料单头
     * @return 外协领料单头
     */
    @Override
    public List<WmOutsourceIssue> selectWmOutsourceIssueList(WmOutsourceIssue wmOutsourceIssue) {
        return wmOutsourceIssueMapper.selectWmOutsourceIssueList(wmOutsourceIssue);
    }

    /**
     * 新增外协领料单头
     *
     * @param wmOutsourceIssue 外协领料单头
     * @return 结果
     */
    @Override
    public int insertWmOutsourceIssue(WmOutsourceIssue wmOutsourceIssue) {
        wmOutsourceIssue.setCreateTime(DateUtils.getNowDate());
        return wmOutsourceIssueMapper.insertWmOutsourceIssue(wmOutsourceIssue);
    }

    /**
     * 修改外协领料单头
     *
     * @param wmOutsourceIssue 外协领料单头
     * @return 结果
     */
    @Override
    public int updateWmOutsourceIssue(WmOutsourceIssue wmOutsourceIssue) {
        wmOutsourceIssue.setUpdateTime(DateUtils.getNowDate());
        return wmOutsourceIssueMapper.updateWmOutsourceIssue(wmOutsourceIssue);
    }

    /**
     * 批量删除外协领料单头
     *
     * @param issueIds 需要删除的外协领料单头主键
     * @return 结果
     */
    @Override
    public int deleteWmOutsourceIssueByIssueIds(Long[] issueIds) {
        return wmOutsourceIssueMapper.deleteWmOutsourceIssueByIssueIds(issueIds);
    }

    /**
     * 删除外协领料单头信息
     *
     * @param issueId 外协领料单头主键
     * @return 结果
     */
    @Override
    public int deleteWmOutsourceIssueByIssueId(Long issueId) {
        return wmOutsourceIssueMapper.deleteWmOutsourceIssueByIssueId(issueId);
    }

    @Override
    public List<OutsourceIssueTxBean> getTxBeans(Long issueId) {
        return wmOutsourceIssueMapper.getTxBeans(issueId);
    }
}
