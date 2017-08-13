package com.do1.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.do1.demo.bean.Customer;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Customer getById(int Id) {
        return jdbcTemplate.queryForObject("select id, first_name, last_name FROM customers WHERE id=? ", new Object[]{1}, (rs, rownum) ->
                new Customer(rs.getInt(1), rs.getString(2), rs.getString(3))
        );
    }
}
