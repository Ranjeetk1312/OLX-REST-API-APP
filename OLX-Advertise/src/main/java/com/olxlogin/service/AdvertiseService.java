package com.olxlogin.service;

import java.time.LocalDate;
import java.util.List;


import com.olxlogin.dto.AdvertiseDTO;

public interface AdvertiseService {
	public AdvertiseDTO createNewAdvertise(AdvertiseDTO advertiseDto,String authToken);
	public AdvertiseDTO updateAdvertise(int advertiseId,AdvertiseDTO updatedAdvertiseDTO,String authToken);
	public List<AdvertiseDTO>returnAllAdvertisementsPostedByLoggedUser(String authToken);
	public AdvertiseDTO returnSpecificAdvertisementsPostedByLoggedUser(int advertiseId,String authToken);
	public boolean deleteSpecificAdvertisementsPostedByLoggedUser(int advertiseId,String authToken);
	public List<AdvertiseDTO> searchAdvertisesByFilterCriteria(String searchText,
			int categoryId, String postedBy, String dateCondition,
			LocalDate onDate, LocalDate fromDate, LocalDate toDate,
			String sortedBy, int startIndex, int records);
	public List<AdvertiseDTO> searchAdvertisesBySearchText(String searchText);
	public AdvertiseDTO returnAdvertiseDetails(int advertiseId,String authToken);



}
