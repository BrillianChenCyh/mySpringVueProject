package com.cyh.project.sqldao;

import com.cyh.project.entity.User;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public interface ThreeSqlDao extends BaseMapper<User> {
}
