package com.ipro.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * date:2011-12-12
 * author: pradoem wongkraso
 * contact : go2doem@gmail.com,destar_@hotmail.com
 * description: 
 * */

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public ModelAndView doHomeAction() {
		//String message = "Hello World, Spring 3.0!";
		System.out.println("Home Action...");
		return new ModelAndView("welcome", "message", null);
	}
	
	@RequestMapping("/error")
	public ModelAndView doMsgErrorAction(HttpServletRequest request) {
		//String message = "Hello World, Spring 3.0!";
		request.setAttribute("url", request.getParameter("url"));
		request.setAttribute("msg", request.getParameter("msg"));

		return new ModelAndView("msg_errors", "msg", request.getParameter("msg"));
	}
	
	@RequestMapping("/success")
	public ModelAndView doMsgSuccessAction(HttpServletRequest request) {
		//String message = "Hello World, Spring 3.0!";
		System.out.println("Success Action...");
		request.setAttribute("url", request.getParameter("url"));
		request.setAttribute("msg", request.getParameter("msg"));
		
		return new ModelAndView("msg_success", "msg", request.getParameter("msg"));
		//return new ModelAndView("msg_test", "msg", null);
	}
	
	
	

}
