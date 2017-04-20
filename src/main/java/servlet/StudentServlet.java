package servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Student;
import service.StudentService;

@WebServlet("/student")
public class StudentServlet extends BaseServlet {
	StudentService studentService = new StudentService();

	public List<Student> login(HttpServletRequest request,
			HttpServletResponse response) {
		List<Student> studentList = studentService.sortStudent();

		HttpSession session = request.getSession();
		session.setAttribute("studentList", studentList);
		int pageNos=1; 
		  
		  session.setAttribute("pageNos",pageNos); 
		  int countPage = (int)Math.round(studentList.size()/10);
		  session.setAttribute("countPage",countPage);
		try {
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		return studentList;
	}

	public void page(HttpServletRequest request,
			HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("pageNos"));
		List<Student> studentList = studentService.page(num);
		HttpSession session = request.getSession();
		session.setAttribute("studentList", studentList);
		int pageNos ; 
		  if(Integer.parseInt(request.getParameter("pageNos"))<1){
			  pageNos = 1; 
		  }else{ 
			  pageNos = Integer.parseInt(request.getParameter("pageNos")); 
			  }
		  session.setAttribute("pageNos",pageNos); 
		try {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			System.out.println(111);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

	public void page1(HttpServletRequest request,
			HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("pageNos"));
		List<Student> student = studentService.sortStudent();
		List<Student> studentList = studentService.page(num);
		HttpSession session = request.getSession();
		session.setAttribute("studentList", studentList);
		int pageNos ; 
		  if(Integer.parseInt(request.getParameter("pageNos"))>Math.round(student.size()/10)){
			  pageNos = Math.round(student.size()/10); 
		  }else{ 
			  pageNos = Integer.parseInt(request.getParameter("pageNos")); 
			  }
		  session.setAttribute("pageNos",pageNos); 
		try {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			System.out.println(111);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}
	public void addStudent(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		System.out.println(request.getParameter("birthday"));
		Date birthday = java.sql.Date.valueOf(request.getParameter("birthday"));
		String description = request.getParameter("description");
		int avgscore = Integer.parseInt(request.getParameter("avgscore"));
		Student student = new Student(id, name, birthday, description, avgscore);
		studentService.addStudent(student);
		login(request, response);
	}

	public void deleteStudent(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		studentService.deleteStudent(id);
		login(request, response);
	}

	public void updateStudent(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		System.out.println(request.getParameter("birthday"));
		Date birthday = java.sql.Date.valueOf(request.getParameter("birthday"));
		String description = request.getParameter("description");
		int avgscore = Integer.parseInt(request.getParameter("avgscore"));
		Student student = new Student(id, name, birthday, description, avgscore);
		studentService.updateStudent(student);
		login(request, response);
	}

	public void updateStudent1(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		Student student = studentService.findStudent(id);
		HttpSession session = request.getSession();
		session.setAttribute("student", student);
		try {
			request.getRequestDispatcher("updateStudent.jsp").forward(request,
					response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
