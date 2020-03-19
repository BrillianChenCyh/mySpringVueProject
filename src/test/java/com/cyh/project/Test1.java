package com.cyh.project;

/**
 *  @author: chenyinghui
 *  @Date: 2020/1/26 17:06
 *  @Description: 函数式接口的使用：一般可以作为方法的参数和返回值类型
 */
public class Test1 {
    //定义一个方法，参数使用函数式接口
    public static void show(MyFunctionInterface myInter){
        myInter.method();
    }

    public static void main(String[] args) {
        //传递实现类
        show(new MyFunctionInterfaceImpl());
        //使用匿名类部类
        show(new MyFunctionInterface() {
            @Override
            public void method() {
                System.out.println("使用匿名类部类调用接口中的抽象方法");
            }
        });
        //由于参数为函数式接口，则可使用lambda表达式
        show(
                ()->{
                    System.out.println("使用Lambda表达式重写接口中的抽象方法");
                }
        );
    }
}
