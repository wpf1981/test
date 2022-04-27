package com.example;

import com.example.Entity.Files;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class FilterListTest {

    /**
     * @Description 创建四个学生对象并设值，保存在list集合中，模拟数据
     * @author 刘鹏博
     * @return List<Files>
     */
    public static List<Files> stuList() {
        List<Files> stuList = new ArrayList<Files>();
        Files stu1 = new Files();
        stu1.setFileId(1L);
        stu1.setFileName("张三");
        stu1.setPage(20L);
        stuList.add(stu1);

        Files stu2 = new Files();
        stu2.setFileId(2L);
        stu2.setFileName("李四");
        stu2.setPage(21L);
        stuList.add(stu2);

        Files stu3 = new Files();
        stu3.setFileId(3L);
        stu3.setFileName("王二");
        stu3.setPage(22L);
        stuList.add(stu3);

        Files stu4 = new Files();
        stu4.setFileId(4L);
        stu4.setFileName("张五");
        stu4.setPage(22L);
        stuList.add(stu4);
        return stuList;
    }

    public static void main(String[] args) {
        List<Files> stuList = stuList();

        // 需要筛选的条件：从stuList中筛选出年龄为21和22的学生
        List<Long> ageList = new ArrayList<Long>();
        ageList.add(21L);
        ageList.add(22L);

        // JDK1.8提供了lambda表达式， 可以从stuList中过滤出符合条件的结果。
        // 定义结果集
        List<Files> result = null;
        result = stuList.stream()
                .filter((Files s) -> ageList.contains(s.getPage()))
                .collect(Collectors.toList());

        // 打印原有stuList集合中的数据
        System.out.println("原有stuList集合中的数据");
        stuList.forEach((Files s) -> System.out.println(s.getFileName() + "--->" + s.getPage()));

        // 打印过滤筛选后的result结果
        System.out.println("过滤筛选后的result结果");
        result.forEach((Files s) -> System.out.println(s.getFileName() + "--->" + s.getPage()));

        //通过Collection 系列集合提供的串行流：stream()、并行流： paralleStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();
//2 通过Arrays中的静态方法stream(T[] array) 获取数组流
        /*public static <T> Stream<T> stream(T[] array) {
            return stream(array, 0, array.length);
        }
         */
        Stu[] stus = new Stu[10];
        Stream<Stu> stream2 = Arrays.stream(stus);
/*
  public static <T> Stream<T> stream(T[] array) {
     return stream(array, 0, array.length);
 }
 */
//        .3 通过Stream类中的静态方法 of()
       /*
        //1.单参泛型of
        public static<T> Stream<T> of(T t) {
            return StreamSupport.stream(new Streams.StreamBuilderImpl<>(t), false);
        }
//2.可变参数
        @SafeVarargs
        @SuppressWarnings("varargs") // Creating a stream from an array is safe
        public static<T> Stream<T> of(T... values) {
            return Arrays.stream(values);
        }

        */

        Stream<String> stream3 = Stream.of("hxh", "aj", "hhh");

    }

    private static class Stu {
    }
}
