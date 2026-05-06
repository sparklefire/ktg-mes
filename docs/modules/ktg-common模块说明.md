# ktg-common 模块说明文档

## 目录结构

```
com.ktg.common/
├── annotation/     # 注解包
├── config/         # 配置包
├── constant/       # 常量包
├── core/           # 核心包
├── enums/          # 枚举包
├── exception/      # 异常包
├── filter/         # 过滤器包
├── utils/          # 工具类包
└── xss/            # XSS防护包
```

---

## 1. annotation 包

### 代码结构

```
com.ktg.common.annotation/
├── DataScope.java      # 数据权限过滤注解
├── DataSource.java     # 多数据源切换注解
├── Excel.java          # Excel导出导入注解
├── Excels.java         # 多Excel注解容器
├── Log.java            # 操作日志记录注解
├── RateLimiter.java    # 接口限流注解
└── RepeatSubmit.java   # 防重复提交注解
```

### 依赖关系

- 依赖本模块的 `enums` 包（如 `DataSourceType`、`BusinessType`、`OperatorType`、`LimitType`）
- 依赖本模块的 `constant` 包（如 `Constants`）
- 依赖本模块的 `utils.poi` 包（如 `ExcelHandlerAdapter`）
- **第三方依赖**：
  - `java.lang.annotation`：Java 原生注解元注解（`@Target`、`@Retention`、`@Documented`、`@Inherited`）

### 功能

该包提供了系统核心功能的声明式注解支持，通过 AOP 切面在运行时拦截并增强方法行为：

| 注解           | 作用目标          | 功能说明                                                     |
| -------------- | ----------------- | ------------------------------------------------------------ |
| @DataScope     | METHOD            | 数据权限过滤，标记需要进行部门/用户数据范围过滤的查询方法    |
| @DataSource    | TYPE, METHOD      | 多数据源切换，支持在类或方法级别指定数据源类型（MASTER/SLAVE） |
| @Excel/@Excels | FIELD             | Excel 导入导出配置（列名、格式、字典转换、日期格式化等）     |
| @Log           | METHOD, PARAMETER | 操作日志记录，自动记录模块、功能类型、操作人、请求/响应参数  |
| @RateLimiter   | METHOD            | 接口限流，支持按时间窗口限制请求次数，支持按 IP 或默认策略   |
| @RepeatSubmit  | METHOD            | 防重复提交，在指定时间间隔内拦截重复请求                     |

#### 注解分类说明

**重要说明**：以下注解按其在框架中的重要性和角色分为三类：

---

**第一类：框架核心关键注解（真正的关键注解）**

这类注解是框架架构的核心，决定了系统的数据访问权限和数据库路由策略。

| 注解 | 功能 | 为什么是关键注解 |
|------|------|-----------------|
| `@DataScope` | 数据权限过滤 | 控制用户能看到哪些数据，是权限系统的核心组件 |
| `@DataSource` | 多数据源切换 | 支持读写分离架构，是数据库层架构的核心 |

---

**第二类：安全防护注解**

这类注解提供系统安全保护功能，防止恶意访问。

| 注解 | 功能 | 说明 |
|------|------|------|
| `@RateLimiter` | 接口限流 | 限制接口调用频率，防止系统过载 |
| `@RepeatSubmit` | 防重复提交 | 防止表单重复提交，避免数据重复 |

---

**第三类：业务辅助注解**

这类注解提供业务开发的便利功能，使用频繁但不是框架核心。

| 注解 | 功能 | 说明 |
|------|------|------|
| `@Excel/@Excels` | Excel 导入导出 | 简化 Excel 文件的导入导出操作 |
| `@Log` | 操作日志记录 | 自动记录操作日志 |

---

#### 注解属性详细说明

---

##### 第一类：框架核心关键注解

---

###### 一、@DataScope 注解完整属性说明

@DataScope 是**权限系统的核心注解**，用于数据权限过滤。它决定了当前登录用户能够查询哪些范围的数据，是实现"数据隔离"的关键组件。

**注解定义：**
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {
    /** 部门表的别名 */
    public String deptAlias() default "";
    /** 用户表的别名 */
    public String userAlias() default "";
}
```

**为什么是关键注解？**

数据权限是企业级系统的核心需求：
- 不同部门的用户只能看到自己部门的数据
- 管理员可以看到所有数据
- 普通用户只能看到自己的数据

@DataScope 通过 AOP 切面在 SQL 执行前动态注入数据权限条件，实现了"一次配置，处处生效"。

**属性详细说明：**

| 属性名 | 类型 | 默认值 | 说明 |
|--------|------|--------|------|
| `deptAlias` | String | `""` | 部门表的 SQL 别名，用于拼接部门权限条件 |
| `userAlias` | String | `""` | 用户表的 SQL 别名，用于拼接用户权限条件 |

**工作原理：**

```
用户执行查询请求
    ↓
AOP 切面拦截 @DataScope 标注的方法
    ↓
获取当前登录用户的角色和数据权限类型
    ↓
根据权限类型构建 SQL 过滤条件：
  - 全部数据：无条件
  - 本部门：dept_id = 当前用户部门ID
  - 本部门及下级：dept_id IN (当前部门及子部门)
  - 仅本人：user_id = 当前用户ID
    ↓
将条件注入到实体对象的 params Map 中
    ↓
MyBatis 从 params 中获取条件拼接到 SQL
    ↓
执行带有数据权限的查询
```

**使用示例：**

```java
@Service
public class UserServiceImpl implements IUserService {
    
    /**
     * 查询用户列表（带数据权限）
     * deptAlias = "d"：SQL 中部门表的别名是 d
     * userAlias = "u"：SQL 中用户表的别名是 u
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SysUser> selectUserList(SysUser user) {
        return userMapper.selectUserList(user);
    }
}
```

**对应的 Mapper XML：**

```xml
<select id="selectUserList" resultType="SysUser">
    SELECT u.*, d.dept_name
    FROM sys_user u
    LEFT JOIN sys_dept d ON u.dept_id = d.dept_id
    WHERE 1=1
    <!-- 其他条件... -->
    
    <!-- 数据权限条件（从 params 中获取） -->
    ${params.dataScope}
</select>
```

---

###### 二、@DataSource 注解完整属性说明

@DataSource 是**数据库架构的核心注解**，用于多数据源切换。它支持读写分离架构，是提高系统性能和可用性的关键组件。

**注解定义：**
```java
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    /** 切换数据源名称 */
    public DataSourceType value() default DataSourceType.MASTER;
}
```

**为什么是关键注解？**

在大型系统中，数据库通常采用主从架构：
- **主库（MASTER）**：处理写操作（INSERT、UPDATE、DELETE）
- **从库（SLAVE）**：处理读操作（SELECT）

@DataSource 允许开发者在代码中声明性地指定使用哪个数据源，AOP 切面负责实际的切换。

**DataSourceType 枚举：**

```java
public enum DataSourceType {
    MASTER,  // 主库 - 写操作
    SLAVE    // 从库 - 读操作
}
```

**属性详细说明：**

| 属性名 | 类型 | 默认值 | 说明 |
|--------|------|--------|------|
| `value` | DataSourceType | `DataSourceType.MASTER` | 目标数据源类型 |

**使用场景：**

| 场景 | 数据源 | 说明 |
|------|--------|------|
| 新增、修改、删除 | MASTER | 写操作必须走主库 |
| 普通查询 | SLAVE | 读操作走从库，分担主库压力 |
| 复杂统计查询 | SLAVE | 大查询走从库，避免影响主库 |
| 事务操作 | MASTER | 事务内不能切换数据源 |

**使用示例：**

```java
@Service
@DataSource(DataSourceType.SLAVE)  // 类级别：默认从库
public class UserServiceImpl implements IUserService {
    
    /**
     * 查询用户列表 - 使用从库（继承类级别注解）
     */
    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return userMapper.selectUserList(user);
    }
    
    /**
     * 新增用户 - 使用主库（方法级别覆盖类级别）
     */
    @Override
    @DataSource(DataSourceType.MASTER)
    public int insertUser(SysUser user) {
        return userMapper.insertUser(user);
    }
    
    /**
     * 修改用户 - 使用主库
     */
    @Override
    @DataSource(DataSourceType.MASTER)
    public int updateUser(SysUser user) {
        return userMapper.updateUser(user);
    }
}
```

**工作原理：**

```
方法被 @DataSource 标注
    ↓
DataSourceAspect 切面拦截
    ↓
从注解中获取数据源类型（MASTER/SLAVE）
    ↓
将数据源标识存入 ThreadLocal
    ↓
DynamicDataSource 路由到对应的数据源
    ↓
执行数据库操作
    ↓
方法返回后，清除 ThreadLocal 中的数据源标识
```

---

##### 第二类：安全防护注解

---

###### 三、@RateLimiter 注解完整属性说明

@RateLimiter 用于接口限流，防止接口被恶意刷取或系统过载。

**注解定义：**
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    /** 限流key */
    public String key() default Constants.RATE_LIMIT_KEY;
    /** 限流时间,单位秒 */
    public int time() default 60;
    /** 限流次数 */
    public int count() default 100;
    /** 限流类型 */
    public LimitType limitType() default LimitType.DEFAULT;
}
```

**属性详细说明：**

| 属性名 | 类型 | 默认值 | 说明 |
|--------|------|--------|------|
| `key` | String | `Constants.RATE_LIMIT_KEY` | Redis Key 前缀 |
| `time` | int | `60` | 限流时间窗口（秒） |
| `count` | int | `100` | 时间窗口内允许的最大请求数 |
| `limitType` | LimitType | `LimitType.DEFAULT` | 限流策略类型 |

**LimitType 枚举：**

```java
public enum LimitType {
    DEFAULT,  // 全局限流
    IP        // 按 IP 限流
}
```

**使用示例：**

```java
/**
 * 登录接口 - 防暴力破解
 * 每个 IP 每分钟最多 10 次
 */
@PostMapping("/login")
@RateLimiter(key = "login", time = 60, count = 10, limitType = LimitType.IP)
public AjaxResult login(@RequestBody LoginBody loginBody) {
    // 登录逻辑
}

/**
 * 公开 API - 全局限流
 * 每秒最多 100 次
 */
@GetMapping("/public/data")
@RateLimiter(key = "public_api", time = 1, count = 100, limitType = LimitType.DEFAULT)
public AjaxResult getPublicData() {
    // 查询逻辑
}
```

---

###### 四、@RepeatSubmit 注解完整属性说明

@RepeatSubmit 用于防止表单重复提交，在指定时间间隔内拦截相同参数的重复请求。

**注解定义：**
```java
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {
    /** 间隔时间(ms)，小于此时间视为重复提交 */
    public int interval() default 5000;
    /** 提示消息 */
    public String message() default "不允许重复提交，请稍候再试";
}
```

**属性详细说明：**

| 属性名 | 类型 | 默认值 | 说明 |
|--------|------|--------|------|
| `interval` | int | `5000` | 重复提交判断的时间间隔（毫秒） |
| `message` | String | `"不允许重复提交，请稍候再试"` | 重复提交时的提示消息 |

**工作原理：**

```
请求进入
    ↓
构建唯一标识：URL + 用户ID + 请求参数的 MD5
    ↓
检查 Redis 中是否存在该 Key
    ↓
Key 不存在：
  - 存储 Key，设置过期时间 interval
  - 放行
Key 已存在：
  - 拒绝，抛出异常
```

**使用示例：**

```java
/**
 * 新增用户 - 防重复提交
 * 5 秒内相同参数的请求视为重复提交
 */
@PostMapping
@RepeatSubmit(interval = 5000, message = "您的操作太快了，请 5 秒后再试")
@Log(title = "用户管理", businessType = BusinessType.INSERT)
public AjaxResult add(@RequestBody SysUser user) {
    return toAjax(userService.insertUser(user));
}

/**
 * 提交订单 - 防重复下单
 * 30 秒内不允许重复提交
 */
@PostMapping("/submit")
@RepeatSubmit(interval = 30000, message = "订单正在处理中，请勿重复提交")
public AjaxResult submitOrder(@RequestBody OrderDTO order) {
    return toAjax(orderService.createOrder(order));
}
```

---

##### 第三类：业务辅助注解

---

###### 五、@Excel 注解完整属性说明

@Excel 是一个功能非常强大的注解，用于实体类字段上，控制 Excel 的导入导出行为。该注解共有 **23 个属性**，以下是每个属性的详细说明：

| 属性名             | 类型       | 默认值                       | 说明                                                         | 使用场景                            |
| ------------------ | ---------- | ---------------------------- | ------------------------------------------------------------ | ----------------------------------- |
| `sort`             | int        | `Integer.MAX_VALUE`          | 导出时在 Excel 中的排序                                      | 控制列的显示顺序，值越小越靠前      |
| `name`             | String     | `""`                         | **必填** - 导出到 Excel 中的列名                             | 定义表头显示的文字                  |
| `dateFormat`       | String     | `""`                         | 日期格式，如: `yyyy-MM-dd` 或 `yyyy-MM-dd HH:mm:ss`          | 日期类型字段的格式化                |
| `dictType`         | String     | `""`                         | 字典类型（如: `sys_user_sex`），从数据库字典表获取           | 需要动态字典转换的字段              |
| `readConverterExp` | String     | `""`                         | 读取内容转表达式（如: `0=男,1=女,2=未知`）                   | 静态值的前后端转换                  |
| `separator`        | String     | `","`                        | 分隔符，用于多值字段的分割与合并                             | 多选字段（如角色、权限）            |
| `scale`            | int        | `-1`                         | BigDecimal 精度，默认 -1 表示不开启格式化                    | 金额、百分比等精确数值字段          |
| `roundingMode`     | int        | `BigDecimal.ROUND_HALF_EVEN` | BigDecimal 舍入规则                                          | 配合 `scale` 使用，控制四舍五入方式 |
| `cellType`         | ColumnType | `ColumnType.STRING`          | 导出列类型（NUMERIC=0, STRING=1, IMAGE=2）                   | 控制 Excel 单元格的数据类型         |
| `height`           | double     | `14`                         | 导出时每行的高度（单位：字符）                               | 调整行高                            |
| `width`            | double     | `16`                         | 导出时每列的宽度（单位：字符）                               | 调整列宽                            |
| `suffix`           | String     | `""`                         | 文字后缀，如 `%`，值 90 变成 `90%`                           | 百分比、单位等固定后缀              |
| `defaultValue`     | String     | `""`                         | 当值为空时字段的默认值                                       | 空值替换                            |
| `prompt`           | String     | `""`                         | 提示信息，鼠标悬停时显示                                     | 引导用户填写正确数据                |
| `combo`            | String[]   | `{}`                         | 设置下拉列表选项（仅导入模板时生效）                         | 性别、状态等固定选项                |
| `isExport`         | boolean    | `true`                       | 是否导出数据                                                 | 导出模板时标题需要但内容为空        |
| `targetAttr`       | String     | `""`                         | 另一个类中的属性名称，支持多级获取（以小数点隔开）           | 嵌套对象属性的导出                  |
| `isStatistics`     | boolean    | `false`                      | 是否自动统计数据，在最后追加一行统计总和                     | 数值列的汇总统计                    |
| `align`            | Align      | `Align.AUTO`                 | 导出字段对齐方式（AUTO=0, LEFT=1, CENTER=2, RIGHT=3）        | 控制内容对齐方式                    |
| `handler`          | Class<?>   | `ExcelHandlerAdapter.class`  | 自定义数据处理器类                                           | 复杂的自定义转换逻辑                |
| `args`             | String[]   | `{}`                         | 自定义数据处理器的参数                                       | 配合 `handler` 使用                 |
| `type`             | Type       | `Type.ALL`                   | 字段类型（ALL=0 导出导入, EXPORT=1 仅导出, IMPORT=2 仅导入） | 控制字段在导入/导出时是否生效       |

---

###### 1.1 @Excel 内部枚举类型详细说明

@Excel 注解内部定义了 3 个枚举类型：

**Type 枚举（字段导入导出类型）：**

```java
public enum Type {
    ALL(0),      // 该字段参与导入和导出（默认）
    EXPORT(1),   // 该字段仅参与导出，导入时忽略
    IMPORT(2);   // 该字段仅参与导入，导出时忽略
}
```

**使用场景示例：**

- `type = Type.EXPORT`：敏感字段如密码哈希值，仅用于导出备份，不允许导入修改
- `type = Type.IMPORT`：临时字段，导入时用于计算，导出时不需要

---

**ColumnType 枚举（单元格数据类型）：**

```java
public enum ColumnType {
    NUMERIC(0),   // 数字类型，Excel 中按数值存储
    STRING(1),    // 字符串类型（默认），Excel 中按文本存储
    IMAGE(2);     // 图片类型，值可以是图片路径或字节数组
}
```

**使用场景示例：**

- `cellType = ColumnType.NUMERIC`：年龄、数量、价格等数值字段，Excel 中可以进行公式计算
- `cellType = ColumnType.STRING`：身份证号、手机号、工号等长数字字符串，避免被 Excel 科学计数法显示
- `cellType = ColumnType.IMAGE`：用户头像、产品图片等图片字段

---

**Align 枚举（对齐方式）：**

```java
public enum Align {
    AUTO(0),     // 默认对齐（数值右对齐，文本左对齐）
    LEFT(1),     // 左对齐
    CENTER(2),   // 居中对齐
    RIGHT(3);    // 右对齐
}
```

**使用场景示例：**

- `align = Align.CENTER`：状态、类型等短文本字段，居中显示更美观
- `align = Align.RIGHT`：金额、数量等数值字段，右对齐便于对比

---

###### 1.2 @Excel 属性深度解析与实际应用

**(1) 排序属性 `sort`**

默认值是 `Integer.MAX_VALUE`，意味着不指定时所有字段按类中定义的顺序导出。

**使用示例：**

```java
// 按指定顺序导出：用户ID -> 用户名 -> 邮箱 -> 手机号
@Excel(name = "用户ID", sort = 1)
private Long userId;

@Excel(name = "用户名", sort = 2)
private String userName;

@Excel(name = "邮箱", sort = 3)
private String email;

@Excel(name = "手机号", sort = 4)
private String phonenumber;
```

**注意事项：**

- sort 值越小，列越靠前
- 相同 sort 值的字段按类中定义顺序排列
- 不建议使用 0 作为 sort 值，从 1 开始更清晰

---

**(2) 列名属性 `name`**

这是最重要的属性，定义 Excel 表头显示的文字。

**使用示例：**

```java
@Excel(name = "用户名称")
private String nickName;

@Excel(name = "所属部门")
private String deptName;

@Excel(name = "最后登录IP")
private String loginIp;
```

**特殊用法 - 多列合并（配合 @Excels）：**

当一个字段需要导出多列时（如嵌套对象的多个属性），使用 `@Excels` 容器注解：

```java
@Excels({
    @Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT),
    @Excel(name = "部门负责人", targetAttr = "leader", type = Type.EXPORT)
})
private SysDept dept;
```

---

**(3) 日期格式化 `dateFormat`**

用于 `Date`、`LocalDateTime`、`LocalDate` 类型字段的格式化。

**支持的日期格式：**

| 格式                  | 示例输出            | 说明               |
| --------------------- | ------------------- | ------------------ |
| `yyyy`                | 2024                | 仅年份             |
| `yyyy-MM`             | 2024-04             | 年月               |
| `yyyy-MM-dd`          | 2024-04-25          | 日期               |
| `HH:mm:ss`            | 14:30:00            | 时间               |
| `yyyy-MM-dd HH:mm`    | 2024-04-25 14:30    | 日期时间（到分钟） |
| `yyyy-MM-dd HH:mm:ss` | 2024-04-25 14:30:00 | 完整日期时间       |
| `yyyy/MM/dd`          | 2024/04/25          | 斜杠分隔           |
| `yyyy年MM月dd日`      | 2024年04月25日      | 中文格式           |

**使用示例：**

```java
@Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
private Date createTime;

@Excel(name = "出生日期", dateFormat = "yyyy年MM月dd日")
private LocalDate birthday;

@Excel(name = "最后登录时间", dateFormat = "yyyy-MM-dd HH:mm")
private LocalDateTime loginDate;
```

**导入时的行为：**

- 导入时会根据 `dateFormat` 解析 Excel 中的日期字符串
- 同时也支持 Excel 原生的日期格式（单元格格式为日期）

---

**(4) 字典转换 `dictType`**

从数据库字典表（`sys_dict_type` 和 `sys_dict_data`）动态获取值标签映射。

**工作原理：**

1. 导出时：根据 `dictType` 从 Redis 缓存获取字典数据，将值（如 `0`）转换为标签（如 `男`）
2. 导入时：根据 `dictType` 反向查找，将标签（如 `男`）转换为值（如 `0`）

**使用示例：**

假设字典表 `sys_user_sex` 定义如下：

| dict_value | dict_label |
| ---------- | ---------- |
| 0          | 男         |
| 1          | 女         |
| 2          | 未知       |

```java
// 实体类中
@Excel(name = "用户性别", dictType = "sys_user_sex")
private String sex;  // 数据库存储 0/1/2

// 导出结果：
// sex="0"  → Excel 显示 "男"
// sex="1"  → Excel 显示 "女"

// 导入时：
// Excel 输入 "男"  → 转换为 "0" 存入数据库
// Excel 输入 "女"  → 转换为 "1" 存入数据库
```

**与 `readConverterExp` 的区别：**

| 特性     | dictType               | readConverterExp     |
| -------- | ---------------------- | -------------------- |
| 数据来源 | 数据库字典表（动态）   | 注解中硬编码（静态） |
| 可维护性 | 高，后台管理界面可修改 | 低，需改代码重新部署 |
| 适用场景 | 可能变化的业务数据     | 固定不变的系统状态   |
| 性能     | 需访问 Redis           | 直接字符串匹配       |

**最佳实践：**

- 用户状态、性别、部门类型等**可能变化**的数据 → 使用 `dictType`
- 成功/失败、是/否等**绝对固定**的状态 → 使用 `readConverterExp`

---

