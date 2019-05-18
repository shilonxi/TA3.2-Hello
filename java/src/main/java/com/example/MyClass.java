package com.example;

public class MyClass {
    public String name;
    //定义一个成员变量（实例变量）
    public MyClass(String name)
    {
        this.name=name;
    }
    //提供自定义的构造器
    public static void test()
    {
        System.out.println("hello");
    }
    //类方法
    public static void main(String[] args)
    {
        MyClass myClass=new MyClass("java");
        //创建对象
        System.out.println(myClass.name);
        MyClass.test();
    }
}
