package com.example.demo.Interceptor;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String CASA_TOKEN = "casa-token";
    private final String ACCESS_TOKEN = "casa";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws  Exception{
        final String userTryToken = request.getHeader(CASA_TOKEN);
        logger.info("###### Checking Token ######");
        if ( !userTryToken.equals(ACCESS_TOKEN) || userTryToken.isEmpty() ){
            //return new ResponseEntity<>("Fail : " + userTryToken, HttpStatus.INTERNAL_SERVER_ERROR);
            return false;
        }
        else {
            //return new ResponseEntity<>("Success : " + userTryToken, HttpStatus.OK);
            return true;// new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws  Exception{
        logger.info("====================== END ======================");
    }



}













