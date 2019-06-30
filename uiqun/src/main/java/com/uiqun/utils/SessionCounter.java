package com.uiqun.utils;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionCounter implements HttpSessionListener {
    private static int activeSessions = 0;
    //session创建时执行
    public void sessionCreated(HttpSessionEvent se) {
        activeSessions++;
    }
    //session销毁时执行
    public void sessionDestroyed(HttpSessionEvent se) {
        if (activeSessions > 0) {
            activeSessions--;
        }
        //删除sessionId
        HttpSession session = se.getSession();
        UnRepeatLogin.getUserMap().remove(session.getId());
    }
    //获取活动的session个数(在线人数)
    public static int getActiveSessions() {
        return activeSessions;
    }

}
