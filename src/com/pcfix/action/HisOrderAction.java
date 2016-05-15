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
public class HisOrderAction {
	private int userId;
	private int result  = 0;
	private int error  = 0;
	private List<HistoryOrder> hisOrders;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	
	public List<HistoryOrder> getHisOrders() {
		return hisOrders;
	}
	public void setHisOrders(List<HistoryOrder> hisOrders) {
		this.hisOrders = hisOrders;
	}
	
	
	public String listHisOrders(){
		Session s = null;
	    Transaction t = null;
	    try{
	    	s  = HibernateSessionFactory.getSession();
	    	String hql = String.format("from HistoryOrder o where o.clientId=%d or o.serverId=%d", userId, userId);
	    	hisOrders = s.createQuery(hql).list();
	    	if(hisOrders.isEmpty() )
	    	{
	    			result = -1;
		    		error = 300;//¶©µ¥Îª¿Õ
	    	}
	    	System.out.println("+++++++listHisOrders+++++++++");
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
		return Action.SUCCESS;
	}
	
	
}
