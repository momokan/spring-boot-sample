package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RestController
public class HelloController {

	@RequestMapping("/")
	public String index(@RequestParam(value="name", required=false, defaultValue="User") String name, Model model) {
		model.addAttribute("name", name);

		return "hello/index";
	}

}
