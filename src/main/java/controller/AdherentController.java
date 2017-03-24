package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.AdherentDao;
import dao.LivreDao;
import entity.Adherent;
import entity.Livre;


@Controller
@RequestMapping("/adherent")
public class AdherentController {
	@Autowired
	AdherentDao dao;
	
	@RequestMapping(value="",method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody List<Adherent> getAdherentAll() {
		System.out.println("getLivreAll()");
		return dao.loadAll();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Adherent getAdherent(@PathVariable Integer id) {
		System.out.println("getLivre()");
		return dao.load(id);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody void updateAdherent(@PathVariable Integer id, @RequestBody Adherent adherent) {
		System.out.println(adherent);
		 dao.update(adherent);
	}
	
	

}
