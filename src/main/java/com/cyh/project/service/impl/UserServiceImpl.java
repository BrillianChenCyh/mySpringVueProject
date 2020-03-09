package com.cyh.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.cyh.project.sqldao.ThreeSqlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cyh.project.entity.User;
import com.cyh.project.onedao.OneUserDao;
import com.cyh.project.service.UserService;
import com.cyh.project.twodao.TwoUserDao;

/**
 *  @author: chenyinghui
 *  @Date: 2019/11/16 21:14
 *  @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private OneUserDao userDao;

    @Autowired
    private TwoUserDao twoUserDao;

    @Autowired
    private ThreeSqlDao threeSqlDao;

    @Override
    public int insert(User user) {
        return userDao.insertReturnKey(user).getInt();
    }

    @Override
    public int insert2(User user) {
        return twoUserDao.insertReturnKey(user).getInt();
    }

    /**开启注解,value为键*/
    @Cacheable(value = "myname")
    @Override
    public User findByName(String name) {
        return userDao.createQuery().andEq("name",name).single();
    }

    @Override
    public void test1() {
        //方式1：通过lambda表达式
        List<User> select = userDao.getSQLManager().lambdaQuery(User.class).andEq(User::getName, "张三").orderBy("id").select();
        select.stream().forEach(System.out::println);
        select.stream().forEach(user -> {
            System.out.println(user.getName());
        });
        //方式二：通过query类
//        List<User> select1 = userDao.getSQLManager().query(User.class).andEq("name", "张三").orderBy("id").select();
        //方式三：通过createQuery
//        userDao.createQuery().andEq("name","张三").orderBy("id").select()
        //方式四：模板查询，简单查询
        User template = new User();
        template.setName("张三");
        template.setAge(123456);
        User user = userDao.getSQLManager().templateOne(template);
        System.out.println(user);
        String name = "";
        //md字符串也要设置非空串
        List<User> user1 = userDao.abc(name);
        List<Long> a = new ArrayList<>();
        //这里数值必须设置为null，否则会报错
        a = null;
        List<User> user2 = userDao.selectArr(a);
        System.out.println("user1:"+user1.size());
    }

    @Override
    public List<User> sqlserverSelect() {
        return threeSqlDao.all();
    }

}
