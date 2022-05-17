package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Map;

@Controller
public class HomeController
{

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String home(@AuthenticationPrincipal OAuth2User principal,Principal user, Model model) {

		if(principal == null){
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			model.addAttribute("security", authentication.getName());
		}else{
			model.addAttribute("security", principal.getAttribute("login"));
			if(userService.findByName(principal.getAttribute("login")) == false){
				User newUser = new User();
				newUser.setUsername(principal.getAttribute("login"));
				newUser.setRole("USER");
				newUser.setFullname(principal.getAttribute("login"));
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				newUser.setPassword(passwordEncoder.encode("a"));
				userService.create(newUser);
			}
		}
		return "bidList/list";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		return "redirect:/bidList/list";
	}

}
