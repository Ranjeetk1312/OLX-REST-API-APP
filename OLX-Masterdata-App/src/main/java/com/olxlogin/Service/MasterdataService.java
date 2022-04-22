package com.olxlogin.Service;

import java.util.List;

import com.olxlogin.dto.MasterdataStatus;
import com.olxlogin.dto.MasterdataCategory;

public interface MasterdataService {
	public List<MasterdataCategory> allMasterdataCategory();
	public List<MasterdataStatus> allMasterdataStatus();

}
