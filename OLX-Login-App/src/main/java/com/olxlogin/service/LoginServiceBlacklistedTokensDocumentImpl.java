package com.olxlogin.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.olxlogin.dto.User;
import com.olxlogin.entity.BlacklistedTokensDocument;
import com.olxlogin.repository.BlacklistedTokensDocumentRepo;


@Service(value="MONGO_SERVICE")
//@Primary
public class LoginServiceBlacklistedTokensDocumentImpl implements LoginService{
	
	@Autowired
	BlacklistedTokensDocumentRepo blacklistedTokensDocumentRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public String authenticate(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean logout(String authToken) {
		BlacklistedTokensDocument blacklistedTokensDocument= blacklistedTokensDocumentRepo.findByToken(authToken);
		if(blacklistedTokensDocument!=null) {
			return false;
		}

		BlacklistedTokensDocument blacklistedTokensDocument2=new BlacklistedTokensDocument(authToken,LocalDate.now());
		blacklistedTokensDocument = blacklistedTokensDocumentRepo.save(blacklistedTokensDocument2);
		return true;
	}

	@Override
	public User registers(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> returnUserInfo(String authToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateToken(String authToken) {
		// TODO Auto-generated method stub
		return false;
	}
	/*
	private BlacklistedTokensDocument convertDtoIntoEntity(Stock stock) {
		TypeMap<Stock,BlacklistedTokensDocument> typeMap = 
				modelMapper.typeMap(Stock.class,BlacklistedTokensDocument.class);
		typeMap.addMappings(mapper->{
			mapper.map(stockDto->stockDto.getToken(), BlacklistedTokensDocument::setToken);});
		BlacklistedTokensDocument blacklistedTokensDocument = modelMapper.map(stock,BlacklistedTokensDocument.class);
		return blacklistedTokensDocument;
	}
	private Stock convertEntityIntoDto(StockDocument stockDocument) {
		TypeMap<StockDocument,Stock> typeMap = 
				modelMapper.typeMap(StockDocument.class,Stock.class);
		typeMap.addMappings(mapper->{
			mapper.map(StockDocument->StockDocument.getMarketName(), Stock::setMarket);});
		Stock stock = modelMapper.map(stockDocument,Stock.class);
		return stock;
	}
	*/
	

}
