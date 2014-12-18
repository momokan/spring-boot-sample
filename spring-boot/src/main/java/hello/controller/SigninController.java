package hello.controller;

import hello.form.SigninForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SigninController {
	
	/**
	 *	ログイン画面
	 */
	@RequestMapping(value="/signin", method=RequestMethod.GET)
	public String index(SigninForm signinForm, @RequestParam(value="error", required=false) String error, Model model) {
		return "hello/signin/index";
	}

}
