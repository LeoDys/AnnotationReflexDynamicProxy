package reflex.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author LeoDys E-mail:changwen.sun@inossem.com 2020/5/8 17:44
 * @version 1.0.8
 * @since 1.0.8
 */

public class ProxyHandler implements InvocationHandler {

    // 被代理对象
    private Object object;

    public ProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoke "  + method.getName());
        method.invoke(object, args);
        System.out.println("After invoke " + method.getName());
        return null;
    }
}
