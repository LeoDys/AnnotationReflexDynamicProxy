import utils.Utils;
import utils.ZipUtils;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Test {

    public static void main(String[] args) {
//
//        String[] names = {"Bob", "Alice", "Grace"};
//        var sj = new StringJoiner(", ", "Hello ", "!");
//        for (String name : names) {
//            sj.add(name);
//        }
//        System.out.println(sj.toString());
//
//
//        var numbers = List.of("a", "b", "c");
//        for (var nr : numbers)
//            System.out.print(nr + " ");
//        for (var i = 0; i < numbers.size(); i++)
//            System.out.print(numbers.get(i) + " ");

//        InvocationHandler handler = new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println(method.getName());
//                if ("living".equals(method.getName())) {
//                    System.out.println(args.length);
//                    if (args.length==1){
//                        System.out.println("努力活着，加油！"+args[0]);
//                    }
//                }
//                return null;
//            }
//        };
//        Person person = (Person)Proxy.newProxyInstance(Person.class.getClassLoader(),new Class[]{Person.class},handler);
//        person.living("雷嘉");
        String message = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        byte[] b1 = Utils.zip(message.getBytes());
        System.out.println("zip:" + Utils.bytesToHexString(b1));
        byte[] b2 = Utils.unZip(b1);
        System.out.println("unZip:" + new String(b2));


        try {
            String m1 = Utils.compress(message);
            System.out.println(m1.length());
            System.out.println(m1);
            String m2 = Utils.unCompress(m1);
            System.out.println(m2.length());
            System.out.println(m2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    interface Person{
        void living(String name);
    }
}
