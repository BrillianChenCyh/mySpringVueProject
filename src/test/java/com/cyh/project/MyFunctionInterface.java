package com.cyh.project;

/**
 *  @author: chenyinghui
 *  @Date: 2020/1/26 17:09
 *  @Description: 函数式接口：有且只有一个抽象方法的接口
 */
@FunctionalInterface//检查接口是否是函数式接口
public interface MyFunctionInterface {
    //定义一个抽象方法
    void method();
}
