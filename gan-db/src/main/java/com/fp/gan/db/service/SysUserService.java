package com.fp.gan.db.service;

import com.fp.gan.db.entity.sys.User;

/**
 * Created by sam on 2017/7/10.
 */
public interface SysUserService {

    public User findUserByName(String username);
    public User findUserById(Long user_id);
}
