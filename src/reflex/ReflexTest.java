package reflex;

import reflex.annotation.Android;
import reflex.annotation.WEB;
import reflex.bean.Worker;
import reflex.bean.Person;
import reflex.constructor.GoodStudent;
import reflex.method.ComputerManager;
import reflex.method.PadManager;
import reflex.proxy.AndroidDeveloper;
import reflex.proxy.Developer;
import reflex.proxy.ProxyHandler;
import reflex.utils.ReflexUtils;

import java.lang.reflect.*;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ReflexTest {

    public static void main(String[] args) {
        //测试反射基础信息
//        testBase();
        //测试反射字段
//        testField();
        //测试反射方法
//        testMethod();
        //测试构造方法
//        testConstructor();
        //测试继承与接口
//        testExtendsAndInterface();
        //测试动态代理
//        testProxy();
        //测试注解
        testAnnotation();
    }

    private static void testAnnotation() {
        try {
            ReflexUtils.printAnnotationInfo(Class.forName("reflex.annotation.JAVA"));
            ReflexUtils.printAnnotationInfo(new Android().getClass());
            ReflexUtils.printAnnotationInfo(WEB.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void testProxy() {
        //通过动态代理的形式，在代码执行时对调用的方法进行修改
        AndroidDeveloper zhangsan = new AndroidDeveloper("张三");
        Developer zhangsanProxy = (Developer) Proxy.newProxyInstance(zhangsan.getClass().getClassLoader(), zhangsan.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("code")) {
                    System.out.println(zhangsan.getName() + "评审原型图和接口文档，需求冻结");
                    return method.invoke(zhangsan, args);
                }
                if (method.getName().equals("debug")) {
                    System.out.println(zhangsan.getName() + "由于编代码前评审原型图和接口文档，调试时问题较少");
                }
                return null;
            }
        });
        zhangsanProxy.code();
        zhangsanProxy.debug();

        System.out.println("");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("");

        AndroidDeveloper lisi = new AndroidDeveloper("李四");
        lisi.code();
        lisi.debug();

        System.out.println("");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("");

        // 这是我自己封装了一个动态代理的东东  可以使用下
        Developer zsDev = (Developer) Proxy.newProxyInstance(zhangsan.getClass().getClassLoader(), zhangsan.getClass().getInterfaces(), new ProxyHandler(zhangsan));
        zsDev.code();
        zsDev.debug();

    }


    private static void testExtendsAndInterface() {
        try {
            ReflexUtils.printExtensAndInterface(Class.forName("reflex.bean.Worker"));
            ReflexUtils.printExtensAndInterface(Integer.class);
            ReflexUtils.printExtensAndInterface(int.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void testConstructor() {
        try {
            ReflexUtils.printConstructor(Class.forName("reflex.constructor.Student").getDeclaredConstructor(String.class, int.class)
                    .newInstance("张三", 28), "张三", 28, -1);
            ReflexUtils.printConstructor(new GoodStudent(), "李四", 32, 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testMethod() {
//        ReflexUtils.printMethods(new ComputerManager(), 3, -1);
        // PadManager继承了ComputerManager 所以把ComputerManager的方法也打印出来了
        ReflexUtils.printMethods(new PadManager(), 2, 1);
    }

    private static void testField() {
        Worker developer = new Worker();
        developer.setName("张三");
        developer.setAge(28);
        developer.setHeight(175);
        developer.setMarryFlag(true);
        developer.setLevel(3);
        List<String> addressList = new ArrayList<>();
        addressList.add("沈阳");
        addressList.add("深圳");
        developer.setAddressList(addressList);
        List<String> phoneList = new ArrayList<>();
        phoneList.add("13999999999");
        phoneList.add("13888888888");
        String[] phoneArray = new String[phoneList.size()];
        developer.setPhoneArray(phoneList.toArray(phoneArray));
        ReflexUtils.printFields(developer);
        System.out.println("================================================================");
        Person.Student student = new Person.Student();
        student.setName("李四");
        student.setAge(30);
        student.setHeight(185);
        student.setMarryFlag(true);
        student.setCartId("000000000000000000000000000000001");
        ReflexUtils.printFields(student);
        System.out.println("================================================================");
        Person person = new Person();
        person.setAge(34);
        person.setName("雷嘉");
        person.setHeight(189.20F);
        person.setMarryFlag(false);
        ReflexUtils.printFields(person);
        System.out.println("================================================================");
        ReflexUtils.printFields(new String("leij"));
    }

    private static void testBase() {
        try {
            Class stringClass = String.class;
            ReflexUtils.printBaseInfo(stringClass);
            ReflexUtils.printBaseInfo(int.class);
            ReflexUtils.printBaseInfo(String[].class);
            ReflexUtils.printBaseInfo(Object.class);
            ReflexUtils.printBaseInfo(Month.class);
            ReflexUtils.printBaseInfo(Class.forName("reflex.bean.Person"));
            ReflexUtils.printBaseInfo(Class.forName("reflex.bean.Person$Student"));
            ReflexUtils.printBaseInfo(new ArrayList<Person>().getClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
