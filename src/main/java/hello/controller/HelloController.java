package hello.controller;

import hello.data.entity.Word;
import hello.data.entity.repository.WordRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RestController
public class HelloController {

	@Autowired
	protected WordRepository wordRepository;

	@RequestMapping("/")
	public String index(@RequestParam(value="name", required=false, defaultValue="User") String name, Model model) {
		model.addAttribute("name", name);

		List<Word>		words = wordRepository.findWordsByContent("Greeting");

		return "hello/index";
	}

}
