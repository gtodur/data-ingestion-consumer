package com.df.data.ingestion.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientConsumerMappingRepository extends MongoRepository<ClientConsumerMapping, String>{
	
	@Query("{'client':?0}")
	ClientConsumerMapping findByClientName(String clientName);
}
