package com.cyh.project.onedao;

import com.cyh.project.entity.User;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@SqlResource("user")
public interface OneUserDao extends BaseMapper<User> {
    List<User> abc(String name);

    List<User> selectArr(List<Long> a);
}

