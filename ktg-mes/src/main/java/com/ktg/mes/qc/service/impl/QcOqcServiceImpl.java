package com.ktg.mes.qc.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.qc.mapper.QcOqcMapper;
import com.ktg.mes.qc.domain.QcOqc;
import com.ktg.mes.qc.service.IQcOqcService;

/**
 * 出货检验单Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-08-31
 */
@Service
public class QcOqcServiceImpl implements IQcOqcService 
{
    @Autowired
    private QcOqcMapper qcOqcMapper;

    /**
     * 查询出货检验单
     * 
     * @param oqcId 出货检验单主键
     * @return 出货检验单
     */
    @Override
    public QcOqc selectQcOqcByOqcId(Long oqcId)
    {
        return qcOqcMapper.selectQcOqcByOqcId(oqcId);
    }

    /**
     * 查询出货检验单列表
     * 
     * @param qcOqc 出货检验单
     * @return 出货检验单
     */
    @Override
    public List<QcOqc> selectQcOqcList(QcOqc qcOqc)
    {
        return qcOqcMapper.selectQcOqcList(qcOqc);
    }

    @Override
    public String checkOqcCodeUnique(QcOqc qcOqc) {
        QcOqc oqc = qcOqcMapper.checkOqcCodeUnique(qcOqc);
        Long oqcId = qcOqc.getOqcId() == null? -1L : qcOqc.getOqcId();
        if(StringUtils.isNotNull(oqc) && oqc.getOqcId().longValue() != oqcId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增出货检验单
     * 
     * @param qcOqc 出货检验单
     * @return 结果
     */
    @Override
    public int insertQcOqc(QcOqc qcOqc)
    {
        qcOqc.setCreateTime(DateUtils.getNowDate());
        return qcOqcMapper.insertQcOqc(qcOqc);
    }

    /**
     * 修改出货检验单
     * 
     * @param qcOqc 出货检验单
     * @return 结果
     */
    @Override
    public int updateQcOqc(QcOqc qcOqc)
    {
        qcOqc.setUpdateTime(DateUtils.getNowDate());
        return qcOqcMapper.updateQcOqc(qcOqc);
    }

    @Override
    public int updateCrMajMinQuaAndRate(Long oqcId) {
        return qcOqcMapper.updateCrMajMinQuaAndRate(oqcId);
    }

    /**
     * 批量删除出货检验单
     * 
     * @param oqcIds 需要删除的出货检验单主键
     * @return 结果
     */
    @Override
    public int deleteQcOqcByOqcIds(Long[] oqcIds)
    {
        return qcOqcMapper.deleteQcOqcByOqcIds(oqcIds);
    }

    /**
     * 删除出货检验单信息
     * 
     * @param oqcId 出货检验单主键
     * @return 结果
     */
    @Override
    public int deleteQcOqcByOqcId(Long oqcId)
    {
        return qcOqcMapper.deleteQcOqcByOqcId(oqcId);
    }
}
