package hello.auth.springsecurity;

import hello.form.SigninForm;
import hello.util.template.StandalonSpringThymeleafUtils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.thymeleaf.spring4.context.SpringWebContext;

/**
 *	ログイン処理に失敗した場合に、特定の URL にリダイレクトする（Spring Security のデフォルト動作）のではなく、
 *	本来表示すべきテンプレートを表示するための AuthenticationFailureHandler
 */
public class HelloAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		SpringWebContext	context = StandalonSpringThymeleafUtils.createContext(request, response);

		context.setVariable("signinForm", new SigninForm());
		context.setVariable("error", true);
		
		StandalonSpringThymeleafUtils.render("hello/signin/index", context, response.getWriter());
	}

}
