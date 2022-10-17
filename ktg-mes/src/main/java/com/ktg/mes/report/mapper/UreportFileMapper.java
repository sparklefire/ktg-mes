package com.ktg.mes.report.mapper;

import com.ktg.mes.report.domain.UreportFileEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Ureport文件 Mapper
 * @author yanshikui
 * @version 2022年10月7日
 *
 */
@Mapper
public interface UreportFileMapper {

    /**
     *  根据报表名称检查报表是否存在
     * @param name 报表名称
     * @return
     */
    public int checkExistByName(String name);

    /**
     *  根据报表名称查询报表
     * @param name 报表名称
     * @return
     */
    public UreportFileEntity queryUreportFileEntityByName(String name);

    /**
     * 查询全部报表
     * @return
     */
    public List<UreportFileEntity> queryReportFileList();

    /**
     * 根据报表名称删除报表
     * @param name
     * @return
     */
    public int deleteReportFileByName(String name);


    /**
     *  保存报表
     */
    public int insertReportFile(UreportFileEntity entity);

    /**
     *  更新报表
     * @param entity
     * @return
     */
    public int updateReportFile(UreportFileEntity entity);
}
