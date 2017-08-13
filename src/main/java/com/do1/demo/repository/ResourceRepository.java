package com.do1.demo.repository;

import com.do1.demo.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
    @Override
    List<Resource> findAll();
    @Query("select r from Resource  r where r.id in (select rr.resourceId from RoleResource rr where rr.roleId=:id)")
    List<Resource> findResourceByRoleId(@Param("id")Integer roleId);
}
