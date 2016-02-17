package com.pcfix.action;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.Action;
import com.pcfix.db.HibernateSessionFactory;
import com.pcfix.db.Order;

public class OrderAction {
	private Order order;
	private int result  = 0;
	private int error  = 0;
	
	public Order getOrder() {
		return order;
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
	
	public String add(){
		Session s = null;
	    Transaction t = null;
	    try{
	    	s  = HibernateSessionFactory.getSession();
	    	t = s.beginTransaction();
	    	order.setPriceId(-1);
	    	s.save(order);
	    	t.commit();
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
		return Action.SUCCESS;
	}
}