**(5) 静态值转换 `readConverterExp`**

在注解中硬编码值与标签的映射关系，适用于固定不变的状态。

**格式：**

```
值1=标签1,值2=标签2,值3=标签3
```

**使用示例：**

```java
// 用户状态
@Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
private String status;  // 0-正常，1-停用

// 导出时：status="0" → "正常"，status="1" → "停用"
// 导入时："正常" → "0"，"停用" → "1"
```

**多值字段（配合 `separator`）：**

对于多选字段，值以分隔符存储，导出时转换为标签拼接：

```java
// 角色ID以逗号分隔存储："1,2,3"
@Excel(name = "角色", readConverterExp = "1=管理员,2=普通用户,3=访客", separator = ",")
private String roleIds;

// 导出结果："管理员,普通用户,访客"
// 导入时："管理员,普通用户" → "1,2"
```

**高级用法 - 复杂表达式：**

支持空格、特殊字符（但等号和逗号作为分隔符，不能出现在值或标签中）：

```java
@Excel(name = "审核状态", readConverterExp = "0=待提交,1=待审核,2=审核通过,3=审核驳回,4=已撤销")
private String auditStatus;
```

---

**(6) 分隔符 `separator`**

用于多值字段的分割与合并，默认值为逗号 `,`。

**工作原理：**

- **导出时**：将数据库中的 `1,2,3` 按分隔符拆分，每个值通过 `readConverterExp` 或 `dictType` 转换，再用分隔符拼接标签
- **导入时**：将 Excel 中的 `管理员,普通用户` 按分隔符拆分，每个标签反向查找值，再用分隔符拼接值

**使用示例：**

```java
// 场景：用户拥有多个角色，角色ID以逗号分隔存储

// 使用默认分隔符逗号
@Excel(name = "角色", readConverterExp = "1=管理员,2=编辑,3=查看者", separator = ",")
private String roleIds;  // 数据库值："1,3"

// 导出结果："管理员,查看者"
```

**自定义分隔符：**

```java
// 使用分号作为分隔符
@Excel(name = "标签", readConverterExp = "1=重要,2=紧急,3=普通", separator = ";")
private String tags;  // 数据库值："1;2"

// 导出结果："重要;紧急"
```

**注意事项：**

- `separator` 必须与 `readConverterExp` 或 `dictType` 配合使用才生效
- 分隔符不能出现在值或标签中，否则会解析错误
- 建议使用逗号、分号、竖线等不常见于业务数据的字符

---

**(7) BigDecimal 精度控制 `scale` 和 `roundingMode`**

用于精确数值类型（金额、百分比、税率等）的格式化。

**`scale` 属性：**

- 默认值 `-1`：不进行 BigDecimal 格式化，保持原值输出
- 正值 `n`：保留 `n` 位小数

**`roundingMode` 属性：**
舍入规则，对应 `BigDecimal` 的常量：

| 常量值 | 舍入模式          | 说明                             |
| ------ | ----------------- | -------------------------------- |
| 0      | ROUND_UP          | 远离零方向舍入（1.1→2, -1.1→-2） |
| 1      | ROUND_DOWN        | 接近零方向舍入（1.9→1, -1.9→-1） |
| 2      | ROUND_CEILING     | 向正无穷方向舍入                 |
| 3      | ROUND_FLOOR       | 向负无穷方向舍入                 |
| 4      | **ROUND_HALF_UP** | **四舍五入**（最常用）           |
| 5      | ROUND_HALF_DOWN   | 五舍六入                         |
| 6      | ROUND_HALF_EVEN   | 银行家舍入法（四舍六入五取偶）   |
| 7      | ROUND_UNNECESSARY | 断言请求的操作具有精确结果       |

**使用示例：**

```java
// 金额：保留2位小数，四舍五入
@Excel(name = "订单金额", scale = 2, roundingMode = BigDecimal.ROUND_HALF_UP)
private BigDecimal orderAmount;

// 百分比：保留0位小数（整数）
@Excel(name = "折扣率", scale = 0, suffix = "%")
private BigDecimal discountRate;

// 税率：保留3位小数
@Excel(name = "税率", scale = 3)
private BigDecimal taxRate;
```

**实际效果：**

| 原始值  | scale | roundingMode  | 导出结果 |
| ------- | ----- | ------------- | -------- |
| 123.456 | 2     | ROUND_HALF_UP | 123.46   |
| 123.454 | 2     | ROUND_HALF_UP | 123.45   |
| 123.456 | 0     | ROUND_HALF_UP | 123      |
| 89.5    | 0     | ROUND_HALF_UP | 90       |

---

**(8) 单元格类型 `cellType`**

控制 Excel 中单元格的数据类型，影响 Excel 的显示和行为。

**三种类型的详细说明：**

| 类型        | 枚举值 | 说明             | 适用场景                         |
| ----------- | ------ | ---------------- | -------------------------------- |
| **STRING**  | 1      | 文本类型（默认） | 身份证、手机号、工号等长数字     |
| **NUMERIC** | 0      | 数值类型         | 年龄、数量、金额等需要计算的数值 |
| **IMAGE**   | 2      | 图片类型         | 用户头像、产品图片               |

**为什么需要 STRING 类型？**

Excel 默认会对长数字进行科学计数法显示，例如：

- 身份证号 `110101199001011234` → Excel 默认显示为 `1.10101E+17`
- 手机号 `13800138000` → 可能显示为 `1.38001E+10`

使用 `cellType = ColumnType.STRING` 可以强制按文本存储，保持原样显示。

**使用示例：**

```java
// 身份证号：必须用 STRING 类型，避免科学计数法
@Excel(name = "身份证号", cellType = ColumnType.STRING)
private String idCard;

// 年龄：数值类型，Excel 中可以求和、平均
@Excel(name = "年龄", cellType = ColumnType.NUMERIC)
private Integer age;

// 头像：图片类型
@Excel(name = "头像", cellType = ColumnType.IMAGE)
private String avatar;  // 可以是图片路径或字节数组
```

**IMAGE 类型的特殊说明：**

`cellType = ColumnType.IMAGE` 支持两种输入格式：

1. **图片路径字符串**：

   ```java
   @Excel(name = "产品图片", cellType = ColumnType.IMAGE, width = 30, height = 30)
   private String productImage;  // 值如："/profile/upload/2024/04/25/image.jpg"
   ```

2. **字节数组**：

   ```java
   @Excel(name = "缩略图", cellType = ColumnType.IMAGE)
   private byte[] thumbnail;
   ```

**导出时的行为：**

- 图片会被插入到 Excel 单元格中
- 可以通过 `width` 和 `height` 调整图片显示大小
- 大图片建议设置较大的列宽和行高

---

**(9) 行列尺寸 `width` 和 `height`**

控制 Excel 中列的宽度和行的高度。

**`width` 属性（列宽）：**

- 默认值：`16`（字符）
- 单位：字符（一个英文字符宽度）
- 建议范围：`8` ~ `50`

**`height` 属性（行高）：**

- 默认值：`14`（字符）
- 单位：字符
- 图片行建议设置较大值

**使用示例：**

```java
// 普通文本列：默认宽度即可
@Excel(name = "用户名")
private String userName;

// 邮箱地址：通常较长，增加宽度
@Excel(name = "邮箱", width = 25)
private String email;

// 备注：可能很长，设置较大宽度
@Excel(name = "备注", width = 40)
private String remark;

// 头像：图片列，设置较大的宽高
@Excel(name = "头像", cellType = ColumnType.IMAGE, width = 20, height = 25)
private String avatar;
```

**常见内容的建议宽度：**

| 内容类型    | 建议宽度 | 说明                |
| ----------- | -------- | ------------------- |
| 状态/类型   | 8-10     | 短文本              |
| 用户名/姓名 | 12-15    | 中等长度            |
| 手机号      | 13       | 固定11位            |
| 邮箱        | 20-25    | 地址较长            |
| 地址/备注   | 30-50    | 长文本              |
| 日期时间    | 18-20    | yyyy-MM-dd HH:mm:ss |
| 图片        | 20-30    | 图片显示            |

---

**(10) 文本后缀 `suffix`**

在导出的值后面添加固定后缀，常用于百分比、单位等。

**使用示例：**

```java
// 百分比：值 85 变成 "85%"
@Excel(name = "完成率", suffix = "%")
private Integer completionRate;  // 85

// 金额单位
@Excel(name = "预算", suffix = "万元")
private BigDecimal budget;  // 100 → "100万元"

// 时长
@Excel(name = "时长", suffix = "小时")
private Double duration;  // 8.5 → "8.5小时"
```

**注意事项：**

- 添加后缀后，单元格类型会变成 STRING，无法再进行数值计算
- 如果需要计算，建议不使用 suffix，而是在 Excel 中设置单元格格式

---

**(11) 默认值 `defaultValue`**

当字段值为 null 或空字符串时，使用默认值填充。

**使用示例：**

```java
// 用户状态：空值显示为"未知"
@Excel(name = "状态", readConverterExp = "0=正常,1=停用", defaultValue = "未知")
private String status;

// 备注：空值显示为"暂无备注"
@Excel(name = "备注", defaultValue = "暂无备注")
private String remark;

// 数值字段：空值显示为 0
@Excel(name = "浏览量", defaultValue = "0")
private Integer viewCount;
```

**生效条件：**

- 导出时：字段值为 `null` 或空字符串 `""` 时生效
- 导入时：Excel 单元格为空时，使用默认值填充

---

**(12) 提示信息 `prompt` 和下拉列表 `combo`**

这两个属性主要用于**导入模板**，引导用户正确填写数据。

**`prompt` - 提示信息：**

- 鼠标悬停在单元格上时显示
- 类似 Excel 的"数据验证"中的输入提示

**`combo` - 下拉列表：**

- 提供固定选项供用户选择
- 类似 Excel 的"数据验证"中的序列

**使用示例：**

```java
// 性别：下拉列表 + 提示
@Excel(name = "性别", 
       readConverterExp = "0=男,1=女,2=未知",
       combo = {"男", "女", "未知"},
       prompt = "请选择性别：男/女/未知")
private String sex;

// 状态：下拉列表
@Excel(name = "状态",
       readConverterExp = "0=正常,1=停用",
       combo = {"正常", "停用"})
private String status;

// 优先级：提示信息
@Excel(name = "优先级",
       prompt = "请输入 1-5 的数字，1 最高，5 最低")
private Integer priority;
```

**导出导入模板时的效果：**

```java
// Controller 中导出模板
@GetMapping("/importTemplate")
public void importTemplate(HttpServletResponse response) {
    ExcelUtil<SysUser> util = new ExcelUtil<>(SysUser.class);
    util.importTemplateExcel(response, "用户数据");
}
```

生成的 Excel 模板特点：

1. 没有数据行，只有表头
2. 设置了 `combo` 的列有下拉列表
3. 设置了 `prompt` 的列有悬停提示

---

**(13) 是否导出 `isExport`**

控制字段是否导出数据，主要用于导出模板的场景。

**属性值：**

- `true`（默认）：导出数据
- `false`：只导出表头，不导出数据

**使用场景：**

导出导入模板时，有些列需要显示标题（引导用户填写），但实际数据不需要导出。

```java
public class UserImportTemplate {
    
    // 必须字段：导出模板时显示标题，也导出数据
    @Excel(name = "用户名")
    private String userName;
    
    @Excel(name = "手机号")
    private String phonenumber;
    
    // 提示字段：只导出模板标题，不导出数据
    // 用于提示用户如何填写，实际数据中不存在
    @Excel(name = "填写说明", isExport = false)
    private String fillInstruction;
}
```

**注意：**

- `isExport = false` 时，导出时该列会被跳过
- 但在导入模板中，该列仍会显示表头

---

**(14) 嵌套对象属性 `targetAttr`**

用于导出嵌套对象的属性，支持多级访问（以小数点 `.` 分隔）。

**使用场景：**

当实体类中包含另一个对象作为属性，需要导出该对象的某些字段。

**示例场景：**

```java
// 用户实体
public class SysUser {
    @Excel(name = "用户名")
    private String userName;
    
    @Excel(name = "邮箱")
    private String email;
    
    // 所属部门对象
    private SysDept dept;
}

// 部门实体
public class SysDept {
    private Long deptId;
    private String deptName;
    private String leader;
    private String phone;
}
```

**不使用 targetAttr 的问题：**

直接在 `dept` 字段上添加 `@Excel` 无法指定导出哪个属性。

**使用 @Excels + targetAttr 解决：**

```java
public class SysUser {
    @Excel(name = "用户名", sort = 1)
    private String userName;
    
    @Excel(name = "邮箱", sort = 2)
    private String email;
    
    // 导出部门的多个属性
    @Excels({
        @Excel(name = "部门名称", targetAttr = "deptName", sort = 3),
        @Excel(name = "部门负责人", targetAttr = "leader", sort = 4),
        @Excel(name = "部门电话", targetAttr = "phone", sort = 5)
    })
    private SysDept dept;
}
```

**多级嵌套示例：**

```java
// 用户 -> 部门 -> 区域
public class SysUser {
    @Excel(name = "用户名")
    private String userName;
    
    // 访问 dept.region.regionName
    @Excel(name = "所属区域", targetAttr = "dept.region.regionName")
    private SysDept dept;
}

public class SysDept {
    private String deptName;
    private SysRegion region;  // 继续嵌套
}

public class SysRegion {
    private String regionName;
    private String regionCode;
}
```

**工作原理：**

导出时通过反射链获取属性值：

```
user.getDept() → dept.getRegion() → region.getRegionName()
```

**注意事项：**

- 任何一级属性为 `null`，结果为空字符串
- 不支持集合类型（List、Set），只支持单个对象
- 配合 `@Excels` 可以从一个嵌套对象导出多个列

---

**(15) 自动统计 `isStatistics`**

对数值列自动求和，在导出的 Excel 最后追加一行统计数据。

**使用示例：**

```java
public class OrderReport {
    
    @Excel(name = "订单编号")
    private String orderNo;
    
    @Excel(name = "客户名称")
    private String customerName;
    
    // 订单金额：需要统计
    @Excel(name = "订单金额", isStatistics = true)
    private BigDecimal orderAmount;
    
    // 商品数量：需要统计
    @Excel(name = "商品数量", isStatistics = true)
    private Integer quantity;
    
    @Excel(name = "下单时间", dateFormat = "yyyy-MM-dd")
    private Date orderTime;
}
```

**导出效果：**

| 订单编号 | 客户名称 | 订单金额    | 商品数量 | 下单时间   |
| -------- | -------- | ----------- | -------- | ---------- |
| ORD001   | 客户A    | 1000.00     | 10       | 2024-04-01 |
| ORD002   | 客户B    | 2500.00     | 25       | 2024-04-02 |
| ORD003   | 客户A    | 800.00      | 8        | 2024-04-03 |
| **合计** |          | **4300.00** | **43**   |            |

**统计行特点：**

- 第一列显示"合计"
- 只有 `isStatistics = true` 的列才会统计
- 统计值保留原列的 `scale` 设置
- 不支持非数值类型的统计

**支持统计的字段类型：**

- `BigDecimal`
- `Integer`、`int`
- `Long`、`long`
- `Double`、`double`
- `Float`、`float`

**注意事项：**

- 空值按 0 处理
- 非数值类型设置 `isStatistics = true` 不会报错，但统计值为 0
- 统计行在所有数据行之后，作为最后一行

---

**(16) 对齐方式 `align`**

控制 Excel 单元格内容的对齐方式。

**对齐选项：**

| 枚举值         | 对齐方式 | 效果示意               |
| -------------- | -------- | ---------------------- |
| `Align.AUTO`   | 默认对齐 | 数值右对齐，文本左对齐 |
| `Align.LEFT`   | 左对齐   | `[内容        ]`       |
| `Align.CENTER` | 居中对齐 | `[   内容   ]`         |
| `Align.RIGHT`  | 右对齐   | `[        内容]`       |

**使用示例：**

```java
public class Product {
    
    @Excel(name = "商品编号", align = Align.CENTER)
    private String productCode;  // 编号居中显示
    
    @Excel(name = "商品名称")  // 默认：文本左对齐
    private String productName;
    
    @Excel(name = "单价", align = Align.RIGHT)  // 金额右对齐
    private BigDecimal price;
    
    @Excel(name = "库存数量", align = Align.RIGHT)  // 数量右对齐
    private Integer stock;
    
    @Excel(name = "状态", align = Align.CENTER)  // 状态居中
    private String status;
}
```

**最佳实践建议：**

- **编号、状态、类型**等短文本 → `Align.CENTER` 居中
- **名称、描述**等长文本 → 默认左对齐或 `Align.LEFT`
- **金额、数量、百分比**等数值 → `Align.RIGHT` 右对齐
- **日期时间** → 默认或 `Align.CENTER`

---

**(17) 自定义数据处理器 `handler` 和 `args`**

当标准的 `dictType`、`readConverterExp` 无法满足需求时，使用自定义处理器。

**适用场景：**

- 复杂的业务逻辑转换
- 需要访问数据库或其他服务
- 动态计算的值
- 多字段组合转换

**处理器接口：**

```java
public interface ExcelHandlerAdapter {
    /**
     * 格式化
     * @param value 单元格数据值
     * @param args excel注解args参数组
     * @return 处理后的值
     */
    Object format(Object value, String[] args);
}
```

**使用示例：**

**场景：将性别码转换为 emoji 图标**

```java
// 1. 自定义处理器
public class GenderEmojiHandler implements ExcelHandlerAdapter {
    
    @Override
    public Object format(Object value, String[] args) {
        if (value == null) {
            return "";
        }
        String gender = String.valueOf(value);
        switch (gender) {
            case "0": return "👨 男";
            case "1": return "👩 女";
            case "2": return "❓ 未知";
            default: return value;
        }
    }
}

// 2. 使用自定义处理器
public class SysUser {
    @Excel(name = "用户名")
    private String userName;
    
    // 使用自定义处理器转换性别
    @Excel(name = "性别", 
           handler = GenderEmojiHandler.class,
           args = {"可选参数1", "可选参数2"})
    private String sex;
}
```

**更复杂的示例：动态获取部门路径**

```java
// 处理器：获取部门完整路径（如：总公司 -> 技术部 -> 研发组）
public class DeptPathHandler implements ExcelHandlerAdapter {
    
    @Autowired
    private ISysDeptService deptService;  // 可以注入 Spring Bean
    
    @Override
    public Object format(Object value, String[] args) {
        if (value == null) {
            return "";
        }
        Long deptId = Long.valueOf(value.toString());
        // 查询部门完整路径
        String deptPath = deptService.getDeptPath(deptId);
        return deptPath;
    }
}

// 使用
@Excel(name = "所属部门路径", handler = DeptPathHandler.class)
private Long deptId;
```

**`args` 参数的使用：**

```java
// 处理器：根据参数决定格式化方式
public class MoneyFormatHandler implements ExcelHandlerAdapter {
    
    @Override
    public Object format(Object value, String[] args) {
        if (value == null) return "";
        
        BigDecimal amount = new BigDecimal(value.toString());
        
        // args[0] 指定货币符号
        String symbol = args.length > 0 ? args[0] : "¥";
        // args[1] 指定小数位数
        int scale = args.length > 1 ? Integer.parseInt(args[1]) : 2;
        
        return symbol + amount.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }
}

// 使用不同参数
@Excel(name = "人民币金额", 
       handler = MoneyFormatHandler.class, 
       args = {"¥", "2"})
private BigDecimal rmbAmount;  // 输出：¥100.00

@Excel(name = "美元金额", 
       handler = MoneyFormatHandler.class, 
       args = {"$", "2"})
private BigDecimal usdAmount;  // 输出：$150.50
```

**注意事项：**

- 处理器类必须有**无参构造函数**
- 导出和导入时都会调用 `format` 方法
- 如果需要区分导出/导入，可以在 `args` 中传递标识
- 处理器可以访问 Spring 容器（通过 `SpringUtils` 获取 Bean）

---

**(18) 导入导出类型 `type`**

控制字段在导入和导出时是否生效。

**类型选项：**

| 类型          | 枚举值 | 说明                     |
| ------------- | ------ | ------------------------ |
| `Type.ALL`    | 0      | 导入和导出都生效（默认） |
| `Type.EXPORT` | 1      | 仅导出时生效，导入时忽略 |
| `Type.IMPORT` | 2      | 仅导入时生效，导出时忽略 |

**使用场景示例：**

```java
public class SysUser {
    
    // ===== 导入导出都需要的字段 =====
    
    @Excel(name = "用户名")
    private String userName;
    
    @Excel(name = "手机号")
    private String phonenumber;
    
    @Excel(name = "邮箱")
    private String email;
    
    // ===== 仅导出的字段 =====
    
    // 密码哈希：敏感信息，仅用于导出备份，不允许导入修改
    @Excel(name = "密码哈希", type = Type.EXPORT)
    private String password;
    
    // 创建时间：系统自动生成，不允许导入时修改
    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private Date createTime;
    
    // 创建者：系统自动记录
    @Excel(name = "创建者", type = Type.EXPORT)
    private String createBy;
    
    // ===== 仅导入的字段 =====
    
    // 临时密码：导入时设置初始密码，导出时不需要
    @Excel(name = "初始密码", type = Type.IMPORT)
    private String tempPassword;
    
    // 导入时的部门名称：用于匹配查找部门ID
    @Excel(name = "部门名称", type = Type.IMPORT)
    private String deptName;
    
    // 实际存储的部门ID
    private Long deptId;
}
```

**导入时的处理逻辑示例：**

```java
@PostMapping("/importData")
public AjaxResult importData(MultipartFile file) throws Exception {
    ExcelUtil<SysUser> util = new ExcelUtil<>(SysUser.class);
    List<SysUser> userList = util.importExcel(file.getInputStream());
    
    for (SysUser user : userList) {
        // 处理仅导入的字段
        if (StringUtils.isNotEmpty(user.getDeptName())) {
            // 根据部门名称查找部门ID
            Long deptId = deptService.selectDeptIdByName(user.getDeptName());
            user.setDeptId(deptId);
        }
        
        if (StringUtils.isNotEmpty(user.getTempPassword())) {
            // 加密临时密码
            String password = SecurityUtils.encryptPassword(user.getTempPassword());
            user.setPassword(password);
        }
        
        // 保存用户...
    }
    
    return AjaxResult.success();
}
```

