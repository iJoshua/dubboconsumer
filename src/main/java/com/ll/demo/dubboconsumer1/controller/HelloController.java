package com.ll.demo.dubboconsumer1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ll.demo.dubboInterface.entity.User;
import com.ll.demo.dubboInterface.service.DemoService;
import com.ll.demo.dubboInterface.service.UserService;


@Controller
public class HelloController {

	@Autowired
	private DemoService demoService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

	@RequestMapping(value = "/testDemo", method = RequestMethod.POST)
	public String getEnteredWordLength(ModelMap model, @RequestParam("username") String enteredWord) {
		int length = this.demoService.getLength(enteredWord);

		model.addAttribute("length", length);
		return "helloRes";
	}

	@RequestMapping(value = "/userAddView", method = RequestMethod.GET)
	public String userAddView (){
		return "userAdd";
	}
	
	/**
	 * �÷���ֻΪ�˷������,�����Ǻϲ�����
	 * @param model
	 * @param userName
	 * @param userEnName
	 * @param country
	 * @param company
	 * @return
	 */
	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)
	public String userAdd(Model model, String userName,String userEnName, String country, String company) {
		User user = new User();
		user.setUserName(userName);
		user.setUserEnName(userEnName);
		user.setCompany(company);
		User saveUser = this.userService.save(user);
		model.addAttribute("saveUser", saveUser);
		return "userAddRes";
	}

/*	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)
	public String userAdd(Model model, @ModelAttribute("user") User user) {
		User saveUser = this.userService.save(user);
		model.addAttribute("saveUser", saveUser);
		return "userAddRes";
	}*/
}