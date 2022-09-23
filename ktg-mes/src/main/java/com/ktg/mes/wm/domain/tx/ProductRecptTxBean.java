package com.ktg.mes.wm.domain.tx;

import com.ktg.common.core.domain.BaseEntity;

import java.math.BigDecimal;

public class ProductRecptTxBean extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 库存记录ID **/
    private Long materialStockId;

    /** 产品物料ID */
    private Long itemId;

    /** 产品物料编码 */
    private String itemCode;

    /** 产品物料名称 */
    private String itemName;

    /** 规格型号 */
    private String specification;

    /** 单位 */
    private String unitOfMeasure;

    /** 批次号 */
    private String batchCode;

    /** 仓库ID */
    private Long warehouseId;

    /** 仓库编码 */
    private String warehouseCode;

    /** 仓库名称 */
    private String warehouseName;

    /** 库区ID */
    private Long locationId;

    /** 库区编码 */
    private String locationCode;

    /** 库区名称 */
    private String locationName;

    /** 库位ID */
    private Long areaId;

    /** 库位编码 */
    private String areaCode;

    /** 库位名称 */
    private String areaName;

    /** 单据类型 */
    private String sourceDocType;

    /** 单据ID */
    private Long sourceDocId;

    /** 单据编号 */
    private String sourceDocCode;

    /** 单据行ID */
    private Long sourceDocLineId;

    /** 事务数量 */
    private BigDecimal transactionQuantity;

    /**
     * 生产工单ID
     */
    private Long workorderId;

    /**
     * 生产工单编号
     */
    private String workorderCode;

}
