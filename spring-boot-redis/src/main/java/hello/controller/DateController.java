package hello.controller;

import hello.auth.redis.cache.CachedDate;
import hello.auth.redis.cache.CachedDateManager;
import hello.config.BeanLoader;
import hello.data.entity.Word;
import hello.data.repository.WordRepository;
import hello.exception.HelloException;
import hello.form.WordForm;
import hello.util.HibernateMessageDecoder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *	@see http://docs.spring.io/spring/docs/current/spring-framework-reference/html/cache.html
 *	@see http://caseyscarborough.com/blog/2014/12/18/caching-data-in-spring-using-redis/
 */
@Controller
public class DateController extends WebMvcConfigurerAdapter {

	@Autowired
	private CachedDateManager	cachedDate;
	
	/**
	 *	Date のトップページ
	 */
	@RequestMapping(value="/dates", method=RequestMethod.GET)
	public String index(Model model) {
		CachedDate	date = cachedDate.getDate();
		
		model.addAttribute("date", date.getDateString());

		return "hello/dates/index";
	}

	/**
	 *	Date の記録ページ
	 */
	@RequestMapping(value="/dates/create", method=RequestMethod.GET)
	public String create(Model model) throws HelloException {
		cachedDate.createDate();

		return "redirect:/dates";
	}

	/**
	 *	Date の破棄ページ
	 */
	@RequestMapping(value="/dates/delete", method=RequestMethod.GET)
	public String dispose(Model model) throws HelloException {
		cachedDate.deleteDate();

		return "redirect:/dates";
	}


}