package com.olxlogin.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/olx/advertise")
@CrossOrigin(origins="*")

public class AdvertiseController {
	
	@Autowired
	AdvertiseService advertiseService;
	//8
	@PostMapping(value="/advertise", consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Create New Advertise",notes="This REST API Create New Advertise")
	public ResponseEntity<AdvertiseDTO> createNewAdvertise(@RequestBody AdvertiseDTO advertiseDto, @RequestHeader("Authorization") String authToken) {
	AdvertiseDTO advertiseDTO = this.advertiseService.createNewAdvertise(advertiseDto, authToken);
	return new ResponseEntity<AdvertiseDTO>(advertiseDTO, HttpStatus.OK);
	}
	//9
	@PutMapping(value = "/advertise/{id}", consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Update Advertise",notes="This REST API Update Advertise")
	public ResponseEntity<AdvertiseDTO> updateAdvertise(@PathVariable("id") int advertiseId, @RequestBody AdvertiseDTO updatedAdvertiseDTO,@RequestHeader("Authorization") String authToken) {
		AdvertiseDTO advertiseDTO = this.advertiseService.updateAdvertise(advertiseId,updatedAdvertiseDTO,authToken);
		return new ResponseEntity<AdvertiseDTO>(advertiseDTO, HttpStatus.OK);
	}
	//10
	@GetMapping(value="/user/advertise",consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Return All Advertisements Poste dBy Logged User",notes="This REST API Return All Advertisements Poste dBy Logged User")
	public List<AdvertiseDTO> returnAllAdvertisementsPostedByLoggedUser(@RequestHeader("Authorization")String authToken) {
		return advertiseService.returnAllAdvertisementsPostedByLoggedUser(authToken);
	}
	//11
	@GetMapping(value="/user/advertise/{id}",consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Return Specific Advertisements Posted By Logged User",notes="This REST API Return Specific Advertisements Posted By Logged User")
	public AdvertiseDTO returnSpecificAdvertisementsPostedByLoggedUser(@PathVariable("id") int advertiseId,@RequestHeader("Authorization")String authToken) {
		return advertiseService.returnSpecificAdvertisementsPostedByLoggedUser(advertiseId,authToken);
	}
	//12
	@DeleteMapping(value="/user/advertise/{id}",consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Delete Specific Advertisements Posted By Logged User",notes="This REST API Delete Specific Advertisements Posted By Logged User")
	public boolean deleteSpecificAdvertisementsPostedByLoggedUser(@PathVariable("id") int advertiseId,@RequestHeader("Authorization")String authToken) {
		return advertiseService.deleteSpecificAdvertisementsPostedByLoggedUser(advertiseId,authToken);
	}

	
	//13
	@GetMapping(value="/search/filtercriteria",consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Search Advertises By Filter Criteria",notes="This REST API Search Advertises By Filter Criteria")
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
	//14
	@GetMapping(value="/advertise/search",consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Search Advertises By Search Text",notes="This REST API Search Advertises By Search Text")
	public List<AdvertiseDTO> searchAdvertisesBySearchText(@RequestParam(name="searchText", required = false)String searchText){
		List<AdvertiseDTO> advertises = advertiseService.searchAdvertisesBySearchText(searchText);
		return advertises;
	}
	//15
	@GetMapping(value="/advertise/{id}",consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Return Advertise Details",notes="This REST API Return Advertise Details")
	public AdvertiseDTO returnAdvertiseDetails(@PathVariable("id") int advertiseId,@RequestHeader("Authorization")String authToken) {
		return advertiseService.returnAdvertiseDetails(advertiseId,authToken);
	}
}
