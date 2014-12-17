package hello.controller;

import hello.form.SigninForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PrivateController {

	/**
	 *	ログイン認証が必要な画面
	 */
	@RequestMapping("/private")
	public String index() {
		return "hello/private/index";
	}

}
