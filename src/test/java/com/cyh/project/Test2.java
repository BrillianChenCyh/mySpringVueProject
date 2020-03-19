package com.cyh.project;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

/**
 *  @author: chenyinghui
 *  @Date: 2020/1/26 17:06
 *  @Description: 使用stream流的方式，遍历集合，对集合中的数据进行过滤
 *  stream流关注的是做什么，而不是怎么做
 */
public class Test2 {
    public static void main(String[] args) {
        ArrayList<String> list = Lists.newArrayList();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");
        list.stream()
                .filter(name->name.startsWith("张"))
                .filter(name->name.length()==3)
                .forEach(name-> System.out.println(name));
    }

    @Test
    public void test(){
        String string = UUID.randomUUID().toString();
        System.out.println(string);//1760f322-03bb-4491-9283-2535f5a94436
    }
}
