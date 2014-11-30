package hello.controller;

import hello.data.entity.Word;
import hello.data.repository.WordRepository;
import hello.util.HibernateMessageDecoder;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
//@RestController
public class WordController extends WebMvcConfigurerAdapter {

	@Autowired
	protected WordRepository wordRepository;

	@RequestMapping(value="/words", method=RequestMethod.GET)
	public String index(Word word, Model model) {
		listWords(model);
		
		return "hello/words/index";
	}

	@RequestMapping(value="/word/{content}", method=RequestMethod.GET)
	public String show(@PathVariable String content, Model model) {
		listWords(model);
		
		Word	word = wordRepository.findWordsByContent(content).iterator().next();
		
		model.addAttribute("word", word);
		
		return "hello/words/index";
	}

	@RequestMapping(value="/words", method=RequestMethod.POST)
	public String create(@Valid Word word, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			HibernateMessageDecoder.decodeObjectErrors(bindingResult);

			listWords(model);

			return "hello/words/index";
		}

		wordRepository.save(word);

		return "redirect:/words";
	}
	
	private void listWords(Model model) {
		//	DB 内のデータを一覧表示する
		Iterable<Word>	words = wordRepository.findAll();
		
		model.addAttribute("words", words);
	}

}