package com.darian.darianSecurityDemo.exception;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * DESCRIPTION : Service Exception <br>
 * AUTHOR : DATE : <br>
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -3255566884327078816L;
    private IReturnCode errCodeEnum;
    private String errCode;
    private String errMsg;


    public ServiceException(IReturnCode serviceReturnCode) {
        this(serviceReturnCode, null, null);
    }


    public ServiceException(IReturnCode serviceReturnCode, Throwable cause) {
        this(serviceReturnCode, null, cause);
    }

    public ServiceException(IReturnCode serviceReturnCode, String overrideErrorMsg) {
        this(serviceReturnCode, overrideErrorMsg, null);
    }

    public ServiceException(Throwable cause) {
        this(CustomerReturnCode.ERR_SERVICE_UNKNOWN_ERROR, cause);
    }



    public ServiceException(IReturnCode serviceReturnCode, String overrideErrorMsg,
                            Throwable cause) {
        super(buildExceptionMessage(serviceReturnCode, overrideErrorMsg, cause), cause);
        if (null == serviceReturnCode) {
            serviceReturnCode = CustomerReturnCode.ERR_SERVICE_UNKNOWN_ERROR;
        }
        this.errCodeEnum = serviceReturnCode;
        this.errCode = serviceReturnCode.getCode();
        this.errMsg = null != overrideErrorMsg ? overrideErrorMsg : serviceReturnCode.getMsg();
    }

    public IReturnCode getErrCodeEnum() {
        return errCodeEnum;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return null != errMsg ? errMsg : getMessage();
    }

    private static String buildExceptionMessage(IReturnCode serviceReturnCode,
                                                String overrideErrorMsg, Throwable cause) {
        String exMsg;
        if (null != overrideErrorMsg) {
            exMsg = overrideErrorMsg;
        } else if (null != serviceReturnCode) {
            exMsg = serviceReturnCode.getMsg();
        } else {
            exMsg = CustomerReturnCode.ERR_SERVICE_UNKNOWN_ERROR.getMsg();
        }

        if (null != cause && !(cause instanceof ServiceException)) {
            String unknownErrRefNo = new SimpleDateFormat("dHHm").format(new Date());
            unknownErrRefNo = unknownErrRefNo + "-" + getNumber(6);
            exMsg += "[错误参考号SERV#" + unknownErrRefNo + "]";
        }

        return exMsg;
    }


    /**
     * 生成随机数
     */
    public static String getNumber(int len) {
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < len; i++) {
            captcha.append(new Random().nextInt(10));
        }
        return captcha.toString();
    }
}
