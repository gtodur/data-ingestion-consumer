package com.df.data.ingestion.consumer;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClientRequestProcessor {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public void process(String collectionName, String jsonMessage) {
		Document document = Document.parse(jsonMessage);
		mongoTemplate.insert(document, collectionName);
	}
	
}
