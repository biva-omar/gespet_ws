package fr.formationspring.gespet.ws.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formationspring.gespet.business.dto.GpClientBasicDTO;
import fr.formationspring.gespet.business.dto.GpClientFullDTO;
import fr.formationspring.gespet.business.exception.GespetBusinessException;
import fr.formationspring.gespet.business.service.IGpClientService;
import fr.formationspring.gespet.business.utils.GespetConstantes.GespetConstantesService;
import fr.formationspring.gespet.ws.urlbase.GespetUrlBase;

@CrossOrigin(origins = GespetUrlBase.url, maxAge = GespetUrlBase.maxAge)
@RestController
@RequestMapping("/clients")
public class GpClientController {
	
	@Resource(name = GespetConstantesService.GP_CLIENT_SERVICE_KEY)
	private IGpClientService gpClientService;
	
	/**
	 * Create GpClient in the database
	 * 
	 **/
	@PostMapping
	public ResponseEntity<?> createClient(@Valid @RequestBody GpClientFullDTO gpClient)
			throws GespetBusinessException {

		return ResponseEntity.ok(gpClientService.create(gpClient));
	}
	
	/***
	 * return the list of all clients
	 * 
	 **/
	@GetMapping
	public List<GpClientBasicDTO> getAllClient() {

		return gpClientService.findAll();
	}
	
	/**
	 * return Client by id
	 * 
	 * @throws GespetBusinessException
	 **/
	@GetMapping("/{id}")
	public GpClientFullDTO getOneClient(@PathVariable(value = "id") int id) throws GespetBusinessException {
		return this.gpClientService.findById(id);
	}
	
	/**
	 * modify a given Client in the database
	 * 
	 * @throws GespetBusinessException
	 * @throws AccessDeniedException
	 */

	@PutMapping("/{id}")
	public ResponseEntity<GpClientBasicDTO> getUpdateGpClient(@PathVariable(value = "id") int idGpClient,
			@Valid @RequestBody GpClientFullDTO clientDetails) throws GespetBusinessException, AccessDeniedException {

		GpClientFullDTO clientFull = gpClientService.findById(idGpClient);
		if (clientFull == null) {
			return ResponseEntity.notFound().build();
		}

		GpClientBasicDTO updateClient = gpClientService.update(clientDetails);
		return ResponseEntity.ok().body(updateClient);
	}
	
	/**
	 * Delete a given client in the database
	 * 
	 * @throws GespetBusinessException
	 * @throws AccessDeniedException
	 **/
	@DeleteMapping("/{id}")
	public ResponseEntity<GpClientBasicDTO> deleteClient(@PathVariable(value = "id") int idClient)
			throws AccessDeniedException, GespetBusinessException {
		gpClientService.deleteById(idClient);
		return ResponseEntity.ok().build();

	}
}
