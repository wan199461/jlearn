package com.wanbo.jearn.I1001Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class App {

	public static void main(String[] args) {

		// 1. 动态代理
//		DynamicRabbitProxy proxy = new DynamicRabbitProxy();
//		IRabbit rabbit = (IRabbit) proxy.bind(new RabbitImpl("兔兔"));
		IRabbit rabbit = (IRabbit)getProxy(new RabbitImpl("兔兔"));
		
		rabbit.run();

//		System.out.println(rabbit.bark("wa wa"));
		
		


	}

	
	/**
	 * <p> 一个通用动态代理， 其关键方法其实是 {@code Proxy.newProxyInstance} 方法， 该方法接收三个参数， 分别是：
	 * <ul>
	 * <li>{@link java.lang.ClassLoader} 代表类加载器，这个参数一般使用目标类的类加载器即可  </li>
	 * <li>{@code  Class<?>[] }  代表该实现类的接口  </li>
	 * <li>{@link java.lang.reflect.InvocationHandler} 是执行原方法的地方，在这里我们可以对原方法进行增强   </li>
	 * </ul>
	 * </p>
	 * 
	 * @param tar
	 * @return
	 */
	public static Object getProxy(final Object tar) {

		Object proxy = Proxy.newProxyInstance(tar.getClass().getClassLoader(), tar.getClass().getInterfaces(),
				new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println(" 代理前增强 ");
						Object invoke0 = method.invoke(tar, args);
						System.out.println(" 代理后增强 ");
						return invoke0;
					}

				});
		
         return proxy;
	}

}
