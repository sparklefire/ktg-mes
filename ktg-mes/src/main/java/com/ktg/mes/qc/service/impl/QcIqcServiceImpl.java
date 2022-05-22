package com.ktg.mes.qc.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.qc.mapper.QcIqcMapper;
import com.ktg.mes.qc.domain.QcIqc;
import com.ktg.mes.qc.service.IQcIqcService;

/**
 * 来料检验单Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-19
 */
@Service
public class QcIqcServiceImpl implements IQcIqcService 
{
    @Autowired
    private QcIqcMapper qcIqcMapper;

    /**
     * 查询来料检验单
     * 
     * @param iqcId 来料检验单主键
     * @return 来料检验单
     */
    @Override
    public QcIqc selectQcIqcByIqcId(Long iqcId)
    {
        return qcIqcMapper.selectQcIqcByIqcId(iqcId);
    }

    /**
     * 查询来料检验单列表
     * 
     * @param qcIqc 来料检验单
     * @return 来料检验单
     */
    @Override
    public List<QcIqc> selectQcIqcList(QcIqc qcIqc)
    {
        return qcIqcMapper.selectQcIqcList(qcIqc);
    }

    @Override
    public String checkIqcCodeUnique(QcIqc qcIqc) {
        QcIqc iqc = qcIqcMapper.checkIqcCodeUnique(qcIqc);
        Long iqcId = qcIqc.getIqcId()==null?-1L:qcIqc.getIqcId();
        if(StringUtils.isNotNull(iqc) && iqc.getIqcId().longValue() != iqcId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增来料检验单
     * 
     * @param qcIqc 来料检验单
     * @return 结果
     */
    @Override
    public int insertQcIqc(QcIqc qcIqc)
    {
        qcIqc.setCreateTime(DateUtils.getNowDate());
        return qcIqcMapper.insertQcIqc(qcIqc);
    }

    /**
     * 修改来料检验单
     * 
     * @param qcIqc 来料检验单
     * @return 结果
     */
    @Override
    public int updateQcIqc(QcIqc qcIqc)
    {
        qcIqc.setUpdateTime(DateUtils.getNowDate());
        return qcIqcMapper.updateQcIqc(qcIqc);
    }

    @Override
    public int updateCrMajMinQuaAndRate(Long iqcId) {
        return qcIqcMapper.updateCrMajMinQuaAndRate(iqcId);
    }

    /**
     * 批量删除来料检验单
     * 
     * @param iqcIds 需要删除的来料检验单主键
     * @return 结果
     */
    @Override
    public int deleteQcIqcByIqcIds(Long[] iqcIds)
    {
        return qcIqcMapper.deleteQcIqcByIqcIds(iqcIds);
    }

    /**
     * 删除来料检验单信息
     * 
     * @param iqcId 来料检验单主键
     * @return 结果
     */
    @Override
    public int deleteQcIqcByIqcId(Long iqcId)
    {
        return qcIqcMapper.deleteQcIqcByIqcId(iqcId);
    }
}
