package br.com.stomach.Uncontrived.security;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	public static final String TOKEN_ADMIN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc5MTIzODU2NywiZXhwIjoxNzkxMjM5NDY3fQ.oftxujxXENQ9s9_az95tJVxrVXJq-TbML6p_6f1iq3w";
	public static final String TOKEN_USER = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzkxMjQ0NDI4LCJleHAiOjE3OTEyNDUzMjh9.zJ-2ijnFNDKt5V9FD9kOJmNWi_lVbapnbIJPqc1hmzg";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
//---
		String auth_header = requestContext.getHeaderString("Authorization");
//---
		if (auth_header == null) {
//---
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return; 
		}
//---
		if (auth_header.startsWith("Basic ")) {
/*
			String base64Credentials = auth_header.substring("Basic ".length()).trim();
	        String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
	        
	        String[] values = credentials.split(":", 2);
	        String username = values[0];
	        String password = values[1];
*/
//---			
	        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return; 
		}		
//---		
		if (auth_header.startsWith("Bearer ")) {
//---
			String token = auth_header.substring("Bearer ".length());
			Set<String> role = new HashSet<>();
	        String user_name = "";
//---	        
	        if (TOKEN_ADMIN.equals(token)) {
	        	user_name = "admin";
	        	role.add("ADMIN"); 
	        } 
	        if (TOKEN_USER.equals(token)) {
	        	user_name = "user";
	        	role.add("USER"); 
	        }
	        if (user_name.equals("")) {
//---	        	
		        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
	            return; 
	        }
//---
	        UserPrincipal principal = new UserPrincipal(user_name);
	        boolean secure = requestContext.getSecurityContext().isSecure();
	        requestContext.setSecurityContext(new CustomSecurityContext(principal, role, secure));
		}
	}

}
