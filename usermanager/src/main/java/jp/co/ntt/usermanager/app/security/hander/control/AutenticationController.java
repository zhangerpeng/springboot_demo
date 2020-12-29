package jp.co.ntt.usermanager.app.security.hander.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AutenticationController {
	
	@RequestMapping({ "/login/badCredentials", "/login/usernameNotFound", "/login/disabled", "/login/systemError" })
	public String badCredentialsExceptionHander() {
		return "views/security/authenticationFailure";

	}

}
