package hello.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrivateController {
	private static final String		SESSION_KEY_COUNT = "count";

	/**
	 *	ログイン認証が必要な画面
	 */
	@RequestMapping("/private")
	public String index(HttpSession session, Model model) {
		//	ログインユーザーを取得する
		Authentication	auth = SecurityContextHolder.getContext().getAuthentication();
		
		model.addAttribute("username", auth.getName());
		
		//	セッションにアクセス回数を保持する
		Integer			count = (Integer)session.getAttribute(SESSION_KEY_COUNT);
		
		if (count == null) {
			count = 0;
		}
		count++;
		
		session.setAttribute(SESSION_KEY_COUNT, count);

		model.addAttribute("count", count);

		return "hello/private/index";
	}

}
