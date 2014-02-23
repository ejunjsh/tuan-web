package com.sky.tuan.web.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MainInterceptor extends HandlerInterceptorAdapter {

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(!ignoreFile(request.getRequestURI())&&
			CookieSupport.getCookieByName(request, "curCity")==null)
		{
			response.sendRedirect(request.getContextPath()+"/selectCity");
			return false;
		}
		
		request.setAttribute("originalUrl",request.getRequestURI());
		request.setAttribute("queryString",request.getQueryString());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		

		return super.preHandle(request, response, handler);
	}
    
	private boolean ignoreFile(String url){
        if (url.indexOf(".xml")>-1 ||url.indexOf(".gif")>-1 || url.indexOf(".jpg")>-1 || url.indexOf(".png")>-1
            || url.indexOf(".bmp")>-1 || url.indexOf(".css")>-1 || url.indexOf(".js")>-1
                || url.indexOf(".jsp")>-1||url.indexOf("selectCity")>-1
                ||url.indexOf("/getAllCity")>-1){
            return true;
        } else {
            return false;
        }
    }
}

