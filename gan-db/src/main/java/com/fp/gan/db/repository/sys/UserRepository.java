package com.fp.gan.db.repository.sys;

import com.fp.gan.db.entity.sys.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sam on 2017/7/7.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
