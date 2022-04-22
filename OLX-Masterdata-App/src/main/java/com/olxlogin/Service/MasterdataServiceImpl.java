package com.olxlogin.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olxlogin.dto.MasterdataStatus;
import com.olxlogin.dto.MasterdataCategory;
import com.olxlogin.entity.MasterdataStatusEntity;
import com.olxlogin.entity.MasterdataCategoryEntity;
import com.olxlogin.repository.MasterdataStatusRepo;
import com.olxlogin.repository.MasterdataCategoryRepo;

@Service
public class MasterdataServiceImpl implements MasterdataService {
	
	@Autowired
	MasterdataCategoryRepo masterdataCategoryRepo;
	@Autowired
	MasterdataStatusRepo masterdataStatusRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<MasterdataCategory> allMasterdataCategory() {
		List<MasterdataCategoryEntity> advertisementCategoryEntityList = masterdataCategoryRepo.findAll();
		List<MasterdataCategory> advertisementCategoryDtoList= new ArrayList<MasterdataCategory>();
		for(MasterdataCategoryEntity advertisementCategoryEntity:advertisementCategoryEntityList) {
			MasterdataCategory advertisementCategory=convertEntityIntoDto(advertisementCategoryEntity);
			advertisementCategoryDtoList.add(advertisementCategory);
		}
		return advertisementCategoryDtoList;
	}

	@Override
	public List<MasterdataStatus> allMasterdataStatus() {
		List<MasterdataStatusEntity> advertiseStatusEntityList = masterdataStatusRepo.findAll();
		List<MasterdataStatus> masterdataStatusDtoList= new ArrayList<MasterdataStatus>();
		for(MasterdataStatusEntity masterdataStatusEntity:advertiseStatusEntityList) {
			MasterdataStatus masterdataStatus=convertEntityIntoDto2(masterdataStatusEntity);
			masterdataStatusDtoList.add(masterdataStatus);
		}
		return masterdataStatusDtoList;
	}
	/*
	private AdvertisementCategoryEntity convertDtoIntoEntity(AdvertisementCategory advertisementCategory) {
		TypeMap<AdvertisementCategory,AdvertisementCategoryEntity> typeMap = 
				modelMapper.typeMap(AdvertisementCategory.class,AdvertisementCategoryEntity.class);
		typeMap.addMappings(mapper->{
			mapper.map(advertisementCategoryDto->advertisementCategoryDto.getId(), AdvertisementCategoryEntity::setId);});
		AdvertisementCategoryEntity advertisementCategoryEntity = modelMapper.map(advertisementCategory,AdvertisementCategoryEntity.class);
		return advertisementCategoryEntity;
	}
	*/
	private MasterdataCategory convertEntityIntoDto(MasterdataCategoryEntity masterdataCategoryEntity) {
		TypeMap<MasterdataCategoryEntity,MasterdataCategory> typeMap = 
				modelMapper.typeMap(MasterdataCategoryEntity.class,MasterdataCategory.class);
		typeMap.addMappings(mapper->{
			mapper.map(masterdataEntit->masterdataEntit.getId(), MasterdataCategory::setId);});
		MasterdataCategory masterdataCategory = modelMapper.map(masterdataCategoryEntity,MasterdataCategory.class);
		return masterdataCategory;
	}
	private MasterdataStatus convertEntityIntoDto2(MasterdataStatusEntity masterdataStatusEntity) {
		TypeMap<MasterdataStatusEntity,MasterdataStatus> typeMap = 
				modelMapper.typeMap(MasterdataStatusEntity.class,MasterdataStatus.class);
		typeMap.addMappings(mapper->{
			mapper.map(masterdataEntit->masterdataEntit.getId(), MasterdataStatus::setId);});
		MasterdataStatus masterdataStatus = modelMapper.map(masterdataStatusEntity,MasterdataStatus.class);
		return masterdataStatus;
	}


}
