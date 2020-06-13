package com.wanbo.jearn.I1001Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;



/**
 * {@code DynamicRabbitProxy} 是通用动态代理的一般实现 </br>
 * 
 * </p> 想要理解动态代理需要先理解类加载的过程，一般理解是是从虚拟机元空间类模板中拷贝出一个对象并放入堆中，
 * 初略这样理解问题不大但是这里面可以分为下面几步: </p>
 * 
 * <ol>
 * <li> ClassLoader识别验证字节码文件，每个ClassLoader拥有自己独立的空间，但可以继承父类加载器已经加载的类</li>
 * <li> ClassLoader将字节码加载到内存空间并生成 {@code Class<A>}类，这一部很重要因为有了{@code Class<A>} 就能获取类信息和实例化对象 </li>
 * </ol>
 * 
 * 所以代理实际上是，生成了一个实现了 {@code Proxy.newProxyInstance}第二个参数（即传入的全部接口）的新类，所以我们可以将返回的代理类转成传入接口；</br>
 * 另外我们调用接口的方法时，实际调用的是代理类的方法实现（PS：最终调用的是我们编写的invoke方法，但是是自动传入的参数和Method）；
 * 
 */
public class DynamicRabbitProxy implements InvocationHandler {

	private Object target;

	public Object bind(Object target) {
		this.target = target;

		Object proxyInstances = Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
		return proxyInstances;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		enhance("before"); // 代理前增强
		Object invoke0 = method.invoke(target, args);
		System.out.println("执行代理方法  " + invoke0);
		enhance("after"); // 代理后增强
		return invoke0;
	}

	private void enhance(String status) {
		String format0 = " Enhance %s";
		System.out.println(String.format(format0, status));
	}

}
