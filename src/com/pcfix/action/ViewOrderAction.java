package com.pcfix.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.Action;
import com.pcfix.db.HibernateSessionFactory;
import com.pcfix.db.Order;
import com.pcfix.db.User;
import com.pcfix.db.ViewOrder;

public class ViewOrderAction {
	private ViewOrder order;
	private int result  = 0;
	private int error  = 0;
	private List<Order> orders;
	
	public ViewOrder getOrder() {
		return order;
	}
	public void setOrder(ViewOrder order) {
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
	
	
	public String list(){
		Session s = null;
	    Transaction t = null;
	    try{
	    	s  = HibernateSessionFactory.getSession();
	    	orders = s.createQuery("from ViewOrder").list();
	    	if(orders.isEmpty() )
	    	{
	    			result = -1;
		    		error = 300;//����Ϊ��
	    	}
	    	System.out.println("++++++++list view order++++++++");
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
		return Action.SUCCESS;
	}
	
	
}
