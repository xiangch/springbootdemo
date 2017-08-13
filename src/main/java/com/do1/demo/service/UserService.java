package com.do1.demo.service;

import com.do1.demo.entity.User;
import com.do1.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository jpa;
    public void updatePassword(int id,String password){
        jpa.updateUserPassword(id,password);
    }
    public Page<User> findByCity(String name, @PageableDefault(value = 2, sort = { "id" }, direction = Sort.Direction.DESC)
            Pageable pageable){
        return jpa.findByCity( name,  pageable);
    }
}
