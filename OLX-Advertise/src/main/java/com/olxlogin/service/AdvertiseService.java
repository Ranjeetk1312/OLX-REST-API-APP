package com.olxlogin.service;

import java.time.LocalDate;
import java.util.List;


import com.olxlogin.dto.AdvertiseDTO;

public interface AdvertiseService {
	public List<AdvertiseDTO> searchAdvertisesByFilterCriteria(String searchText,
			int categoryId, String postedBy, String dateCondition,
			LocalDate onDate, LocalDate fromDate, LocalDate toDate,
			String sortedBy, int startIndex, int records);
			
	public AdvertiseDTO createNewAdvertise(AdvertiseDTO advertiseDto,String authToken);
	public AdvertiseDTO updateAdvertise(int advertiseId,AdvertiseDTO updatedAdvertiseDTO);


}
