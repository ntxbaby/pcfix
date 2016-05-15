package com.pcfix.db;

import java.util.Date;


public class HistoryOrder {
	
	private int hisOrderId;
	//order信息
	private int orderId;
	private String desc;
	private String phone;
	private String addr;
	private Date createTime;
	private Date serveTime;
	private int mathod;
	private int problem;
	private int clientId;
	private String clientName;
	
	//维修信息
	private int serverId;
	private String serverName;
	private int price;
	//完成类型
	private int finishType;
	private Date finishTime;
	//评论
	private String comment;

	public int getHisOrderId() {
		return hisOrderId;
	}
	public void setHisOrderId(int hisOrderId) {
		this.hisOrderId = hisOrderId;
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
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
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
	public int getFinishType() {
		return finishType;
	}
	public void setFinishType(int finishType) {
		this.finishType = finishType;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public static final int STATUS_APPLY = 0;
	public static final int STATUS_DEAL = 1;
	public static final int STATUS_VARIFY = 2;
	public static final int STATUS_FINISH = 3;
	public static final int STATUS_TIMEOUT = 4;
	
	//完成类型
	public static final int FINISH_TYPE_FINISH = 0;
	public static final int FINISH_TYPE_TIMEOUT = 1;
	
	
}
