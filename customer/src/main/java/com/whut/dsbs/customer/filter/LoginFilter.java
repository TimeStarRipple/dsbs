package com.whut.dsbs.customer.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whut.dsbs.common.dto.User;
import com.whut.dsbs.customer.constants.JsonResult;
import com.whut.dsbs.customer.constants.ResponseMsg;
import com.whut.dsbs.customer.utils.DecodeUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 权限控制
 *
 * Created by zyb on 2017-04-30.
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器过滤");

        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        String url = httpRequest.getRequestURI();

        //过滤除了login的请求
        if(!url.contains("login")){
            boolean result = checkHTTPBasicAuthorize(servletRequest);
            if (!result) {
                HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

                //设置跨域
//                httpResponse.setHeader("Access-Control-Allow-Origin", httpRequest.getHeader("Origin"));
//                httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//                httpResponse.setHeader("Access-Control-Max-Age", "3600");
//                httpResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");
//                httpResponse.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域

                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.setContentType("application/json; charset=utf-8");
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                ObjectMapper mapper = new ObjectMapper();

                ResponseMsg responseMsg = new ResponseMsg(403, "未经授权", null);
                httpResponse.getWriter().write(mapper.writeValueAsString(responseMsg));
                return;
            }
            else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
        else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 检查是否通过认证
     * @param request
     * @return
     */
    private boolean checkHTTPBasicAuthorize(ServletRequest request)
    {
        try
        {
            HttpServletRequest httpRequest = (HttpServletRequest)request;

            //session过期，重新登录
            HttpSession httpSession = httpRequest.getSession();
            User user = (User) httpSession.getAttribute("user");
            if(user == null){
                return false;
            }

            String auth = httpRequest.getHeader("Authorization");
            if ((auth != null) && (auth.length() > 6))
            {
                String HeadStr = auth.substring(0, 5).toLowerCase();
                if (HeadStr.compareTo("basic") == 0)
                {
                    auth = auth.substring(6, auth.length());
                    String decodedAuth = DecodeUtil.decodeToString(auth);
                    if (decodedAuth != null)
                    {
                        String[] UserArray = decodedAuth.split(":");

                        if (UserArray.length == 2)
                        {
                            if (UserArray[0].compareTo(user.getUsername()) == 0
                                    && UserArray[1].compareTo(user.getPassword()) == 0)
                            {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
}
