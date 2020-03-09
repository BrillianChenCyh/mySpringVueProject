package com.cyh.project.service;

import com.cyh.project.entity.User;

import java.util.List;

public interface UserService {
    int insert(User user);

    int insert2(User user);

    User findByName(String name);

    void test1();

    List<User> sqlserverSelect();
}
