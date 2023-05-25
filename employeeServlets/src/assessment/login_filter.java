package assessment;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/index.jsp" })
public class login_filter implements Filter {

	public login_filter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		boolean islogin = (session != null && session.getAttribute("user") != null);
		if (islogin) {
			System.out.println("Filter invoked");

			chain.doFilter(request, response);
		} else {
			res.sendRedirect(req.getContextPath() + "/index.jsp");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
