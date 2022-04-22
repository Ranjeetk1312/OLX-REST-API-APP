package com.olxlogin.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olxlogin.dto.AdvertiseDTO;
import com.olxlogin.entity.AdvertiseEntity;
import com.olxlogin.repository.AdvertiseRepo;

@Service(value="ADVERTISE")
public class AdvertiseServiceImpl implements AdvertiseService {
	
	@Autowired
	AdvertiseRepo advertiseRepo;
	@Autowired
	LoginServiceDelegate loginServiceDelegate;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	EntityManager entityManager;//It is From JPA

	@Override
	public List<AdvertiseDTO> searchAdvertisesByFilterCriteria(String searchText, int categoryId, String postedBy,
	String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate,
	String sortedBy, int startIndex, int records) {

	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	CriteriaQuery<AdvertiseEntity> criteriaQuery = criteriaBuilder.createQuery(AdvertiseEntity.class);
	Root<AdvertiseEntity> rootEntity = criteriaQuery.from(AdvertiseEntity.class);

	Predicate predicateTitle = criteriaBuilder.and();
	Predicate predicateDescription = criteriaBuilder.and();
	Predicate predicateSearchText = criteriaBuilder.and();
	Predicate predicateCategory = criteriaBuilder.and();
	Predicate predicateDateCondition = criteriaBuilder.and();
	Predicate predicatePostedBy = criteriaBuilder.and();
	Predicate predicateFinal = criteriaBuilder.and();

	if(searchText!=null && !"".equalsIgnoreCase(searchText)) {
	predicateTitle = criteriaBuilder.like(rootEntity.get("title"), "%" + searchText + "%");
	predicateDescription = criteriaBuilder.like(rootEntity.get("description"), "%" + searchText + "%");
	predicateSearchText = criteriaBuilder.or(predicateTitle, predicateDescription);
	}
	//Write a code to create predicates for dateConditions, categoryId, posted_by etc.
	predicateFinal = criteriaBuilder.and(predicateSearchText, predicateCategory, predicateDateCondition,
	predicatePostedBy);
	criteriaQuery.where(predicateFinal);
	TypedQuery<AdvertiseEntity> typedQuery = entityManager.createQuery(criteriaQuery);
	typedQuery.setFirstResult(startIndex);
	typedQuery.setMaxResults(records);
	List<AdvertiseEntity> advertiseEntityList = typedQuery.getResultList();
	return convertEntityListIntoDTOList(advertiseEntityList);
	}
	
	@Override
	public AdvertiseDTO createNewAdvertise(AdvertiseDTO advertiseDto, String authToken) {
		if(loginServiceDelegate.isTokenValid(authToken)) {
			//login to persist new advertise into db.
			AdvertiseEntity advertiseEntity= convertDtoIntoEntity(advertiseDto);
			advertiseEntity = advertiseRepo.save(advertiseEntity);
			return convertEntityIntoDto(advertiseEntity);
		}
		else {
			//throw new InvalidTokenException(authToken);
		return null;
		}
	}
	private List<AdvertiseDTO> convertEntityListIntoDTOList(List<AdvertiseEntity> advertiseEntityList) {
		
		TypeMap<AdvertiseEntity,AdvertiseDTO> typeMap = 
				modelMapper.typeMap(AdvertiseEntity.class,AdvertiseDTO.class);
		typeMap.addMappings(mapper->{
			mapper.map(AdvertiseEntity->AdvertiseEntity.getTitle(), AdvertiseDTO::setTitle);});
		List<AdvertiseDTO> advertiseDTOList= new ArrayList<AdvertiseDTO>();
		for(AdvertiseEntity advertiseEntity: advertiseEntityList) {
			AdvertiseDTO advertiseDTO = modelMapper.map(advertiseEntity,AdvertiseDTO.class);
			advertiseDTOList.add(advertiseDTO);
		}
		return advertiseDTOList;
	}
	private AdvertiseEntity convertDtoIntoEntity(AdvertiseDTO advertiseDTO) {
		TypeMap<AdvertiseDTO,AdvertiseEntity> typeMap = 
				modelMapper.typeMap(AdvertiseDTO.class,AdvertiseEntity.class);
		typeMap.addMappings(mapper->{
			mapper.map(AdvertiseDTO->AdvertiseDTO.getTitle(), AdvertiseEntity::setTitle);});
		AdvertiseEntity advertiseEntity = modelMapper.map(advertiseDTO,AdvertiseEntity.class);
		return advertiseEntity;
	}
	private AdvertiseDTO convertEntityIntoDto(AdvertiseEntity advertiseEntity) {
		TypeMap<AdvertiseEntity,AdvertiseDTO> typeMap = 
				modelMapper.typeMap(AdvertiseEntity.class,AdvertiseDTO.class);
		typeMap.addMappings(mapper->{
			mapper.map(AdvertiseEntity->AdvertiseEntity.getTitle(), AdvertiseDTO::setTitle);});
		AdvertiseDTO advertiseDTO = modelMapper.map(advertiseEntity,AdvertiseDTO.class);
		return advertiseDTO;
	}

	@Override
	public AdvertiseDTO updateAdvertise(int advertiseId, AdvertiseDTO updatedAdvertiseDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
