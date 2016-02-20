package com.pcfix.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.pcfix.db.HibernateSessionFactory;
import com.pcfix.db.User;

public class RegisterAction  {
	
	private User user;
	private int result  = 0;
	private int error  = 0;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
	public static class FindTableWork implements Work{
		private boolean bFind;
		public boolean isTableExisted(){
			return bFind;
		}
		@Override
		public void execute(Connection arg0) throws SQLException {
			// TODO Auto-generated method stub
			ResultSet rs = arg0.getMetaData().getTables(null, "pc_fix_sys", "user", new String []{"TABLE"});
			 if(rs.next()){    
				 bFind = true;
			    }    
			    else{    
			         bFind = false;
			    }   
		}
		
	} 
	
	private boolean isTableExisted(){
		Session s = null;
	    Transaction t = null;
	    try{
	    	s  = HibernateSessionFactory.getSession();
	    	FindTableWork findTableWork = new FindTableWork();
	    	
	    	s.doWork( findTableWork );
	    	
	    	if(findTableWork.isTableExisted())
	    	{
	    		return true;
	    	}
	    	
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
		return false;
		
	}
	
	
	
	public boolean isUserExisted(){
		if(!isTableExisted()){
			return false;
		}
		Session s = null;
	    Transaction t = null;
	    try{
	    	s  = HibernateSessionFactory.getSession();

	    	List<User> l = s.createQuery("from User u where u.name = '" + user.getName() + "'").list();
	    	if(l.size() > 0)
	    	{
	    		return true;
	    	}
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
		return false;
	}
	
	public void addUser(){
		
		Session s = null;
	    Transaction t = null;
	    try{
	    	s  = HibernateSessionFactory.getSession();
	    	t = s.beginTransaction();
	    	user.setStar(0);
	    	s.save(user);
	    	t.commit();
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
	}

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(!isUserExisted()){
			addUser();
		}else{
			result = -1;
			error = 100;//注册用户已经存在
		}
	    	
		return Action.SUCCESS;
	}
	
	public void validate(){
		Session s = null;
	    Transaction t = null;
	    try{
	    	s  = HibernateSessionFactory.getSession();
	    	if(null == user)
	    	{
	    		result = -1;
	    		error = 203;//登陆用户缺少参数
	    		return;
	    	}
	    	List<User> l = s.createQuery("from User u where u.name = '" + user.getName() + "'").list();
	    	if(!l.isEmpty() )
	    	{
	    		User u = l.get(0);
	    		if( !u.getPwd().equals(user.getPwd() ) ){
	    			result = -1;
		    		error = 201;//登陆用户密码错误
	    		}
	    		if( u.getType() != user.getType() ){
	    			result = -1;
		    		error = 202;//登陆用户类型错误
	    		}
	    		user = u;
	    	}
	    	else{
	    		result = -1;
	    		error = 200;//登陆用户不存在
	    	}
	    	
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
	    
	}
	public String login(){
		validate();
		return Action.SUCCESS;
	}
}
