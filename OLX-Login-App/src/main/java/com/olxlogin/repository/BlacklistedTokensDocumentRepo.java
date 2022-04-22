package com.olxlogin.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.olxlogin.entity.BlacklistedTokensDocument;


public interface BlacklistedTokensDocumentRepo extends MongoRepository<BlacklistedTokensDocument, Integer> {
	
	BlacklistedTokensDocument findByToken(String authToken);

}
