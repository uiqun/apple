package com.uiqun.config.intercepors;

import com.sun.istack.internal.Nullable;
import com.uiqun.constant.PowerConstant;
import com.uiqun.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Component
public class LoginInterceptor implements HandlerInterceptor {

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
        HttpSession session = request.getSession();
        //这里的User是登陆时放入session的
        User user = (User) session.getAttribute("user");
        //如果session中没有user，表示没登陆
        String requestURI = request.getRequestURI();
        if("index".equals(requestURI)||"".equals(requestURI)||"/".equals(requestURI)){
            response.sendRedirect("/index");
            return false;
        }else {
            if (user == null) {
                //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
                //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
                response.sendRedirect("/user/login");
                return false;
            } else {
                return true;    //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
            }
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        User user = (User)request.getSession().getAttribute("user");
        if(user!=null){
            //将用户信息写入modelAndView
            if(modelAndView!=null) {
                modelAndView.addObject("user", user);
                //访问权限处理
                if("/findPrice".equals(request.getRequestURI())||
                        "/findPrice".equals(request.getRequestURI())||
                        "/jumprfq".equals(request.getRequestURI())||
                        "/inquote1".equals(request.getRequestURI())||
                        "/queryLevel/11".equals(request.getRequestURI())||
                        "/bom/searchbom".equals(request.getRequestURI())) {
                    //查价
                    if ("/findPrice".equals(request.getRequestURI()) && user.getRfind() == 1) {
                        request.setAttribute("status", PowerConstant.HAVE_PERMISSION);
                    }
                    //报价  -1为管理员
                    else if ("/jumprfq".equals(request.getRequestURI()) && user.getRquote() > 0 || user.getRquote() == -1) {
                        request.setAttribute("status", PowerConstant.HAVE_PERMISSION);
                    }
                    //询价 -1为管理员
                    else if (("/inquote1".equals(request.getRequestURI())
                            || ("/inquote".equals(request.getRequestURI())
                    ) && user.getRrfq() > 0 || user.getRrfq() == -1)) {
                        request.setAttribute("status", PowerConstant.HAVE_PERMISSION);
                    }
                    //供应商
                    else if ("/queryLevel/11".equals(request.getRequestURI()) && user.getRvendor() == 1) {
                        request.setAttribute("status", PowerConstant.HAVE_PERMISSION);
                    }
                    //热卖库存(不知道哪个路径)
////                    else if("/bom/searchbom".equals(request.getRequestURI())&&user.getRhot()==1){
////                        request.setAttribute("status", PowerConstant.HAVE_PERMISSION);
////                    }
                    //bom
                    else if ("/bom/searchbom".equals(request.getRequestURI()) && user.getRbom() == 1) {
                        request.setAttribute("status", PowerConstant.HAVE_PERMISSION);
                    }
                    //权限不足
                    else {
                        request.setAttribute("status", PowerConstant.PERMISSION_DENIED);
                        request.getRequestDispatcher("/tips").forward(request, response);
                    }
                }

            }
        }
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
