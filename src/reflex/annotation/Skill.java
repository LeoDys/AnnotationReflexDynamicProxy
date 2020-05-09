package reflex.annotation;

import java.lang.annotation.*;
import java.util.List;


/**
 * @Target
 *     TYPE,    //表明此注解可以用在类或接口上
 *     FIELD,     //表明此注解可以用在属性上(还没用过，)
 *     METHOD,     //表明此注解可以用在方法上
 *     PARAMETER,     //表明此注解可以用在参数上
 *     CONSTRUCTOR,     //表明此注解可以用在构造方法上
 *     LOCAL_VARIABLE,     //表明此注解可以用在局部变量上
 *     ANNOTATION_TYPE,     //表明此注解可以用在注解类型上
 *     PACKAGE,     //用于记录java文件的package文件信息，
 *                    不使用在一般的类中，而用在固定文件package-info.java中。
 *                    注意命名一定是“package-info”。
 *                    由于package- info.java并不是一个合法的类，
 *                   使用eclipse创建类的方式会提示不合法，所以需要以创建
 *                   文件的方式来创建package-info.java。
 *
 *     TYPE_PARAMETER,     //类型参数声明
 *     TYPE_USE  //类型使用声明 (未使用过，也不知道怎么用)
 *
 */



@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Skill {
    String[] technologyTypes() default {"JAVA", "GIT"};

    int englishLevel() default 4;

    String position() default "JAVADeveloper";

    boolean value() default true;
}
