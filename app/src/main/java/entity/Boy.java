package entity;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by ${cs} on 2019/2/18.
 */
public class Boy implements Observer {//观察者

	private String name;

	public Boy(String name) {
		this.name = name;
	}

	// 接收到更新
	@Override
	public void update(Observable observable, Object o) {
		System.out.println(String.format("\"%s\" has receive Observable => \"%s\" sent msg => \"%s\"",
				this.name,observable.getClass().getName(),o.toString()));
	}

	public static void setBoy(String name){
		System.out.println(name);
	}

}
