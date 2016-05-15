package com.pcfix.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.Action;

public class Applyer {
	private int id;
	private int orderId;
	private int serverId;
	private int price;
	private int selected;
	private String serverName;
	public Applyer(int id, int orderId, int serverId, int price, int selected,
			String serverName) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.serverId = serverId;
		this.price = price;
		this.selected = selected;
		this.serverName = serverName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSelected() {
		return selected;
	}
	public void setSelected(int selected) {
		this.selected = selected;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
}
