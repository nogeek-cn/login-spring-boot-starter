package com.darian.darianSecurityDemo.exception;

import java.util.stream.Stream;


public class CustomException extends RuntimeException {

    /***
     *
     * when you use the method,
     * exceptionMessage while show on app and pop
     * {@link ServiceException)}
     * Example:
     * {
     *   response: {
     *     code: "0005",
     *     msg: message,
     *     data: {...},
     *     time: "2018-12-27 12:08:09:059"
     *   }
     * }
     *
     * @param message
     */
    public static ServiceException genertorPointServiceException(String message) {
        return new ServiceException(new IReturnCode() {
            @Override
            public String getCode() {
                return "ET4B2011";
            }

            @Override
            public String getMsg() {
                return message;
            }

            @Override
            public boolean isSuccess() {
                return false;
            }
        });
    }

    /***
     * <p>when you use this method ,
     * <p>exceptionMessage while show on returndata.errorMessage
     * <p>"系统业务异常" while pop on app
     * <p>{@link ServiceException)}
     * <p>While return
     * <p>{
     * <p>  response: {
     * <p>    code: "0005",
     * <p>    msg: "系统业务异常",
     * <p>    data: {
     * <p>      errorMessage: message,
     * <p>      exceptionClassName: ThisRuntimeException.className,
     * <p>    },
     * <p>    time: "2018-12-27 12:08:09:059"
     * <p>  }
     * <p>}
     *
     * @param message
     */
    public static RuntimeException generatorRuntimeException(String message) {
        return new RuntimeException(message);
    }

    /***
     *
     * @param errorCode
     * @return
     */
    public static ServiceException generatorByErrorCode(String errorCode) {
        CustomerReturnCode darianReturnCode = Stream.of(CustomerReturnCode.values())
                .filter(GPsCode -> GPsCode.getCode().equals(errorCode))
                .findFirst().orElse(CustomerReturnCode.ERR_DATA_OPER_ERROR);
        return new ServiceException(darianReturnCode);
    }
}
