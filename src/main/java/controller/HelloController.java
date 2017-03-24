package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/hello")
public class HelloController {
	
	@RequestMapping("")
	public ModelAndView helloWorld(@RequestParam String name) {
		ModelAndView model = new ModelAndView("helloView");
		model.addObject("name", name.toUpperCase());
		return model;
	}

}
