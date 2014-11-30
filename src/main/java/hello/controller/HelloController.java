package hello.controller;

import hello.data.entity.Word;
import hello.data.repository.WordRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RestController
public class HelloController {

	@Autowired
	protected WordRepository wordRepository;

	//	プロパティファイルの値を Component に自動設定してみる
	@Value("${app.name}")
	protected String appName;

	@RequestMapping("/")
	public String index(@RequestParam(value="name", required=false, defaultValue="User") String name, Model model) {
		//	データベースからレコードを取り出す
		Iterable<Word>		words = wordRepository.findWordsByType(1);

		//	テンプレートに変数を渡す
		model.addAttribute("name", name);
		model.addAttribute("words", words);
		model.addAttribute("appName", appName);

		return "hello/index";
	}

}