**最佳实践：**

| 场景                 | 建议类型      | 原因                           |
| -------------------- | ------------- | ------------------------------ |
| 普通业务字段         | `Type.ALL`    | 导入导出都需要                 |
| 系统自动生成字段     | `Type.EXPORT` | 创建时间、创建者等不应手动修改 |
| 敏感字段（密码哈希） | `Type.EXPORT` | 仅用于备份，不允许导入覆盖     |
| 导入时的临时字段     | `Type.IMPORT` | 用于匹配查找，不实际存储       |
| 计算得出的字段       | `Type.EXPORT` | 导出时展示，导入时重新计算     |

---

###### 1.3 @Excels 容器注解说明

当一个字段需要使用多个 `@Excel` 注解时（如导出嵌套对象的多个属性），使用 `@Excels` 作为容器。

**定义：**

```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Excels {
    Excel[] value();
}
```

**使用示例：**

```java
public class SysUser {
    
    private String userName;
    
    // 从 dept 对象导出多个属性
    @Excels({
        @Excel(name = "部门ID", targetAttr = "deptId", sort = 1),
        @Excel(name = "部门名称", targetAttr = "deptName", sort = 2),
        @Excel(name = "部门负责人", targetAttr = "leader", sort = 3),
        @Excel(name = "部门电话", targetAttr = "phone", sort = 4)
    })
    private SysDept dept;
}
```

**与单独使用 @Excel 的区别：**

| 特性            | @Excel   | @Excels            |
| --------------- | -------- | ------------------ |
| 注解数量        | 1 个     | 多个               |
| 目标字段        | 当前字段 | 嵌套对象的多个属性 |
| 配合 targetAttr | 可选     | 必须配合           |

**常见使用场景：**

1. 嵌套对象的多属性导出
2. 同一字段的多格式导出（不同 type）
3. 复杂的数据结构展开

---

###### 1.4 @Excel 完整使用示例

以下是一个完整的实体类示例，展示了各种属性的组合使用：

```java
package com.ktg.mes.md.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.ktg.common.annotation.Excel;
import com.ktg.common.annotation.Excels;
import com.ktg.common.annotation.Excel.*;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 产品信息对象 md_product
 */
public class MdProduct extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 产品ID */
    @Excel(name = "产品ID", sort = 1, cellType = ColumnType.NUMERIC)
    private Long productId;

    /** 产品编码 */
    @Excel(name = "产品编码", sort = 2, cellType = ColumnType.STRING, width = 20)
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称", sort = 3, width = 25)
    private String productName;

    /** 产品规格 */
    @Excel(name = "产品规格", sort = 4, width = 20)
    private String specification;

    /** 单位 */
    @Excel(name = "单位", sort = 5, combo = {"个", "件", "台", "套", "箱"}, prompt = "请选择单位")
    private String unitOfMeasure;

    /** 产品类型（字典） */
    @Excel(name = "产品类型", sort = 6, dictType = "md_product_type")
    private String productType;

    /** 状态（静态转换） */
    @Excel(name = "状态", sort = 7, 
           readConverterExp = "0=启用,1=停用,2=淘汰",
           combo = {"启用", "停用", "淘汰"})
    private String status;

    /** 销售价格 */
    @Excel(name = "销售价格", sort = 8, 
           scale = 2, roundingMode = BigDecimal.ROUND_HALF_UP,
           cellType = ColumnType.NUMERIC, align = Align.RIGHT,
           isStatistics = true)
    private BigDecimal salePrice;

    /** 成本价格 */
    @Excel(name = "成本价格", sort = 9,
           scale = 4, cellType = ColumnType.NUMERIC, align = Align.RIGHT)
    private BigDecimal costPrice;

    /** 毛利率 */
    @Excel(name = "毛利率", sort = 10, suffix = "%", align = Align.RIGHT)
    private BigDecimal grossMargin;

    /** 安全库存 */
    @Excel(name = "安全库存", sort = 11, 
           cellType = ColumnType.NUMERIC, align = Align.RIGHT,
           defaultValue = "0")
    private Integer safetyStock;

    /** 最大库存 */
    @Excel(name = "最大库存", sort = 12,
           cellType = ColumnType.NUMERIC, align = Align.RIGHT,
           defaultValue = "99999")
    private Integer maxStock;

    /** 负责人 */
    @Excel(name = "负责人", sort = 13)
    private String responsiblePerson;

    /** 生效日期 */
    @Excel(name = "生效日期", sort = 14, dateFormat = "yyyy-MM-dd")
    private Date effectiveDate;

    /** 失效日期 */
    @Excel(name = "失效日期", sort = 15, dateFormat = "yyyy-MM-dd")
    private Date expiryDate;

    /** 所属分类（嵌套对象） */
    @Excels({
        @Excel(name = "分类编码", targetAttr = "categoryCode", sort = 16, width = 15),
        @Excel(name = "分类名称", targetAttr = "categoryName", sort = 17, width = 20)
    })
    private MdProductCategory category;

    /** 产品图片 */
    @Excel(name = "产品图片", sort = 18, 
           cellType = ColumnType.IMAGE,
           width = 15, height = 30)
    private String productImage;

    /** 备注 */
    @Excel(name = "备注", sort = 19, width = 40)
    private String remark;

    /** ===== 仅导出的系统字段 ===== */
    
    /** 创建时间 */
    @Excel(name = "创建时间", sort = 20,
           dateFormat = "yyyy-MM-dd HH:mm:ss",
           type = Type.EXPORT)
    private Date createTime;

    /** 创建者 */
    @Excel(name = "创建者", sort = 21, type = Type.EXPORT)
    private String createBy;

    /** 更新时间 */
    @Excel(name = "更新时间", sort = 22,
           dateFormat = "yyyy-MM-dd HH:mm:ss",
           type = Type.EXPORT)
    private Date updateTime;

    /** 更新者 */
    @Excel(name = "更新者", sort = 23, type = Type.EXPORT)
    private String updateBy;

    // ===== getter/setter 省略 =====
}
```

**导出效果说明：**

| 列顺序 | 列名     | 说明                                      |
| ------ | -------- | ----------------------------------------- |
| 1      | 产品ID   | 数值类型                                  |
| 2      | 产品编码 | 文本类型（避免长数字科学计数法），列宽 20 |
| 3      | 产品名称 | 列宽 25                                   |
| 4      | 产品规格 | 列宽 20                                   |
| 5      | 单位     | 下拉列表（个/件/台/套/箱），有悬停提示    |
| 6      | 产品类型 | 从字典 `md_product_type` 动态转换         |
| 7      | 状态     | 静态转换 0=启用,1=停用,2=淘汰，下拉列表   |
| 8      | 销售价格 | 保留2位小数，右对齐，自动统计求和         |
| 9      | 成本价格 | 保留4位小数，右对齐                       |
| 10     | 毛利率   | 添加 % 后缀，右对齐                       |
| 11     | 安全库存 | 数值类型，空值默认为 0                    |
| 12     | 最大库存 | 数值类型，空值默认为 99999                |
| 13     | 负责人   | 普通文本                                  |
| 14     | 生效日期 | 格式 yyyy-MM-dd                           |
| 15     | 失效日期 | 格式 yyyy-MM-dd                           |
| 16     | 分类编码 | 从嵌套对象 category.categoryCode 获取     |
| 17     | 分类名称 | 从嵌套对象 category.categoryName 获取     |
| 18     | 产品图片 | 图片类型，列宽 15，行高 30                |
| 19     | 备注     | 列宽 40                                   |
| 20-23  | 系统字段 | 仅导出时显示，导入时忽略                  |

---

###### 1.5 @Excel 属性优先级与冲突处理

当多个属性可能产生冲突时，遵循以下优先级规则：

**优先级从高到低：**

1. **`handler` 自定义处理器**（最高优先级）
   - 如果指定了 `handler`，其他转换属性可能被忽略
   - 取决于处理器的实现

2. **`dictType` 字典转换**
   - 优先于 `readConverterExp`

3. **`readConverterExp` 静态转换**
   - 优先于直接值输出

4. **`dateFormat` 日期格式化**
   - 仅对日期类型字段生效

5. **`scale` + `roundingMode` 数值格式化**
   - 仅对 BigDecimal 类型生效

**冲突处理示例：**

```java
// 场景：同时设置了 dictType 和 readConverterExp
// 结果：dictType 优先级更高，readConverterExp 被忽略
@Excel(name = "状态", 
       dictType = "sys_user_status",  // 优先级高
       readConverterExp = "0=正常,1=停用")  // 优先级低，被忽略
private String status;


// 场景：同时设置了 handler 和其他属性
// 结果：handler 完全控制转换，其他属性可能无效
@Excel(name = "金额",
       handler = CustomMoneyHandler.class,  // 完全控制
       dictType = "md_currency",            // 可能被忽略
       scale = 2)                            // 可能被忽略
private BigDecimal amount;
```

**最佳实践：**

- 同一字段不要同时设置 `dictType` 和 `readConverterExp`
- 使用 `handler` 时确保理解其内部逻辑
- 简单场景优先使用 `readConverterExp`
- 动态数据使用 `dictType`
- 复杂逻辑使用 `handler`

---

---

##### 二、@Log 注解完整属性说明

@Log 注解用于标记需要记录操作日志的方法，自动记录模块名称、操作类型、请求参数、响应参数等信息。

**注解定义：**

```java
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /** 模块 */
    public String title() default "";
    /** 功能 */
    public BusinessType businessType() default BusinessType.OTHER;
    /** 操作人类别 */
    public OperatorType operatorType() default OperatorType.MANAGE;
    /** 是否保存请求的参数 */
    public boolean isSaveRequestData() default true;
    /** 是否保存响应的参数 */
    public boolean isSaveResponseData() default true;
}
```

---

###### 2.1 @Log 属性详细解析

| 属性名               | 类型         | 默认值                | 说明                                           |
| -------------------- | ------------ | --------------------- | ---------------------------------------------- |
| `title`              | String       | `""`                  | **模块名称**，用于标识操作所属的业务模块       |
| `businessType`       | BusinessType | `BusinessType.OTHER`  | **业务操作类型**，如新增、修改、删除、导出等   |
| `operatorType`       | OperatorType | `OperatorType.MANAGE` | **操作人类别**，区分后台用户、手机端用户       |
| `isSaveRequestData`  | boolean      | `true`                | **是否保存请求参数**，敏感接口可设置为 false   |
| `isSaveResponseData` | boolean      | `true`                | **是否保存响应参数**，大响应数据可设置为 false |

---

###### 2.2 BusinessType 业务操作类型枚举

BusinessType 枚举定义了系统支持的所有业务操作类型：

```java
public enum BusinessType {
    OTHER,      // 其它（默认）
    INSERT,     // 新增
    UPDATE,     // 修改
    DELETE,     // 删除
    GRANT,      // 授权
    EXPORT,     // 导出
    IMPORT,     // 导入
    FORCE,      // 强退
    GENCODE,    // 生成代码
    CLEAN       // 清空数据
}
```

**各类型使用场景：**

| 枚举值    | 使用场景 | 典型 Controller 方法         |
| --------- | -------- | ---------------------------- |
| `INSERT`  | 新增数据 | `add()`、`insert()`          |
| `UPDATE`  | 修改数据 | `edit()`、`update()`         |
| `DELETE`  | 删除数据 | `remove()`、`delete()`       |
| `GRANT`   | 权限授权 | `editRole()`、`updateAuth()` |
| `EXPORT`  | 导出数据 | `export()`                   |
| `IMPORT`  | 导入数据 | `importData()`               |
| `FORCE`   | 强制下线 | `forceLogout()`              |
| `GENCODE` | 代码生成 | `genCode()`                  |
| `CLEAN`   | 清空数据 | `clean()`                    |
| `OTHER`   | 其他操作 | 无法归类的特殊操作           |

---

###### 2.3 OperatorType 操作人类别枚举

OperatorType 枚举定义了操作人的来源：

```java
public enum OperatorType {
    OTHER,      // 其它
    MANAGE,     // 后台用户（默认）
    MOBILE      // 手机端用户
}
```

**使用场景：**

| 枚举值   | 适用场景                             |
| -------- | ------------------------------------ |
| `MANAGE` | PC 端管理后台的所有操作（默认）      |
| `MOBILE` | 移动端 App 或小程序的操作            |
| `OTHER`  | 无法确定来源的操作（如定时任务触发） |

**日志记录中的用途：**

- 用于统计不同端的操作频率
- 安全审计时区分操作来源
- 问题排查时快速定位操作端

---

###### 2.4 isSaveRequestData 和 isSaveResponseData 详解

这两个属性控制日志中是否保存请求和响应数据。

**isSaveRequestData = true（默认）：**

- 日志中会保存请求 URL、请求方法、请求参数
- 请求参数包括：Query 参数、Form 参数、JSON Body

**isSaveRequestData = false 的场景：**

- **登录接口**：请求参数包含密码，不应记录
- **支付接口**：请求参数包含敏感支付信息
- **文件上传**：请求体很大，记录浪费存储空间
- **私密接口**：涉及用户隐私数据

**isSaveResponseData = true（默认）：**

- 日志中会保存响应体（JSON 格式）

**isSaveResponseData = false 的场景：**

- **大数据量查询**：响应体过大，如导出数据的查询
- **文件下载**：响应是二进制流，无法记录
- **敏感数据查询**：如用户隐私信息列表

---

###### 2.5 @Log 完整使用示例

**基础用法示例：**

```java
/**
 * 用户管理 Controller
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    
    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUser user) {
        // 业务逻辑...
        return toAjax(userService.insertUser(user));
    }
    
    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUser user) {
        return toAjax(userService.updateUser(user));
    }
    
    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds) {
        return toAjax(userService.deleteUserByIds(userIds));
    }
    
    /**
     * 导出用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:export')")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUser user) {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<>(SysUser.class);
        util.exportExcel(response, list, "用户数据");
    }
    
    /**
     * 导入用户
     * 注意：不保存请求数据（文件上传请求体很大）
     */
    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @Log(title = "用户管理", businessType = BusinessType.IMPORT, 
         isSaveRequestData = false)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<SysUser> util = new ExcelUtil<>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        return AjaxResult.success(userService.importUser(userList));
    }
    
    /**
     * 用户授权
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @PutMapping("/authRole")
    public AjaxResult insertAuthRole(@RequestBody SysUser user) {
        return toAjax(userService.insertAuthRole(user));
    }
}
```

**登录接口示例（不保存请求参数）：**

```java
/**
 * 登录接口
 * 注意：isSaveRequestData = false，避免记录密码
 */
@PostMapping("/login")
@Log(title = "登录", businessType = BusinessType.OTHER, 
     isSaveRequestData = false)
public AjaxResult login(@RequestBody LoginBody loginBody) {
    AjaxResult ajax = AjaxResult.success();
    // 登录逻辑...
    return ajax;
}
```

**移动端接口示例：**

```java
/**
 * 移动端用户接口
 */
@RestController
@RequestMapping("/mobile/user")
public class MobileUserController {
    
    /**
     * 移动端修改个人信息
     * operatorType = OperatorType.MOBILE 标记为手机端操作
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE,
         operatorType = OperatorType.MOBILE)
    @PutMapping("/profile")
    public AjaxResult updateProfile(@RequestBody SysUser user) {
        return toAjax(userService.updateUserProfile(user));
    }
}
```

---

###### 2.6 日志记录的数据结构

当方法被 `@Log` 标记后，AOP 切面会自动记录以下信息到日志表（`sys_oper_log`）：

| 字段             | 说明       | 数据来源                             |
| ---------------- | ---------- | ------------------------------------ |
| `title`          | 模块标题   | `@Log.title()`                       |
| `business_type`  | 业务类型   | `@Log.businessType()`                |
| `method`         | 方法名称   | 反射获取                             |
| `request_method` | 请求方式   | `HttpServletRequest.getMethod()`     |
| `operator_type`  | 操作人类别 | `@Log.operatorType()`                |
| `oper_name`      | 操作人员   | 当前登录用户名                       |
| `dept_name`      | 部门名称   | 当前登录用户部门                     |
| `oper_url`       | 请求 URL   | `HttpServletRequest.getRequestURI()` |
| `oper_ip`        | 主机地址   | IP 工具获取                          |
| `oper_location`  | 操作地点   | IP 地址解析                          |
| `oper_param`     | 请求参数   | 序列化后的请求参数                   |
| `json_result`    | 响应参数   | 序列化后的响应结果                   |
| `status`         | 操作状态   | 0=正常,1=异常                        |
| `error_msg`      | 错误消息   | 异常时的堆栈信息                     |
| `oper_time`      | 操作时间   | 当前时间                             |
| `cost_time`      | 消耗时间   | 方法执行耗时（毫秒）                 |

**日志记录的执行流程：**

```
请求进入
    ↓
AOP 切面拦截 @Log 标注的方法
    ↓
记录请求开始时间
    ↓
执行目标方法（业务逻辑）
    ↓
方法正常返回：
    - 记录成功状态
    - 序列化响应数据
    ↓
方法抛出异常：
    - 记录失败状态
    - 记录异常堆栈
    ↓
计算执行耗时
    ↓
异步保存日志到数据库
    ↓
返回结果
```

---

###### 2.7 @Log 使用最佳实践

**1. 明确指定 title 和 businessType**

```java
// ❌ 不推荐：使用默认值，日志难以区分
@Log
@PostMapping
public AjaxResult add(@RequestBody SysUser user) { ... }

// ✅ 推荐：明确指定模块和类型
@Log(title = "用户管理", businessType = BusinessType.INSERT)
@PostMapping
public AjaxResult add(@RequestBody SysUser user) { ... }
```

**2. 敏感接口禁用参数保存**

```java
// ✅ 登录接口：不保存请求参数（避免记录密码）
@Log(title = "登录", isSaveRequestData = false)
@PostMapping("/login")
public AjaxResult login(@RequestBody LoginBody body) { ... }

// ✅ 文件导入：不保存请求参数（请求体很大）
@Log(title = "数据导入", businessType = BusinessType.IMPORT, 
     isSaveRequestData = false)
@PostMapping("/import")
public AjaxResult importData(MultipartFile file) { ... }

// ✅ 大数据导出：不保存响应数据
@Log(title = "数据导出", businessType = BusinessType.EXPORT,
     isSaveResponseData = false)
@PostMapping("/export")
public void export(HttpServletResponse response) { ... }
```

**3. 区分操作端**

```java
// PC 端管理后台（默认 MANAGE）
@Log(title = "用户管理", businessType = BusinessType.UPDATE)
@PutMapping
public AjaxResult edit(@RequestBody SysUser user) { ... }

// 移动端
@Log(title = "个人信息", businessType = BusinessType.UPDATE,
     operatorType = OperatorType.MOBILE)
@PutMapping("/mobile/profile")
public AjaxResult updateProfile(@RequestBody SysUser user) { ... }
```

**4. 批量操作的日志处理**

```java
/**
 * 批量删除用户
 * businessType = DELETE，AOP 会自动识别路径中的 ID
 */
@Log(title = "用户管理", businessType = BusinessType.DELETE)
@DeleteMapping("/{userIds}")
public AjaxResult remove(@PathVariable Long[] userIds) {
    return toAjax(userService.deleteUserByIds(userIds));
}
```

---

---

##### 三、@DataSource 注解完整属性说明

@DataSource 注解用于多数据源切换，支持在类或方法级别指定使用哪个数据源。

**注解定义：**

```java
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    /** 切换数据源名称 */
    public DataSourceType value() default DataSourceType.MASTER;
}
```

**关键元注解：**

- `@Inherited`：注解可被子类继承
- `@Target({METHOD, TYPE})`：可用于类和方法

---

###### 3.1 DataSourceType 数据源类型枚举

```java
public enum DataSourceType {
    MASTER,  // 主库（默认）- 写操作
    SLAVE    // 从库 - 读操作
}
```

**主从库职责：**

| 数据源           | 职责     | 适用场景                              |
| ---------------- | -------- | ------------------------------------- |
| `MASTER`（主库） | 写入数据 | INSERT、UPDATE、DELETE 操作           |
| `SLAVE`（从库）  | 读取数据 | SELECT 查询操作（复杂查询、报表统计） |

---

###### 3.2 数据源切换优先级

**优先级规则：方法级 > 类级 > 默认（MASTER）**

```java
/**
 * 类级别的数据源切换（所有方法默认使用从库）
 */
@Service
@DataSource(DataSourceType.SLAVE)
public class UserServiceImpl implements IUserService {
    
    /**
     * 查询用户列表 - 使用类级别注解（从库）
     */
    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return userMapper.selectUserList(user);
    }
    
    /**
     * 新增用户 - 方法级注解覆盖类级别（主库）
     */
    @Override
    @DataSource(DataSourceType.MASTER)
    public int insertUser(SysUser user) {
        return userMapper.insertUser(user);
    }
    
    /**
     * 修改用户 - 方法级注解覆盖类级别（主库）
     */
    @Override
    @DataSource(DataSourceType.MASTER)
    public int updateUser(SysUser user) {
        return userMapper.updateUser(user);
    }
}
```

**执行时的数据源选择：**

- `selectUserList()`：类级别 `@DataSource(SLAVE)` → 从库
- `insertUser()`：方法级别 `@DataSource(MASTER)` → 主库（覆盖类级别）
- `updateUser()`：方法级别 `@DataSource(MASTER)` → 主库

---

###### 3.3 完整使用示例

**场景：读写分离架构**

