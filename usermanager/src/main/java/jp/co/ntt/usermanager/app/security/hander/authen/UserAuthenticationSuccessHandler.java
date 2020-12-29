package jp.co.ntt.usermanager.app.security.hander.authen;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jp.co.ntt.usermanager.domain.model.UserDTO;
@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpReuqets, HttpServletResponse httpResponse, Authentication authentication)
			throws IOException, ServletException {
		//认证成功后的后置处理器，用户的状态未INIT时，进行密码变更处理
	   //用户状态未ACTIVE时,迁移到menue画面
		String baseUrl = httpReuqets.getContextPath();
		String dispatchByDisplayUrl = null;
		
		UserDTO userDTO = (UserDTO) authentication.getPrincipal();
		if(userDTO !=null) {
			if("INIT".equals(userDTO.getStatus())) {
				dispatchByDisplayUrl = "/password/change?form";
			} else {
				dispatchByDisplayUrl = "/top";
			}
		}
		httpResponse.sendRedirect(baseUrl+dispatchByDisplayUrl);
				
	}

}
