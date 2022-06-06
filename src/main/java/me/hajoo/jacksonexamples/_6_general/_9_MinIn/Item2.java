package me.hajoo.jacksonexamples._6_general._9_MinIn;

import me.hajoo.jacksonexamples._4_jacksonProperty._03_JsonIgnoreType.User;

public class Item2 {

	public int id;
	public String itemName;
	public User owner;

	public Item2(int id, String itemName, User owner) {
		this.id = id;
		this.itemName = itemName;
		this.owner = owner;
	}
}
