package reflex.utils;

import reflex.annotation.Skill;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ReflexUtils {

    public static void printBaseInfo(Class clazz) {
        System.out.println("************" + clazz.getSimpleName() + "************");
        System.out.println("Class Name:" + clazz.getName() + "(类全路径)");
        //当是基础类型时，getPackage返回null
        if (clazz.getPackage() != null) {
            System.out.println("***Package:" + clazz.getPackage() + "(类的包路径，包含含package)");
//            System.out.println("***Package Name:" + clazz.getPackageName() + "(类的包路径)");
        }

        if (clazz.isInterface()) {
            System.out.println("***Is Interface(是接口)");
        }
        if (clazz.isEnum()) {
            System.out.println("***Is Enum(是枚举)");
        }
        if (clazz.isArray()) {
            System.out.println("***Is Array(是数组)");
            //数组的类型
            System.out.println("***Component Type:" + clazz.getComponentType() + "(数组的类型对象)");
            System.out.println("***Component Type Name:" + clazz.getComponentType().getName() + "(数组的类型全路径)");
        }
        if (clazz.isPrimitive()) {
            //是否是基本类型
            System.out.println("***Is Primitive(是基本类型)");
        }
        //判断cls是否为集合的子类
        if (Collection.class.isAssignableFrom(clazz)) {
            System.out.println("***Is Collection(是集合)");
        }
        System.out.println();
    }

    /**
     * 属性相关
     */
    public static Field[] getAllFields(Object object) {
        Class clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            // 获取所有的属性  添加到list中
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            // 看看这个类有么有父类  有父类把父类也挖出来  属实丧心病狂
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    public static void printFields(Object object) {
        System.out.println("************" + object.getClass().getName() + "************");
        Field[] fieldArray = getAllFields(object);
        for (int i = 0; i < fieldArray.length; i++) {
            printFieldInfo(fieldArray[i], object);
        }
        System.out.println();
    }

    /**
     * 打印每个属性的信息
     *
     * @param field
     * @param object
     */
    public static void printFieldInfo(Field field, Object object) {
        System.out.println("Name:" + field.getName() + "(名称)");
        System.out.println("Type:" + field.getType().getSimpleName() + "(类型)");
        // 获取这个属性的修饰符
        int modifier = field.getModifiers();
        // 判断这个属性的修饰符
        printModifier(modifier);
        try {
            // 私有变量反射时可访问
            field.setAccessible(true);
            // 获取属性对应的value值
            System.out.println("Info:" + field.get(object));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    /**
     * 判断这个属性方法的修饰符
     *
     * @param modifier
     */
    public static void printModifier(int modifier) {
        System.out.println("Modifiers:" + Modifier.toString(modifier) + "(全部修饰符)");
        if (Modifier.isPublic(modifier)) {
            System.out.println("Is Public(公共)");
        }
        if (Modifier.isProtected(modifier)) {
            System.out.println("Is Producted(保护)");
        }
        if (Modifier.isPrivate(modifier)) {
            System.out.println("Is Private(私有)");
        }
        if (Modifier.isFinal(modifier)) {
            System.out.println("Is Final(无法被修改)");
        }
        if (Modifier.isStatic(modifier)) {
            System.out.println("Is Static(静态)");
        }
        if (Modifier.isSynchronized(modifier)) {
            System.out.println("Is Synchronized(同步)");
        }
        if (Modifier.isNative(modifier)) {
            System.out.println("Is Native(原生函数,调用C/C++)");
        }
        if (Modifier.isAbstract(modifier)) {
            System.out.println("Is Abstract(抽象方法)");
        }
        if (Modifier.isInterface(modifier)) {
            System.out.println("Is Interface(接口)");
        }
        if (Modifier.isStrict(modifier)) {
            System.out.println("Is Strictfp(精确浮点)");
        }
        if (Modifier.isTransient(modifier)) {
            System.out.println("Is Transient(不需要序列化)");
        }
        if (Modifier.isVolatile(modifier)) {
            System.out.println("Is Volatile(保证可见性和有序性，不保证原子性)");
        }
    }

    /**
     * 方法相关
     */
    public static void printMethods(Object object, int cpuCoreIndex, int cameraIndex) {
        System.out.println("************" + object.getClass().getName() + "************");
        // 获取类的所有的方法
        Method[] methodArray = getAllMethods(object);
        for (int i = 0; i < methodArray.length; i++) {
            printMethodInfo(methodArray[i], object, cpuCoreIndex, cameraIndex);
        }
        System.out.println();
    }

    /**
     * 获取方法详细信息 执行方法
     *
     * @param method
     * @param object
     * @param cpuCoreIndex
     * @param cameraIndex
     */
    public static void printMethodInfo(Method method, Object object, int cpuCoreIndex, int cameraIndex) {
        String methodName = method.getName();
        System.out.println("Name:" + methodName + "(名称)");
        System.out.println("Return Type:" + method.getReturnType().getSimpleName() + "(返回值类型)");
        System.out.println("Parameter count:" + method.getParameters().length + "(参数数量)");
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            System.out.println("第" + i + "个参数类型:" + parameter.getType().getSimpleName());
            // 这里我加了一个参数key 但是老是get出来的是arge0
            System.out.println("第" + i + "个参数key:" + parameter.getName());
        }
        // 获取方法的修饰符
        int modifier = method.getModifiers();
        printModifier(modifier);
        try {
            // 私有方法可访问
            method.setAccessible(true);
            // 判断方法名
            if (methodName.equals("open")) {
                // 执行对应方法
                method.invoke(object);
            } else if (methodName.equals("standBy")) {
                method.invoke(object);
            } else if (methodName.equals("getCpuStatus")) {
                System.out.println((String) method.invoke(object, cpuCoreIndex));
            } else if (methodName.equals("openCamera")) {
                System.out.println((boolean) method.invoke(object, cameraIndex));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    /**
     * 获取类的所有的方法
     *
     * @param object
     * @return
     */
    public static Method[] getAllMethods(Object object) {
        Class clazz = object.getClass();
        List<Method> methodList = new ArrayList<>();
        while (clazz != null) {
            methodList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredMethods())));
            clazz = clazz.getSuperclass();
        }
        Method[] methods = new Method[methodList.size()];
        methodList.toArray(methods);
        return methods;
    }

    public static void printConstructor(Object object, String name, int age, int level) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        System.out.println("************" + object.getClass().getName() + "************");
        Constructor[] constructorArray = getAllConstructors(object);
        for (int i = 0; i < constructorArray.length; i++) {
            printConstructorInfo(constructorArray[i], object, name, age, level);
        }
        System.out.println();
    }

    /**
     * 获取构造函数信息
     *
     * @param constructor
     * @param object
     * @param name
     * @param age
     * @param level
     */
    public static void printConstructorInfo(Constructor constructor, Object object, String name, int age, int level) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        String constructorName = constructor.getName();
        System.out.println("Name:" + constructorName + "(名称)");
        int length = constructor.getParameters().length;
        System.out.println("Parameter count:" + length + "(参数数量)");

        // 这里我自己手动调用了一下构造函数
        constructor.setAccessible(true);
        if (length == 0) {
            constructor.newInstance();
        }

        if (length == 1) {
            constructor.newInstance(1111);
        }

        Parameter[] parameters = constructor.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            System.out.println("第" + i + "个参数类型:" + parameter.getType().getSimpleName());
        }
        int modifier = constructor.getModifiers();
        printModifier(modifier);

        System.out.println();
    }

    public static Constructor[] getAllConstructors(Object object) {
        Class clazz = object.getClass();
        List<Constructor> constructorList = new ArrayList<>();
        while (clazz != null) {
            constructorList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredConstructors())));
            clazz = clazz.getSuperclass();
        }
        Constructor[] constructors = new Constructor[constructorList.size()];
        constructorList.toArray(constructors);
        return constructors;
    }

    public static void printExtensAndInterface(Class clazz) {
        System.out.println("************" + clazz.getName() + "************");
        printExtendsAndInterfaceInfo(clazz);
        System.out.println();
    }


    /**
     * 获取父类和接口类
     *
     * @param clazz
     */
    public static void printExtendsAndInterfaceInfo(Class clazz) {
        Class superClass = clazz.getSuperclass();
        if (superClass != null) {
            printExtendsAndInterfaceInfo(superClass);
            System.out.println("***父类：" + superClass.getName());
        }
        Class[] interfaceArray = clazz.getInterfaces();
        for (int i = 0; i < interfaceArray.length; i++) {
            Class interfaceClass = interfaceArray[i];
            System.out.println("***实现的第" + i + "接口：" + interfaceClass.getName());
        }
    }

    /**
     * 读取注解
     *
     * @param clazz 被读取的类class对象
     */
    public static void printAnnotationInfo(Class clazz) {
        System.out.println("************" + clazz.getName() + "************");
        // clazz类上有没有Skill注解
        if (clazz.isAnnotationPresent(Skill.class)) {
            Skill skill = (Skill) clazz.getAnnotation(Skill.class);
            System.out.println("Value:" + skill.value());
            System.out.println("Position:" + skill.position());
            System.out.println("English Level:" + skill.englishLevel());
            System.out.println("Technology Types:" + Arrays.toString(skill.technologyTypes()));
        }
        System.out.println();
    }

}
