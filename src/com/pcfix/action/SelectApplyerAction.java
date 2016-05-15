package com.pcfix.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.Action;
import com.pcfix.db.HibernateSessionFactory;
import com.pcfix.db.HistoryOrder;
import com.pcfix.db.Order;
import com.pcfix.db.User;
import com.pcfix.db.Price;
public class SelectApplyerAction {
	
	int priceId;
	int orderId;
	int serverId;
	
	private Order order;
	private Price price;
	private int result  = 0;
	private int error  = 0;
	
	private List<Order> orders;
	
	public int getPriceId() {
		return priceId;
	}
	public void setPriceId(int priceId) {
		this.priceId = priceId;
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
	public Order getOrder() {
		return order;
	}
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	public void setOrder(Order order) {
		this.order = order;
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
	
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public String selectApplyer(){
		Session s = null;
	    Transaction t = null;
	    try{
	    	
	    	s  = HibernateSessionFactory.getSession();
	    	Transaction trans = s.beginTransaction();
	    	//修改order订单的priceId 和status
	    	String hql  = String.format("update Order o set o.priceId=%d,o.status=%d where o.orderId=%d", 
	    			priceId, Order.STATUS_DEAL, orderId);
	    	s.createQuery(hql).executeUpdate();
	    	
	    	System.out.println("++++++++++++++修改order订单表的priceId 和status+++++++++++++++");
	    	
	    	//修改price表的status
	    	String hqlUpdatePrice  = String.format("update Price p set p.selected=1 where p.id=%d", 
	    			priceId);
	    	s.createQuery(hqlUpdatePrice).executeUpdate();
	    	
	    	System.out.println("++++++++++++++修改price表的状态+++++++++++++++");
	    	
	    	
	    	//查询没被选择的server的price,进入历史订单
	    	String sql =  String.format("select o.orderId,o.descript,o.phone,o.addr,o.createTime,o.serveTime,o.mathod,o.problem,o.clientId," +
	    			"(select name from user where id=o.clientId) as clientName," +
	    			"p.serverId,(select name from user where id=p.serverId) as serverName,p.price from fix_order o, price p " +
	    			"where o.orderId=p.orderId and p.serverId!=%d and p.orderId=%d", serverId, orderId);
	    	List<Object[]> timeoutServers = s.createSQLQuery(sql).list();
	    	System.out.printf("++++++++++++++timeoutServers.size=%d+++++++++++++++\n", timeoutServers.size());
	    	for(Object[] item : timeoutServers){
	    		HistoryOrder ho = new HistoryOrder();
	    		ho.setOrderId((Integer)item[0]);
	    		ho.setDesc((String)item[1]);
	    		ho.setPhone((String)item[2]);
	    		ho.setAddr((String)item[3]);
	    		//ho.setCreateTime((Date)item[4]);System.out.println("++++++++++++++444+++++++++++++++\n");
	    		//ho.setServeTime((Date)item[5]);System.out.println("++++++++++++++555+++++++++++++++\n");
	    		ho.setMathod((Integer)item[6]);
	    		ho.setProblem((Integer)item[7]);
	    		ho.setClientId((Integer)item[8]);
	    		ho.setClientName((String)item[9]);
	    		ho.setServerId((Integer)item[10]);
	    		ho.setServerName((String)item[11]);
	    		ho.setPrice((Integer)item[12]);
	    		ho.setFinishTime(new Date());
	    		ho.setFinishType(HistoryOrder.FINISH_TYPE_TIMEOUT);
	    		s.save(ho);
	    	}
	    	
	    	System.out.println("++++++++++++++查询没被选择的server的price,进入历史订单+++++++++++++++");
	    	
	    	//删除没被选择的server的price记录
	    	String hqlDelete  = String.format("delete from Price p where p.serverId!=%d and p.orderId=%d", 
	    			serverId, orderId);
	    	s.createQuery(hqlDelete).executeUpdate();
	    	
	    	System.out.println("++++++++++++++删除没被选择的server的price记录+++++++++++++++");
	    	
	    	trans.commit();
	    	
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
		return Action.SUCCESS;
	}
	
	
}

