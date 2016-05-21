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
import com.pcfix.db.Order;
import com.pcfix.db.User;
import com.pcfix.db.Price;
public class OrderAction {
	private Order order;
	private Price price;
	private int result  = 0;
	private int error  = 0;
	private List<Order> orders;
	
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
	public String add(){
		Session s = null;
	    Transaction t = null;
	    try{
	    	s  = HibernateSessionFactory.getSession();
	    	t = s.beginTransaction();
	    	order.setPriceId(-1);
	    	s.save(order);
	    	t.commit();
	    	System.out.println("add++++++++++++++++");
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
		return Action.SUCCESS;
	}
	
	public String delete(){
		Session s = null;
	    Transaction t = null;
	    try{
	    	s  = HibernateSessionFactory.getSession();
	    	t = s.beginTransaction();
	    	String hql1 = String.format("delete from Order o where o.orderId=%d", order.getOrderId());
	    	s.createQuery(hql1).executeUpdate();
	    	String hql2 = String.format("delete from Price p where p.orderId=%d", order.getOrderId());
	    	s.createQuery(hql2).executeUpdate();
	    	t.commit();
	    	System.out.println("++++++++delete orders++++++++");
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
		return Action.SUCCESS;
	}
	
	public String list(){
		Session s = null;
	    Transaction t = null;
	    try{
	    	s  = HibernateSessionFactory.getSession();
	    	orders = s.createQuery("from Order o where o.priceId=-1").list();
	    	if(orders.isEmpty() )
	    	{
	    			result = -1;
		    		error = 300;//订单为空
	    	}
	    	System.out.println("+++++++++list orders+++++++");
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
		return Action.SUCCESS;
	}
	
	
}
