package tool;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class GlobalFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");

		// contextPathをJSPで使用できるように
		String contextPath = request.getServletContext().getContextPath();
		if (contextPath.isEmpty()) {
			contextPath = "/.";
		}

		request.setAttribute("contextPath", contextPath);

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