```java
/**
 * 用户 Service 层
 * 类级别使用从库（读操作多）
 */
@Service
@DataSource(DataSourceType.SLAVE)
public class UserServiceImpl implements IUserService {
    
    @Autowired
    private SysUserMapper userMapper;
    
    /**
     * 查询用户列表 - 从库（读操作）
     */
    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return userMapper.selectUserList(user);
    }
    
    /**
     * 根据ID查询用户 - 从库（读操作）
     */
    @Override
    public SysUser selectUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }
    
    /**
     * 新增用户 - 主库（写操作）
     */
    @Override
    @DataSource(DataSourceType.MASTER)
    public int insertUser(SysUser user) {
        user.setCreateTime(DateUtils.getNowDate());
        return userMapper.insertUser(user);
    }
    
    /**
     * 修改用户 - 主库（写操作）
     */
    @Override
    @DataSource(DataSourceType.MASTER)
    public int updateUser(SysUser user) {
        user.setUpdateTime(DateUtils.getNowDate());
        return userMapper.updateUser(user);
    }
    
    /**
     * 删除用户 - 主库（写操作）
     */
    @Override
    @DataSource(DataSourceType.MASTER)
    public int deleteUserByIds(Long[] userIds) {
        return userMapper.deleteUserByIds(userIds);
    }
    
    /**
     * 复杂统计查询 - 从库（读操作，大数据量）
     */
    @Override
    public List<Map<String, Object>> selectUserStatistics() {
        return userMapper.selectUserStatistics();
    }
}
```

**Controller 层示例：**

```java
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    
    @Autowired
    private IUserService userService;
    
    /**
     * 获取用户列表（读操作 - 从库）
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }
    
    /**
     * 新增用户（写操作 - 主库）
     */
    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUser user) {
        return toAjax(userService.insertUser(user));
    }
}
```

---

###### 3.4 注解继承特性

由于使用了 `@Inherited` 元注解，@DataSource 注解可以被子类继承。

```java
/**
 * 基础 Service - 使用从库
 */
@Service
@DataSource(DataSourceType.SLAVE)
public class BaseServiceImpl {
    // ...
}

/**
 * 用户 Service - 继承 BaseService
 * 自动继承 @DataSource(SLAVE) 注解
 */
@Service
public class UserServiceImpl extends BaseServiceImpl {
    
    /**
     * 此方法默认使用从库（继承自父类）
     */
    public List<SysUser> selectUserList(SysUser user) {
        // ...
    }
    
    /**
     * 方法级注解覆盖继承的注解
     */
    @DataSource(DataSourceType.MASTER)
    public int insertUser(SysUser user) {
        // ...
    }
}
```

---

###### 3.5 数据源切换的实现原理

**AOP 切面实现：**

`DataSourceAspect` 切面在方法执行前切换数据源：

```
@Before("@annotation(dataSource)")
public void doBefore(JoinPoint point, DataSource dataSource) {
    // 1. 获取注解中指定的数据源类型
    DataSourceType value = dataSource.value();
    
    // 2. 将数据源标识存入 ThreadLocal
    DynamicDataSourceContextHolder.setDataSourceType(value.name());
}

@After("@annotation(dataSource)")
public void doAfter(JoinPoint point, DataSource dataSource) {
    // 3. 方法执行后，清空数据源标识
    DynamicDataSourceContextHolder.clearDataSourceType();
}
```

**动态数据源路由：**

`AbstractRoutingDataSource` 决定使用哪个数据源：

```java
@Override
protected Object determineCurrentLookupKey() {
    // 从 ThreadLocal 获取当前线程的数据源标识
    return DynamicDataSourceContextHolder.getDataSourceType();
}
```

**关键类说明：**

| 类名                             | 作用                                              |
| -------------------------------- | ------------------------------------------------- |
| `DataSourceAspect`               | AOP 切面，在方法前后切换数据源                    |
| `DynamicDataSourceContextHolder` | 数据源上下文，使用 ThreadLocal 存储当前数据源标识 |
| `DynamicDataSource`              | 继承 `AbstractRoutingDataSource`，实现数据源路由  |

---

###### 3.6 使用注意事项与最佳实践

**1. 事务与多数据源**

```java
// ⚠️ 注意：同一事务内不能切换数据源
@Transactional(rollbackFor = Exception.class)
public void transferMoney(Long fromId, Long toId, BigDecimal amount) {
    // 如果这里使用了 @DataSource，可能不会按预期工作
    // 因为 Spring 的事务管理器会缓存 Connection
}
```

**建议：**

- 读操作使用从库，写操作使用主库
- 同一事务内尽量使用同一数据源
- 复杂的跨数据源操作考虑分布式事务

**2. 方法内部调用问题**

```java
@Service
public class UserServiceImpl {
    
    /**
     * 外部调用此方法时，@DataSource 生效
     */
    @DataSource(DataSourceType.SLAVE)
    public List<SysUser> selectUserList(SysUser user) {
        return userMapper.selectUserList(user);
    }
    
    /**
     * ⚠️ 问题：内部调用的方法，@DataSource 不生效
     * 因为 AOP 只拦截外部直接调用
     */
    @DataSource(DataSourceType.MASTER)
    public void internalMethod() {
        // 这个方法被内部调用时，@DataSource 不会生效
    }
    
    public void businessMethod() {
        // ❌ 内部调用，@DataSource 不生效
        internalMethod();
        
        // ✅ 解决：从 Spring 容器获取代理对象调用
        ((UserServiceImpl) AopContext.currentProxy()).internalMethod();
    }
}
```

**3. 空指针保护**

```java
// DynamicDataSourceContextHolder 的实现
public class DynamicDataSourceContextHolder {
    
    private static final ThreadLocal<String> CONTEXT_HOLDER = 
        new ThreadLocal<>();
    
    public static void setDataSourceType(String dsType) {
        CONTEXT_HOLDER.set(dsType);
    }
    
    public static String getDataSourceType() {
        // 返回 null 时使用默认数据源
        return CONTEXT_HOLDER.get();
    }
    
    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }
}
```

**4. 最佳实践总结**

| 场景                   | 建议                                                     |
| ---------------------- | -------------------------------------------------------- |
| **读多写少的 Service** | 类级别 `@DataSource(SLAVE)`，写操作方法级覆盖为 `MASTER` |
| **写多读少的 Service** | 使用默认 `MASTER`，复杂查询方法级使用 `SLAVE`            |
| **纯查询 Service**     | 类级别 `@DataSource(SLAVE)`                              |
| **纯写入 Service**     | 使用默认 `MASTER`                                        |
| **批量操作**           | 确保使用主库                                             |
| **事务方法**           | 避免在同一事务内切换数据源                               |

---

---

##### 四、@DataScope 注解完整属性说明

@DataScope 注解用于数据权限过滤，控制用户只能查看其权限范围内的数据。

**注解定义：**

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {
    /** 部门表的别名 */
    public String deptAlias() default "";
    /** 用户表的别名 */
    public String userAlias() default "";
}
```

**作用目标：** `METHOD`（仅用于方法）

---

###### 4.1 属性详细解析

| 属性名      | 类型   | 默认值 | 说明                                     |
| ----------- | ------ | ------ | ---------------------------------------- |
| `deptAlias` | String | `""`   | **部门表的别名**，SQL 查询中部门表的别名 |
| `userAlias` | String | `""`   | **用户表的别名**，SQL 查询中用户表的别名 |

**为什么需要表别名？**

数据权限过滤通过动态拼接 SQL 条件实现：

```sql
-- 原始查询（没有数据权限）
SELECT u.*, d.dept_name 
FROM sys_user u 
LEFT JOIN sys_dept d ON u.dept_id = d.dept_id
WHERE u.status = '0';

-- 加了数据权限后的查询
SELECT u.*, d.dept_name 
FROM sys_user u 
LEFT JOIN sys_dept d ON u.dept_id = d.dept_id
WHERE u.status = '0'
  -- 动态拼接的数据权限条件
  AND (d.dept_id IN (100, 101, 102) OR u.user_id = 100);
```

如果 SQL 中使用了表别名，就需要告诉 AOP 切面正确的别名：

```java
// 假设 SQL 中部门表别名是 d，用户表别名是 u
@DataScope(deptAlias = "d", userAlias = "u")
public List<SysUser> selectUserList(SysUser user) {
    return userMapper.selectUserList(user);
}
```

---

###### 4.2 数据权限过滤的实现原理

**AOP 切面处理流程：**

```
方法被 @DataScope 标记
    ↓
DataScopeAspect 切面拦截
    ↓
获取当前登录用户的权限信息
    ↓
根据用户权限类型，构建数据权限 SQL 条件
    ↓
将条件注入到 BaseEntity 的 params Map 中
    ↓
执行目标方法（MyBatis 从 params 中获取条件拼接 SQL）
    ↓
方法执行完成，清除条件
```

**权限类型判断：**

```java
// 用户的权限类型存储在 sys_role 表的 data_scope 字段
public enum DataScopeType {
    ALL,           // 1 全部数据权限
    CUSTOM,        // 2 自定义数据权限
    DEPT,          // 3 本部门数据权限
    DEPT_AND_CHILD, // 4 本部门及以下数据权限
    SELF           // 5 仅本人数据权限
}
```

**不同权限类型生成的 SQL 条件：**

| 权限类型           | 生成的 SQL 条件                                              | 说明                         |
| ------------------ | ------------------------------------------------------------ | ---------------------------- |
| ALL (1)            | 无                                                           | 不添加任何条件，查询全部数据 |
| CUSTOM (2)         | `dept_id IN (SELECT dept_id FROM sys_role_dept WHERE role_id = ?)` | 根据角色配置的部门权限       |
| DEPT (3)           | `dept_id = 当前用户部门ID`                                   | 只能查看本部门数据           |
| DEPT_AND_CHILD (4) | `dept_id IN (当前部门及所有子部门ID)`                        | 本部门及下级部门数据         |
| SELF (5)           | `user_id = 当前用户ID`                                       | 只能查看自己的数据           |

---

###### 4.3 完整使用示例

**Mapper XML 中的数据权限拼接：**

```xml
<!-- SysUserMapper.xml -->
<select id="selectUserList" resultType="SysUser">
    SELECT u.*, d.dept_name, d.leader
    FROM sys_user u
    LEFT JOIN sys_dept d ON u.dept_id = d.dept_id
    WHERE 1=1
    <!-- 其他条件... -->
    
    <!-- 数据权限过滤（从 params 中获取动态拼接的 SQL） -->
    ${params.dataScope}
</select>
```

**Service 层使用示例：**

```java
@Service
public class UserServiceImpl implements IUserService {
    
    @Autowired
    private SysUserMapper userMapper;
    
    /**
     * 查询用户列表（带数据权限）
     * deptAlias = "d"：部门表别名是 d
     * userAlias = "u"：用户表别名是 u
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SysUser> selectUserList(SysUser user) {
        // AOP 切面会自动在 user.getParams().put("dataScope", "...")
        return userMapper.selectUserList(user);
    }
    
    /**
     * 查询角色列表（带数据权限）
     * 假设 SQL 中部门表别名是 d
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<SysRole> selectRoleList(SysRole role) {
        return roleMapper.selectRoleList(role);
    }
    
    /**
     * 查询部门列表（带数据权限）
     * 部门表的别名通常是自身，如 sys_dept d
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<SysDept> selectDeptList(SysDept dept) {
        return deptMapper.selectDeptList(dept);
    }
}
```

**Controller 层：**

```java
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    
    @Autowired
    private IUserService userService;
    
    /**
     * 获取用户列表
     * 数据权限由 Service 层的 @DataScope 处理
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user) {
        startPage();
        // 传入 user 对象，AOP 会修改其 params 属性
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }
}
```

**BaseEntity 中的 params 属性：**

```java
public class BaseEntity implements Serializable {
    // ... 其他字段
    
    /** 请求参数 */
    private Map<String, Object> params;
    
    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }
    
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
```

**AOP 切面中的关键代码：**

```java
@Before("@annotation(controllerDataScope)")
public void doBefore(JoinPoint point, DataScope controllerDataScope) {
    // 1. 获取方法参数（通常是继承 BaseEntity 的对象）
    Object params = point.getArgs()[0];
    
    // 2. 获取当前登录用户
    LoginUser loginUser = SecurityUtils.getLoginUser();
    if (loginUser == null || loginUser.getUser() == null) {
        return;
    }
    
    // 3. 构建数据权限条件
    StringBuilder sqlString = new StringBuilder();
    
    // 4. 根据用户角色的数据权限类型，拼接不同的 SQL
    for (SysRole role : loginUser.getUser().getRoles()) {
        String dataScope = role.getDataScope();
        switch (dataScope) {
            case "1": // ALL - 全部
                sqlString = new StringBuilder();
                break;
            case "2": // CUSTOM - 自定义
                sqlString.append(" OR ").append(deptAlias)
                    .append(".dept_id IN (SELECT dept_id FROM sys_role_dept WHERE role_id = ")
                    .append(role.getRoleId()).append(")");
                break;
            case "3": // DEPT - 本部门
                sqlString.append(" OR ").append(deptAlias)
                    .append(".dept_id = ").append(loginUser.getUser().getDeptId());
                break;
            case "4": // DEPT_AND_CHILD - 本部门及以下
                sqlString.append(" OR ").append(deptAlias)
                    .append(".dept_id IN (SELECT dept_id FROM sys_dept WHERE dept_id = ")
                    .append(loginUser.getUser().getDeptId())
                    .append(" OR find_in_set(").append(loginUser.getUser().getDeptId())
                    .append(", ancestors))");
                break;
            case "5": // SELF - 仅本人
                sqlString.append(" OR ").append(userAlias)
                    .append(".user_id = ").append(loginUser.getUser().getUserId());
                break;
        }
    }
    
    // 5. 将条件注入到 params 中
    if (StringUtils.isNotEmpty(sqlString.toString())) {
        // 去掉开头的 " OR "
        String condition = " AND (" + sqlString.substring(4) + ")";
        // 注入到 BaseEntity 的 params Map
        Map<String, Object> paramsMap = (Map<String, Object>) 
            ReflectUtils.invokeMethod(params, "getParams");
        paramsMap.put("dataScope", condition);
    }
}
```

---

###### 4.4 多表关联查询的别名配置

**场景：复杂的多表关联查询**

```xml
<!-- 多表关联查询的 SQL -->
<select id="selectOrderList" resultType="OrderVO">
    SELECT 
        o.order_id,
        o.order_no,
        o.order_date,
        c.customer_name,
        u.user_name AS salesman,
        d.dept_name
    FROM biz_order o
    LEFT JOIN biz_customer c ON o.customer_id = c.customer_id
    LEFT JOIN sys_user u ON o.salesman_id = u.user_id
    LEFT JOIN sys_dept d ON o.dept_id = d.dept_id
    WHERE 1=1
    ${params.dataScope}
</select>
```

**Service 层的注解配置：**

```java
/**
 * 查询订单列表
 * 数据权限按订单所属部门过滤
 * SQL 中部门表别名是 d
 */
@DataScope(deptAlias = "d")
public List<OrderVO> selectOrderList(OrderVO order) {
    return orderMapper.selectOrderList(order);
}

/**
 * 查询销售统计
 * 数据权限按销售员过滤
 * SQL 中用户表别名是 u
 */
@DataScope(userAlias = "u")
public List<SalesStatistics> selectSalesStatistics(SalesStatistics stat) {
    return statMapper.selectSalesStatistics(stat);
}
```

**如果需要同时按部门和用户过滤：**

```java
/**
 * 同时按部门和用户过滤
 * deptAlias = "d"：部门表别名
 * userAlias = "u"：用户表别名
 */
@DataScope(deptAlias = "d", userAlias = "u")
public List<ReportVO> selectReportList(ReportVO report) {
    return reportMapper.selectReportList(report);
}
```

---

###### 4.5 使用注意事项

**1. 确保参数继承 BaseEntity**

```java
// ✅ 正确：继承 BaseEntity，有 params 属性
public class SysUser extends BaseEntity { ... }

// ❌ 错误：没有继承 BaseEntity，无法注入数据权限条件
public class SysUser {
    // 没有 params 属性
}
```

**2. 方法参数的位置**

AOP 切面默认取第一个参数作为目标对象：

```java
// ✅ 正确：第一个参数是继承 BaseEntity 的对象
@DataScope(deptAlias = "d")
public List<SysUser> selectUserList(SysUser user) { ... }

// ⚠️ 注意：如果第一个参数不是目标对象，需要调整 AOP 逻辑
@DataScope(deptAlias = "d")
public List<SysUser> selectUserList(String status, SysUser user) {
    // AOP 切面会取第一个参数 status，这是错误的
}
```

**3. SQL 注入的安全考虑**

```xml
<!-- 使用 ${} 而不是 #{}，因为是直接拼接 SQL -->
<!-- 但这带来 SQL 注入风险，需要确保 AOP 生成的条件是安全的 -->
${params.dataScope}

<!-- AOP 切面生成的条件是安全的，因为：
     1. 所有 ID 值都是数字类型，使用参数绑定
     2. 字符串值都经过校验
     3. 没有直接拼接用户输入
-->
```

**4. 多个 @DataScope 的处理**

如果一个用户有多个角色，且角色有不同的数据权限：

```java
// 用户拥有两个角色：
// 角色1：数据权限 = 本部门 (DEPT)
// 角色2：数据权限 = 全部 (ALL)

// AOP 处理逻辑：
// - 只要有一个角色是 ALL，就使用 ALL 权限（最高权限）
// - 否则，合并所有角色的权限条件（OR 连接）
```

---

###### 4.6 最佳实践总结

| 场景                   | 建议配置                                      |
| ---------------------- | --------------------------------------------- |
| **单表查询**           | 根据表别名设置 `deptAlias` 或 `userAlias`     |
| **多表关联查询**       | 确定数据权限基于哪个表的 ID，设置对应别名     |
| **部门权限**           | 设置 `deptAlias`                              |
| **用户权限**           | 设置 `userAlias`                              |
| **同时需要部门和用户** | 两个别名都设置                                |
| **管理员用户**         | 数据权限 ALL，不添加任何条件                  |
| **复杂自定义权限**     | 使用 `CUSTOM` 类型，在 `sys_role_dept` 表配置 |

---

---

##### 五、@RateLimiter 注解完整属性说明

@RateLimiter 注解用于接口限流，防止接口被频繁调用。

**注解定义：**

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    /** 限流key */
    public String key() default Constants.RATE_LIMIT_KEY;
    /** 限流时间,单位秒 */
    public int time() default 60;
    /** 限流次数 */
    public int count() default 100;
    /** 限流类型 */
    public LimitType limitType() default LimitType.DEFAULT;
}
```

---

###### 5.1 属性详细解析

| 属性名      | 类型      | 默认值                     | 说明                                           |
| ----------- | --------- | -------------------------- | ---------------------------------------------- |
| `key`       | String    | `Constants.RATE_LIMIT_KEY` | **限流 Key 前缀**，用于 Redis 中存储计数的 Key |
| `time`      | int       | `60`                       | **限流时间窗口**，单位：秒                     |
| `count`     | int       | `100`                      | **限流次数**，在时间窗口内允许的最大请求数     |
| `limitType` | LimitType | `LimitType.DEFAULT`        | **限流类型**，全局限流或按 IP 限流             |

---

###### 5.2 LimitType 限流类型枚举

```java
public enum LimitType {
    DEFAULT,  // 默认策略 - 全局限流
    IP        // 根据请求者 IP 限流
}
```

**两种限流类型的区别：**

| 限流类型  | Redis Key 格式                            | 说明                         |
| --------- | ----------------------------------------- | ---------------------------- |
| `DEFAULT` | `rate_limit:{key}:{methodSignature}`      | 全局限流，所有用户共享计数   |
| `IP`      | `rate_limit:{key}:{ip}:{methodSignature}` | 按 IP 限流，每个 IP 独立计数 |

**使用场景：**

| 场景                   | 建议限流类型 | 配置示例             |
| ---------------------- | ------------ | -------------------- |
| **防止系统过载**       | DEFAULT      | 所有用户共享限流     |
| **防止恶意用户刷接口** | IP           | 每个 IP 独立限流     |
| **公开 API 保护**      | DEFAULT      | 全局限制总调用量     |
| **登录接口防暴力破解** | IP           | 每个 IP 限制登录次数 |
| **短信验证码发送**     | IP           | 每个 IP 限制发送频率 |

---

###### 5.3 限流实现原理

**基于 Redis 的计数器限流算法：**

```
请求进入
    ↓
检查是否有 @RateLimiter 注解
    ↓
根据限流类型构建 Redis Key
    ↓
检查 Redis 中该 Key 的当前计数
    ↓
计数不存在或已过期：
    - 设置初始值 1
    - 设置过期时间 time 秒
    ↓ 放行
计数存在且 < count：
    - 计数 +1
    ↓ 放行
计数 >= count：
    ↓ 拒绝，抛出异常
```

**Redis 操作流程：**

```java
// 伪代码示意
public boolean tryAcquire(String key, int limitCount, int limitTime) {
    // 1. 检查 key 是否存在
    Long currentCount = redisTemplate.opsForValue().get(key);
    
    if (currentCount == null) {
        // 2. key 不存在，创建并设置过期时间
        redisTemplate.opsForValue().set(key, 1, limitTime, TimeUnit.SECONDS);
        return true;
    }
    
    if (currentCount < limitCount) {
        // 3. 计数未满，自增
        redisTemplate.opsForValue().increment(key);
        return true;
    }
    
    // 4. 计数已满，拒绝
    return false;
}
```

**Redis Key 格式示例：**

```java
// DEFAULT 类型
// Key: rate_limit:rate_limit:com.ktg.web.controller.system.SysLoginController.login

// IP 类型
// Key: rate_limit:rate_limit:192.168.1.100:com.ktg.web.controller.system.SysLoginController.login
```

---

###### 5.4 完整使用示例

**场景 1：登录接口限制（按 IP 限流）**

```java
/**
 * 登录接口
 * 限制：每个 IP 每分钟最多 10 次
 * 目的：防止暴力破解密码
 */
@PostMapping("/login")
@RateLimiter(
    key = "login",
    time = 60,      // 60 秒窗口
    count = 10,     // 最多 10 次
    limitType = LimitType.IP  // 按 IP 限流
)
public AjaxResult login(@RequestBody LoginBody loginBody) {
    // 登录逻辑...
}
```

