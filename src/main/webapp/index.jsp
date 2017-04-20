<%@page import="model.Student"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="">
<meta name="description" content="">

<title>学生数据管理系统</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="bootstrap/js/jquery.min.js"></script>
 
<!-- Google web font  -->

<%
	if ((List<Student>)session.getAttribute("studentList")==null) {
%>
<jsp:forward page="student?v=login"></jsp:forward>
<%
	}
%>
<%
	List<Student> studentList = (List<Student>) session.getAttribute("studentList");
%>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
		<span style="font-size: large;">学生数据管理系统</span>
		<div style="height:3em"></div>
			<div>
				<span class="label label-warning"><a href="addStudent.jsp">新增</a></span>
			</div>
			
			<div class="col-md-12 column">
				
				<table class="table table-striped border-top" id="sample_1" border ="1px">
					<thead>
						<tr>
						<th></th>
							<th class="hidden-phone">id</th>
							<th class="hidden-phone">姓名</th>
							<th class="hidden-phone">出生日期</th>
							<th class="hidden-phone">备注</th>
							<th class="hidden-phone">平均分</th>
							<th class="hidden-phone">操作</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (int i = 0; i < studentList.size(); i++) {
								Student student = studentList.get(i);
						%>
						<tr class="odd gradeX">
						<td>
                                <input name="id" value="<%=student.getId() %>" type="hidden">
                            </td>
							<td class="hidden-phone"><%=student.getId()%></td>
							<td class="hidden-phone"><%=student.getName()%></td>
							<td class="hidden-phone"><%=student.getBirthday()%></td>
							<td class="hidden-phone"><%=student.getDescription()%></td>
							<td class="hidden-phone"><%=student.getAvgscore()%></td>
							<td class="hidden-phone"><span class="label label-success">
							<a href="student?v=updateStudent1&id=<%=student.getId() %>">修改</a></span><span class="label label-danger">
							<a href="javascript:void(0);" onclick="change(this);"><label>删除</label></a></span></td>
									<script>
                                function change(obj){
                                	var state = $(obj).children("label").text();
                                	 var id = $(obj).parent().parent().parent().children().children("input").val();
                                	 alert(id);
                                	 $.post("student?v=deleteStudent",{"id":id},function(result){
                                		 window.location.reload();
                                		 });
                                }
                            </script>
							<%
								}
							%>
						</tr>
					</tbody>
				</table>
				<%Integer pageNos = Integer.parseInt(session.getAttribute("pageNos").toString()); %>
						<a href="student?v=page&pageNos=<%=pageNos-1 %>"><label>上一页</label></a><a href="student?v=page&pageNos=<%=pageNos +1%>"><label>下一页</label></a>
			</div>
		</div>
	</div>
</body>

