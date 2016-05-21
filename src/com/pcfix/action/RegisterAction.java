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
	private String newPwd;
	private User user;
	private int result  = 0;
	private int error  = 0;
	private List<User> users;
	
	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

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
	
	
	
	private boolean isUserExisted(){
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

	public String add() {
		// TODO Auto-generated method stub
		if(!isUserExisted()){
			addUser();
		}else{
			result = -1;
			error = 100;//ע���û��Ѿ�����
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
	    		error = 203;//��½�û�ȱ�ٲ���
	    		return;
	    	}
	    	List<User> l = s.createQuery("from User u where u.name = '" + user.getName() + "'").list();
	    	if(!l.isEmpty() )
	    	{
	    		User u = l.get(0);
	    		if( !u.getPwd().equals(user.getPwd() ) ){
	    			result = -1;
		    		error = 201;//��½�û��������
	    		}
	    		if( u.getType() != user.getType() ){
	    			result = -1;
		    		error = 202;//��½�û����ʹ���
	    		}
	    		user = u;
	    	}
	    	else{
	    		result = -1;
	    		error = 200;//��½�û�������
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
	
	public String delete(){
		Session s = null;
	    Transaction t = null;
	    try{
	    	s  = HibernateSessionFactory.getSession();
	    	t = s.beginTransaction();
	    	String hql1 = String.format("delete from User u where u.id=%d", user.getId());
	    	s.createQuery(hql1).executeUpdate();
	    	String hql2 = String.format("delete from Order o where o.clientId=%d", user.getId());
	    	s.createQuery(hql2).executeUpdate();
	    	t.commit();
	    	System.out.println("++++++++delete users++++++++");
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
	    	String hql1 = "from User";
	    	users = s.createQuery(hql1).list();
	    	result = 0;
    		error = 200;//��½�û��������
	    	System.out.println("++++++++list users++++++++");
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
		return Action.SUCCESS;
	}
	
	public String changePwd(){
		Session s = null;
	    Transaction t = null;
	    try{
	    	s  = HibernateSessionFactory.getSession();
	    	t = s.beginTransaction();
	    	String hql1 = String.format("from User u where u.id=%d", user.getId());
	    	User u =  (User) s.createQuery(hql1).uniqueResult();
	    	if(u.getPwd().equals(user.getPwd()))
	    	{
	    		u.setPwd(newPwd);
	    		s.update(u);
	    		result = 0;
	    	}
	    	else
	    	{
	    		//旧密码不对
	    		result = -1;
	    		error = -100;
	    	}
	    	t.commit();
	    	System.out.println("++++++++change pwd++++++++");
	    	}catch (HibernateException e){
	    		e.printStackTrace();
	    	}finally{
	    		s.close();
	    	}
		return Action.SUCCESS;
	}
}
