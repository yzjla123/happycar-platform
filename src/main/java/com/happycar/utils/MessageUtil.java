package com.happycar.utils;

import org.springframework.ui.Model;

public class MessageUtil {
	
	public static void addAlert(String msg, Model model) {
		model.addAttribute("msg", msg);
	}
	
	public static void success(String msg, Model model) {
		addAlert(msg, model);
		model.addAttribute("success", true);
	}
	
	public static void back(String msg, Model model) {
		addAlert(msg, model);
		model.addAttribute("success", true);
		model.addAttribute("back", true);
	}
	
	public static void fail(String msg, Model model) {
		addAlert(msg, model);
		model.addAttribute("success", false);
	}
}
