package hello.auth.springsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()	//	ひとまず CSRF は無効にしておく
			//	URL への認証の設定
			.authorizeRequests()
				//	以下の URL は認証を不要とする
				.antMatchers("/words/**", "/static/**").permitAll()
				//	以下の URL は指定のロールへの認証を必要とする
				.antMatchers("/private/**").hasRole("USER")
				.anyRequest().authenticated()
				.and()	//	更に設定を続ける
			//	ログイン処理の設定
			.formLogin()
				.loginPage("/signin")	//	ログイン画面の URL
				.usernameParameter("account")	//	ログイン処理で、ユーザー名をあらわすパラメーター
//				.defaultSuccessUrl("/private")	//	ログイン処理成功時の遷移先
//				.failureUrl("/signin?error")	//	ログイン処理失敗時の遷移先
				.failureHandler(new HelloAuthenticationFailureHandler())	//	ログイン処理失敗時	の処理を独自実装する
				.permitAll()
				.and()	//	更に設定を続ける
			//	ログアウト処理の設定
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/signout"))	//	ログアウト処理の URL
				.logoutSuccessUrl("/signin?signout")	//	ログアウト処理成功時の遷移先
				.permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//	他にも org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder.jdbcAuthentication() を使うと 
		//	DB にユーザー情報を持てる。
		auth
			//	認証するユーザー情報をインメモリでもつ
//			.inMemoryAuthentication()
//				.withUser("user1").password("pass1").roles("USER");
			//	ユーザー情報の認証を実装する
			.authenticationProvider(authenticationProvider());	//	認証ロジックを実装する

	}
	
	private AuthenticationProvider authenticationProvider() {
		return new HelloAuthenticationProvider();
	}
}