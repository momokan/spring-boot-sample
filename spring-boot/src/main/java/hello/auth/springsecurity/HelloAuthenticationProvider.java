package hello.auth.springsecurity;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 *	独自実装したログイン認証をあらわすクラス
 */
public class HelloAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String	username = auth.getName();
		String	password = (String)auth.getCredentials();

		//	ログイン認証をする
		if ((!username.equals("user1")) || (!password.equals("pass1"))) {
			//	認証に失敗した場合はエラーを投げる
			throw new BadCredentialsException("Account not found.");
		}
		
		//	認証に成功した場合は Authentication を返す
		User	user = new User();
		
		user.setUsername(username);
		user.setPassword(password);
 
		return new UsernamePasswordAuthenticationToken(user, password, Arrays.asList(new Role("ROLE_USER")));		        
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