**场景 2：发送短信验证码（按 IP 限流）**

```java
/**
 * 发送短信验证码
 * 限制：每个 IP 每分钟最多 5 次
 * 目的：防止短信轰炸
 */
@PostMapping("/sendCode")
@RateLimiter(
    key = "sms_code",
    time = 60,
    count = 5,
    limitType = LimitType.IP
)
public AjaxResult sendCode(@RequestParam String phone) {
    // 发送短信逻辑...
}
```

**场景 3：公开 API 全局限流**

```java
/**
 * 公开数据查询接口
 * 限制：全局限流，每秒最多 100 次
 * 目的：防止系统过载
 */
@GetMapping("/public/data")
@RateLimiter(
    key = "public_api",
    time = 1,       // 1 秒窗口
    count = 100,    // 100 次
    limitType = LimitType.DEFAULT  // 全局限流
)
public AjaxResult getPublicData() {
    // 查询逻辑...
}
```

**场景 4：导出接口限流**

```java
/**
 * 导出数据（消耗资源大）
 * 限制：每个用户（按 IP）每 10 分钟最多 3 次
 * 目的：防止大量导出请求占用资源
 */
@PostMapping("/export")
@RateLimiter(
    key = "export",
    time = 600,     // 10 分钟
    count = 3,      // 3 次
    limitType = LimitType.IP
)
public void export(HttpServletResponse response) {
    // 导出逻辑...
}
```

**场景 5：复杂查询接口限流**

```java
/**
 * 复杂统计查询（耗时较长）
 * 限制：全局每分钟最多 50 次
 */
@GetMapping("/statistics")
@RateLimiter(
    key = "statistics",
    time = 60,
    count = 50,
    limitType = LimitType.DEFAULT
)
public AjaxResult getStatistics() {
    // 复杂查询逻辑...
}
```

---

###### 5.5 限流被拒绝后的处理

当请求超过限流阈值时，系统会抛出 `ServiceException`：

```java
// 限流切面中的处理
if (!allowed) {
    throw new ServiceException("访问过于频繁，请稍候再试");
}
```

**前端收到的响应：**

```json
{
    "code": 500,
    "msg": "访问过于频繁，请稍候再试",
    "data": null
}
```

**也可以自定义限流消息：**

```java
// 可以通过全局异常处理器自定义响应
@ExceptionHandler(ServiceException.class)
public AjaxResult handleServiceException(ServiceException e) {
    if (e.getMessage().contains("访问过于频繁")) {
        return AjaxResult.error(HttpStatus.TOO_MANY_REQUESTS, 
            "请求频率过高，请 " + time + " 秒后再试");
    }
    return AjaxResult.error(e.getMessage());
}
```

---

###### 5.6 不同限流策略的对比

| 限流策略     | 适用场景     | 优点                 | 缺点                            |
| ------------ | ------------ | -------------------- | ------------------------------- |
| **计数器**   | 简单限流     | 实现简单，资源消耗少 | 临界问题（如 59秒和1秒各100次） |
| **滑动窗口** | 精确限流     | 解决临界问题         | 实现复杂，资源消耗大            |
| **令牌桶**   | 需要平滑限流 | 允许突发流量         | 实现复杂                        |
| **漏桶**     | 需要匀速处理 | 强制流量整形         | 无法处理突发流量                |

**本项目使用的是计数器算法**，基于 Redis 实现：

- 优点：实现简单，性能好
- 缺点：存在临界问题（如 59秒时请求100次，1秒时又请求100次，2秒内共200次）
- 改进：可以使用 Redis 的 ZSET 实现滑动窗口

---

###### 5.7 最佳实践建议

**1. 限流参数的合理设置**

```java
// ❌ 不合理：时间太短或限制太严
@RateLimiter(time = 1, count = 1)  // 每秒1次，用户体验差

// ❌ 不合理：限制太松，起不到保护作用
@RateLimiter(time = 60, count = 100000)  // 几乎等于无限

// ✅ 合理：根据接口实际情况设置
// 登录接口：防暴力破解
@RateLimiter(time = 60, count = 10, limitType = LimitType.IP)

// 导出接口：资源消耗大
@RateLimiter(time = 600, count = 3, limitType = LimitType.IP)

// 公开 API：防止系统过载
@RateLimiter(time = 1, count = 100, limitType = LimitType.DEFAULT)
```

**2. 选择正确的限流类型**

```java
// 需要区分用户的接口 → LimitType.IP
// 登录、注册、短信验证码、用户操作

// 不需要区分用户的接口 → LimitType.DEFAULT
// 公开数据查询、系统状态查询
```

**3. 与其他保护机制配合使用**

```java
// 登录接口：多重保护
@PostMapping("/login")
@RateLimiter(time = 60, count = 10, limitType = LimitType.IP)  // 限流
@RepeatSubmit(interval = 1000)  // 防重复提交
@Log(title = "登录", isSaveRequestData = false)  // 日志记录
public AjaxResult login(@RequestBody LoginBody loginBody) {
    // 业务逻辑...
}
```

**4. 限流 Key 的命名规范**

```java
// 使用有意义的 key，便于监控和排查
@RateLimiter(key = "user_login", ...)      // 用户登录
@RateLimiter(key = "sms_send", ...)        // 短信发送
@RateLimiter(key = "data_export", ...)     // 数据导出
@RateLimiter(key = "public_api", ...)      // 公开 API
```

**5. 监控和告警**

```java
// 建议在限流时记录日志，便于监控
if (!allowed) {
    log.warn("接口被限流: key={}, ip={}", key, ip);
    // 可以集成告警系统，当限流频繁时通知管理员
    throw new ServiceException("访问过于频繁，请稍候再试");
}
```

---

---

##### 六、@RepeatSubmit 注解完整属性说明

@RepeatSubmit 注解用于防止表单重复提交，在指定时间间隔内拦截重复请求。

**注解定义：**

```java
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {
    /** 间隔时间(ms)，小于此时间视为重复提交 */
    public int interval() default 5000;
    /** 提示消息 */
    public String message() default "不允许重复提交，请稍候再试";
}
```

**关键元注解：**

- `@Inherited`：可被子类继承

---

###### 6.1 属性详细解析

| 属性名     | 类型   | 默认值                         | 说明                                                |
| ---------- | ------ | ------------------------------ | --------------------------------------------------- |
| `interval` | int    | `5000`                         | **重复提交判断的时间间隔**，单位：毫秒（默认 5 秒） |
| `message`  | String | `"不允许重复提交，请稍候再试"` | **重复提交时的提示消息**                            |

---

###### 6.2 防重复提交的实现原理

**基于 Redis 的实现方式：**

```
请求进入
    ↓
检查是否有 @RepeatSubmit 注解
    ↓
生成唯一标识（Key）：
- URL + 用户ID（或 IP）+ 请求参数的 MD5
    ↓
检查 Redis 中是否存在该 Key
    ↓
Key 不存在（首次请求）：
    - 存储 Key 到 Redis，设置过期时间 interval
    ↓ 放行，执行目标方法
Key 已存在（重复请求）：
    ↓ 拒绝，抛出异常
```

**Redis Key 的生成策略：**

```java
// 伪代码示意
public String generateRepeatSubmitKey(HttpServletRequest request) {
    // 1. 获取请求 URL
    String url = request.getRequestURI();
    
    // 2. 获取当前用户 ID（或 IP，如果未登录）
    String userId = SecurityUtils.getUserId() != null ? 
        SecurityUtils.getUserId().toString() : 
        IpUtils.getIpAddr(request);
    
    // 3. 获取请求参数（JSON Body 或 Form 参数）
    String params = HttpHelper.getBodyString(request);
    if (StringUtils.isEmpty(params)) {
        params = JSON.toJSONString(request.getParameterMap());
    }
    
    // 4. 计算参数的 MD5
    String paramsMd5 = DigestUtils.md5DigestAsHex(params.getBytes());
    
    // 5. 组合成唯一 Key
    return Constants.REPEAT_SUBMIT_KEY + url + ":" + userId + ":" + paramsMd5;
}
```

**Redis Key 示例：**

```
// 格式
repeat_submit:{url}:{userId}:{paramsMd5}

// 示例
repeat_submit:/system/user:100:5f4dcc3b5aa765d61d8327deb882cf99
```

**为什么需要参数的 MD5？**

防止以下场景：

- 用户快速点击"保存"按钮两次（相同参数）→ 拦截
- 用户修改表单后再次提交（不同参数）→ 放行
- 用户刷新页面重复提交（相同参数）→ 拦截

---

###### 6.3 完整使用示例

**场景 1：新增/修改接口**

```java
/**
 * 新增用户
 * 防止用户快速点击保存按钮导致重复插入
 */
@PostMapping
@RepeatSubmit(interval = 5000)  // 5 秒内不允许重复提交
@Log(title = "用户管理", businessType = BusinessType.INSERT)
public AjaxResult add(@RequestBody SysUser user) {
    return toAjax(userService.insertUser(user));
}

/**
 * 修改用户
 */
@PutMapping
@RepeatSubmit(interval = 3000)  // 3 秒内不允许重复提交
@Log(title = "用户管理", businessType = BusinessType.UPDATE)
public AjaxResult edit(@RequestBody SysUser user) {
    return toAjax(userService.updateUser(user));
}
```

**场景 2：导入接口**

```java
/**
 * 导入用户数据
 * 防止重复导入相同文件
 */
@PostMapping("/importData")
@RepeatSubmit(
    interval = 10000,  // 10 秒
    message = "数据正在导入中，请勿重复提交"
)
@Log(title = "用户管理", businessType = BusinessType.IMPORT, isSaveRequestData = false)
public AjaxResult importData(MultipartFile file) throws Exception {
    ExcelUtil<SysUser> util = new ExcelUtil<>(SysUser.class);
    List<SysUser> userList = util.importExcel(file.getInputStream());
    return AjaxResult.success(userService.importUser(userList));
}
```

**场景 3：支付/下单接口**

```java
/**
 * 提交订单
 * 防止重复下单，时间间隔较长
 */
@PostMapping("/submit")
@RepeatSubmit(
    interval = 30000,  // 30 秒
    message = "订单正在处理中，请勿重复提交"
)
@Log(title = "订单管理", businessType = BusinessType.INSERT)
public AjaxResult submitOrder(@RequestBody OrderDTO order) {
    return toAjax(orderService.createOrder(order));
}
```

**场景 4：登录接口**

```java
/**
 * 登录
 * 配合限流使用
 */
@PostMapping("/login")
@RepeatSubmit(interval = 2000)  // 2 秒内不允许重复登录
@RateLimiter(time = 60, count = 10, limitType = LimitType.IP)
@Log(title = "登录", isSaveRequestData = false)
public AjaxResult login(@RequestBody LoginBody loginBody) {
    // 登录逻辑...
}
```

**场景 5：发送验证码**

```java
/**
 * 发送短信验证码
 */
@PostMapping("/sendCode")
@RepeatSubmit(
    interval = 60000,  // 60 秒
    message = "验证码发送过于频繁，请 60 秒后再试"
)
public AjaxResult sendCode(@RequestParam String phone) {
    // 发送验证码逻辑...
}
```

---

###### 6.4 重复提交被拦截后的响应

**默认响应：**

```json
{
    "code": 500,
    "msg": "不允许重复提交，请稍候再试",
    "data": null
}
```

**自定义消息：**

```java
@RepeatSubmit(
    interval = 5000,
    message = "您的操作太快了，请 5 秒后再试"
)
```

**响应变为：**

```json
{
    "code": 500,
    "msg": "您的操作太快了，请 5 秒后再试",
    "data": null
}
```

---

###### 6.5 与 @RateLimiter 的区别

| 特性         | @RepeatSubmit                 | @RateLimiter           |
| ------------ | ----------------------------- | ---------------------- |
| **目的**     | 防止重复提交                  | 限制接口调用频率       |
| **判断依据** | URL + 用户 + 参数（相同参数） | URL + 用户（或全局）   |
| **时间窗口** | 固定间隔                      | 滑动窗口（计数器）     |
| **适用场景** | 表单提交、订单创建            | 防止暴力破解、系统过载 |
| **Key 生成** | 包含参数 MD5                  | 不包含参数             |
| **效果**     | 相同参数在间隔内只能提交一次  | 时间内限制总次数       |

**典型场景对比：**

```java
// 场景：用户提交订单

// 使用 @RepeatSubmit
// 用户快速点击"提交"按钮 3 次（相同参数）
// → 只有第一次成功，后两次被拦截

// 使用 @RateLimiter(count=1, time=5)
// 用户快速点击"提交"按钮 3 次（相同参数）
// → 只有第一次成功，后两次被拦截

// 区别：
// @RepeatSubmit：用户修改订单金额后再次提交 → 放行（参数不同）
// @RateLimiter：用户修改订单金额后再次提交 → 可能被拦截（时间窗口内）
```

**建议配合使用：**

```java
// 重要接口建议同时使用两种保护
@PostMapping("/submit")
@RepeatSubmit(interval = 5000)           // 防重复提交
@RateLimiter(time = 60, count = 10)     // 防频繁调用
public AjaxResult submit(@RequestBody OrderDTO order) {
    // 业务逻辑...
}
```

---

###### 6.6 实现细节和注意事项

**1. 请求体的可重复读取**

由于防重复提交需要读取请求体计算 MD5，而 `HttpServletRequest` 的输入流只能读取一次，因此需要配合 `RepeatableFilter` 使用。

```java
// RepeatableFilter 的作用：
// 将请求体缓存到字节数组中，支持多次读取
```

**2. GET 请求的处理**

```java
// 通常只对 POST/PUT 等写操作的接口使用 @RepeatSubmit
// GET 请求一般是幂等的，不需要防重复提交

// 但如果有特殊需求，也可以使用
@GetMapping("/refresh")
@RepeatSubmit(interval = 3000)
public AjaxResult refresh() {
    // 刷新缓存等操作
}
```

**3. 文件上传的处理**

```java
// 文件上传时，请求体是 MultipartFile
// MD5 计算会包含文件内容
// 如果用户上传相同文件，会被认为是重复提交

@PostMapping("/import")
@RepeatSubmit(interval = 10000)
public AjaxResult importData(MultipartFile file) {
    // 导入逻辑...
}
```

**4. 分布式环境下的有效性**

```java
// 由于使用 Redis 存储 Key，分布式环境下也有效
// 多台服务器共享同一个 Redis，重复提交会被正确拦截

// 注意：
// - 如果不使用 Redis（使用本地缓存），分布式环境下可能失效
// - 确保所有服务器连接同一个 Redis 实例
```

---

###### 6.7 最佳实践建议

**1. 根据接口特性设置合理的间隔时间**

```java
// 简单表单提交：3-5 秒
@RepeatSubmit(interval = 3000)

// 复杂业务处理：10-30 秒
@RepeatSubmit(interval = 10000)

// 支付/订单类：30-60 秒
@RepeatSubmit(interval = 30000)

// 验证码发送：60 秒或更长
@RepeatSubmit(interval = 60000)
```

**2. 设置有意义的提示消息**

```java
// 默认消息太笼统
@RepeatSubmit(message = "不允许重复提交，请稍候再试")

// 更友好的消息
@RepeatSubmit(message = "您的操作太快了，请 5 秒后再试")
@RepeatSubmit(message = "订单正在处理中，请勿重复提交")
@RepeatSubmit(message = "验证码发送过于频繁，请 60 秒后再试")
```

**3. 与其他注解配合使用**

```java
// 完整的保护机制
@PostMapping
@RepeatSubmit(interval = 5000)                    // 1. 防重复提交
@RateLimiter(time = 60, count = 10)              // 2. 限频
@Log(title = "用户管理", businessType = BusinessType.INSERT)  // 3. 日志
@PreAuthorize("@ss.hasPermi('system:user:add')")  // 4. 权限
public AjaxResult add(@RequestBody SysUser user) {
    return toAjax(userService.insertUser(user));
}
```

**4. 只在写操作接口使用**

```java
// ✅ 正确：写操作可能产生重复数据
@PostMapping
@PutMapping
@DeleteMapping

// ❌ 不需要：读操作是幂等的
@GetMapping
```

**5. 考虑用户体验**

```java
// 前端配合：
// 1. 点击按钮后立即禁用（disabled）
// 2. 显示加载状态（loading）
// 3. 请求完成后恢复按钮状态
// 4. 收到"重复提交"错误时友好提示

// 后端配合：
// 1. 设置合理的间隔时间（不要太长，影响用户体验）
// 2. 设置友好的提示消息
// 3. 可以考虑返回剩余等待时间
```

---

---

##### 七、@Excels 容器注解说明

@Excels 是一个容器注解，用于在一个字段上应用多个 @Excel 注解。

**注解定义：**

```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Excels {
    Excel[] value();
}
```

---

###### 7.1 使用场景

**场景 1：导出嵌套对象的多个属性**

```java
public class SysUser {
    
    private String userName;
    
    // 从 dept 对象导出多个属性
    @Excels({
        @Excel(name = "部门ID", targetAttr = "deptId", sort = 1),
        @Excel(name = "部门名称", targetAttr = "deptName", sort = 2),
        @Excel(name = "部门负责人", targetAttr = "leader", sort = 3),
        @Excel(name = "部门电话", targetAttr = "phone", sort = 4)
    })
    private SysDept dept;
}
```

**场景 2：同一字段的多格式导出（不同 type）**

```java
public class Order {
    
    private String orderNo;
    
    // 金额同时以两种格式导出（实际场景较少见）
    @Excels({
        @Excel(name = "订单金额(数字)", targetAttr = "amount", 
               cellType = ColumnType.NUMERIC, sort = 1),
        @Excel(name = "订单金额(带单位)", targetAttr = "amountWithUnit",
               cellType = ColumnType.STRING, sort = 2)
    })
    private BigDecimal amount;
}
```

---

###### 7.2 与 @Excel 的对比

| 特性                | @Excel       | @Excels            |
| ------------------- | ------------ | ------------------ |
| 注解数量            | 1 个         | 多个               |
| 目标字段            | 当前字段     | 嵌套对象的属性     |
| 必须配合 targetAttr | 可选         | 必须               |
| 使用场景            | 普通字段导出 | 嵌套对象多属性导出 |

---

---

## 2. config 包

### 代码结构

```
com.ktg.common.config/
├── MinioConfig.java    # MinIO 对象存储配置
└── RuoYiConfig.java    # 项目全局配置
```

### 依赖关系

- **RuoYiConfig**：
  - 依赖 Spring Boot 的 `@Component`、`@ConfigurationProperties` 注解
  - 无其他模块内部依赖
- **MinioConfig**：
  - 依赖 Spring 的 `@Configuration`、`@Bean`、`@ConfigurationProperties` 注解
  - **第三方依赖**：`io.minio.MinioClient`（MinIO Java SDK），用于构建 MinIO 客户端连接

### 功能

该包定义配置属性的绑定类，通过 `@ConfigurationProperties` 注解将配置文件中的值映射为 Java 对象，提供类型安全的配置访问方式：

#### RuoYiConfig - 项目全局配置

**配置前缀：** `ktg-mes`

**配置项：**

| 属性           | 类型    | 说明           |
| -------------- | ------- | -------------- |
| name           | String  | 项目名称       |
| version        | String  | 版本号         |
| copyrightYear  | String  | 版权年份       |
| demoEnabled    | boolean | 演示模式开关   |
| profile        | String  | 上传路径根目录 |
| addressEnabled | boolean | 获取地址开关   |
| captchaType    | String  | 验证码类型     |

**静态方法：**

- `getProfile()`：获取上传根路径
- `getImportPath()`：获取导入文件路径（{profile}/import）
- `getAvatarPath()`：获取头像上传路径（{profile}/avatar）
- `getDownloadPath()`：获取下载路径（{profile}/download/）
- `getUploadPath()`：获取上传路径（{profile}/upload）

#### MinioConfig - MinIO 对象存储配置

**配置前缀：** `minio`

**配置项：**

| 属性       | 类型   | 说明               |
| ---------- | ------ | ------------------ |
| url        | String | MinIO 服务地址     |
| accessKey  | String | 访问密钥（用户名） |
| secretKey  | String | 安全密钥（密码）   |
| bucketName | String | 默认存储桶名称     |

**Bean 注册：**

- `getMinioClient()`：使用 `@Bean` 注解创建 `MinioClient` 实例，供 Spring 容器管理

---

## 3. constant 包

### 代码结构

```
com.ktg.common.constant/
├── Constants.java       # 通用常量
├── GenConstants.java    # 代码生成常量
├── HttpStatus.java      # HTTP状态码常量
├── ScheduleConstants.java  # 定时任务常量
└── UserConstants.java   # 用户相关常量
```

### 依赖关系

- **Constants**：
  - **第三方依赖**：`io.jsonwebtoken.Claims`（JWT 库），用于定义 JWT 令牌相关的常量键名
- 其他常量类无外部依赖，均为纯静态常量定义

### 功能

该包集中管理系统的一部分核心常量定义（字符集、HTTP 状态码、Redis Key 前缀等），提高代码可维护性：

#### Constants - 通用常量

**字符集：**

- `UTF8` = "UTF-8"
- `GBK` = "GBK"

**HTTP 协议：**

- `HTTP` = "http://"
- `HTTPS` = "https://"

**通用标识：**

- `SUCCESS` = "0"（成功）
- `FAIL` = "1"（失败）

**登录状态：**

- `LOGIN_SUCCESS` = "Success"
- `LOGOUT` = "Logout"
- `REGISTER` = "Register"
- `LOGIN_FAIL` = "Error"

**Redis Key 前缀：**

| 常量名            | 值               | 说明     |
| ----------------- | ---------------- | -------- |
| CAPTCHA_CODE_KEY  | "captcha_codes:" | 验证码   |
| LOGIN_TOKEN_KEY   | "login_tokens:"  | 登录令牌 |
| REPEAT_SUBMIT_KEY | "repeat_submit:" | 防重提交 |
| RATE_LIMIT_KEY    | "rate_limit:"    | 限流     |
| SYS_CONFIG_KEY    | "sys_config:"    | 参数管理 |
| SYS_DICT_KEY      | "sys_dict:"      | 字典管理 |

