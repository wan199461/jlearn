package com.wanbo.jearn.I1001Proxy;

public class RabbitImpl implements IRabbit {

	private String name;

	RabbitImpl(String name) {
		this.name = name;
		System.out.println(" 实例化 ");
	}

	@Override
	public void run() {
		String doRun = "I am %s, and i can run";
		System.out.println(String.format(doRun, name));
	}

	@Override
	public String bark() {
		String format0 = "I am %s, I cam bark \"wang wang\" ";
		format0 = String.format(format0, name);
		return format0;
	}

	@Override
	public String bark(String word) {
		String format0 = "I am %s, I cam bark \" %s \" ";
		format0 = String.format(format0, name, word);
		return format0;
	}

}
