package hello.controller;

import hello.data.entity.Word;
import hello.data.repository.WordRepository;
import hello.exception.HelloException;
import hello.form.WordForm;
import hello.util.HibernateMessageDecoder;

import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
//@RestController
public class WordController extends WebMvcConfigurerAdapter {

	@Autowired
	protected WordRepository wordRepository;

	/**
	 *	Word の一覧画面
	 */
	@RequestMapping(value="/words", method=RequestMethod.GET)
	public String index(Word word, Model model) {
		listWords(model);
		
		return "hello/words/index";
	}

	/**
	 *	Word の詳細画面
	 */
	@RequestMapping(value="/word/{content}", method=RequestMethod.GET)
	public String show(@PathVariable String content, Model model) throws HelloException {
		listWords(model);
		
		//	指定の Word を取り出す
		//	ない場合はエラーを投げる
		Iterator<Word>		wordsIterator = wordRepository.findWordsByContent(content).iterator();
		
		if (!wordsIterator.hasNext()) {
			throw new HelloException();
		}
		Word					word = wordsIterator.next();
		
		model.addAttribute("word", word);
		
		return "hello/words/index";
	}

	/**
	 *	Word の検索結果画面
	 */
	@RequestMapping(value="/words/{keyword}/search", method=RequestMethod.GET)
	public String search(@PathVariable String keyword, Model model) {
		Iterable<Word>	words = wordRepository.findLikeContent(keyword);
		
		model.addAttribute("words", words);

		return "hello/words/index";
	}
	
	/**
	 *	Word の作成画面
	 */
	@RequestMapping(value="/words/create", method=RequestMethod.GET)
	public String createForm(WordForm wordForm, Model model) {
		model.addAttribute("wordForm", wordForm);

		return "hello/words/create";
	}

	/**
	 *	Word の作成
	 */
	@RequestMapping(value="/words/create", method=RequestMethod.POST)
	public String create(@Valid WordForm wordForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			HibernateMessageDecoder.decodeObjectErrors(bindingResult);

			listWords(model);

			return "hello/words/create";
		}

		//	メモは使わない
		//	関連ワードも使わない
		
		wordRepository.save(wordForm.getWord());

		return "redirect:/words";
	}

	/*
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
	*/

	private void listWords(Model model) {
		//	DB 内のデータを一覧表示する
		Iterable<Word>	words = wordRepository.findAll();
		
		model.addAttribute("words", words);
	}

}