package com.mall.shiro;

import com.mall.cache.J2CacheUtils;
import com.mall.utils.Constant;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

import java.io.Serializable;

/**
 * 分布式session管理
 *
 * @author taylor
 * @date 2018年07月31日 上午14:50
 */
public class CluterShiroSessionDao extends EnterpriseCacheSessionDAO {

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);

        final String key = Constant.SESSION_KEY + sessionId.toString();
        setShiroSession(key, session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = super.doReadSession(sessionId);
        if (null == session) {
            final String key = Constant.SESSION_KEY + sessionId.toString();
            session = getShiroSession(key);
        }
        return session;
    }

    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        final String key = Constant.SESSION_KEY + session.getId().toString();
        setShiroSession(key, session);
    }

    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        final String key = Constant.SESSION_KEY + session.getId().toString();

        J2CacheUtils.remove(key);
    }

    private Session getShiroSession(String key) {
        return (Session) J2CacheUtils.get(key);
    }

    private void setShiroSession(String key, Session session) {
        J2CacheUtils.put(key, session);
    }
}
