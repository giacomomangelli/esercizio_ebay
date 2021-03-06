package it.prova.ebay.service.utente;

import java.util.List;

import it.prova.ebay.model.Ruolo;
import it.prova.ebay.model.Utente;

public interface UtenteService {

	public List<Utente> listAllUtenti();

	public Utente caricaSingoloUtente(Long id);

	public void aggiorna(Utente utenteInstance);

	public void inserisci(Utente utenteInstance);

	public void rimuovi(Utente utenteInstance);

	public List<Utente> findByExample(Utente utenteExample);

	public Utente findByUserName(String usernameInput);

	public void invertUserAbilitation(Long utenteInstanceId);

	public List<Utente> findByCognomeNomeRuolo(String cognome, String nome, Ruolo ruolo);

}
