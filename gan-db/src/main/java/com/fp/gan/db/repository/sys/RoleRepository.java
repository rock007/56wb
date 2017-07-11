package com.fp.gan.db.repository.sys;

import com.fp.gan.db.entity.sys.Permission;
import com.fp.gan.db.entity.sys.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sam on 2017/7/7.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {


}
