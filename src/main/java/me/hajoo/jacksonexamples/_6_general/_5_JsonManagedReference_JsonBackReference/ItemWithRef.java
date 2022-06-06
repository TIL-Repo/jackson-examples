package me.hajoo.jacksonexamples._6_general._5_JsonManagedReference_JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class ItemWithRef {

	public int id;
	public String itemName;

	@JsonManagedReference
	public UserWithRef owner;

	public ItemWithRef(int id, String itemName, UserWithRef owner) {
		this.id = id;
		this.itemName = itemName;
		this.owner = owner;
	}
}
