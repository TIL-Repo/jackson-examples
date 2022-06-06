package me.hajoo.jacksonexamples._6_general._5_JsonManagedReference_JsonBackReference;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class UserWithRef {

	public int id;
	public String name;

	@JsonBackReference
	public List<ItemWithRef> userItems;

	public UserWithRef(int id, String name) {
		this.id = id;
		this.name = name;
		userItems = new ArrayList<>();
	}

	public void addItem(ItemWithRef userItem) {
		this.userItems.add(userItem);
	}
}
