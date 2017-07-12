package com.fp.gan.system.comm.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SystemSessionListener implements SessionListener {

    private static Logger _log = LoggerFactory.getLogger(SystemSessionListener.class);

    @Override
    public void onStart(Session session) {
        _log.debug("会话创建：" + session.getId());
    }

    @Override
    public void onStop(Session session) {
        _log.debug("会话停止：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        _log.debug("会话过期：" + session.getId());
    }

}
