<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.List"%>    
<%@page import="edu.fzu.icanteen.pojo.Merchant"%>
<%@page import="edu.fzu.icanteen.dao.MerchantDAO"%>
<%@page import="edu.fzu.icanteen.dao.MerchantDAOImpl"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>测试</div>
	<%
	MerchantDAO merchantDAO = new MerchantDAOImpl();
	List<Merchant> merchants = merchantDAO.list();
	for (Merchant merchant:merchants){
   	%>
   	<tr>
   		<td><%=merchant.getId() %></td>
   		<td><%=merchant.getName() %></td>
   	</tr>  		
	<%
	}
	%>
</body>
</html>