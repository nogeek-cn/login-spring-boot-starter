
package com.darian.darianSecurityDemo.exception;

import java.io.Serializable;

/***
 * @author Darian
 */
public interface IReturnCode extends Serializable {

    String CURRENT_APP_SYSTEM_ID = "ALL";

    String getCode();

    String getMsg();

    boolean isSuccess();

}
