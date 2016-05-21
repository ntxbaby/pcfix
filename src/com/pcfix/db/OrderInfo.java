package com.pcfix.db;

import java.math.BigInteger;
import java.util.Date;


public class OrderInfo {
	
	private int orderId;
	private String desc;
	private String phone;
	private String addr;
	private String createTime;
	private String serveTime;
	private int mathod;
	private int problem;
	private int clientId;
	private String clientName;
	private int priceId;
	private int status;
	//������(������)��Ϣ
	private int serverId;
	private String serverName;
	private int price;
	private int selected;
	//�����߸���
	private BigInteger applyerNum;


	
	
	public OrderInfo(int orderId, String desc, String phone, String addr,
			String createTime, String serveTime, int mathod, int problem,
			int clientId, String clientName, int priceId, int status,
			int serverId, String serverName, int price, int selected,
			BigInteger applyerNum) {
		super();
		this.orderId = orderId;
		this.desc = desc;
		this.phone = phone;
		this.addr = addr;
		this.createTime = createTime;
		this.serveTime = serveTime;
		this.mathod = mathod;
		this.problem = problem;
		this.clientId = clientId;
		this.clientName = clientName;
		this.priceId = priceId;
		this.status = status;
		this.serverId = serverId;
		this.serverName = serverName;
		this.price = price;
		this.selected = selected;
		this.applyerNum = applyerNum;
	}
	
	
	
	public OrderInfo() {
		// TODO Auto-generated constructor stub
	}



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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getServeTime() {
		return serveTime;
	}
	public void setServeTime(String serveTime) {
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
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
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
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
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
	public BigInteger getApplyerNum() {
		return applyerNum;
	}
	public void setApplyerNum(BigInteger applyerNum) {
		this.applyerNum = applyerNum;
	}



	public static final int STATUS_APPLY = 0;
	public static final int STATUS_DEAL = 1;
	public static final int STATUS_VARIFY = 2;
	public static final int STATUS_FINISH = 3;
	public static final int STATUS_TIMEOUT = 4;
	
	
	
}
