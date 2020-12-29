package jp.co.ntt.usermanager.app.top;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopController {
	@GetMapping("/top")
	public String menu_display() {
		return "views/top/menue";
	}
}
