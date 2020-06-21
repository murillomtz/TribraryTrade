package br.ucsal.filter;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.dao.LivroDAO;


/**
 * Servlet Filter implementation class Filter
 */
@WebFilter("/*")
public class Filter implements javax.servlet.Filter {

	/**
	 * Default constructor. 
	 */
	public Filter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		Object object = httpServletRequest.getSession().getAttribute("usuario");

		if (httpServletRequest.getRequestURI().endsWith("LivroForm") || 
				httpServletRequest.getRequestURI().endsWith("SolicitarTroca")) {
			if(object==null) {
				httpServletResponse.sendRedirect("./index.jsp");
			}else {
				chain.doFilter(request, response);
			}
			
		}else {
			if( httpServletRequest.getRequestURI().endsWith("index.jsp") ||httpServletRequest.getRequestURI().endsWith("Login")) {
				LivroDAO livroDAO = new LivroDAO();
				request.setAttribute("livros", livroDAO.listar());
				chain.doFilter(request, response);
			}else {
				chain.doFilter(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
