package jp.co.ntt.usermanager.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.ntt.usermanager.domain.model.UserDTO;
import jp.co.ntt.usermanager.domain.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String mybatisConn(ModelMap modelMap) {
		UserDTO userDTO = userService.singleUserInforById(null);
		modelMap.addAttribute("user", userDTO);
		return "layout";
	}

}
