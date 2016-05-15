package com.pcfix.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.Action;
import com.pcfix.db.Applyer;
import com.pcfix.db.HibernateSessionFactory;
import com.pcfix.db.Order;
import com.pcfix.db.Price;
import com.pcfix.db.User;

public class PriceAction {
	private Price price;
	private int result  = 0;
	private int error  = 0;
	private List<Applyer> applyers;
	
	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
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

	public List<Applyer> getApplyers() {
		return applyers;
	}

	public void setApplyers(List<Applyer> applyers) {
		this.applyers = applyers;
	}

	public String add(){
		Session s = null;
	    Transaction t = null;
	    try{
	    	s  = HibernateSessionFactory.getSession();
	    	t = s.beginTransaction();
	    	price.setSelected(0);
	    	s.save(price);
	    	t.commit();
	    	System.out.println("add shenqingzhe++++++++++++++++");
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
		return Action.SUCCESS;
	}
	
	
	
	public String listApplyer(){
		Session s = null;
	    Transaction t = null;
	    try{
	    	s  = HibernateSessionFactory.getSession();
	    	
	    	String hql = "select new com.pcfix.db.Applyer(o.id,o.orderId,o.serverId,o.price,o.selected,u.name) from Price o,User u where o.serverId=u.id and o.orderId=" + price.getOrderId();
	    	applyers = s.createQuery(hql).list();
	    	if(applyers.isEmpty() )
	    	{
	    			result = -1;
		    		error = 400;//…Í«Î’ﬂŒ™ø’
	    	}
	    	System.out.println("+++++++listApplyer+++++++++");
	    	
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
		return Action.SUCCESS;
	}
}
