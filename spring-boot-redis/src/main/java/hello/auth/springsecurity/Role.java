package hello.auth.springsecurity;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
	private final String	authority;

	public Role(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

}
