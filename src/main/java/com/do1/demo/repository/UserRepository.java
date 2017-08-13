package com.do1.demo.repository;

import com.do1.demo.entity.Resource;
import com.do1.demo.entity.Role;
import com.do1.demo.entity.User;
import jdk.nashorn.internal.runtime.UserAccessorProperty;
import org.dom4j.util.UserDataAttribute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);

    @Override
    User getOne(Integer integer);

    @Override
    User findOne(Integer integer);

    @Modifying
    @Query("update User u set u.password=:password where u.id=:id")
    int updateUserPassword(@Param("id") int id,@Param("password") String password);

    @Modifying
    @Query("delete from User u where u.id = :id")
    void deleteUser(@Param("id")int id);

    Page<User> findByCity(String name, Pageable pageable);

    User getUserByUserName(String userName);

    @Query("select r from Role r where r.id in (select ur.roleId from UserRole ur where ur.userId = :id)")
    List<Role> getUserRoleList(@Param("id") int userId );


    @Query("select r from Resource r where r.id in (select rr.resourceId from RoleResource rr where rr.roleId in (select ur.roleId from UserRole ur where ur.userId = :id))")
    List<Resource> getUserResourceList(@Param("id") int userId );
}
