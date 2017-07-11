package com.fp.gan.db.service.imp;

import com.fp.gan.db.entity.sys.User;
import com.fp.gan.db.repository.ActLogRepository;
import com.fp.gan.db.repository.sys.UserPermissionRepository;
import com.fp.gan.db.repository.sys.UserRepository;
import com.fp.gan.db.repository.sys.UserRoleRepository;
import com.fp.gan.db.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sam on 2017/7/10.
 */
@Component("sysUserService")
public class SysUserServiceImp implements SysUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserPermissionRepository userPermissionRepository;


    @Override
    public User findUserByName(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public User findUserById(Long user_id){
        return userRepository.findOne(user_id);
    }
}
