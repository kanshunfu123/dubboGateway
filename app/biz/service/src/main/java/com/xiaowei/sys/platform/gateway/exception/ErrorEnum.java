package com.xiaowei.sys.platform.gateway.exception;

/**
 * 错误码
 * @author jim
 * @date 16/10/12
 */
public enum ErrorEnum {
    /**
     * 错误码分内外两种
     * 对内使用最细粒度错误吗，对外使用统一错误码
     * 对外统一使用本类型第一个错误码。
     */
    //1开头返回接收细节错误码,其它返回父错误码
    RES_CODE_SYS_INVALID_TICKET(2, 2, "用户身份过期"),
    ERROR_HAS_NO_ACL_FAIL(2,402,"您无权限访问系统资源"),
    SYSTEM_ERROR(10000, 10000, "系统错误"),
    ERROR_BIZ_FAIL(10000, 10001, "业务失败"),
    ERROR_BIZ_UNIQUE_REQ_ID(10000, 10002, "唯一性约束重复"),
    SERVICE_ERROR(10000, 90001, "服务错误"),
    ERROR_IMG_CODE_LOSE(10000, 10003, "验证码已失效，请重新获取"),
    ERROR_IMG_CODE(10000, 10004, "验证码不正确"),
    ERROR_USER_LOGIN(10000, 10005, "用户登录信息异常"),
    ERROR_USER_INFO_LIST(10000, 10006, "用户信息列表异常"),
    ERROR_ADD_USER_INFO(10000, 10007, "添加用户信息异常"),
    ERROR_DEL_USER_INFO(10000, 10008, "添加用户信息异常"),
    ERROR_EDIT_USER_INFO(10000, 10009, "编辑用户信息异常"),
    ERROR_DELETE_DEPT_INFO(10000, 10010, "删除部门信息异常"),
    ERROR_AREA_TREE_FAIL(10000,10011,"获取区域树异常"),
    ERROR_ADD_AREA_TREE_FAIL(10000,10012,"新增区域树异常"),
    ERROR_UPDATE_AREA_FAIL(10000, 10013, "编辑区域信息异常"),
    ERROR_DEL_AREA_FAIL(10000, 10014, "删除区域信息异常"),
    ERROR_AREA_NAME_EXIST_FAIL(10000, 10015, "同一层级下存在相同名称的区域"),
    ERROR_ROLE_LIST_FAIL(10000,10016,"获取角色列表异常"),
    ERROR_ROLE_ACL_TREE_FAIL(10000,10017,"获取该角色的权限树异常"),
    ERROR_ROLE_ACL_MENU_TREE_FAIL(10000,10018,"获取该角色的权限菜单异常"),
    ERROR_WATING_PERMISSION_ACL_FAIL(10000,10019,"获取待授权列表异常"),
    ERROR_EDIT_DEPT_INFO(10000, 10020, "编辑部门信息异常"),
    ERROR_DD_DEPT_INFO(10000, 10021, "新增部门信息异常"),
    ERROR_DEPT__TREE_INFO(10000, 10022, "新增部门信息异常"),
    ERROR_ADD_ROLE_INFO(1000,10023,"新增角色信息异常"),
    ERROR_EDIT_ROLE_INFO(1000,10024,"修改角色信息异常"),
    ERROR_ADD_WATER_STANDARD_INFO(1000,10025,"添加水质信息异常"),
    ERROR_DEFAULT(10000, 10000, "系统异常"),
    ERROR_DICTIONARY_NAME_EXIST_FAIL(10000, 10026, "同一层级下存在相同名称的字典"),
    ERROR_DICTIONARY_TREE_FAIL(10000, 10027, "查询字典架构树信息异常"),
    ERROR_ADD_DICTIONARY_FAIL(10000, 10028, "新增字典架构信息异常"),
    ERROR_EDIT_DICTIONARY_FAIL(10000, 10029, "编辑字典架构信息异常"),
    ERROR_DELL_DICTIONARY_FAIL(10000, 10030, "删除字典架构信息异常"),
    ERROR_UPDATE_DICTIONARY_NO_EXIST_FAIL(10000, 10031, "待更新的字典不存在"),
    ERROR_DEL_DICTIONARY_NO_EXIST_FAIL(10000, 10032, "待删除的字典不存在，您无法删除"),
    ERROR_DEL_DICTIONARY_EXIST_CHILD_DEPT_FAIL(10000, 10033, "该字典下还有子级字典,您无法删除"),
    ERROR_DEL_DICTIONARY_EXIST_USER_FAIL(10000, 10034, "该字典下还有子级字典，您无法删除"),
    ERROR_ADD_PARAMETER_FAIL(10000, 10035, "新增参数信息异常"),
    ERROR_EDIT_PARAMETER_FAIL(10000, 10036, "编辑参数信息异常"),
    ERROR_DEL_PARAMETER_FAIL(10000, 10037, "删除参数信息异常"),
    ERROR_SELECT_PARAMETER_FAIL(10000, 10038, "查看水质标准所有类型信息异常"),
    ERROR_ALL_ROLE_FAIL(10000,10039,"获取全部角色信息列表异常"),
    ERROR_STANDARD_FIND_BY_TYPE_FAIL(10000, 10040, "根据水质类型查询参数异常"),
    ERROR_USER_SAFE_OUT_EXCEPTION_FAIL(10000,0,"用户退出异常"),
    ERROR_USER_SAFE_OUT_FAIL(10000,0,"用户退出失败"),
    ERROR_IMG_CODE_FAIL(10000,0,"获取验证码base64异常"),
    ERROR_CHILD_AREA_LIST_FAIL(10000,0,"根据父级id查看子区域信息列表异常"),
    ERROR_CHILD_DICT_ERROR(10000,0,"查询子数据字典异常"),
    //2开头为参数校验信息错误
    ERROR_PARAM(20000, 20000, "参数错误"),
    ERROR_PARAM_EMPTY(20000, 20001, "参数为空"),
    ERROR_PARAM_FORMAT(20000, 20002, "参数格式不正确"),
    ERROR_PARAM_KEY_NOT_EXIST(20000, 20007, "参数不存在"),;

    private final int errorCode;
    private final int parentCode;
    private final String errorMessage;

    ErrorEnum(int parentCode, int errorCode, String errorMessage) {
        this.parentCode = parentCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getParentCode() {
        if (String.valueOf(errorCode).startsWith("1")) {
            return errorCode;
        }

        return parentCode;
    }

    public ErrorEnum getOutError() {
        return getErrorByCode(getParentCode());
    }

    public static ErrorEnum getErrorByCode(int code) {
        for (ErrorEnum errorEnum : values()) {
            if (errorEnum.getErrorCode() == code) {
                return errorEnum;
            }
        }
        return null;
    }
}
