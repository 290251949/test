package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * ���˵��࣬�����˶Է���������ҳȨ�޵Ĺ��ˣ��Լ��ַ������ת��
 * @author bob
 *
 */
public class LoginFilter implements Filter {
    private FilterConfig config;
	@Override
	public void destroy() {
		

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		
		//Ϊ�˹����ַ�������request��response������Ϊutf-8��ʽ
		
		String charset=config.getInitParameter("charset");
		if(charset==null){
			charset="utf-8";
		}
		request.setCharacterEncoding(charset);
		response.setContentType("text/html;charset="+charset);
		
		//Ϊ���ж������URL�Ƿ���Ҫ���ˣ��������Ҫ���ˣ���ֱ�ӷ���
		
		if(config!=null){
			String str=config.getInitParameter("noFilterURL");
			if(str!=null){
			String[] array=str.split(";");
			for(int i=0;i<array.length;i++){
				if(array[i]==null || array[i].equals("")) continue;
				if(request.getRequestURI().indexOf(array[i])!=-1){
					arg2.doFilter(arg0, arg1);
					return;
				}
			}
			}
		}
		
		HttpSession session=request.getSession();
		
		if(session.getAttribute("user")!=null){
			arg2.doFilter(arg0, arg1);
		}
		else{
			response.sendRedirect("login.jsp");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		config=arg0;
	}

}
