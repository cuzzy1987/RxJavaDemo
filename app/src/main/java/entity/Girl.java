package entity;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by ${cs} on 2019/2/18.
 */
public class Girl implements Observer {

	private String name;

	public Girl(String name) {
		this.name = name;
	}

	@Override
	public void update(Observable observable, Object o) {
		System.out.println(String.format("\"%s\" has receive Observable => \"%s\" sent msg => \"%s\"",
				this.name,observable.getClass().getName(),o.toString()));
	}
}
