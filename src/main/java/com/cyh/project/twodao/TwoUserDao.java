package com.cyh.project.twodao;

import com.cyh.project.entity.User;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public interface TwoUserDao extends BaseMapper<User> {
}

