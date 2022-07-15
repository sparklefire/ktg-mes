package com.ktg.mes.wm.mapper;

import java.util.List;
import com.ktg.mes.wm.domain.WmIssueHeader;
import com.ktg.mes.wm.domain.tx.IssueTxBean;

/**
 * 生产领料单头Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-07-14
 */
public interface WmIssueHeaderMapper 
{
    /**
     * 查询生产领料单头
     * 
     * @param issueId 生产领料单头主键
     * @return 生产领料单头
     */
    public WmIssueHeader selectWmIssueHeaderByIssueId(Long issueId);

    /**
     * 查询生产领料单头列表
     * 
     * @param wmIssueHeader 生产领料单头
     * @return 生产领料单头集合
     */
    public List<WmIssueHeader> selectWmIssueHeaderList(WmIssueHeader wmIssueHeader);

    /**
     * 检查生产领料单编号是否唯一
     * @param wmIssueHeader
     * @return
     */
    public WmIssueHeader checkIssueCodeUnique(WmIssueHeader wmIssueHeader);

    /**
     * 新增生产领料单头
     * 
     * @param wmIssueHeader 生产领料单头
     * @return 结果
     */
    public int insertWmIssueHeader(WmIssueHeader wmIssueHeader);

    /**
     * 修改生产领料单头
     * 
     * @param wmIssueHeader 生产领料单头
     * @return 结果
     */
    public int updateWmIssueHeader(WmIssueHeader wmIssueHeader);

    /**
     * 删除生产领料单头
     * 
     * @param issueId 生产领料单头主键
     * @return 结果
     */
    public int deleteWmIssueHeaderByIssueId(Long issueId);

    /**
     * 批量删除生产领料单头
     * 
     * @param issueIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWmIssueHeaderByIssueIds(Long[] issueIds);


    public List<IssueTxBean> getTxBeans(Long issueId);

}
