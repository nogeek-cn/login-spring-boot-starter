package com.darian.darianSercurityCore.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.darian.darianSercurityCore.exception.Response.error;
import static java.util.stream.Collectors.toList;

/**
 *
 * user this class to view Error View
 * {@link org.springframework.stereotype.Controller} 通知
 *
 * @author Darian
 */
@ControllerAdvice
@RestController
@Slf4j
public class CustomControllerAdvice {

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response onERR_SERVICE_INPUT_VALIDATION_REJECTED(Exception e) {

        BindingResult bindingResult;
        if (e instanceof BindException) {
            bindingResult = BindException.class.cast(e).getBindingResult();
        } else {
            bindingResult = MethodArgumentNotValidException.class.cast(e).getBindingResult();
        }
        Map<String, Object> errorMap = new HashMap<>();
        genertorMapAndLogger(errorMap, e);
        List<String> bindExceptionMessages = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String errorCode = Stream.of(objectError.getCodes()).findFirst().get();
                    errorCode = errorCode.substring(errorCode.indexOf(".") + 1);
                    return "[" + errorCode + "]:" + objectError.getDefaultMessage();
                }).collect(toList());
        errorMap.put("errorMessage", bindExceptionMessages);
        errorMap.put("errorStank", null);
        return error(errorMap, CustomerReturnCode.ERR_SERVICE_INPUT_VALIDATION_REJECTED);
    }

    @ExceptionHandler({SQLException.class})
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public Response onSQLException(Exception e) {
        Map<String, Object> errorMap = new HashMap<>();
        genertorMapAndLogger(errorMap, e);
        return error(errorMap, CustomerReturnCode.ERR_DATA_OPER_ERROR);
    }

    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler({BadSqlGrammarException.class})
    public Response BadSqlGrammarException(Exception e) {
        Map<String, Object> errorMap = new HashMap<>();
        genertorMapAndLogger(errorMap, e);
        if (e instanceof BadSqlGrammarException) {
            String errorSql = Stream.of(e.getMessage().split("###"))
                    .filter(s -> s.startsWith(" SQL: "))
                    .findFirst().get();
            errorMap.put("errorSql", errorSql.substring(" SQL: ".length()));
        }
        return error(errorMap, CustomerReturnCode.ERR_DATA_OPER_ERROR);
    }


    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(ServiceException.class)
    public Response onPointsServiceException(ServiceException e) {
        Map<String, Object> errorMap = new HashMap<>();
        genertorMapAndLogger(errorMap, e);
        errorMap.put("errorStank", null);
        return error(errorMap, e);
    }

    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(Throwable.class)
    public Response onThrowable(Throwable e) {
        Map<String, Object> errorMap = new HashMap<>();
        genertorMapAndLogger(errorMap, e);
        return error(errorMap, CustomerReturnCode.ERR_APPLICATION_OPER_ERROR);
    }

    private void genertorMapAndLogger(Map<String, Object> errorMap, Throwable e) {
        errorMap.put("errorMessage", e.getMessage());
        errorMap.put("exceptionClassName", e.getClass().getSimpleName());
        List<StackTraceElement> stackTraceElement = Stream.of(e.getStackTrace())
                .filter(ste -> ste.getClassName().startsWith("com.darian")
                ).limit(2).collect(toList());
        if (!stackTraceElement.isEmpty()) {
            errorMap.put("firstAndSecondStanck", stackTraceElement);
        } else {
            errorMap.put("errorStank", Arrays.asList(e.getStackTrace()));
        }

        log.error(errorMap.toString());
    }
}
