package com.darian.darianSecurityDemo.exception;

/***
 * @author Darian
 */
public enum CustomerReturnCode implements IReturnCode {

    SUCCESS("0000", "成功", true),

    NEED_LOGIN("0001", "请去登陆"),

    // M：表示数据信息/处理错误类
    ERR_SERVICE_INPUT_VALIDATION_REJECTED("0002", "输入参数有误"),

    ERR_DATA_OPER_ERROR("0003", "数据操作异常"),

    ERR_SERVICE_UNKNOWN_ERROR("0004", "系统繁忙"),

    ERR_APPLICATION_OPER_ERROR("0005", "系统业务异常"),

    ERR_REMOTE_INVOKE_ERROR("0006", "远程服务调用异常！"),;

    private String code;
    private String msg;
    private String CURRENT_APP_SYSTEM_ID = "COM";

    CustomerReturnCode(String errCodeRightPart, String returnDesc) {
        this.code = this.CURRENT_APP_SYSTEM_ID + errCodeRightPart;
        this.msg = returnDesc;
    }

    CustomerReturnCode(String fullReturnCode, String returnDesc, boolean isSuccessReturnCode) {
        this.code = fullReturnCode;
        this.msg = returnDesc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public boolean isSuccess() {
        return SUCCESS.code.equals(code);
    }

}
