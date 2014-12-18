package hello.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrivateController {

	/**
	 *	ログイン認証が必要な画面
	 */
	@RequestMapping("/private")
	public String index(Model model) {
		//	ログインユーザーを取得する
		Authentication	auth = SecurityContextHolder.getContext().getAuthentication();
		
		model.addAttribute("username", auth.getName());
		
		return "hello/private/index";
	}

}