**验证码有效期：**

- `CAPTCHA_EXPIRATION` = 2（分钟）

**JWT 声明键名：**

- `TOKEN` = "token"
- `TOKEN_PREFIX` = "Bearer "
- `LOGIN_USER_KEY` = "login_user_key"
- `JWT_USERID` = "userid"
- `JWT_USERNAME` = Claims.SUBJECT
- `JWT_AVATAR` = "avatar"
- `JWT_CREATED` = "created"
- `JWT_AUTHORITIES` = "authorities"

**其他：**

- `RESOURCE_PREFIX` = "/profile"（资源映射路径前缀）
- `LOOKUP_RMI` = "rmi:"（RMI 远程方法调用）
- `LOOKUP_LDAP` = "ldap:"（LDAP 远程方法调用）
- `LOOKUP_LDAPS` = "ldaps:"（LDAPS 远程方法调用）
- `JOB_WHITELIST_STR` = { "com.ruoyi" }（定时任务白名单）
- `JOB_ERROR_STR` = 定时任务违规字符数组

#### HttpStatus - HTTP 状态码常量

| 常量名                | 值   | 说明           |
| --------------------- | ---- | -------------- |
| SUCCESS               | 200  | 成功           |
| CREATED               | 201  | 已创建         |
| ACCEPTED              | 202  | 已接受         |
| NO_CONTENT            | 204  | 无内容         |
| MOVED_PERM            | 301  | 永久移动       |
| SEE_OTHER             | 303  | 查看其他       |
| TEMPORARY_REDIRECT    | 307  | 临时重定向     |
| PERMANENT_REDIRECT    | 308  | 永久重定向     |
| BAD_REQUEST           | 400  | 错误请求       |
| UNAUTHORIZED          | 401  | 未授权         |
| FORBIDDEN             | 403  | 禁止访问       |
| NOT_FOUND             | 404  | 未找到         |
| BAD_METHOD            | 405  | 方法不允许     |
| NOT_ACCEPTABLE        | 406  | 不可接受       |
| CONFLICT              | 409  | 冲突           |
| TOO_MANY_REQUESTS     | 429  | 请求过多       |
| INTERNAL_SERVER_ERROR | 500  | 服务器内部错误 |
| NOT_IMPLEMENTED       | 501  | 未实现         |
| BAD_GATEWAY           | 502  | 错误网关       |
| SERVICE_UNAVAILABLE   | 503  | 服务不可用     |
| GATEWAY_TIMEOUT       | 504  | 网关超时       |

#### UserConstants - 用户相关常量

**用户名长度：**

- `USERNAME_MIN_LENGTH` = 2
- `USERNAME_MAX_LENGTH` = 20

**密码长度：**

- `PASSWORD_MIN_LENGTH` = 5
- `PASSWORD_MAX_LENGTH` = 20

**用户状态：**

- `USER_NORMAL` = "0"（正常）
- `USER_DISABLE` = "1"（停用）
- `USER_DELETE` = "2"（删除）

**菜单类型：**

- `TYPE_DIR` = "M"（目录）
- `TYPE_MENU` = "C"（菜单）
- `TYPE_BUTTON` = "F"（按钮）

---

## 4. core 包

### 代码结构

```
com.ktg.common.core/
├── controller/
│   └── BaseController.java        # 控制器基类
├── domain/
│   ├── AjaxResult.java            # 统一响应对象
│   ├── BaseEntity.java            # 实体基类
│   ├── TreeEntity.java            # 树形结构实体基类
│   ├── TreeSelect.java            # 树形选择数据结构
│   ├── entity/                    # 系统实体类
│   │   ├── ItemType.java
│   │   ├── SysAutoCodePart.java
│   │   ├── SysAutoCodeResult.java
│   │   ├── SysAutoCodeRule.java
│   │   ├── SysDept.java
│   │   ├── SysDictData.java
│   │   ├── SysDictType.java
│   │   ├── SysMenu.java
│   │   ├── SysRole.java
│   │   └── SysUser.java
│   └── model/                     # 数据传输模型
│       ├── LoginBody.java
│       ├── LoginUser.java
│       └── RegisterBody.java
├── page/
│   ├── PageDomain.java            # 分页参数域
│   ├── TableDataInfo.java         # 表格数据响应
│   └── TableSupport.java          # 表格参数支持
├── redis/
│   └── RedisCache.java            # Redis 缓存工具类
└── text/
    ├── CharsetKit.java            # 字符集工具
    ├── Convert.java               # 类型转换工具
    └── StrFormatter.java          # 字符串格式化工具
```

### 依赖关系

#### BaseController 依赖

- **本模块依赖**：
  - `constant.HttpStatus` - HTTP 状态码
  - `core.domain.AjaxResult` - 统一响应对象
  - `core.domain.model.LoginUser` - 登录用户模型
  - `core.page.PageDomain` - 分页参数域
  - `core.page.TableDataInfo` - 表格数据响应
  - `core.page.TableSupport` - 表格参数支持
  - `utils.DateUtils` - 日期工具
  - `utils.PageUtils` - 分页工具
  - `utils.SecurityUtils` - 安全工具
  - `utils.StringUtils` - 字符串工具
  - `utils.sql.SqlUtil` - SQL 工具

- **第三方依赖**：
  - `org.slf4j` - 日志框架
  - `org.springframework.web` - Web 数据绑定（WebDataBinder）
  - `com.github.pagehelper` - 分页插件

#### RedisCache 依赖

- **第三方依赖**：`org.springframework.data.redis.core.RedisTemplate`（Spring Data Redis）

### 功能

#### controller 子包 - BaseController

所有 Controller 的基类，提供通用的 Web 层数据处理能力。

**核心方法：**

| 方法                                                        | 说明                                           |
| ----------------------------------------------------------- | ---------------------------------------------- |
| `initBinder(WebDataBinder)`                                 | 日期参数绑定，将字符串自动转换为 Date 类型     |
| `startPage()`                                               | 设置请求分页数据（调用 PageUtils.startPage()） |
| `startOrderBy()`                                            | 设置请求排序数据                               |
| `clearPage()`                                               | 清理分页的线程变量                             |
| `getDataTable(List<?>)`                                     | 响应请求分页数据，封装为 TableDataInfo         |
| `success()` / `success(String)` / `success(String, Object)` | 返回成功响应                                   |
| `error()` / `error(String)`                                 | 返回失败响应                                   |
| `toAjax(int)` / `toAjax(boolean)`                           | 根据影响行数/布尔值返回响应                    |
| `redirect(String)`                                          | 页面跳转（重定向）                             |
| `getLoginUser()`                                            | 获取当前登录用户信息                           |
| `getUserId()`                                               | 获取登录用户 ID                                |
| `getDeptId()`                                               | 获取登录部门 ID                                |
| `getUsername()`                                             | 获取登录用户名                                 |

#### domain 子包

**BaseEntity - 实体基类**

所有实体类的父类，包含通用审计字段：

| 字段        | 类型                | 说明                           |
| ----------- | ------------------- | ------------------------------ |
| searchValue | String              | 搜索值                         |
| createBy    | String              | 创建者                         |
| createTime  | Date                | 创建时间（@JsonFormat 格式化） |
| updateBy    | String              | 更新者                         |
| updateTime  | Date                | 更新时间（@JsonFormat 格式化） |
| remark      | String              | 备注                           |
| params      | Map<String, Object> | 请求参数（动态扩展）           |

**AjaxResult - 统一响应对象**

继承 `HashMap<String, Object>`，所有 API 响应的统一格式。

**核心字段：**

- `code` - 状态码（int）
- `msg` - 消息（String）
- `data` - 数据对象（Object）

**静态工厂方法：**

```java
AjaxResult.success()                    // {code:200, msg:"操作成功"}
AjaxResult.success(Object)              // {code:200, msg:"操作成功", data:...}
AjaxResult.success(String)              // {code:200, msg:自定义消息}
AjaxResult.success(String, Object)      // {code:200, msg:..., data:...}
AjaxResult.error()                      // {code:500, msg:"操作失败"}
AjaxResult.error(String)                // {code:500, msg:自定义消息}
AjaxResult.error(String, Object)        // {code:500, msg:..., data:...}
AjaxResult.error(int, String)           // 自定义状态码和消息
```

**链式调用支持：**

- 重写 `put(String, Object)` 方法，返回 `this`，支持链式操作

**TreeEntity - 树形结构实体基类**

继承 BaseEntity，用于菜单、部门等树形结构数据。

**扩展字段：**

- `parentId` - 父节点 ID
- `parentName` - 父节点名称
- `ancestors` - 祖级列表
- `children` - 子节点列表

**TreeSelect - 树形选择数据结构**

用于前端树形下拉选择组件的数据结构：

- `id` - 节点 ID
- `label` - 节点标签
- `children` - 子节点列表

**entity 子包 - 系统核心实体**

| 实体类            | 说明                 |
| ----------------- | -------------------- |
| SysUser           | 系统用户             |
| SysRole           | 系统角色             |
| SysMenu           | 系统菜单             |
| SysDept           | 系统部门             |
| SysDictType       | 字典类型             |
| SysDictData       | 字典数据             |
| SysAutoCodeRule   | 自动编码规则         |
| SysAutoCodePart   | 自动编码规则组成部分 |
| SysAutoCodeResult | 自动编码生成结果     |
| ItemType          | 物料类型             |

**model 子包 - 数据传输模型**

| 模型类       | 说明                                         |
| ------------ | -------------------------------------------- |
| LoginBody    | 登录请求体（username, password, code, uuid） |
| RegisterBody | 注册请求体                                   |
| LoginUser    | 登录用户信息（实现 UserDetails 接口）        |

**LoginUser 核心字段：**

- `userId` - 用户 ID
- `deptId` - 部门 ID
- `token` - 登录令牌
- `loginTime` - 登录时间
- `expireTime` - 过期时间
- `permissions` - 权限列表
- `user` - 用户实体（SysUser）

#### page 子包 - 分页支持

**PageDomain - 分页参数域**

从请求参数中提取的分页信息：

- `pageNum` - 页码
- `pageSize` - 每页数量
- `orderBy` - 排序列
- `isAsc` - 排序方向（asc/desc）

**TableDataInfo - 表格数据响应**

分页查询的统一响应格式：

- `code` - 状态码
- `msg` - 消息
- `rows` - 数据列表
- `total` - 总记录数

**TableSupport - 表格参数支持**

从 HttpServletRequest 构建分页参数的工具类：

- `buildPageRequest()` - 从请求参数构建 PageDomain

#### redis 子包 - RedisCache

Spring RedisTemplate 的封装类，提供便捷的缓存操作方法。

**核心方法分类：**

**对象缓存（ValueOperations）：**

```java
setCacheObject(key, value)                    // 存储对象
setCacheObject(key, value, timeout, timeUnit) // 存储对象并设置过期时间
getCacheObject(key)                            // 获取对象
deleteObject(key)                               // 删除单个对象
deleteObject(collection)                        // 批量删除
expire(key, timeout)                            // 设置过期时间
expire(key, timeout, unit)                     // 设置过期时间（指定单位）
```

**List 缓存（ListOperations）：**

```java
setCacheList(key, dataList)      // 存储 List
getCacheList(key)                 // 获取 List
```

**Set 缓存（SetOperations）：**

```java
setCacheSet(key, dataSet)         // 存储 Set
getCacheSet(key)                   // 获取 Set
```

**Hash 缓存（HashOperations）：**

```java
setCacheMap(key, dataMap)                     // 存储 Map
getCacheMap(key)                               // 获取 Map
setCacheMapValue(key, hKey, value)             // 设置 Hash 中的单个值
getCacheMapValue(key, hKey)                     // 获取 Hash 中的单个值
delCacheMapValue(key, hKey)                     // 删除 Hash 中的单个值
getMultiCacheMapValue(key, hKeys)               // 批量获取 Hash 值
```

**键操作：**

```java
keys(pattern)  // 按模式获取键列表
```

#### text 子包 - 文本工具

**CharsetKit - 字符集工具**

提供字符集名称和 Charset 对象的转换：

- `toCharset(String)` - 字符串转 Charset
- `toCharset(String, Charset)` - 字符串转 Charset，带默认值
- 支持 UTF-8、GBK、ISO-8859-1 等常用字符集

**Convert - 类型转换工具**

支持各种基本类型和数组的转换：

- `toStr(Object)` / `toStr(Object, String)` - 转字符串
- `toInt(Object)` / `toInt(Object, int)` - 转 int
- `toLong(Object)` / `toLong(Object, long)` - 转 long
- `toDouble(Object)` / `toDouble(Object, double)` - 转 double
- `toFloat(Object)` / `toFloat(Object, float)` - 转 float
- `toBool(Object)` / `toBool(Object, boolean)` - 转 boolean
- `toBigDecimal(Object)` - 转 BigDecimal
- `toDate(Object)` - 转 Date
- `toEnum(Class<T>, Object)` - 转枚举
- `toStrMap(Map<?, ?>)` - 转 String 类型的 Map

**StrFormatter - 字符串格式化工具**

支持 `{}` 占位符的字符串格式化：

```java
StrFormatter.format("Hello, {}!", "World")  // "Hello, World!"
StrFormatter.format("Value: {}", 123)        // "Value: 123"
```

---

## 5. enums 包

### 代码结构

```
com.ktg.common.enums/
├── BusinessStatus.java    # 业务操作状态
├── BusinessType.java      # 业务操作类型
├── CycleMethodMnum.java   # 周期方法枚举
├── DataSourceType.java    # 数据源类型
├── HttpMethod.java        # HTTP请求方法
├── LimitType.java         # 限流类型
├── OperatorType.java      # 操作人类型
├── PartTypeEnum.java      # 编码部分类型
└── UserStatus.java        # 用户状态
```

### 依赖关系

- 所有枚举类均为纯定义，无外部依赖（除 Java 标准库）

### 功能

该包定义系统所有枚举类型，提高代码可读性和类型安全。

#### 枚举列表

| 枚举                | 枚举值                                                       | 说明                                           |
| ------------------- | ------------------------------------------------------------ | ---------------------------------------------- |
| **BusinessType**    | OTHER, INSERT, UPDATE, DELETE, GRANT, EXPORT, IMPORT, FORCE, GENCODE, CLEAN | 业务操作类型，用于 @Log 注解                   |
| **BusinessStatus**  | SUCCESS, FAIL                                                | 业务操作状态（成功/失败）                      |
| **DataSourceType**  | MASTER, SLAVE                                                | 数据源类型（主库/从库），用于 @DataSource 注解 |
| **HttpMethod**      | GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE          | HTTP 请求方法                                  |
| **LimitType**       | DEFAULT, IP, CUSTOMER                                        | 限流类型，用于 @RateLimiter 注解               |
| **OperatorType**    | OTHER, MANAGE, MOBILE                                        | 操作人类型（管理端/移动端），用于 @Log 注解    |
| **UserStatus**      | OK, DISABLE, DELETED                                         | 用户状态（正常/停用/删除）                     |
| **CycleMethodMnum** | -                                                            | 周期方法枚举（用于编码规则）                   |
| **PartTypeEnum**    | -                                                            | 编码部分类型枚举（用于自动编码规则）           |

#### BusinessType 详解

```java
OTHER(0),      // 其它
INSERT(1),     // 新增
UPDATE(2),     // 修改
DELETE(3),     // 删除
GRANT(4),      // 授权
EXPORT(5),     // 导出
IMPORT(6),     // 导入
FORCE(7),      // 强退
GENCODE(8),    // 生成代码
CLEAN(9)       // 清空数据
```

---

## 6. exception 包

### 代码结构

```
com.ktg.common.exception/
├── base/
│   └── BaseException.java        # 基础异常
├── file/
│   ├── FileException.java        # 文件异常基类
│   ├── FileNameLengthLimitExceededException.java  # 文件名过长异常
│   ├── FileSizeLimitExceededException.java        # 文件大小超限异常
│   └── InvalidExtensionException.java             # 文件扩展名无效异常
├── job/
│   └── TaskException.java        # 定时任务异常
├── user/
│   ├── CaptchaException.java      # 验证码异常
│   ├── CaptchaExpireException.java  # 验证码过期异常
│   ├── UserException.java          # 用户异常基类
│   └── UserPasswordNotMatchException.java  # 密码不匹配异常
├── BussinessException.java        # 业务异常
├── DemoModeException.java         # 演示模式异常
├── GlobalException.java           # 全局异常处理器（接口）
├── ServiceException.java          # 服务层异常
└── UtilException.java             # 工具类异常
```

### 依赖关系

- **BaseException**：
  - 依赖本模块：`utils.MessageUtils`、`utils.StringUtils`
- **ServiceException**：无额外依赖，直接继承 RuntimeException
- 其他异常类：继承相应的基类异常

### 功能

该包定义系统所有异常类型和全局异常处理规范。

#### 异常继承体系

```
RuntimeException
├── BaseException                    # 基础异常（支持国际化）
│   ├── FileException                # 文件异常基类
│   │   ├── FileNameLengthLimitExceededException
│   │   ├── FileSizeLimitExceededException
│   │   └── InvalidExtensionException
│   ├── TaskException                # 定时任务异常
│   └── UserException                # 用户异常基类
│       ├── CaptchaException
│       ├── CaptchaExpireException
│       └── UserPasswordNotMatchException
├── ServiceException                 # 服务层异常（最常用）
├── BussinessException               # 业务异常（拼写遗留）
├── DemoModeException                # 演示模式异常
├── UtilException                    # 工具类异常
└── GlobalException                  # 全局异常处理器接口（标记）
```

#### BaseException - 基础异常

所有自定义异常的基类，支持国际化消息解析。

**核心字段：**

| 字段           | 类型     | 说明             |
| -------------- | -------- | ---------------- |
| module         | String   | 所属模块         |
| code           | String   | 错误码           |
| args           | Object[] | 错误码对应的参数 |
| defaultMessage | String   | 默认消息         |

**getMessage() 方法逻辑：**

1. 如果 code 不为空，尝试通过 `MessageUtils.message(code, args)` 获取国际化消息
2. 如果获取失败或 code 为空，返回 defaultMessage

#### ServiceException - 服务层异常

业务代码中最常用的异常类型。

**核心字段：**

| 字段          | 类型    | 说明                   |
| ------------- | ------- | ---------------------- |
| code          | Integer | 错误码                 |
| message       | String  | 错误提示               |
| detailMessage | String  | 错误明细（内部调试用） |

**构造方法：**

```java
ServiceException()                                    // 空构造（反序列化）
ServiceException(String message)                      // 仅消息
ServiceException(String message, Integer code)        // 消息 + 错误码
```

**链式调用支持：**

```java
ServiceException.setMessage(String)
ServiceException.setDetailMessage(String)
```

#### 其他异常说明

| 异常类                               | 触发场景                 |
| ------------------------------------ | ------------------------ |
| FileException                        | 文件操作相关错误的基类   |
| FileSizeLimitExceededException       | 上传文件大小超过限制     |
| FileNameLengthLimitExceededException | 文件名长度超过限制       |
| InvalidExtensionException            | 文件扩展名不允许         |
| TaskException                        | 定时任务执行失败         |
| UserException                        | 用户相关异常基类         |
| CaptchaException                     | 验证码错误               |
| CaptchaExpireException               | 验证码过期               |
| UserPasswordNotMatchException        | 密码不匹配               |
| DemoModeException                    | 演示模式下禁止的操作     |
| UtilException                        | 工具类执行过程中发生错误 |

---

## 7. filter 包

### 代码结构

```
com.ktg.common.filter/
├── RepeatableFilter.java          # 可重复读请求过滤器
├── RepeatedlyRequestWrapper.java  # 可重复读请求包装器
├── XssFilter.java                 # XSS攻击防护过滤器
└── XssHttpServletRequestWrapper.java  # XSS请求包装器
```

### 依赖关系

- **所有过滤器类**：
  - 依赖本模块：`utils.StringUtils`
  - **第三方依赖**：`javax.servlet`（Servlet API），实现 `Filter` 接口
  - **RepeatedlyRequestWrapper** 还依赖 `org.springframework.http.MediaType` 判断请求内容类型

### 功能

该包提供 HTTP 请求的过滤器链组件。

#### XssFilter - XSS 攻击防护过滤器

防止跨站脚本（XSS）攻击的 Servlet 过滤器。

**过滤规则：**

- 仅过滤 POST/PUT 请求，GET/DELETE 请求不过滤
- 支持配置排除 URL 列表

**核心方法：**

| 方法                                                        | 说明                                                 |
| ----------------------------------------------------------- | ---------------------------------------------------- |
| `init(FilterConfig)`                                        | 初始化，读取 exclude 参数（排除 URL 列表，逗号分隔） |
| `doFilter(ServletRequest, ServletResponse, FilterChain)`    | 执行过滤逻辑                                         |
| `handleExcludeURL(HttpServletRequest, HttpServletResponse)` | 判断当前 URL 是否在排除列表中                        |

**过滤流程：**

1. 获取请求方法，如果是 GET/DELETE，直接放行
2. 检查当前 URL 是否在排除列表中，如果是，直接放行
3. 将请求包装为 `XssHttpServletRequestWrapper`
4. 传递包装后的请求给过滤器链

#### XssHttpServletRequestWrapper - XSS 请求包装器

继承 `HttpServletRequestWrapper`，对参数进行 HTML 转义处理。

**重写方法：**

- `getParameter(String)` - 获取参数值并转义
- `getParameterValues(String)` - 获取参数值数组并转义
- `getParameterMap()` - 获取参数 Map 并转义所有值
- `getHeader(String)` - 获取 Header 并转义

**转义实现：**
使用 `EscapeUtil` 进行 HTML 转义，将 `<script>alert('xss')</script>` 转换为 `&lt;script&gt;alert(&#39;xss&#39;)&lt;/script&gt;`

