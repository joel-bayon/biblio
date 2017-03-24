package controller;

import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import users.User;


@Controller
@RequestMapping("/authentification")
@SessionAttributes({"user", "dateConnexion"})
public class AuthentificationController {
	@Autowired
	Properties users;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/execute")
	public String execute(@RequestParam String identifiant, @RequestParam String motDePasse, Model model) {
		//todo ...
		System.out.println("login=" + identifiant + " mot de passe = " + motDePasse);
		if(users.containsKey(identifiant) && users.getProperty(identifiant).equals(motDePasse)) {
			model.addAttribute("user",new User(identifiant, motDePasse, null));
			model.addAttribute("dateConnexion", new Date());
			return "redirect:../home/accueil";
		}
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model) {
		//todo ...
		return "login";
	}
}
