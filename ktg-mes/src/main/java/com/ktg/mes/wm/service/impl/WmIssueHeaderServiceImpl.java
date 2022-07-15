package com.ktg.mes.wm.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.wm.domain.tx.IssueTxBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmIssueHeaderMapper;
import com.ktg.mes.wm.domain.WmIssueHeader;
import com.ktg.mes.wm.service.IWmIssueHeaderService;

/**
 * 生产领料单头Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-07-14
 */
@Service
public class WmIssueHeaderServiceImpl implements IWmIssueHeaderService 
{
    @Autowired
    private WmIssueHeaderMapper wmIssueHeaderMapper;

    /**
     * 查询生产领料单头
     * 
     * @param issueId 生产领料单头主键
     * @return 生产领料单头
     */
    @Override
    public WmIssueHeader selectWmIssueHeaderByIssueId(Long issueId)
    {
        return wmIssueHeaderMapper.selectWmIssueHeaderByIssueId(issueId);
    }

    /**
     * 查询生产领料单头列表
     * 
     * @param wmIssueHeader 生产领料单头
     * @return 生产领料单头
     */
    @Override
    public List<WmIssueHeader> selectWmIssueHeaderList(WmIssueHeader wmIssueHeader)
    {
        return wmIssueHeaderMapper.selectWmIssueHeaderList(wmIssueHeader);
    }

    @Override
    public String checkIssueCodeUnique(WmIssueHeader wmIssueHeader) {
        WmIssueHeader header = wmIssueHeaderMapper.checkIssueCodeUnique(wmIssueHeader);
        Long headerId = wmIssueHeader.getIssueId()==null?-1l:wmIssueHeader.getIssueId();
        if(StringUtils.isNotNull(header) && headerId.longValue() !=header.getIssueId().longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增生产领料单头
     * 
     * @param wmIssueHeader 生产领料单头
     * @return 结果
     */
    @Override
    public int insertWmIssueHeader(WmIssueHeader wmIssueHeader)
    {
        wmIssueHeader.setCreateTime(DateUtils.getNowDate());
        return wmIssueHeaderMapper.insertWmIssueHeader(wmIssueHeader);
    }

    /**
     * 修改生产领料单头
     * 
     * @param wmIssueHeader 生产领料单头
     * @return 结果
     */
    @Override
    public int updateWmIssueHeader(WmIssueHeader wmIssueHeader)
    {
        wmIssueHeader.setUpdateTime(DateUtils.getNowDate());
        return wmIssueHeaderMapper.updateWmIssueHeader(wmIssueHeader);
    }

    /**
     * 批量删除生产领料单头
     * 
     * @param issueIds 需要删除的生产领料单头主键
     * @return 结果
     */
    @Override
    public int deleteWmIssueHeaderByIssueIds(Long[] issueIds)
    {
        return wmIssueHeaderMapper.deleteWmIssueHeaderByIssueIds(issueIds);
    }

    /**
     * 删除生产领料单头信息
     * 
     * @param issueId 生产领料单头主键
     * @return 结果
     */
    @Override
    public int deleteWmIssueHeaderByIssueId(Long issueId)
    {
        return wmIssueHeaderMapper.deleteWmIssueHeaderByIssueId(issueId);
    }

    @Override
    public List<IssueTxBean> getTxBeans(Long issueId) {
        return wmIssueHeaderMapper.getTxBeans(issueId);
    }
}
