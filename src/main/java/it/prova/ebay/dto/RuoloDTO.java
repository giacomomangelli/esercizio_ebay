package it.prova.ebay.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import it.prova.ebay.model.Ruolo;

public class RuoloDTO {

	private Long id;

	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;

	@NotBlank(message = "{codice.notblank}")
	private String codice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public RuoloDTO(Long id, @NotBlank(message = "{descrizione.notblank}") String descrizione,
			@NotBlank(message = "{codice.notblank}") String codice) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.codice = codice;
	}

	public RuoloDTO(@NotBlank(message = "{descrizione.notblank}") String descrizione,
			@NotBlank(message = "{codice.notblank}") String codice) {
		super();
		this.descrizione = descrizione;
		this.codice = codice;
	}

	public static Ruolo createModelFromDTO(RuoloDTO ruoloInstance) {
		return new Ruolo(ruoloInstance.getDescrizione(), ruoloInstance.getCodice());
	}

	public static RuoloDTO createDTOFromModel(Ruolo ruoloInstance) {
		return new RuoloDTO(ruoloInstance.getDescrizione(), ruoloInstance.getCodice());
	}

	public static Set<Ruolo> createRuoloModelListFromDTOList(Set<RuoloDTO> dtoListInput) {
		return dtoListInput.stream().map(ruoloEntity -> createModelFromDTO(ruoloEntity)).collect(Collectors.toSet());
	}

	public static Set<RuoloDTO> createRuoloDTOListFromModelList(List<Ruolo> modelListInput) {
		return modelListInput.stream().map(ruoloEntity -> createDTOFromModel(ruoloEntity)).collect(Collectors.toSet());
	}

}
