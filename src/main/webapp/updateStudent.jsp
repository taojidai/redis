<%@page import="model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<%Student student = (Student)session.getAttribute("student"); %>
</head>
<body>
	<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<form action = "student?v=updateStudent" method="post">
			<table>
				<tr>
					 <td><label for="exampleInputEmail1">id</label></td>
					 <td><input type="text" class="form-control" readonly="readonly" name="id" value="<%=student.getId()%>"/></td>
				</tr>
				<tr>
					 <td><label for="exampleInputEmail1">姓名</label></td>
					 <td><input type="text" class="form-control"  name="name" value="<%=student.getName()%>"/></td>
				</tr>
				<tr>
					 <td><label for="exampleInputEmail1">出生日期</label></td>
					 <td><input type="text" class="form-control"  name="birthday" value="<%=student.getBirthday()%>"/></td>
				</tr>
				<tr>
					 <td><label for="exampleInputEmail1">备注</label></td>
					 <td><input type="text" class="form-control"  name="description" value="<%=student.getDescription()%>"/></td>
				</tr>
				<tr>
					 <td><label for="exampleInputEmail1">平均分</label></td>
					 <td><input type="text" class="form-control"  name="avgscore" value="<%=student.getAvgscore()%>"/></td>
				</tr>
				<tr>
				 <td><input type="submit" class="btn btn-default"></td>
				 <td><button type="button" class="btn btn-default" onclick="history.go(-1)"><a href="">返回</a></button></td>
				 </tr>
				  </table>
			</form>
		</div>
	</div>
</div>		
			
</body>
</html>