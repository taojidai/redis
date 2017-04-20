package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class BaseServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String v = request.getParameter("v");
		System.out.println(v);
		Method method = null;
		try {
			method = getClass().getMethod(v, HttpServletRequest.class,HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("方法"+v+"不存在");
		}
		try {
			String result = (String) method.invoke(this,request,response);
			if (result!=null && !result.isEmpty()) {
				String type = result.substring(0,1);
				String url = result.substring(2);
				System.out.println(url);
				if ("f".equals(type)) {
					
					request.getRequestDispatcher(url).forward(request, response);
				}
				if ("r".equals(type)) {
					
					response.sendRedirect(url);
				}
			}
			
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
