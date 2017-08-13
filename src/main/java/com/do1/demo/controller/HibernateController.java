package com.do1.demo.controller;

import com.do1.demo.entity.Resource;
import com.do1.demo.entity.Role;
import com.do1.demo.entity.User;
import com.do1.demo.repository.UserRepository;
import com.do1.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/hibernate")
@EnableAutoConfiguration
public class HibernateController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping("user")
    @ResponseBody
    public User getUserById(Integer id, String name, String password) {

        if (name == null) {
            //userService.updatePassword(id,password==null?"zxc":password);
            return userRepository.findOne(id);
        }
        return userRepository.getUserByUserName(name);
    }

    @RequestMapping("findUserByCity")
    @ResponseBody
    public Page<User> getUserListByCity(String city) {
        Pageable pageable = new PageRequest(0, 3, Sort.Direction.DESC, "id");
        return userService.findByCity(city, pageable);
    }

    @RequestMapping("getUserRoleList")
    @ResponseBody
    public List<Role> getUserRoleList(int userId) {
        return userRepository.getUserRoleList(userId);
    }

    @RequestMapping("getUserResourceList")
    @ResponseBody
    public List<Resource> getUserResourceList(int userId) {
        return userRepository.getUserResourceList(userId);
    }

    @RequestMapping("saveUser")
    @ResponseBody
    public void saveUser() {
        User u = new User();
        u.setUserName("zxc");
        u.setPassword("zxc");
        userRepository.save(u);
    }
}
