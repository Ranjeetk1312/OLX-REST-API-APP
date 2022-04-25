package com.olxlogin.actuator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="bug-fixes")
public class BugFixesCustomActuator {
	private Map<String, List<String>> bugFixesByVersionMap = new HashMap<>();
	@PostConstruct
	public void init() {
		bugFixesByVersionMap.put("v1", Arrays.asList("Invalid status issue", "Application closing not working"));
		bugFixesByVersionMap.put("v2", Arrays.asList("Window size change not working", "Window minimizing not working"));
	}
	@DeleteOperation
	public void deleteBugFixes(@Selector String id) {
		bugFixesByVersionMap.remove(id);
	}
	
	@WriteOperation
	public void addBugFixes(@Selector String id, String bugs) {
		bugFixesByVersionMap.put(id, Arrays.asList(bugs.split(",")));
	}
	
	@ReadOperation
	public List<String> getBugFixesByVersion(@Selector String id) {
		return this.bugFixesByVersionMap.get(id);
	}

	@ReadOperation
	public Map<String, List<String>> getAllBugFixes() {
	return this.bugFixesByVersionMap;
	}
}
