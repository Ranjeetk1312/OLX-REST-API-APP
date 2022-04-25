package com.olxlogin.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.zensar.exception.InvalidAdvertiseIdException;
import com.zensar.exception.InvalidAuthTokenException;

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
	public AdvertiseDTO createNewAdvertise(AdvertiseDTO advertiseDto, String authToken) {
		if(loginServiceDelegate.isTokenValid(authToken)) {
			//login to persist new advertise into db.
			int categoryId = advertiseDto.getCategoryId();
			int statusId = advertiseDto.getStatusId();
			String category=loginServiceDelegate.getCategoryFromMastedata(categoryId);
			String status=loginServiceDelegate.getStatusFromMastedata(statusId);
			String username=loginServiceDelegate.returnUserName(authToken);
			advertiseDto.setUsername(username);
			advertiseDto.setCategory(category);
			advertiseDto.setStatus(status);
			advertiseDto.setCreatedDate(LocalDate.now());
			advertiseDto.setModifiedDate(LocalDate.now());
			AdvertiseEntity advertiseEntity= convertDtoIntoEntity(advertiseDto);
			advertiseEntity = advertiseRepo.save(advertiseEntity);
			return convertEntityIntoDto(advertiseEntity);
		}
		else {
			throw new InvalidAuthTokenException(authToken);
		}
	}
	@Override
	public AdvertiseDTO updateAdvertise(int advertiseId, AdvertiseDTO updatedAdvertiseDTO,String authToken) {
		if(loginServiceDelegate.isTokenValid(authToken)) {
			//login to persist new advertise into db.
			Optional<AdvertiseEntity> opAdvertiseEntity = advertiseRepo.findById(advertiseId);
			if(opAdvertiseEntity.isPresent()) {
				int categoryId = updatedAdvertiseDTO.getCategoryId();
				int statusId = updatedAdvertiseDTO.getStatusId();
				String category=loginServiceDelegate.getCategoryFromMastedata(categoryId);
				String status=loginServiceDelegate.getStatusFromMastedata(statusId);
				String username=loginServiceDelegate.returnUserName(authToken);
				updatedAdvertiseDTO.setUsername(username);
				updatedAdvertiseDTO.setCategory(category);
				updatedAdvertiseDTO.setStatus(status);
				updatedAdvertiseDTO.setModifiedDate(LocalDate.now());
				AdvertiseEntity advertiseEntity = opAdvertiseEntity.get();
				advertiseEntity.setTitle(updatedAdvertiseDTO.getTitle());
				advertiseEntity.setDescription(updatedAdvertiseDTO.getDescription());
				advertiseEntity.setPrice(updatedAdvertiseDTO.getPrice());
				advertiseEntity.setModifiedDate(updatedAdvertiseDTO.getModifiedDate());
				advertiseEntity.setCategory(updatedAdvertiseDTO.getCategory());
				advertiseEntity.setStatus(updatedAdvertiseDTO.getStatus());
				advertiseEntity.setUsername(updatedAdvertiseDTO.getUsername());
				advertiseEntity=advertiseRepo.save(advertiseEntity);
				return convertEntityIntoDto(advertiseEntity);
			}
			else {
				throw new InvalidAdvertiseIdException(""+advertiseId);
			}
		}
		else {
			throw new InvalidAuthTokenException(authToken);
		}
	}
	@Override
	public List<AdvertiseDTO> returnAllAdvertisementsPostedByLoggedUser(String authToken) {
		if(loginServiceDelegate.isTokenValid(authToken)) {
			//login to persist new advertise into db.
			String username=loginServiceDelegate.returnUserName(authToken);
			List<AdvertiseEntity> advertiseEntityList = advertiseRepo.findByUsername(username);
			List<AdvertiseDTO> advertiseDTOList= new ArrayList<AdvertiseDTO>();
			for(AdvertiseEntity advertiseEntity:advertiseEntityList) {
				AdvertiseDTO advertiseDTO=convertEntityIntoDto( advertiseEntity);
				advertiseDTOList.add(advertiseDTO);
			}
			return advertiseDTOList;
		}
		throw new InvalidAuthTokenException(authToken);
	}
	@Override
	public AdvertiseDTO returnSpecificAdvertisementsPostedByLoggedUser(int advertiseId, String authToken) {
		if(loginServiceDelegate.isTokenValid(authToken)) {
			String username=loginServiceDelegate.returnUserName(authToken);
			Optional<AdvertiseEntity> opAdvertiseEntity = advertiseRepo.findById(advertiseId);
			if(opAdvertiseEntity.isPresent()) {
				AdvertiseEntity advertiseEntity = opAdvertiseEntity.get();
				if(username.equals(advertiseEntity.getUsername())) {
					return convertEntityIntoDto(advertiseEntity);
				}
			}
			throw new InvalidAdvertiseIdException(""+advertiseId);
		}
		throw new InvalidAuthTokenException(authToken);
	}
	@Override
	public boolean deleteSpecificAdvertisementsPostedByLoggedUser(int advertiseId, String authToken) {
		if(loginServiceDelegate.isTokenValid(authToken)) {
			String username=loginServiceDelegate.returnUserName(authToken);
			Optional<AdvertiseEntity> opAdvertiseEntity = advertiseRepo.findById(advertiseId);
			if(opAdvertiseEntity.isPresent()) {
				AdvertiseEntity advertiseEntity = opAdvertiseEntity.get();
				if(username.equals(advertiseEntity.getUsername())) {
					advertiseRepo.deleteById(advertiseId);
					return true;
				}
			}
			throw new InvalidAdvertiseIdException(""+advertiseId);
		}
		throw new InvalidAuthTokenException(authToken);
	}
	
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
	
	predicatePostedBy = criteriaBuilder.like(rootEntity.get("description"), "%" + postedBy + "%");
	predicateFinal = criteriaBuilder.and(predicateSearchText, predicateCategory, predicateDateCondition,predicatePostedBy);
	criteriaQuery.where(predicateFinal);
	criteriaQuery.orderBy(criteriaBuilder.asc(rootEntity.get("title")));
	
	TypedQuery<AdvertiseEntity> typedQuery = entityManager.createQuery(criteriaQuery);
	typedQuery.setFirstResult(startIndex);
	typedQuery.setMaxResults(records);
	List<AdvertiseEntity> advertiseEntityList = typedQuery.getResultList();
	return convertEntityListIntoDTOList(advertiseEntityList);
	}
	@Override
	public List<AdvertiseDTO> searchAdvertisesBySearchText(String searchText) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AdvertiseEntity> criteriaQuery = criteriaBuilder.createQuery(AdvertiseEntity.class);
		Root<AdvertiseEntity> rootEntity = criteriaQuery.from(AdvertiseEntity.class);

		Predicate predicateTitle = criteriaBuilder.and();
		Predicate predicateDescription = criteriaBuilder.and();
		Predicate predicateSearchText = criteriaBuilder.and();
		Predicate predicateUsername = criteriaBuilder.and();

		if(searchText!=null && !"".equalsIgnoreCase(searchText)) {
			predicateTitle = criteriaBuilder.like(rootEntity.get("title"), "%" + searchText + "%");
			predicateDescription = criteriaBuilder.like(rootEntity.get("description"), "%" + searchText + "%");
			predicateUsername = criteriaBuilder.like(rootEntity.get("username"), "%" + searchText + "%");
			predicateSearchText = criteriaBuilder.or(predicateTitle, predicateDescription,predicateUsername);
		}
		criteriaQuery.where(predicateSearchText);
		TypedQuery<AdvertiseEntity> typedQuery = entityManager.createQuery(criteriaQuery);
		List<AdvertiseEntity> advertiseEntityList = typedQuery.getResultList();
		return convertEntityListIntoDTOList(advertiseEntityList);
		
	}
	
	
	@Override
	public AdvertiseDTO returnAdvertiseDetails(int advertiseId, String authToken) {
		if(loginServiceDelegate.isTokenValid(authToken)) {
			Optional<AdvertiseEntity> opAdvertiseEntity = advertiseRepo.findById(advertiseId);
			if(opAdvertiseEntity.isPresent()) {
				AdvertiseEntity advertiseEntity = opAdvertiseEntity.get();
				return convertEntityIntoDto(advertiseEntity);
			}
			throw new InvalidAdvertiseIdException(""+advertiseId);
		}
		throw new InvalidAuthTokenException(authToken);

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

}
