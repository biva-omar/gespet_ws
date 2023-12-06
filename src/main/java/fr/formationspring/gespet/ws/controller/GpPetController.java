package fr.formationspring.gespet.ws.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import fr.formationspring.gespet.ws.urlbase.GespetUrlBase;
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
import fr.formationspring.gespet.business.dto.GpPetBasicDTO;
import fr.formationspring.gespet.business.dto.GpPetFullDTO;
import fr.formationspring.gespet.business.exception.GespetBusinessException;
import fr.formationspring.gespet.business.service.IGpPetService;
import fr.formationspring.gespet.business.utils.GespetConstantes.GespetConstantesService;

@CrossOrigin(origins = GespetUrlBase.url, maxAge = GespetUrlBase.maxAge)
@RestController
@RequestMapping("/pets")
public class GpPetController {
	
	@Resource(name = GespetConstantesService.GP_PET_SERVICE_KEY)
	private IGpPetService gpPetService ;
	
	/**
	 * Create GpPet in the database
	 * 
	 **/
	@PostMapping
	public ResponseEntity<?> createPet(@Valid @RequestBody GpPetFullDTO gpPet)
			throws GespetBusinessException {

		return ResponseEntity.ok(gpPetService.create(gpPet));
	}
	
	/***
	 * return the list of all pets
	 * 
	 **/
	@GetMapping
	public List<GpPetBasicDTO> getAllPet() {

		return gpPetService.findAll();
	}
	
	/**
	 * return Pet by id
	 * 
	 * @throws GespetBusinessException
	 **/
	@GetMapping("/{id}")
	public GpPetFullDTO getOnePet(@PathVariable(value = "id") int id) throws GespetBusinessException {
		return this.gpPetService.findById(id);
	}
	
	/**
	 * modify a given Pet in the database
	 * 
	 * @throws GespetBusinessException
	 * @throws AccessDeniedException
	 */

	@PutMapping("/{id}")
	public ResponseEntity<GpPetBasicDTO> getUpdateGpPet(@PathVariable(value = "id") int idGpPet,
			@Valid @RequestBody GpPetFullDTO petDetails) throws GespetBusinessException, AccessDeniedException {

		GpPetFullDTO petFull = gpPetService.findById(idGpPet);
		if (petFull == null) {
			return ResponseEntity.notFound().build();
		}

		GpPetBasicDTO updatePet = gpPetService.update(petDetails);
		return ResponseEntity.ok().body(updatePet);
	}
	
	/**
	 * Delete a given pet in the database
	 * 
	 * @throws GespetBusinessException
	 * @throws AccessDeniedException
	 **/
	@DeleteMapping("/{id}")
	public ResponseEntity<GpPetBasicDTO> deletePet(@PathVariable(value = "id") int idPet)
			throws AccessDeniedException, GespetBusinessException {
		gpPetService.deleteById(idPet);
		return ResponseEntity.ok().build();

	}

}
