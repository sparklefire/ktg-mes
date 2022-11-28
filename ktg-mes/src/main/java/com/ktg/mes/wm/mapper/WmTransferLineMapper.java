package com.ktg.mes.wm.mapper;

import java.util.List;
import com.ktg.mes.wm.domain.WmTransferLine;

/**
 * 转移单行Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-11-28
 */
public interface WmTransferLineMapper 
{
    /**
     * 查询转移单行
     * 
     * @param lineId 转移单行主键
     * @return 转移单行
     */
    public WmTransferLine selectWmTransferLineByLineId(Long lineId);

    /**
     * 查询转移单行列表
     * 
     * @param wmTransferLine 转移单行
     * @return 转移单行集合
     */
    public List<WmTransferLine> selectWmTransferLineList(WmTransferLine wmTransferLine);

    /**
     * 新增转移单行
     * 
     * @param wmTransferLine 转移单行
     * @return 结果
     */
    public int insertWmTransferLine(WmTransferLine wmTransferLine);

    /**
     * 修改转移单行
     * 
     * @param wmTransferLine 转移单行
     * @return 结果
     */
    public int updateWmTransferLine(WmTransferLine wmTransferLine);

    /**
     * 删除转移单行
     * 
     * @param lineId 转移单行主键
     * @return 结果
     */
    public int deleteWmTransferLineByLineId(Long lineId);

    /**
     * 批量删除转移单行
     * 
     * @param lineIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWmTransferLineByLineIds(Long[] lineIds);

    /**
     * 根据头ID删除所有行
     * @param transferId
     * @return
     */
    public int deleteByTransferId(Long transferId);
}
