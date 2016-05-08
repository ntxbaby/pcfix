<%@page import="org.hibernate.HibernateException"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.pcfix.db.*" %>
<%@ page import="org.hibernate.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
    <%
    Session s = null;
    Transaction t = null;
    try{
    	s  = HibernateSessionFactory.getSession();
    	t = s.beginTransaction();
    	
    	User u = new User();
    	u.setName("chengdong");
    	u.setPwd("111111");
    	u.setType(0);
    	s.save(u);
    	t.commit();
    	
    	
    	}catch (HibernateException e){
    		e.printStackTrace();
    		//t.rollback();
    	}finally{
    		s.close();
    	}
     %>
  </body>
</html>
