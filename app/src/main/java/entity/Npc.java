package entity;

import java.util.Observable;

/**
 * Created by ${cs} on 2019/2/18.
 */
public class Npc extends Observable {

	private String name;

	public Npc(String name) {
		this.name = name;
	}

	public void nativeNotify(){
		this.setChanged();
	}
}
