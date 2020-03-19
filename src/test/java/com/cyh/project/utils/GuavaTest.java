package com.cyh.project.utils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName DateTest
 * @Description: Guava使用
 * @Author chenyinghui
 * @Date 2020/3/5
 * @Version V1.0
 **/
public class GuavaTest {

    // 缓存的实现
    private static final CacheLoader<Long, String> cacheLoader = new CacheLoader<Long, String>() {
        @Override
        public String load(Long key) throws Exception {
            // TODO 从数据库加载数据
            System.out.println("从数据库加载数据");
            return key + ":value";
        }
    };

    // 定义缓存的策略
    private static final LoadingCache<Long, String> loadingCache = CacheBuilder.newBuilder()
            .expireAfterAccess(2, TimeUnit.SECONDS) // 设置在2秒内未访问则过期
            .expireAfterWrite(2, TimeUnit.SECONDS) // 设置缓存在写入2秒后失效
            .refreshAfterWrite(3, TimeUnit.SECONDS) // 设置缓存在写入3秒后，通过CacheLoader的load方法进行刷新
            .maximumSize(100L) // 设置缓存数量上限为100
            .build(cacheLoader);

    public static void main(String[] args) throws Exception{
        // 快速完成到集合的转换
        List<Integer> list = Ints.asList(1, 2, 3, 4);
        System.out.println(list);
        // 快速按分隔符连接
        String join = Ints.join(",", 2, 3, 4, 5);
        System.out.println(join);
        // 数组快速合并
        int[] newIntArray  = Ints.concat(new int[]{1, 2}, new int[]{2, 4});
        // 最大
        int max = Ints.max(newIntArray);
        // 最小
        int min = Ints.min(newIntArray);
        System.out.println("max:"+max+"min:"+min);
        boolean contains = Ints.contains(newIntArray, 1);
        // 集合到数组的转换
        int[] array = Ints.toArray(list);
        System.out.println(contains);
        System.out.println(array.length);
        /** 缓存的简单实现 */
//        loadingCache.put(1L, "James");
//        System.out.println(loadingCache.get(1L));
//        Thread.sleep(5000L);
//        System.out.println(loadingCache.get(1L));
         /*
         on:制定拼接符号，如：test1-test2-test3 中的 “-“ 符号
         skipNulls()：忽略NULL,返回一个新的Joiner实例
         useForNull(“Hello”)：NULL的地方都用字符串”Hello”来代替
        */
        StringBuilder sb=new StringBuilder();
        Joiner.on(",").skipNulls().appendTo(sb,"Hello","guava");
        System.out.println(sb);
        System.out.println(Joiner.on(",").useForNull("none").join(1,null,3));
        System.out.println(Joiner.on(",").skipNulls().join(Arrays.asList(1,2,3,4,null,6)));
        Map<String,String> map=new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        System.out.println(Joiner.on(",").withKeyValueSeparator("=").join(map));
          /*
         on():指定分隔符来分割字符串
         limit():当分割的子字符串达到了limit个时则停止分割
         fixedLength():根据长度来拆分字符串
         trimResults():去掉子串中的空格
         omitEmptyStrings():去掉空的子串
         withKeyValueSeparator():要分割的字符串中key和value间的分隔符,分割后的子串中key和value间的分隔符默认是=
        */
        System.out.println(Splitter.on(",").limit(3).trimResults().split(" a,  b,  c,  d"));//[ a, b, c,d]
        System.out.println(Splitter.fixedLength(3).split("1 2 3"));//[1 2,  3]
        System.out.println(Splitter.on(" ").omitEmptyStrings().splitToList("1  2 3"));
        System.out.println(Splitter.on(",").omitEmptyStrings().split("1,,,,2,,,3"));//[1, 2, 3]
        System.out.println(Splitter.on(" ").trimResults().split("1 2 3")); //[1, 2, 3],默认的连接符是,
        System.out.println(Splitter.on(";").withKeyValueSeparator(":").split("a:1;b:2;c:3"));//{a=1, b=2, c=3}
        /**
         * Multimap可以很容易地把一个键映射到多个值
         */
        Multimap<String,Integer> map1= HashMultimap.create(); //Multimap是把键映射到任意多个值的一般方式
        map1.put("a",1); //key相同时不会覆盖原value
        map1.put("a",2);
        map1.put("a",3);
        System.out.println(map1); //{a=[1, 2, 3]}
        System.out.println(map1.get("a")); //返回的是集合
        System.out.println(map1.size()); //返回所有”键-单个值映射”的个数,而非不同键的个数
        System.out.println(map1.keySet().size()); //返回不同key的个数
        Map<String, Collection<Integer>> mapView=map1.asMap();
        System.out.println(mapView);
        /** BiMap */
        BiMap<String,String> biMap= HashBiMap.create();
        biMap.put("sina","sina.com");
        biMap.put("qq","qq.com");
        biMap.put("sina","sina.cn"); //会覆盖原来的value
//        biMap.put("tecent","qq.com"); //抛出异常
        biMap.forcePut("tecent","qq.com"); //强制替换key
        System.out.println(biMap);
        System.out.println(biMap.inverse().get("sina.com")); //通过value找key
        System.out.println(biMap.inverse().inverse()==biMap); //true
        /** Table它有两个支持所有类型的键：”行”和”列”。 */
        //记录学生在某门课上的成绩
        Table<String,String,Integer> table= HashBasedTable.create();
        table.put("jack","java",100);
        table.put("jack","c",90);
        table.put("mike","java",93);
        table.put("mike","c",100);
        Set<Table.Cell<String,String,Integer>> cells=table.cellSet();
        for (Table.Cell<String,String,Integer> cell : cells) {
            System.out.println(cell.getRowKey()+" "+cell.getColumnKey()+" "+cell.getValue());
        }
        System.out.println(table.row("jack"));
        System.out.println(table);
        System.out.println(table.rowKeySet());
        System.out.println(table.columnKeySet());
        System.out.println(table.values());
        /** 集合操作：交集、差集、并集 */
        Set<Integer> set1= Sets.newHashSet(1,2,3,4,5);
        Set<Integer> set2= Sets.newHashSet(3,4,5,6);
        Sets.SetView<Integer> inter= Sets.intersection(set1,set2); //交集
        System.out.println(inter);
        Sets.SetView<Integer> diff= Sets.difference(set1,set2); //差集,在A中不在B中
        System.out.println(diff);
        Sets.SetView<Integer> union= Sets.union(set1,set2); //并集
        System.out.println(union);
        /** Cache */
        LoadingCache<String,String> cache= CacheBuilder.newBuilder()
                .maximumSize(100) //最大缓存数目
                .expireAfterAccess(1, TimeUnit.SECONDS) //缓存1秒后过期
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return key;
                    }
                });
        cache.put("j","java");
        cache.put("c","cpp");
        cache.put("s","scala");
        cache.put("g","go");
        try {
            System.out.println(cache.get("j"));
            TimeUnit.SECONDS.sleep(2);
            System.out.println(cache.get("s")); //输出s
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        Cache<String,String> cache1= CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .build();
        try {
            String result=cache1.get("java", () -> "hello java");
            System.out.println(result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
