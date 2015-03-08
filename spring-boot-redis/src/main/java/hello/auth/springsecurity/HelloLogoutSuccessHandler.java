package hello.auth.springsecurity;

import hello.form.SigninForm;
import hello.util.template.StandalonSpringThymeleafUtils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.thymeleaf.spring4.context.SpringWebContext;

/**
 *	ログアウト処理に成功した場合に、特定の URL にリダイレクトする（Spring Security のデフォルト動作）のではなく、
 *	本来表示すべきテンプレートを表示するための LogoutSuccessHandler
 */
public class HelloLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		SpringWebContext	context = StandalonSpringThymeleafUtils.createContext(request, response);

		context.setVariable("signinForm", new SigninForm());
		context.setVariable("signout", true);
		
		StandalonSpringThymeleafUtils.render("hello/signin/index", context, response.getWriter());
	}

}
