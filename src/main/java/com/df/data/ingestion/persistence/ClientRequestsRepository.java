package com.df.data.ingestion.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRequestsRepository extends MongoRepository<ClientRequests, String> {

}
