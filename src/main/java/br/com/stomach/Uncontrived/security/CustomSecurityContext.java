package br.com.stomach.Uncontrived.security;

import java.security.Principal;
import java.util.Set;

import javax.ws.rs.core.SecurityContext;

public class CustomSecurityContext implements SecurityContext {

	private final UserPrincipal principal;
    private final Set<String> roles;
    private final boolean secure;
    
    public CustomSecurityContext(UserPrincipal principal, Set<String> roles, boolean secure) {
        this.principal = principal;
        this.roles = roles;
        this.secure = secure;
    }
    
	@Override
	public Principal getUserPrincipal() {
		return principal;
	}

	@Override
	public boolean isUserInRole(String role) {
		if (roles == null) {
            return false;
        }
        return roles.contains(role);
	}

	@Override
	public boolean isSecure() {
		return secure;
	}

	@Override
	public String getAuthenticationScheme() {
		return BASIC_AUTH;
	}

}
