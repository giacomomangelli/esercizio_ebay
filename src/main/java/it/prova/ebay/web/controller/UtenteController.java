package it.prova.ebay.web.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.ebay.dto.AnnuncioDTO;
import it.prova.ebay.dto.RegistrationValid;
import it.prova.ebay.dto.RuoloDTO;
import it.prova.ebay.dto.UtenteDTO;
import it.prova.ebay.model.StatoUtente;
import it.prova.ebay.service.annuncio.AnnuncioService;
import it.prova.ebay.service.ruolo.RuoloService;
import it.prova.ebay.service.utente.UtenteService;

@Controller
@RequestMapping("/utente")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;
	@Autowired
	private RuoloService ruoloService;
	@Autowired
	private AnnuncioService annuncioService;

	@GetMapping
	public ModelAndView listAllUtenti() {
		ModelAndView mv = new ModelAndView();
		List<UtenteDTO> utentiDTO = UtenteDTO.createUtenteDTOListFromModelList(utenteService.listAllUtenti());
		mv.addObject("utente_list_attribute", utentiDTO);
		mv.setViewName("utente/list");
		return mv;
	}

	@GetMapping("/search")
	public String searchUtente(Model model) {
		model.addAttribute("list_ruoli_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAllRuoli()));
		model.addAttribute("list_stati_attribute", StatoUtente.values());
		model.addAttribute("list_annunci_attr",
				AnnuncioDTO.createAnnuncioDTOListFromModelList(annuncioService.listAllAnnunci()));
		return "utente/search";
	}

	@PostMapping("/list")
	public String listUtenti(UtenteDTO utenteExampleDTO, ModelMap model, HttpServletRequest request) {
		String[] ruoliParam = request.getParameterValues("roles");
		utenteExampleDTO.setRuoli(convertParamsInDTO(ruoliParam));
		List<UtenteDTO> utentiDTO = UtenteDTO
				.createUtenteDTOListFromModelList(utenteService.findByExample(utenteExampleDTO.buildUtenteModel()));
		model.addAttribute("utente_list_attribute", utentiDTO);
		return "utente/list";
	}

	@PostMapping("/cambiaStato")
	public String cambiaStato(@RequestParam(name = "idUtenteForChangingStato", required = true) Long idUtente) {
		utenteService.invertUserAbilitation(idUtente);
		return "redirect:/utente";
	}

	@GetMapping("/insert")
	public String insertUtente(Model model) {
		model.addAttribute("insert_utente_attr", new UtenteDTO());
		model.addAttribute("list_ruoli_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAllRuoli()));
		return "utente/insert";
	}

	@PostMapping("/save")
	public String saveUtente(
			@Validated(RegistrationValid.class) @ModelAttribute("insert_utente_attr") UtenteDTO utenteDTO,
			BindingResult result, RedirectAttributes redirectAttrs) {

		if (!utenteDTO.validatePassword()) {
			result.rejectValue("confermaPassword", "confermaPassword.notequals");
		}

		if (result.hasErrors()) {
			return "utente/insert";
		}
		utenteService.inserisci(utenteDTO.buildUtenteModel());
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/utente";
	}

	public Set<RuoloDTO> convertParamsInDTO(String[] ruoliParams) {
		Set<RuoloDTO> ruoli = new HashSet<>(0);
		if (ruoliParams != null) {
			for (String ruoloItem : ruoliParams) {
				ruoli.add(RuoloDTO.createDTOFromModel(ruoloService.caricaSingoloElemento(Long.parseLong(ruoloItem))));
			}
		}
		return ruoli;
	}
}