#### RepeatableFilter - 可重复读请求过滤器

解决 HttpServletRequest 的输入流只能读取一次的问题。

**应用场景：**

- 日志记录需要读取请求体
- 防重提交校验需要读取请求体
- 签名校验需要读取请求体

**过滤条件：**

- 仅对 `application/json` 类型的请求进行包装
- 其他类型请求直接放行

**过滤流程：**

1. 判断请求是否为 JSON 类型
2. 如果是，包装为 `RepeatedlyRequestWrapper`
3. 否则，使用原始请求

#### RepeatedlyRequestWrapper - 可重复读请求包装器

继承 `HttpServletRequestWrapper`，将输入流缓存到字节数组中。

**核心实现：**

```java
// 在构造方法中读取输入流并缓存
byte[] body = IOUtils.toByteArray(request.getInputStream());

// 重写 getInputStream()，返回缓存的 ByteArrayInputStream
@Override
public ServletInputStream getInputStream() {
    return new ByteArrayServletInputStream(body);
}

// 重写 getReader()，使用缓存的字节数组
@Override
public BufferedReader getReader() {
    return new BufferedReader(new InputStreamReader(getInputStream()));
}
```

这样，请求体可以被多次读取，而不会出现 "Stream closed" 错误。

---

## 8. utils 包

### 代码结构

```
com.ktg.common.utils/
├── barcode/
│   └── BarcodeUtil.java           # 条码/二维码工具
├── bean/
│   ├── BeanUtils.java             # Bean工具
│   └── BeanValidators.java        # Bean校验工具
├── file/
│   ├── FileTypeUtils.java         # 文件类型判断
│   ├── FileUploadUtils.java       # 文件上传工具
│   ├── FileUtils.java             # 文件操作工具
│   ├── ImageUtils.java            # 图片处理工具
│   ├── MimeTypeUtils.java         # MIME类型工具
│   └── MinioUtil.java             # MinIO文件操作
├── html/
│   ├── EscapeUtil.java            # HTML转义工具
│   └── HTMLFilter.java            # HTML过滤工具
├── http/
│   ├── HttpHelper.java            # HTTP请求辅助
│   └── HttpUtils.java             # HTTP请求工具
├── ip/
│   ├── AddressUtils.java          # IP地址解析
│   └── IpUtils.java               # IP获取工具
├── poi/
│   ├── ExcelHandlerAdapter.java   # Excel处理器适配器
│   └── ExcelUtil.java             # Excel导入导出工具
├── reflect/
│   └── ReflectUtils.java          # 反射工具
├── sign/
│   ├── Base64.java                # Base64编解码
│   └── Md5Utils.java              # MD5加密
├── spring/
│   └── SpringUtils.java           # Spring上下文工具
├── sql/
│   └── SqlUtil.java               # SQL工具
├── uuid/
│   ├── IdUtils.java               # ID生成工具
│   ├── Seq.java                   # 序列生成器
│   └── UUID.java                  # UUID工具
├── Arith.java                     # 精确计算工具
├── DateUtils.java                 # 日期工具
├── DictUtils.java                 # 字典工具
├── ExceptionUtil.java             # 异常工具
├── LogUtils.java                  # 日志工具
├── MessageUtils.java              # 消息工具
├── PageUtils.java                 # 分页工具
├── SecurityUtils.java             # 安全工具
├── ServletUtils.java              # Servlet工具
├── StringUtils.java               # 字符串工具
└── Threads.java                   # 线程工具
```

### 依赖关系

#### 核心工具类依赖

**StringUtils**：

- **第三方依赖**：`org.apache.commons.lang3.StringUtils`（继承扩展）、`org.springframework.util.AntPathMatcher`（路径匹配）
- 依赖本模块：`constant.Constants`、`core.text.StrFormatter`

**DateUtils**：

- **第三方依赖**：`org.apache.commons.lang3.time.DateUtils`（继承扩展）、`java.time.*`（Java 8 时间 API）

**SecurityUtils**：

- **第三方依赖**：`org.springframework.security.core.Authentication`、`org.springframework.security.core.context.SecurityContextHolder`、`org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder`
- 依赖本模块：`constant.HttpStatus`、`core.domain.model.LoginUser`、`exception.ServiceException`

#### 子包依赖

**barcode/BarcodeUtil**：

- **第三方依赖**：`net.sf.barcode4j`（条码生成）、`com.google.zxing`（二维码生成）

**file/MinioUtil**：

- **第三方依赖**：`io.minio.MinioClient`（MinIO SDK）
- 依赖本模块：`utils.ServletUtils`、`utils.spring.SpringUtils`

**poi/ExcelUtil**：

- **第三方依赖**：`org.apache.poi`（Apache POI Excel 库）
- 依赖本模块：`annotation.Excel/Excels`、`config.RuoYiConfig`、`core.domain.AjaxResult`、`core.text.Convert`、`exception.UtilException`、多个 utils 子工具类

**spring/SpringUtils**：

- **第三方依赖**：`org.springframework.beans.BeansException`、`org.springframework.context.ApplicationContext`

### 功能

#### 顶层工具类

##### StringUtils - 字符串工具

继承并扩展 `org.apache.commons.lang3.StringUtils`。

**核心方法：**

| 方法                                                     | 说明                                          |
| -------------------------------------------------------- | --------------------------------------------- |
| `nvl(T, T)`                                              | 获取参数不为空值（空则返回默认值）            |
| `isEmpty(Collection/Map/Array/String)`                   | 判断是否为空                                  |
| `isNotEmpty(...)`                                        | 判断是否非空                                  |
| `isNull(Object)` / `isNotNull(Object)`                   | 判断是否为 null                               |
| `isArray(Object)`                                        | 判断是否为数组类型                            |
| `trim(String)`                                           | 去空格                                        |
| `substring(String, int)` / `substring(String, int, int)` | 截取字符串（支持负数索引）                    |
| `format(String, Object...)`                              | 格式化文本（{} 占位符）                       |
| `ishttp(String)`                                         | 是否为 http(s):// 开头                        |
| `str2Set(String, String)`                                | 字符串转 Set                                  |
| `str2List(String, String, boolean, boolean)`             | 字符串转 List                                 |
| `toUnderScoreCase(String)`                               | 驼峰转下划线命名                              |
| `convertToCamelCase(String)`                             | 下划线大写转驼峰（HELLO_WORLD -> HelloWorld） |
| `toCamelCase(String)`                                    | 下划线转驼峰（user_name -> userName）         |
| `matches(String, List<String>)`                          | 查找字符串是否匹配列表中的任意一个            |
| `isMatch(String, String)`                                | URL 路径匹配（支持 ?, *, ** 通配符）          |
| `padl(Number, int)` / `padl(String, int, char)`          | 数字/字符串左补齐                             |

**路径匹配规则：**

- `?` - 匹配单个字符
- `*` - 匹配一层路径内的任意字符串，不可跨层级
- `**` - 匹配任意层路径

##### DateUtils - 日期工具

继承并扩展 `org.apache.commons.lang3.time.DateUtils`。

**日期格式常量：**

```java
YYYY = "yyyy"
YYYY_MM = "yyyy-MM"
YYYY_MM_DD = "yyyy-MM-dd"
YYYYMMDDHHMMSS = "yyyyMMddHHmmss"
YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"
```

**核心方法：**

| 方法                                          | 说明                                      |
| --------------------------------------------- | ----------------------------------------- |
| `getNowDate()`                                | 获取当前 Date 型日期                      |
| `getDate()`                                   | 获取当前日期字符串（yyyy-MM-dd）          |
| `getTime()`                                   | 获取当前时间字符串（yyyy-MM-dd HH:mm:ss） |
| `dateTimeNow()` / `dateTimeNow(String)`       | 获取当前日期时间字符串                    |
| `parseDateToStr(String, Date)`                | 日期转字符串（指定格式）                  |
| `dateTime(String, String)`                    | 字符串转日期（指定格式）                  |
| `datePath()`                                  | 日期路径（年/月/日 如 2024/04/24）        |
| `parseDate(Object)`                           | 智能解析日期字符串（支持多种格式）        |
| `getServerStartDate()`                        | 获取服务器启动时间                        |
| `differentDaysByMillisecond(Date, Date)`      | 计算相差天数                              |
| `getDatePoor(Date, Date)`                     | 计算两个时间差（返回如 "3天2小时30分钟"） |
| `toDate(LocalDateTime)` / `toDate(LocalDate)` | Java 8 Time API 转 Date                   |

**parsePatterns 支持的格式：**

```
"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM"
"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM"
"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"
```

##### SecurityUtils - 安全工具

与 Spring Security 集成的安全相关工具。

**核心方法：**

| 方法                              | 说明                              |
| --------------------------------- | --------------------------------- |
| `getUserId()`                     | 获取当前登录用户 ID               |
| `getDeptId()`                     | 获取当前登录部门 ID               |
| `getUsername()`                   | 获取当前登录用户名                |
| `getLoginUser()`                  | 获取当前登录用户对象（LoginUser） |
| `getAuthentication()`             | 获取 Authentication 对象          |
| `encryptPassword(String)`         | 使用 BCrypt 加密密码              |
| `matchesPassword(String, String)` | 判断密码是否匹配                  |
| `isAdmin(Long)`                   | 判断是否为管理员（userId == 1）   |

**实现原理：**

- 从 `SecurityContextHolder.getContext().getAuthentication()` 获取认证信息
- Principal 中存储的是 `LoginUser` 对象

##### ServletUtils - Servlet 工具

封装 Servlet API 的常用操作。

**核心方法：**

| 方法                                                         | 说明                                                   |
| ------------------------------------------------------------ | ------------------------------------------------------ |
| `getRequest()` / `getResponse()`                             | 获取当前 Request/Response（通过 RequestContextHolder） |
| `getParameter(String)` / `getParameterToInt(String)`         | 获取请求参数                                           |
| `getHeader(String)`                                          | 获取请求头                                             |
| `setCookie(HttpServletResponse, String, String)` / `deleteCookie()` | Cookie 操作                                            |
| `renderString(HttpServletResponse, String)`                  | 将字符串渲染到客户端                                   |
| `urlEncode(String)` / `urlDecode(String)`                    | URL 编解码                                             |
| `getIpAddr(HttpServletRequest)`                              | 获取客户端 IP 地址                                     |

##### PageUtils - 分页工具

与 PageHelper 集成的分页工具。

**核心方法：**

| 方法          | 说明                                         |
| ------------- | -------------------------------------------- |
| `startPage()` | 开始分页（从请求参数读取 pageNum, pageSize） |
| `clearPage()` | 清理分页的线程变量                           |

**实现原理：**

- 从 Request 中读取分页参数：
  - `pageNum` - 页码（默认 1）
  - `pageSize` - 每页数量（默认 10）
  - `orderByColumn` - 排序列
  - `isAsc` - 排序方向

##### DictUtils - 字典工具

从 Redis 缓存中获取字典数据。

**核心方法：**

| 方法                                      | 说明                     |
| ----------------------------------------- | ------------------------ |
| `getDictLabel(String, String, String)`    | 根据字典类型和值获取标签 |
| `getDictValue(String, String, String)`    | 根据字典类型和标签获取值 |
| `getDictCache(String)`                    | 获取字典缓存             |
| `setDictCache(String, List<SysDictData>)` | 设置字典缓存             |
| `removeDictCache(String, String)`         | 删除指定字典缓存         |
| `clearDictCache()`                        | 清空所有字典缓存         |

**缓存 Key 格式：**

- `SYS_DICT_KEY + dictType`

##### Arith - 精确计算工具

解决浮点数精度问题的精确计算工具。

**核心方法：**

| 方法                                               | 说明                           |
| -------------------------------------------------- | ------------------------------ |
| `add(double, double)`                              | 精确加法                       |
| `sub(double, double)`                              | 精确减法                       |
| `mul(double, double)`                              | 精确乘法                       |
| `div(double, double)` / `div(double, double, int)` | 精确除法（可指定保留小数位数） |
| `round(double, int)`                               | 四舍五入（指定小数位数）       |

**实现原理：**

- 使用 `BigDecimal` 进行精确计算
- 避免 `double` 类型的精度丢失问题

##### 其他顶层工具

| 工具类            | 主要功能                                   |
| ----------------- | ------------------------------------------ |
| **Threads**       | 线程工具（sleep、关闭线程池）              |
| **MessageUtils**  | 国际化消息工具（从 i18n 资源文件获取消息） |
| **LogUtils**      | 日志操作记录工具（异步记录操作日志）       |
| **ExceptionUtil** | 异常信息获取工具（获取异常堆栈信息）       |

#### barcode 子包 - 条码/二维码工具

**BarcodeUtil** 基于 barcode4j 和 zxing 库生成一维码和二维码。

**核心方法：**

| 方法                                | 说明                |
| ----------------------------------- | ------------------- |
| `generateCode128(String, int, int)` | 生成 Code128 一维码 |
| `generateCode39(String, int, int)`  | 生成 Code39 一维码  |
| `generateEAN13(String, int, int)`   | 生成 EAN-13 一维码  |
| `generateQRCode(String, int, int)`  | 生成 QR Code 二维码 |
| `decodeQRCode(BufferedImage)`       | 解析二维码内容      |

**第三方依赖说明：**

- **barcode4j**：一维码生成（Code128、Code39、EAN-13 等）
- **zxing**：二维码生成和解析（QR Code）

#### bean 子包 - Bean 工具

**BeanUtils**：

| 方法                                       | 说明                            |
| ------------------------------------------ | ------------------------------- |
| `copyBean(Object, Class<T>)`               | Bean 属性复制（返回新对象）     |
| `copyProperties(Object, Object)`           | Bean 属性复制（目标对象已存在） |
| `beanToMap(Object)`                        | Bean 转 Map                     |
| `mapToBean(Map<String, Object>, Class<T>)` | Map 转 Bean                     |
| `getProperty(Object, String)`              | 获取属性值                      |
| `setProperty(Object, String, Object)`      | 设置属性值                      |

**BeanValidators**：JSR-303 Bean 校验工具

| 方法                                          | 说明                                              |
| --------------------------------------------- | ------------------------------------------------- |
| `validate(T)`                                 | 校验对象，返回约束冲突集合                        |
| `validateWithException(T)`                    | 校验对象，失败则抛出 ConstraintViolationException |
| `validateProperty(T, String)`                 | 校验对象的单个属性                                |
| `extractMessage(Set<ConstraintViolation<T>>)` | 提取校验错误消息                                  |

#### file 子包 - 文件工具

**FileUtils**：文件基础操作

| 方法                                             | 说明                   |
| ------------------------------------------------ | ---------------------- |
| `writeBytes(byte[], String)`                     | 将字节数组写入文件     |
| `readFileToBytes(String)`                        | 读取文件到字节数组     |
| `writeString(String, String)`                    | 将字符串写入文件       |
| `readFileToString(String)`                       | 读取文件到字符串       |
| `copyFile(String, String)`                       | 复制文件               |
| `deleteFile(String)` / `deleteDirectory(String)` | 删除文件/目录          |
| `getFileExtension(String)`                       | 获取文件扩展名         |
| `getFileName(String)`                            | 获取文件名（不含路径） |

**FileUploadUtils**：文件上传处理

| 方法                                    | 说明                     |
| --------------------------------------- | ------------------------ |
| `upload(String baseDir, MultipartFile)` | 上传文件（返回访问 URL） |
| `upload(MultipartFile)`                 | 上传文件到默认目录       |
| `getExtension(MultipartFile)`           | 获取上传文件扩展名       |
| `assertAllowed(String extension)`       | 校验文件扩展名是否允许   |

**配置项：**

- 最大文件大小：从 RuoYiConfig 获取
- 允许的扩展名：可配置白名单

**FileTypeUtils**：根据文件头判断真实文件类型

| 方法                                    | 说明                     |
| --------------------------------------- | ------------------------ |
| `getFileType(String fileName)`          | 根据文件名获取类型       |
| `getFileExtendName(byte[] headerBytes)` | 根据文件头字节获取扩展名 |

**支持的文件类型：**

- 图片：JPG, PNG, GIF, BMP
- 文档：PDF, DOC, DOCX, XLS, XLSX, PPT, PPTX
- 压缩包：ZIP, RAR, 7Z
- 视频：MP4, AVI, MOV
- 音频：MP3, WAV

**ImageUtils**：图片处理

| 方法                                                         | 说明               |
| ------------------------------------------------------------ | ------------------ |
| `getImage(String path)`                                      | 读取图片为字节数组 |
| `compressImage(String sourcePath, String targetPath)`        | 压缩图片           |
| `scaleImage(String sourcePath, String targetPath, int width, int height)` | 缩放图片           |
| `convertImageFormat(String sourcePath, String targetPath, String format)` | 转换图片格式       |

**MimeTypeUtils**：MIME 类型判断

| 方法                                            | 说明                         |
| ----------------------------------------------- | ---------------------------- |
| `getMimeType(String fileName)`                  | 根据文件名获取 MIME 类型     |
| `getExtension(String mimeType)`                 | 根据 MIME 类型获取扩展名     |
| `belongsTo(String mimeType, String parentType)` | 判断 MIME 类型是否属于某一类 |

**常用 MIME 类型映射：**

| 扩展名 | MIME 类型                                                    |
| ------ | ------------------------------------------------------------ |
| .txt   | text/plain                                                   |
| .html  | text/html                                                    |
| .css   | text/css                                                     |
| .js    | application/javascript                                       |
| .json  | application/json                                             |
| .xml   | application/xml                                              |
| .pdf   | application/pdf                                              |
| .doc   | application/msword                                           |
| .docx  | application/vnd.openxmlformats-officedocument.wordprocessingml.document |
| .xls   | application/vnd.ms-excel                                     |
| .xlsx  | application/vnd.openxmlformats-officedocument.spreadsheetml.sheet |
| .jpg   | image/jpeg                                                   |
| .png   | image/png                                                    |
| .gif   | image/gif                                                    |
| .mp3   | audio/mpeg                                                   |
| .mp4   | video/mp4                                                    |
| .zip   | application/zip                                              |

**MinioUtil**：MinIO 对象存储工具

| 方法                                                         | 说明             |
| ------------------------------------------------------------ | ---------------- |
| `uploadFile(String bucketName, String fileName, MultipartFile)` | 上传文件到 MinIO |

**上传流程：**

1. 从 Spring 容器获取 `MinioClient` Bean
2. 调用 `putObject()` 上传文件
3. 调用 `getPresignedObjectUrl()` 获取访问 URL
4. 去掉签名参数，返回永久访问 URL

**第三方依赖：**

- `io.minio.MinioClient` - MinIO Java 客户端
- 使用的方法：
  - `MinioClient.builder().endpoint().credentials().build()` - 构建客户端
  - `PutObjectArgs.builder().bucket().object().stream().contentType().build()` - 构建上传参数
  - `GetPresignedObjectUrlArgs.builder().bucket().object().method().build()` - 构建获取 URL 参数

#### html 子包 - HTML 工具

**EscapeUtil**：HTML/JS 转义与反转义

| 方法                         | 说明                        |
| ---------------------------- | --------------------------- |
| `escape(String)`             | HTML 转义（< 转 &lt; 等）   |
| `unescape(String)`           | HTML 反转义（&lt; 转 < 等） |
| `escapeJavaScript(String)`   | JavaScript 转义             |
| `unescapeJavaScript(String)` | JavaScript 反转义           |

**转义规则：**

| 原字符 | 转义后 |
| ------ | ------ |
| <      | &lt;   |
| >      | &gt;   |
| "      | &quot; |
| '      | &#39;  |
| &      | &amp;  |
| /      | &#47;  |

**HTMLFilter**：基于白名单的 HTML 标签过滤

| 方法                  | 说明                        |
| --------------------- | --------------------------- |
| `filter(String html)` | 过滤 HTML，只保留白名单标签 |

**白名单标签：**

- 允许的标签：`a`, `b`, `blockquote`, `br`, `caption`, `cite`, `code`, `col`, `colgroup`, `dd`, `del`, `div`, `dl`, `dt`, `em`, `h1`-`h6`, `i`, `img`, `li`, `ol`, `p`, `pre`, `q`, `small`, `span`, `strike`, `strong`, `sub`, `sup`, `table`, `tbody`, `td`, `tfoot`, `th`, `thead`, `tr`, `u`, `ul`
- 允许的属性：`href`, `src`, `width`, `height`, `alt`, `title`, `class`, `style`, `target`

**过滤策略：**

- 移除不在白名单中的标签
- 移除不在白名单中的属性
- 移除 `javascript:` 协议的链接
- 移除 `on*` 事件属性

#### http 子包 - HTTP 工具

**HttpUtils**：HTTP 客户端封装

| 方法                                                         | 说明                        |
| ------------------------------------------------------------ | --------------------------- |
| `sendGet(String url)`                                        | 发送 GET 请求               |
| `sendGet(String url, Map<String, String> params)`            | 发送带参数的 GET 请求       |
| `sendPost(String url, String data)`                          | 发送 POST 请求（JSON 数据） |
| `sendPost(String url, Map<String, String> params)`           | 发送 POST 请求（表单数据）  |
| `sendPost(String url, Map<String, String> headers, Map<String, String> params)` | 发送带 Header 的 POST 请求  |

**实现原理：**

- 使用 Java 原生 `HttpURLConnection`
- 支持 HTTP 和 HTTPS
- 自动处理编码和响应解析

**HttpHelper**：HTTP 请求辅助

| 方法                                  | 说明                     |
| ------------------------------------- | ------------------------ |
| `getBodyString(HttpServletRequest)`   | 从请求中获取 Body 字符串 |
| `getParameterMap(HttpServletRequest)` | 获取请求参数 Map         |

#### ip 子包 - IP 工具

**IpUtils**：获取客户端真实 IP 地址

| 方法                            | 说明              |
| ------------------------------- | ----------------- |
| `getIpAddr(HttpServletRequest)` | 获取客户端真实 IP |

**IP 查找顺序：**

