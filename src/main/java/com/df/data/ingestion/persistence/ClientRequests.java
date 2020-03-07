package com.df.data.ingestion.persistence;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class ClientRequests implements Serializable{
	
	private static final long serialVersionUID = -4653927022755941252L;

	@Id
	private ObjectId _id;
	
	private String clientName;
	
	private String jsonMessage;
	
	public ClientRequests() {
		
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getJsonMessage() {
		return jsonMessage;
	}

	public void setJsonMessage(String jsonMessage) {
		this.jsonMessage = jsonMessage;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

}
