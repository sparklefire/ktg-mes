package com.ktg.common.constant;

/**
 * 用户常量信息
 * 
 * @author ruoyi
 */
public class UserConstants
{
    /**
     * 平台内系统用户的唯一标志
     */
    public static final String SYS_USER = "SYS_USER";

    /** 正常状态 */
    public static final String NORMAL = "0";

    /** 异常状态 */
    public static final String EXCEPTION = "1";

    /** 用户封禁状态 */
    public static final String USER_DISABLE = "1";

    /** 角色封禁状态 */
    public static final String ROLE_DISABLE = "1";

    /** 部门正常状态 */
    public static final String DEPT_NORMAL = "0";

    /** 部门停用状态 */
    public static final String DEPT_DISABLE = "1";

    /** 字典正常状态 */
    public static final String DICT_NORMAL = "0";

    /** 是否为系统默认（是） */
    public static final String YES = "Y";

    /** 是否菜单外链（是） */
    public static final String YES_FRAME = "0";

    /** 是否菜单外链（否） */
    public static final String NO_FRAME = "1";

    /** 菜单类型（目录） */
    public static final String TYPE_DIR = "M";

    /** 菜单类型（菜单） */
    public static final String TYPE_MENU = "C";

    /** 菜单类型（按钮） */
    public static final String TYPE_BUTTON = "F";

    /** Layout组件标识 */
    public final static String LAYOUT = "Layout";
    
    /** ParentView组件标识 */
    public final static String PARENT_VIEW = "ParentView";

    /** InnerLink组件标识 */
    public final static String INNER_LINK = "InnerLink";

    /** 校验返回结果码 */
    public final static String UNIQUE = "0";
    public final static String NOT_UNIQUE = "1";

    /**
     * 用户名长度限制
     */
    public static final int USERNAME_MIN_LENGTH = 2;
    public static final int USERNAME_MAX_LENGTH = 20;

    /**
     * 密码长度限制
     */
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 20;

    /**
     * 各种业务单据的内定自动编码规则标识
     */
    public static final String ITEM_TYPE_CODE ="ITEM_TYPE_CODE";
    public static final String ITEM_CODE ="ITEM_CODE";
    public static final String MACHINERY_TYPE_CODE="MACHINERY_TYPE_CODE";
    public static final String TASK_CODE="TASK_CODE";
    public static final String DEFECT_CODE = "DEFECT_CODE";

    /**
     * 单据的状态类型
     */
    public static final String ORDER_STATUS_PREPARE="PREPARE";
    public static final String ORDER_STATUS_CONFIRMED="CONFIRMED";
    public static final String ORDER_STATUS_APPROVING="APPROVING";
    public static final String ORDER_STATUS_APPROVED="APPROVED";
    public static final String ORDER_STATUS_FINISHED="FINISHED";

    /**
     * 维护类型
     */
    public static final String MAINTEN_TYPE_REGULAR="REGULAR";
    public static final String MAINTEN_TYPE_USAGE="USAGE";

    /**
     * 甘特图中的TASK类型
     */
    public static final String GANTT_TASK_TYPE_TASK="task";
    public static final String GANTT_TASK_TYPE_PROJECT="project";


    /**
     * 报表相关
     */
    public static final String REPORT_PRINT_TYPE ="print";
    public static final String REPORT_PDF_TYPE ="pdf";
    public static final String REPORT_EXCEL_TYPE ="excel";
    public static final String REPORT_WORD_TYPE ="word";
    public static final String REPORT_JASPER_PATH="reports/jasper/";


    /**
     * 库存事务类型
     */
    public static final String TRANSACTION_TYPE_ITEM_RECPT = "ITEM_RECPT"; //原材料接收入库
    public static final String TRANSACTION_TYPE_ITEM_RTV = "ITEM_RTV"; //原材料退回供应商
    public static final String TRANSACTION_TYPE_ITEM_ISSUE = "ITEM_ISSUE"; //生产领用
    public static final String TRANSACTION_TYPE_ITEM_RS = "ITEM_RS"; //生产退库
    public static final String TRANSACTION_TYPE_WAREHOUSE_TRANS_OUT = "TRANS_OUT"; //移库,移出
    public static final String TRANSACTION_TYPE_WAREHOUSE_TRANS_IN = "TRANS_IN"; //移库,移入
    public static final String TRANSACTION_TYPE_PRODUCT_ISSUE = "PRODUCT_ISSUE"; //销售出库
    public static final String TRANSACTION_TYPE_PRODUCT_RS = "PRODUCT_RS"; //销售退货
    public static final String TRANSACTION_TYPE_MISC_RECPT = "MISC_RECPT"; //杂项入库
    public static final String TRANSACTION_TYPE_MISC_ISSUE = "MISC_ISSUE"; //杂项出库

    /**
     * 轮班方式
     */
    public static final String CAL_SHIFT_TYPE_SINGLE="SINGLE";
    public static final String CAL_SHIFT_TYPE_TWO="SHIFT_TWO";
    public static final String CAL_SHIFT_TYPE_THREE="SHIFT_THREE";
    public static final String CAL_SHIFT_NAME_DAY="白班";
    public static final String CAL_SHIFT_NAME_NIGHT="夜班";
    public static final String CAL_SHIFT_NAME_MID="中班";
    public static final String CAL_SHIFT_METHOD_QUARTER="QUARTER";
    public static final String CAL_SHIFT_METHOD_MONTH="MONTH";
    public static final String CAL_SHIFT_METHOD_WEEK="WEEK";
    public static final String CAL_SHIFT_METHOD_DAY="DAY";

    /**
     * 排班日历的查询方式
     */
    public static final String CAL_QUERY_BY_TYPE="TYPE";
    public static final String CAL_QUERY_BY_TEAM="TEAM";
    public static final String CAL_QUERY_BY_USER="USER";

    /**
     * 生产投料单据的类型
     */
    public static final String TASK_ISSUE_DOC_TYPE_ISSUE="ISSUE"; //领料单
    public static final String TASK_ISSUE_DOC_TYPE_TRANS="TRANS"; //流转单

    /**
     * 检测单类型，这里的类型是大类
     * 首检、末检等等是过程检验中的子分类
     */
    public static final String QC_TYPE_IQC = "IQC"; //来料检验单
    public static final String QC_TYPE_IPQC = "IPQC"; //过程检验单
    public static final String QC_TYPE_OQC = "OQC"; //出货检验
}
