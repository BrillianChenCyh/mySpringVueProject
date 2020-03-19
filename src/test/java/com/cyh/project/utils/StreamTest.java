package com.cyh.project.utils;

import com.cyh.project.entity.UserLombok;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * jdk1.8 Stream流的使用
 * @author 25656
 *
 */
public class StreamTest {

	@Test
	public void contextLoads() {
		List<UserLombok> list = new ArrayList();
		UserLombok user1 = UserLombok.builder().age(18).name("张三").build();
		UserLombok user2 = UserLombok.builder().age(18).name("李四").build();
		UserLombok user3 = UserLombok.builder().age(18).name("王五").build();
		list.add(user1);
		list.add(user2);
		list.add(user3);
		// 获取指定属性的集合
		List<Integer> collect = list.stream().map(UserLombok::getId).collect(Collectors.toList());
		System.out.println(collect.size());
		// 遍历
		list.stream().filter(u -> u.getAge() == 18).forEach(System.out::println);
		// 返回第一个
		Optional<UserLombok> findFirst = list.stream().filter(u -> u.getAge() == 18).findFirst();
		System.out.println(findFirst);
		Stream<UserLombok> userStream = list.stream().filter(user -> "张三".equals(user.getName())// 只保留男性
		);
		// 返回集合
		list = userStream.collect(Collectors.toList());// 将Stream转化为List
		System.out.println(list.toString());// 查看结果
	}

}
