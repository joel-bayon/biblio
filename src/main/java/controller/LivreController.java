package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.LivreDao;
import entity.Livre;


@Controller
@RequestMapping("/livres")
public class LivreController {
	@Autowired
	LivreDao livredao;
	
	@RequestMapping(value="",method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody List<Livre> getLivreAll() {
		System.out.println("getLivreAll()");
		return livredao.loadAll();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Livre getLivre(@PathVariable Integer id) {
		System.out.println("getLivre()");
		return livredao.load(id);
	}

}
