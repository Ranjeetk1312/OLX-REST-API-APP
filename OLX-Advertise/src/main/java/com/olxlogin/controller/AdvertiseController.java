package com.olxlogin.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olxlogin.dto.AdvertiseDTO;
import com.olxlogin.service.AdvertiseService;


@RestController
@RequestMapping("/olx/advertise")
@CrossOrigin(origins="*")

public class AdvertiseController {
	
	@Autowired
	AdvertiseService advertiseService;
	//8
	@PostMapping(value="/advertise", consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<AdvertiseDTO> createNewAdvertise(@RequestBody AdvertiseDTO advertiseDto, @RequestHeader("Authorization") String authToken) {
	AdvertiseDTO advertiseDTO = this.advertiseService.createNewAdvertise(advertiseDto, authToken);
	return new ResponseEntity<AdvertiseDTO>(advertiseDTO, HttpStatus.OK);
	}
	@PutMapping(value = "/advertise/{id}", consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<AdvertiseDTO> updateAdvertise(@PathVariable("id") int advertiseId, @RequestBody AdvertiseDTO updatedAdvertiseDTO) {
		AdvertiseDTO advertiseDTO = this.advertiseService.updateAdvertise(advertiseId,updatedAdvertiseDTO);
		return new ResponseEntity<AdvertiseDTO>(advertiseDTO, HttpStatus.OK);
	}
	
	//13
	@GetMapping(value="/search/filtercriteria",consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public List<AdvertiseDTO> searchAdvertisesByFilterCriteria(@RequestParam(name="searchText", required = false)String searchText,
	@RequestParam(name = "category", required = false, defaultValue = "0")int categoryId, @RequestParam(name="postedBy", required=false)String postedBy,
	@RequestParam(name="dateCondition", required=false)String dateCondition,
	@RequestParam(name="onDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate onDate,
	@RequestParam(name="fromDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
	@RequestParam(name="toDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
	@RequestParam(name="sortedBy", required=false)String sortedBy, @RequestParam(name = "startIndex", defaultValue="0")int startIndex, @RequestParam(name="records", defaultValue = "10")int records
	) {
	List<AdvertiseDTO> advertises = advertiseService.searchAdvertisesByFilterCriteria(searchText, categoryId, postedBy, dateCondition,
	onDate, fromDate, toDate, sortedBy, startIndex, records);
	return advertises;
	}
	
}
