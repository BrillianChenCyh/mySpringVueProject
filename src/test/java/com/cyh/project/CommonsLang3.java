package com.cyh.project;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassPathUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName CommonsLang3
 * @Description: commonsLong3的用法
 * @Author chenyinghui
 * @Date 2020/3/7
 * @Version V1.0
 **/
public class CommonsLang3 {
    public static void main(String[] args) {
        /** ArrayUtils：用于对数组的操作，如添加、查找、删除、子数组、倒序、元素类型转换等 */
        Integer[] integers = ArrayUtils.toArray(1, 2, 3);
        System.out.println(integers);

        int[] ints = ArrayUtils.toPrimitive(integers);//对象数组转普通数组
        Integer[] intArr = ArrayUtils.toObject(ints);//普通int转对象数组
        System.out.println(intArr);
        String[] strings = ArrayUtils.toStringArray(intArr);//对象数组转String数组
        System.out.println(strings);

        Integer[] inArr = new Integer[]{1, 2, null};

        //如果里面有null元素，会报错的，所以我们可以用下面这个方法 把null转成指定的值即可
        String[] strings2 = ArrayUtils.toStringArray(inArr,"");
        System.out.println(strings2);

        /**ClassPathUtils：处理类路径的一些工具类 */
        String fullPath = ClassPathUtils.toFullyQualifiedName(Integer.class, "");
        System.out.println(fullPath); //java.lang.
        fullPath = ClassPathUtils.toFullyQualifiedName(Integer.class, "Integer.value");
        System.out.println(fullPath); //java.lang.Integer.value

        /** StringUtils */
        //isEmpty,isNotEmpty
        System.out.println(StringUtils.isEmpty(null));//true
        System.out.println(StringUtils.isEmpty(""));//true
        System.out.println(StringUtils.isEmpty(" "));//false
        //isAnyEmpty,isNoneEmpty
        System.out.println(StringUtils.isAnyEmpty(null,"hello"));//true
        System.out.println(StringUtils.isAnyEmpty("","hello"));//true
        System.out.println(StringUtils.isAnyEmpty(" ","hello"));//false
        //isBlank
        System.out.println(StringUtils.isBlank("  "));//true
        //trim
        System.out.println(StringUtils.trim("  abc  "));//abc
        System.out.println(StringUtils.trim(null));//null
        //substring
        /**
         * //从左边开始截取指定个数
         * public static String left(String str,int len)
         * //从右边开始截取指定个数
         * public static String right(String str,int len)
         * //从中间的指定位置开始截取  指定个数
         */
        System.out.println(StringUtils.left("abcdefg",3)); //从左边开始截取 abc
        //join
        System.out.println(StringUtils.join(strings));//默认空串join 123
        System.out.println(StringUtils.join(strings,";"));//默认空串join 1;2;3
        //deleteWhitespace删除空格
        //removeStart删除以特定字符串开头的字符串，如果没有的话，就不删除
        System.out.println(StringUtils.removeStart("www.domain.com", "www."));
        /** ClassUtils – 用于对Java类的操作（有很多方法还是挺有用的） */

        /** RandomUtils : 需要随机字符串/数字*/

        /** RegExUtils：处理字符串用正则替换等*/

        //...
    }
}
