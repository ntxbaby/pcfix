package com.pcfix.action;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.Action;
import com.pcfix.db.HibernateSessionFactory;
import com.pcfix.db.Order;
import com.pcfix.db.OrderInfo;
import com.pcfix.db.User;
import com.pcfix.db.Price;
public class OrderInfoAction {
	
	
	private int clientId;
	private int serverId;
	private int result  = 0;
	private int error  = 0;
	private List<OrderInfo> orderInfos;
	
	
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

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public List<OrderInfo> getOrderInfos() {
		return orderInfos;
	}

	public void setOrderInfos(List<OrderInfo> orderInfos) {
		this.orderInfos = orderInfos;
	}

	public String listActiveOrders(){
		Session s = null;
	    Transaction t = null;
	    try{
	    	s  = HibernateSessionFactory.getSession();
/*
	    	String hql = "select new com.pcfix.db.OrderInfo(" +
	    			"o.orderId,o.desc,o.phone,o.addr,o.createTime,o.serveTime,o.mathod," +
	    			"o.problem,o.clientId," +
	    			"(select name from User where id=o.clientId) as clientName," +
	    			"o.priceId,o.status,p.serverId," +
	    			"(select name from User where id=p.serverId) as serverName," +
	    			"p.price,p.selected," +
	    			"(select count(*) from Price where orderId=o.orderId) as applyerNum) " +
	    			"from Order o left join Price p with o.priceId=p.id where o.priceId=-1";
	    	*/
	    	
	    	/*
	    	 
	    	 
	    	String sql = "select o.orderId,o.descript,o.phone,o.addr,o.createTime,o.serveTime,o.mathod,o.problem,o.clientId,"+
			"(select name from user where id=o.clientId) as clientName," +
			"o.priceId,o.status,p.serverId," +
			"(select name from user where id=p.serverId) as serverName," +
			"p.price,p.selected," +
			"(select count(*) from price where orderId=o.orderId) as applyerNum "+ 
			"from fix_order o left join price p on o.orderId=p.orderId where o.priceId=-1";
	    	*/
	    	String sql = "select o.orderId,o.descript,o.phone,o.addr," +
	    			"o.createTime,o.serveTime,o.mathod,o.problem,o.clientId," +
	    			"(select name from user where id=o.clientId) as clientName," +
	    			"o.priceId,o.status,(select count(*) from price where orderId=o.orderId)" +
	    			" as applyerNum from fix_order o  where o.priceId=-1";
	    	
	    	List<Object[]> list = s.createSQLQuery(sql).list();
	    	orderInfos = new ArrayList<OrderInfo>();
	    	System.out.println("++++++++orderInfos.size++++++++" + list.size());
	    	for (Object[] item : list)
	    	{
	    		OrderInfo oi = new OrderInfo();
	    		oi.setOrderId((Integer)item[0]);
	    		oi.setDesc((String)item[1]);
	    		oi.setPhone((String)item[2]);
	    		oi.setAddr((String)item[3]);
	    		//oi.setCreateTime(item[4]);
	    		//oi.setServeTime(item[5]);
	    		oi.setMathod((Integer)item[6]);
	    		oi.setProblem((Integer)item[7]);
	    		oi.setClientId((Integer)item[8]);
	    		oi.setClientName((String)item[9]);
	    		oi.setPriceId((Integer)item[10]);
	    		oi.setStatus((Integer)item[11]);
	    		//oi.setServerId((Integer)item[12] == null ? -1 : (Integer)item[12]);
	    		//oi.setServerName((String)item[13] == null ? "" : (String)item[13]);
	    		//oi.setPrice((Integer)item[14] == null ? -1 : (Integer)item[14]);
	    		//oi.setSelected((Integer)item[15]  == null ? -1 : (Integer)item[15]);
	    		oi.setApplyerNum((BigInteger)item[12]);
	    		orderInfos.add(oi);
	    	}
	    	if(orderInfos.isEmpty() )
	    	{
	    			result = -1;
		    		error = 300;//订单为空
	    	}
	    	System.out.println("++++++++listActiveOrders++++++++");
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
		return Action.SUCCESS;
	}
	
	public String listClientOrders(){
		Session s = null;
	    Transaction t = null;
	    try{
	    	s  = HibernateSessionFactory.getSession();
	    	
	    	String sql = String.format("select o.orderId,o.descript,o.phone,o.addr," +
	    			"o.createTime,o.serveTime,o.mathod,o.problem,o.clientId," +
	    			"(select name from user where id=o.clientId) as clientName," +
	    			"o.priceId,o.status,(select count(*) from price where orderId=o.orderId)" +
	    			" as applyerNum from fix_order o  where o.clientId=%d", clientId);
	    	
	    	List<Object[]> list = s.createSQLQuery(sql).list();
	    	orderInfos = new ArrayList<OrderInfo>();
	    	System.out.println("++++++++orderInfos.size++++++++" + list.size());
	    	for (Object[] item : list)
	    	{
	    		OrderInfo oi = new OrderInfo();
	    		oi.setOrderId((Integer)item[0]);
	    		oi.setDesc((String)item[1]);
	    		oi.setPhone((String)item[2]);
	    		oi.setAddr((String)item[3]);
	    		//oi.setCreateTime(item[4]);
	    		//oi.setServeTime(item[5]);
	    		oi.setMathod((Integer)item[6]);
	    		oi.setProblem((Integer)item[7]);
	    		oi.setClientId((Integer)item[8]);
	    		oi.setClientName((String)item[9]);
	    		oi.setPriceId((Integer)item[10]);
	    		oi.setStatus((Integer)item[11]);
	    		//oi.setServerId((Integer)item[12] == null ? -1 : (Integer)item[12]);
	    		//oi.setServerName((String)item[13] == null ? "" : (String)item[13]);
	    		//oi.setPrice((Integer)item[14] == null ? -1 : (Integer)item[14]);
	    		//oi.setSelected((Integer)item[15]  == null ? -1 : (Integer)item[15]);
	    		oi.setApplyerNum((BigInteger)item[12]);
	    		orderInfos.add(oi);
	    	}
	    	if(orderInfos.isEmpty() )
	    	{
	    			result = -1;
		    		error = 300;//订单为空
	    	}
	    	System.out.println("++++++++++++++listClientOrders+++++++++++++++");
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
		return Action.SUCCESS;
	}
	
	public String listServerOrders(){
		Session s = null;
	    Transaction t = null;
	    try{
	    	s  = HibernateSessionFactory.getSession();
	    	
	    	String sql = String.format("select o.orderId,o.descript,o.phone,o.addr,o.createTime,o.serveTime,o.mathod,o.problem,o.clientId,"+
	    			"(select name from user where id=o.clientId) as clientName," +
	    			"o.priceId,o.status,p.serverId," +
	    			"(select name from user where id=p.serverId) as serverName," +
	    			"p.price,p.selected," +
	    			"(select count(*) from price where orderId=o.orderId) as applyerNum "+ 
	    			"from fix_order o left join price p on o.orderId=p.orderId where p.serverId=%d", serverId);
	   
	    	List<Object[]> list = s.createSQLQuery(sql).list();
	    	orderInfos = new ArrayList<OrderInfo>();
	    	System.out.println("++++++++orderInfos.size++++++++" + list.size());
	    	for (Object[] item : list)
	    	{
	    		OrderInfo oi = new OrderInfo();
	    		oi.setOrderId((Integer)item[0]);
	    		oi.setDesc((String)item[1]);
	    		oi.setPhone((String)item[2]);
	    		oi.setAddr((String)item[3]);
	    		//oi.setCreateTime(item[4]);
	    		//oi.setServeTime(item[5]);
	    		oi.setMathod((Integer)item[6]);
	    		oi.setProblem((Integer)item[7]);
	    		oi.setClientId((Integer)item[8]);
	    		oi.setClientName((String)item[9]);
	    		oi.setPriceId((Integer)item[10]);
	    		oi.setStatus((Integer)item[11]);
	    		oi.setServerId((Integer)item[12] == null ? -1 : (Integer)item[12]);
	    		oi.setServerName((String)item[13] == null ? "" : (String)item[13]);
	    		oi.setPrice((Integer)item[14] == null ? -1 : (Integer)item[14]);
	    		oi.setSelected((Integer)item[15]  == null ? -1 : (Integer)item[15]);
	    		oi.setApplyerNum((BigInteger)item[16]);
	    		orderInfos.add(oi);
	    	}
	    	if(orderInfos.isEmpty() )
	    	{
	    			result = -1;
		    		error = 300;//订单为空
	    	}
	    	System.out.println("++++++++++++++listServerOrders+++++++++++++++");
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
		return Action.SUCCESS;
	}
}
