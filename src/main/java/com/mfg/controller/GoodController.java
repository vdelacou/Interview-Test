package com.mfg.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mfg.model.Good;
import com.mfg.repo.GoodMongoRepository;

@RestController
public class GoodController {

	@Autowired
	private GoodMongoRepository repository;

	/**
	 * Find list of goods with page and size option 
	 * @param page default=0
	 * @param size default=10
	 * @return list of goods
	 */
	@GetMapping("/api/good")
	public List<Good> findGoods(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		if (page == null) {
			page = 0;
		}
		if (size == null) {
			size = 10;
		}

		return repository.findAll(new PageRequest(page, size)).getContent();
	}

	/**
	 * Find one good with given id
	 * @param id 
	 * @return Found good if present, null otherwise
	 */
	@GetMapping("/api/good/{id}")
	public Good findOne(@PathVariable String id) {
		return repository.findOne(id);
	}

	/**
	 * Creates a single good with given constraints @seeAlso <code>validate</code>
	 * @param good given good instance, id can be ignored
	 * @return saved object or an error message
	 */
	@PostMapping("/api/good")
	public Good createGood(@RequestBody Good good) {
		
		// validate input
		validateGood(good);
		
		// validate uniqueness
		if (repository.countByName(good.getName()) > 0){
			throw new IllegalArgumentException(String.format("Name '%s' is not unique", good.getName()));
		}
		
		return repository.save(good);
	}

	/**
	 * Update a good instance
	 * @param newGood given instance with id field
	 * @return updated Good instance
	 */
	@PutMapping("/api/good")
	public Good updateGood(@RequestBody Good newGood) {
		validateGood(newGood);

		Good existing = repository.findOne(newGood.getId());
		if (existing == null) {
			throw new IllegalArgumentException("Object not found");
		}

		return repository.save(newGood);
	}

	
	/**
	 * Delete a good with given id
	 * @param id
	 */
	@DeleteMapping("/api/good/{id}")
	public void deleteGood(@PathVariable("id") String id){
		repository.delete(id);
	}
	
	
	/**
	 * Default validation (id is optional)
	 * @param good
	 * @return
	 * @throws IllegalArgumentException
	 */
	private boolean validateGood(Good good) throws IllegalArgumentException {
		return validateGood(good, false);
	}

	/**
	 * Validation method for good:
	 * 1. name is not null and must be unique
	 * 2. age is integer and >= 0
	 * 3. productionDate is before the current date
	 * @param good
	 * @param needId true if id must be present, false otherwise
	 * @return true if successful
	 * @throws IllegalArgumentException whenever there is a validation violation
	 */
	private boolean validateGood(Good good, boolean needId)
			throws IllegalArgumentException {
		if (good == null) {
			throw new IllegalArgumentException("Cannot convert json to object");
		}
		if (needId && (good.getId() == null || good.getId().length() == 0)) {
			throw new IllegalArgumentException("id cannot be empty");
		}
		if (good.getName() == null) {
			throw new IllegalArgumentException("Name cannot be empty");
		}
		if (good.getName().length() > 50) {
			throw new IllegalArgumentException("Name too long");
		}
		if (good.getAge() == null || good.getAge() < 0) {
			throw new IllegalArgumentException(
					"Age must be greater or equal to 0");
		}
		if (good.getProductionDate() == null
				|| !good.getProductionDate().before(new Date())) {
			throw new IllegalArgumentException(
					"Production date must be earlier than or equal to current time");
		}
		return true;
	}
	
	/**
	 * Replace 500 status code with 400 bad request
	 * @param e
	 * @param response
	 * @throws IOException
	 */
	@ExceptionHandler
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value());
	}
}
