package com.ktg.mes.wm.mapper;

import java.util.List;
import com.ktg.mes.wm.domain.WmOutsourceIssue;
import com.ktg.mes.wm.domain.tx.OutsourceIssueTxBean;

/**
 * 外协领料单头Mapper接口
 * 
 * @author yinjinlu
 * @date 2023-10-30
 */
public interface WmOutsourceIssueMapper 
{
    /**
     * 查询外协领料单头
     * 
     * @param issueId 外协领料单头主键
     * @return 外协领料单头
     */
    public WmOutsourceIssue selectWmOutsourceIssueByIssueId(Long issueId);

    /**
     * 查询外协领料单头列表
     * 
     * @param wmOutsourceIssue 外协领料单头
     * @return 外协领料单头集合
     */
    public List<WmOutsourceIssue> selectWmOutsourceIssueList(WmOutsourceIssue wmOutsourceIssue);

    /**
     * 新增外协领料单头
     * 
     * @param wmOutsourceIssue 外协领料单头
     * @return 结果
     */
    public int insertWmOutsourceIssue(WmOutsourceIssue wmOutsourceIssue);

    /**
     * 修改外协领料单头
     * 
     * @param wmOutsourceIssue 外协领料单头
     * @return 结果
     */
    public int updateWmOutsourceIssue(WmOutsourceIssue wmOutsourceIssue);

    /**
     * 删除外协领料单头
     * 
     * @param issueId 外协领料单头主键
     * @return 结果
     */
    public int deleteWmOutsourceIssueByIssueId(Long issueId);

    /**
     * 批量删除外协领料单头
     * 
     * @param issueIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWmOutsourceIssueByIssueIds(Long[] issueIds);

    public List<OutsourceIssueTxBean> getTxBeans(Long issueId);
}
