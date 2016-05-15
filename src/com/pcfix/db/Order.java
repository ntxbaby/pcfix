package com.pcfix.db;

import java.util.Date;


public class Order {
	private int orderId;
	
	private String desc;
	private String phone;
	private String addr;
	private Date createTime = new Date();
	private Date serveTime;
	private int mathod;
	private int problem;
	private int clientId;
	private int serverId;
	private int priceId;
	private int status;
//	public Order(int orderId, String desc, String phone, String addr,
//			Date createTime, Date serveTime, int mathod, int problem,
//			int clientId, int serverId, int priceId, int status) {
//		super();
//		this.orderId = orderId;
//		this.desc = desc;
//		this.phone = phone;
//		this.addr = addr;
//		this.createTime = createTime;
//		this.serveTime = serveTime;
//		this.mathod = mathod;
//		this.problem = problem;
//		this.clientId = clientId;
//		this.serverId = serverId;
//		this.priceId = priceId;
//		this.status = status;
//	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getServeTime() {
		return serveTime;
	}
	public void setServeTime(Date serveTime) {
		this.serveTime = serveTime;
	}
	public int getMathod() {
		return mathod;
	}
	public void setMathod(int mathod) {
		this.mathod = mathod;
	}
	public int getProblem() {
		return problem;
	}
	public void setProblem(int problem) {
		this.problem = problem;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public int getPriceId() {
		return priceId;
	}
	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public static final int STATUS_APPLY = 0;
	public static final int STATUS_DEAL = 1;
	public static final int STATUS_VARIFY = 2;
	public static final int STATUS_FINISH = 3;
	public static final int STATUS_TIMEOUT = 4;
	
	
	
}
