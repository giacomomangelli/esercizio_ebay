package it.prova.ebay.service.annuncio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.ebay.model.Annuncio;
import it.prova.ebay.repository.annuncio.AnnuncioRepository;

@Service
public class AnnuncioServiceImpl implements AnnuncioService {

	@Autowired
	private AnnuncioRepository repository;

	@Transactional(readOnly = true)
	public List<Annuncio> listAllUtenti() {
		return (List<Annuncio>) repository.findAll();
	}

	@Transactional(readOnly = true)
	public Annuncio caricaSingoloUtente(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Annuncio annuncioInstance) {
		repository.save(annuncioInstance);

	}

	@Transactional
	public void inserisci(Annuncio annuncioInstance) {
		repository.save(annuncioInstance);

	}

	@Transactional
	public void rimuovi(Annuncio annuncioInstance) {
		repository.delete(annuncioInstance);

	}

	@Transactional(readOnly = true)
	public List<Annuncio> findByExample(Annuncio annuncioExample) {
		return repository.findByExample(annuncioExample);
	}

}