1. `X-Forwarded-For`
2. `Proxy-Client-IP`
3. `WL-Proxy-Client-IP`
4. `HTTP_CLIENT_IP`
5. `HTTP_X_FORWARDED_FOR`
6. `X-Real-IP`
7. `request.getRemoteAddr()`

**特殊处理：**

- 多个 IP 时取第一个非 unknown 的 IP
- `0:0:0:0:0:0:0:1` 转换为 `127.0.0.1`

**AddressUtils**：通过 IP 地址解析地理位置

| 方法                            | 说明                   |
| ------------------------------- | ---------------------- |
| `getRealAddressByIP(String ip)` | 获取 IP 对应的真实地址 |

**实现方式：**

- 支持纯真 IP 库（本地文件）
- 支持在线接口查询（如淘宝 IP 库）
- 内网 IP 返回 "内网IP"
- 解析失败返回 "XX XX"

#### poi 子包 - Excel 工具

**ExcelUtil**：功能强大的 Excel 导入导出工具

**核心功能：**

- 基于 `@Excel` 注解的配置化导出
- 日期格式化、字典转换、表达式转换
- 图片导出、下拉列表、数据统计
- 大数据量流式写入（SXSSF）防止内存溢出
- 导入数据解析、类型转换、校验

**导出示例：**

```java
// 1. 定义实体类
public class User {
    @Excel(name = "用户ID")
    private Long userId;
    
    @Excel(name = "用户名称")
    private String userName;
    
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;
    
    @Excel(name = "状态", dictType = "sys_user_status")
    private String status;
    
    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}

// 2. 导出
ExcelUtil<User> util = new ExcelUtil<>(User.class);
util.exportExcel(response, userList, "用户数据");

// 3. 导入
List<User> list = util.importExcel(inputStream);
```

**@Excel 注解常用属性：**

| 属性             | 类型       | 说明                             |
| ---------------- | ---------- | -------------------------------- |
| name             | String     | 列名                             |
| sort             | int        | 导出排序                         |
| dateFormat       | String     | 日期格式                         |
| dictType         | String     | 字典类型                         |
| readConverterExp | String     | 读取转换表达式（如 "0=男,1=女"） |
| separator        | String     | 分隔符                           |
| scale            | int        | BigDecimal 精度                  |
| cellType         | ColumnType | 列类型（NUMERIC/STRING/IMAGE）   |
| type             | Type       | 导出类型（ALL/EXPORT/IMPORT）    |
| isExport         | boolean    | 是否导出                         |
| isStatistics     | boolean    | 是否自动统计                     |

**ExcelUtil 核心方法：**

| 方法                                                        | 说明                               |
| ----------------------------------------------------------- | ---------------------------------- |
| `exportExcel(List<T>, String)`                              | 导出 Excel 到文件                  |
| `exportExcel(HttpServletResponse, List<T>, String)`         | 导出 Excel 到响应流                |
| `exportExcel(HttpServletResponse, List<T>, String, String)` | 导出 Excel（带标题）               |
| `importExcel(InputStream)`                                  | 从输入流导入 Excel                 |
| `importExcel(InputStream, int)`                             | 从输入流导入 Excel（指定标题行数） |
| `importTemplateExcel(HttpServletResponse, String)`          | 导出导入模板                       |

**大数据量处理：**

- 使用 `SXSSFWorkbook` 进行流式写入
- 内存中只保留指定数量的行（默认 500 行）
- 超过部分写入临时文件，避免 OOM

**图片导出：**

- 支持 `ColumnType.IMAGE` 类型
- 可以是图片路径或字节数组
- 自动调整单元格大小

**下拉列表：**

- 通过 `combo()` 属性设置下拉选项
- 通过 `prompt()` 属性设置提示信息
- 支持从字典动态获取选项

**数据统计：**

- 通过 `isStatistics = true` 启用
- 自动对数值列求和
- 在最后一行显示"合计"

**ExcelHandlerAdapter**：自定义数据处理器接口

```java
public interface ExcelHandlerAdapter {
    /**
     * 格式化
     * @param value 单元格数据值
     * @param args excel注解args参数组
     * @return 处理后的值
     */
    Object format(Object value, String[] args);
}
```

使用场景：需要自定义复杂的数据转换逻辑时，可以实现此接口。

#### reflect 子包 - 反射工具

**ReflectUtils**：反射操作工具类

**核心方法：**

| 方法                                                 | 说明                   |
| ---------------------------------------------------- | ---------------------- |
| `getFieldValue(Object, String)`                      | 获取对象的字段值       |
| `setFieldValue(Object, String, Object)`              | 设置对象的字段值       |
| `invokeMethod(Object, String, Class<?>[], Object[])` | 调用对象的方法         |
| `invokeGetter(Object, String)`                       | 调用对象的 getter 方法 |
| `invokeSetter(Object, String, Object)`               | 调用对象的 setter 方法 |
| `getAccessibleField(Class<?>, String)`               | 获取可访问的字段       |
| `getAccessibleMethod(Class<?>, String, Class<?>...)` | 获取可访问的方法       |
| `getSuperClassGenricType(Class<?>)`                  | 获取父类的泛型类型     |

**特殊处理：**

- 自动处理私有字段/方法的访问权限（setAccessible(true)）
- 支持多级属性访问（如 "user.dept.name"）
- 支持继承的字段和方法

#### sign 子包 - 签名/加密工具

**Base64**：Base64 编解码

| 方法                     | 说明                         |
| ------------------------ | ---------------------------- |
| `encode(byte[])`         | 字节数组编码为 Base64 字符串 |
| `encode(String)`         | 字符串编码为 Base64 字符串   |
| `decode(String)`         | Base64 字符串解码为字节数组  |
| `decodeToString(String)` | Base64 字符串解码为字符串    |

**Md5Utils**：MD5 加密

| 方法                           | 说明                  |
| ------------------------------ | --------------------- |
| `encrypt(String)`              | MD5 加密（32 位小写） |
| `encrypt(String, String salt)` | MD5 加密（加盐）      |
| `encryptUpper(String)`         | MD5 加密（32 位大写） |

**加盐策略：**

- 通常将盐值存储在数据库中
- 加密方式：`MD5(password + salt)` 或 `MD5(salt + password)`

#### spring 子包 - Spring 上下文工具

**SpringUtils**：Spring 容器操作工具

| 方法                                   | 说明                              |
| -------------------------------------- | --------------------------------- |
| `getBean(String name)`                 | 通过名称获取 Bean                 |
| `getBean(Class<T> clazz)`              | 通过类型获取 Bean                 |
| `getBean(String name, Class<T> clazz)` | 通过名称和类型获取 Bean           |
| `containsBean(String name)`            | 判断容器中是否包含指定名称的 Bean |
| `isSingleton(String name)`             | 判断是否为单例 Bean               |
| `getType(String name)`                 | 获取 Bean 的类型                  |
| `getAliases(String name)`              | 获取 Bean 的别名                  |

**实现原理：**

- 实现 `ApplicationContextAware` 接口
- 在 Spring 容器启动时注入 `ApplicationContext`
- 通过 `ApplicationContext` 操作 Bean

**使用场景：**

- 非 Spring 管理的类需要访问 Spring Bean
- 动态获取 Bean（根据名称或类型）
- 检查 Bean 是否存在

#### sql 子包 - SQL 工具

**SqlUtil**：SQL 安全工具

| 方法                                | 说明                          |
| ----------------------------------- | ----------------------------- |
| `escapeOrderBySql(String orderBy)`  | 转义排序字段（防止 SQL 注入） |
| `filterKeyword(String value)`       | 过滤 SQL 关键字               |
| `isValidOrderByField(String field)` | 验证排序字段是否合法          |
| `hasOrderByInject(String value)`    | 检查是否包含 SQL 注入风险     |

**安全检查：**

- 检查是否包含 SQL 关键字（如 `UNION`, `SELECT`, `INSERT`, `DELETE`, `UPDATE`, `DROP`, `--`, `;` 等）
- 检查是否包含特殊字符
- 只允许字母、数字、下划线、逗号、空格
- 只允许 `asc` 和 `desc` 作为排序方向

**使用场景：**

- 动态排序时，对前端传递的排序字段进行校验
- 防止 SQL 注入攻击

#### uuid 子包 - UUID/ID 工具

**UUID**：UUID 生成工具

| 方法           | 说明                                                         |
| -------------- | ------------------------------------------------------------ |
| `randomUUID()` | 生成随机 UUID（带横杠，如 550e8400-e29b-41d4-a716-446655440000） |
| `simpleUUID()` | 生成简化 UUID（去掉横杠，如 550e8400e29b41d4a716446655440000） |
| `fastUUID()`   | 使用 ThreadLocalRandom 生成高性能 UUID                       |
| `uuid32()`     | 生成 32 位 UUID（无横杠）                                    |

**IdUtils**：ID 生成器

| 方法            | 说明                            |
| --------------- | ------------------------------- |
| `snowflakeId()` | 生成雪花算法 ID（long 类型）    |
| `randomUUID()`  | 生成 UUID 字符串                |
| `objectId()`    | 生成 MongoDB ObjectId 风格的 ID |

**雪花算法（Snowflake）：**

- 64 位 long 类型
- 1 位符号位 + 41 位时间戳 + 10 位工作机器 ID + 12 位序列号
- 支持分布式环境下的唯一 ID 生成
- 趋势递增

**Seq**：序列生成器

| 方法                   | 说明                                        |
| ---------------------- | ------------------------------------------- |
| `getId()`              | 生成日期+序列号格式的 ID（如 202404240001） |
| `getId(String prefix)` | 生成带前缀的 ID（如 ORDER202404240001）     |
| `nextId()`             | 生成下一个序列号                            |

**实现原理：**

- 日期格式：yyyyMMdd
- 序列号：4 位数字，每日从 1 开始
- 支持线程安全

---

## 9. xss 包

### 代码结构

```
com.ktg.common.xss/
├── Xss.java              # XSS校验注解
└── XssValidator.java     # XSS校验器实现
```

### 依赖关系

- **Xss** 注解：
  - **第三方依赖**：`javax.validation`（Bean Validation API），使用 `@Constraint` 元注解
- **XssValidator**：
  - **第三方依赖**：`javax.validation.ConstraintValidator` 接口
  - 依赖本模块：`utils.html.HTMLFilter`（HTML 过滤逻辑）

### 功能

该包提供基于 Bean Validation 的 XSS 防护注解。

#### @Xss - XSS 校验注解

**作用目标：**

- `METHOD` - 方法
- `FIELD` - 字段
- `CONSTRUCTOR` - 构造器
- `PARAMETER` - 参数

**属性：**

| 属性    | 类型                       | 默认值               | 说明                 |
| ------- | -------------------------- | -------------------- | -------------------- |
| message | String                     | "不允许任何脚本运行" | 校验失败时的提示消息 |
| groups  | Class<?>[]                 | {}                   | 校验分组             |
| payload | Class<? extends Payload>[] | {}                   | 负载信息             |

#### XssValidator - XSS 校验器实现

实现 `ConstraintValidator<Xss, String>` 接口。

**校验逻辑：**

```java
@Override
public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    if (value == null) {
        return true;  // null 值通过校验（由 @NotNull 负责）
    }
    // 使用 HTMLFilter 过滤后与原值比较
    String filtered = HTMLFilter.filter(value);
    return value.equals(filtered);
}
```

**校验原理：**

- 如果过滤后的字符串与原字符串不同，说明包含 HTML 标签或脚本
- 此时校验失败，返回 false

#### 与 XssFilter 的区别

| 特性     | XssFilter          | @Xss 注解          |
| -------- | ------------------ | ------------------ |
| 实现方式 | Servlet Filter     | Bean Validation    |
| 作用时机 | 请求进入时         | 数据绑定时         |
| 处理方式 | HTML 转义          | 校验并拒绝         |
| 粒度     | 全局（可配置排除） | 字段级（精确控制） |
| 使用场景 | 通用防护           | 特定字段校验       |

**使用建议：**

- **XssFilter**：作为第一道防线，对所有请求参数进行转义
- **@Xss 注解**：作为第二道防线，对特定字段进行严格校验
- 两者配合使用，提供多层防护

#### 使用示例

```java
public class Article {
    /**
     * 文章标题（不允许包含 HTML）
     */
    @Xss(message = "标题不能包含脚本内容")
    private String title;
    
    /**
     * 文章内容（允许包含部分 HTML 标签）
     * 注意：这里不使用 @Xss，因为内容需要保留部分 HTML
     * 由业务代码使用 HTMLFilter 进行白名单过滤
     */
    private String content;
    
    /**
     * 作者名称
     */
    @Xss
    private String author;
}
```

**校验触发：**

- 在 Controller 方法参数上使用 `@Valid` 或 `@Validated` 注解
- Spring MVC 会自动执行校验

```java
@PostMapping("/article")
public AjaxResult add(@Valid @RequestBody Article article) {
    // article 已通过 @Xss 校验
    return AjaxResult.success();
}
```

---

## ktg-common 模块功能汇总

### 模块定位

ktg-common 是 ktg-mes 项目的**公共通用模块**，为整个系统提供基础设施和通用能力支持。该模块不包含任何业务逻辑，而是被其他所有模块（ktg-admin、ktg-framework、ktg-mes 等）依赖使用。

### 模块职责

#### 1. AOP 注解体系

提供声明式编程能力，通过切面在运行时增强方法行为：

- `@DataScope` - 数据权限过滤
- `@DataSource` - 多数据源切换
- `@Excel/@Excels` - Excel 导入导出配置
- `@Log` - 操作日志记录
- `@RateLimiter` - 接口限流
- `@RepeatSubmit` - 防重复提交

#### 2. 配置管理

封装特定功能模块的配置属性绑定类：

- `RuoYiConfig` - 项目元数据和文件上传相关配置（名称、版本、上传路径等）
- `MinioConfig` - MinIO 对象存储连接配置

#### 3. 常量定义

集中管理一部分核心魔法数字和字符串常量：

- `Constants` - 通用常量（字符集、Redis Key 前缀、JWT 声明等）
- `HttpStatus` - HTTP 状态码
- `UserConstants` - 用户相关常量
- `GenConstants` - 代码生成常量
- `ScheduleConstants` - 定时任务常量

#### 4. 核心领域模型

定义系统基础实体类和统一响应：

- `BaseEntity` - 实体基类（审计字段）
- `AjaxResult` - 统一 API 响应对象
- `BaseController` - 控制器基类（分页、响应封装）
- `RedisCache` - Redis 缓存封装
- `PageDomain/TableDataInfo` - 分页支持

#### 5. 枚举类型

定义所有业务枚举，确保类型安全：

- `BusinessType` - 业务操作类型
- `DataSourceType` - 数据源类型
- `UserStatus` - 用户状态
- `LimitType` - 限流类型
- `OperatorType` - 操作人类型

#### 6. 异常体系

构建完整的自定义异常继承体系：

- `BaseException` - 基础异常（支持国际化）
- `ServiceException` - 服务层异常（最常用）
- `FileException` 系列 - 文件上传异常
- `UserException` 系列 - 用户认证异常

#### 7. Web 过滤器

提供 HTTP 请求的预处理和后处理：

- `XssFilter` - XSS 攻击防护
- `RepeatableFilter` - 可重复读请求体

#### 8. 工具类库

提供开发中最常用的各种工具方法：

| 工具类                      | 功能                                     |
| --------------------------- | ---------------------------------------- |
| `StringUtils`               | 字符串处理（空判断、转换、格式化）       |
| `DateUtils`                 | 日期时间处理（格式化、解析、计算）       |
| `SecurityUtils`             | 安全工具（用户信息、密码加密）           |
| `ServletUtils`              | Servlet 操作（Request/Response、Cookie） |
| `ExcelUtil`                 | Excel 导入导出（基于注解）               |
| `RedisCache`                | Redis 缓存操作                           |
| `BeanUtils`                 | Bean 属性复制                            |
| `FileUtils/FileUploadUtils` | 文件操作和上传                           |
| `HttpUtils`                 | HTTP 客户端                              |
| `Arith`                     | 精确浮点计算                             |
| `DictUtils`                 | 字典工具                                 |
| `BarcodeUtil`               | 条码/二维码生成                          |
| `MinioUtil`                 | MinIO 对象存储                           |
| `IpUtils/AddressUtils`      | IP 地址获取和解析                        |

### 技术栈与第三方依赖

| 第三方库                 | 版本     | 用途                | 关键类/方法                                      |
| ------------------------ | -------- | ------------------- | ------------------------------------------------ |
| **Spring Framework**     | -        | IoC/DI、Web 基础    | `ApplicationContext`, `WebDataBinder`            |
| **Spring Security**      | -        | 安全认证与授权      | `SecurityContextHolder`, `BCryptPasswordEncoder` |
| **Spring Data Redis**    | -        | Redis 缓存操作      | `RedisTemplate`, `ValueOperations`               |
| **PageHelper**           | -        | MyBatis 分页插件    | `PageHelper.startPage()`, `PageInfo`             |
| **Apache Commons Lang3** | -        | 基础工具类          | `StringUtils`, `DateUtils`（继承扩展）           |
| **Apache Commons IO**    | -        | IO 操作             | `FileUtils`, `IOUtils`                           |
| **Apache POI**           | -        | Excel 处理          | `Workbook`, `Sheet`, `SXSSFWorkbook`（流式写入） |
| **Jackson**              | -        | JSON 序列化         | `ObjectMapper`, `@JsonFormat`                    |
| **Fastjson**             | -        | JSON 处理           | `JSON`, `JSONObject`                             |
| **MinIO SDK**            | 8.2.1    | 对象存储            | `MinioClient`, `PutObjectArgs`                   |
| **Barcode4j**            | 2.0      | 一维码生成          | `Code128Bean`, `EAN13Bean`                       |
| **ZXing**                | 3.3.3    | 二维码生成/解析     | `QRCodeWriter`, `BitMatrix`                      |
| **JJWT**                 | -        | JWT 令牌处理        | `Jwts`, `Claims`                                 |
| **UserAgentUtils**       | -        | 客户端信息解析      | `UserAgent`, `Browser`                           |
| **Hutool**               | 5.8.0.M3 | Java 工具包（备用） | 各种工具类                                       |
| **UReport**              | 2.2.9    | 报表引擎            | 报表设计与生成                                   |
| **SnakeYAML**            | -        | YAML 解析           | 配置文件解析                                     |

### 模块依赖关系

```
                    ┌─────────────────┐
                    │   ktg-common    │
                    │  (本模块: 公共) │
                    └────────┬────────┘
                             │
            ┌────────────────┼────────────────┐
            │                │                │
            ▼                ▼                ▼
    ┌─────────────┐  ┌─────────────┐  ┌─────────────┐
    │  ktg-admin  │  │ktg-framework│  │  ktg-mes    │
    │  (管理后台)  │  │  (框架层)   │  │  (MES业务)  │
    └─────────────┘  └─────────────┘  └─────────────┘
            │
            ▼
    ┌─────────────┐
    │ktg-generator│
    │ (代码生成)   │
    └─────────────┘
```

### 设计亮点

#### 1. 分层清晰

按功能职责划分为 9 个包，职责单一明确：

- `annotation` - 声明式注解
- `config` - 配置管理
- `constant` - 常量定义
- `core` - 核心模型和控制器
- `enums` - 枚举类型
- `exception` - 异常体系
- `filter` - Web 过滤器
- `utils` - 工具类库
- `xss` - XSS 防护注解

#### 2. 声明式编程

大量使用注解 + AOP 模式，使业务代码更简洁：

- 横切关注点（日志、权限、限流、事务）与业务逻辑分离
- 通过注解配置行为，而非硬编码

#### 3. 统一响应

所有 API 响应统一使用 `AjaxResult`：

- 前端处理标准化
- 状态码、消息、数据结构清晰
- 支持链式调用

#### 4. 完整异常体系

- 继承 `RuntimeException`，无需强制捕获
- 支持错误码和国际化消息
- 按业务场景细分异常类型

#### 5. 工具类封装

对第三方库进行二次封装：

- 提供更友好的 API
- 屏蔽底层实现细节
- 统一异常处理
- 便于未来替换实现

#### 6. 多层安全防护

- **XSS 防护**：`XssFilter`（全局转义）+ `@Xss`（字段校验）
- **SQL 注入防护**：`SqlUtil`（排序字段校验）
- **密码安全**：`BCryptPasswordEncoder`（不可逆加密）
- **防重提交**：`@RepeatSubmit`（时间窗口限制）
- **接口限流**：`@RateLimiter`（请求频率控制）

#### 7. 性能优化

- **Excel 大数据处理**：使用 `SXSSFWorkbook` 流式写入，避免 OOM
- **Redis 封装**：统一缓存操作，减少样板代码
- **请求体可重复读**：`RepeatableFilter` 支持日志记录和防重校验
- **日期工具优化**：缓存 `SimpleDateFormat`，避免重复创建

### 与其他模块的交互

#### 被依赖方式

ktg-common 作为所有其他模块的依赖，通过以下方式被使用：

1. **Controller 继承**

   ```java
   @RestController
   @RequestMapping("/user")
   public class UserController extends BaseController {
       // 继承 startPage()、success()、error() 等方法
   }
   ```

2. **实体继承**

   ```java
   public class SysUser extends BaseEntity {
       // 继承 createBy、createTime、updateBy、updateTime 等字段
   }
   ```

3. **注解使用**

   ```java
   @Log(title = "用户管理", businessType = BusinessType.INSERT)
   @PostMapping
   public AjaxResult add(@RequestBody SysUser user) { ... }
   ```

4. **工具类调用**

   ```java
   String username = SecurityUtils.getUsername();
   Date now = DateUtils.getNowDate();
   if (StringUtils.isEmpty(name)) { ... }
   ```

5. **异常抛出**

   ```java
   if (user == null) {
       throw new ServiceException("用户不存在");
   }
   ```

### 文件位置

本文档位于：`docs/modules/ktg-common-模块说明.md`

---

**文档生成时间**：2026-04-24  
**模块版本**：3.8.2  
**基于代码分析**：ktg-common/src/main/java/com/ktg/common/