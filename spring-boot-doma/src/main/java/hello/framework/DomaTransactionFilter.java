package hello.framework;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class DomaTransactionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		DomaTransactionManager	transactionManager = BeanLoader.getBean("domaTransactionManager");
		LocalTransaction tx = transactionManager.getLocalTransaction();

		try {
			tx.begin();

			filterChain.doFilter(request, response);

			tx.commit();
		} finally {
			tx.rollback();
		}

	}

}
