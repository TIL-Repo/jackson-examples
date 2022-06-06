package me.hajoo.jacksonexamples._6_general._6_JsonIdentityInfo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
	generator = ObjectIdGenerators.PropertyGenerator.class,
	property = "id"
)
public class UserWithIdentity {

	public int id;
	public String name;
	public List<ItemWithIdentity> userItems;

	public UserWithIdentity(int id, String name) {
		this.id = id;
		this.name = name;
		userItems = new ArrayList<>();
	}

	public void addItem(ItemWithIdentity itemWithIdentity) {
		userItems.add(itemWithIdentity);
	}
}
