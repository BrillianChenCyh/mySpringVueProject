package com.cyh.project.utils;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.java.Log;

/**
 *  @author: chenyinghui
 *  @Date: 2020/2/14 22:48
 *  @Description: lombok的魅力
 *  使用@CleanUp完成资源释放
 *  使用@SneakyThrows帮我们捕获异常
 */
@Data//getset\tostring\构造啥的都有了
@Builder//new 对象变得优雅
@ToString
public class LombokTest {

    private String name;
    private Integer age;
}

@Log
//日志
//@Slf4j
//@CommonsLog(topic="Test1")
class Test1{
    public static void main(String[] args) {
        //链式编程一步完成!
        LombokTest lombokTest = LombokTest.builder().age(18).name("cyh").build();
        System.out.println(lombokTest);
        log.info("日志记录....");
//        test1(null);
    }

    //非空属性的验证
    public static void test1(@NonNull String name){
        System.out.println(name);
    }

}
