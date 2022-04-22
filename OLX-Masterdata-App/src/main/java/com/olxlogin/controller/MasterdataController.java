package com.olxlogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olxlogin.Service.MasterdataService;
import com.olxlogin.dto.MasterdataStatus;
import com.olxlogin.dto.MasterdataCategory;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/olx/masterdata")
@CrossOrigin(origins="*")

public class MasterdataController {
	
	@Autowired
	MasterdataService masterdataService;
	
	@GetMapping(value="/advertise/category", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Masterdata Category",notes="This REST API Masterdata Category")
	public List<MasterdataCategory> allMasterdataCategory() {
		return masterdataService.allMasterdataCategory();
	}
	@GetMapping(value="/advertise/status", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Masterdata Status",notes="This REST API Masterdata Status")
	public List<MasterdataStatus> allMasterdataStatus() {
		return masterdataService.allMasterdataStatus();
	}
	
}
