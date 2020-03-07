package com.df.data.ingestion.persistence;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clientConsumerMapping")
public class ClientConsumerMapping implements Serializable{

	private static final long serialVersionUID = 9089325131983048990L;
	
	@Id
	private ObjectId _id;
	
	private String client;
	
	private String processor;
	
	private String postprocessing;
	
	public ClientConsumerMapping() {
		
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public String getPostprocessing() {
		return postprocessing;
	}

	public void setPostProcessing(String postprocessing) {
		this.postprocessing = postprocessing;
	}

}
