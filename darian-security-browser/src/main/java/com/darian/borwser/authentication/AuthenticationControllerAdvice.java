package com.darian.borwser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class AuthenticationControllerAdvice {
    private final ObjectMapper objectMapper;

//    @ExceptionHandler(AuthenticationException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @RequestMapping("/onFailUserNamePasswordLogin")
//    public Response<Object> onFailUserNamePasswordLogin(AuthenticationException e) {
//        log.error("login ... error" + "\t[application/json;charset=UTF-8]");
////        if (true) {
////            throw CustomException.generatorRuntimeException("登陆失败！！！");
////        }
//        return Response.error(e, CustomerReturnCode.ERR_APPLICATION_OPER_ERROR);
//    }


//    @ExceptionHandler(AuthenticationException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
////    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
//    public void onFailUserNamePasswordLoginHtml(HttpServletRequest request,
//                                                  HttpServletResponse response,
//                                                  AuthenticationException e, Model model) throws IOException {
//        String produces = request.getHeader("produces");
//        if (produces.contains("application/json")){
//            log.error("login ... error" + "\t[application/json;charset=UTF-8]");
//            response.getWriter().write(objectMapper.writeValueAsString(
//                    Response.error(e, CustomerReturnCode.ERR_SERVICE_UNKNOWN_ERROR)
//            ));
//        }else{
//            log.error("login ... error" + "\t[text/html]");
////            super.onAuthenticationFailure(request, response, exception);
//            response.sendRedirect("loginerror");
//        }
//
//    }

}
