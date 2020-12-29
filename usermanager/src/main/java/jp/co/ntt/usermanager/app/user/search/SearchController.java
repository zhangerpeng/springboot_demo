package jp.co.ntt.usermanager.app.user.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchController {

	@ModelAttribute
	public SearchForm getSearchFrom() {
		return new SearchForm();
	}

	@GetMapping(value = "/user/search", params = "form")
	public String searchInit() {
		return "views/user/searchInit";
	}

	@RequestMapping(value = "/user/searchList", method = RequestMethod.POST)
	public String search(@ModelAttribute SearchForm searchFrom) {
		System.out.print(searchFrom);
		return "views/user/searchList";
	}

}
