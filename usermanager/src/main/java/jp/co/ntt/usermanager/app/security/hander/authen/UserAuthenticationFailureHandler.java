package jp.co.ntt.usermanager.app.security.hander.authen;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationFailureHandler extends ExceptionMappingAuthenticationFailureHandler {

	private static Map<String, String> FAILUREURLMAP = new HashMap();
	private String defaultURL = "/login/systemError";

	static {
		FAILUREURLMAP.put("org.springframework.security.authentication.BadCredentialsException",
				"/login/badCredentials");
		FAILUREURLMAP.put("org.springframework.security.core.userdetails.UsernameNotFoundException",
				"/login/usernameNotFound");
		FAILUREURLMAP.put("org.springframework.security.authentication.DisabledException", "/login/disabled");

	}

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String url = FAILUREURLMAP.get(exception.getClass().getName().toString());
		String baseUrl = request.getContextPath();

		if (url != null) {
			response.sendRedirect(baseUrl + url);
		} else {
			response.sendRedirect(baseUrl + defaultURL);
		}

	}

}
