package com.df.data.ingestion.consumer;

import java.io.IOException;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class DefaultKafkaListener {
	
	public static final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultKafkaListener.class);
	
	@Autowired
	ClientRequestProcessor clientRequestProcessor;
	
	@Autowired
	KafkaConsumerConfig kafkaConsumerConfig;
	
	@Value("${my.kafka.topics}.split(',')")
	private List<String> topicsList;
	
	//@KafkaListener(topics = "kafkaTopic1", groupId = "kafkaGroupId123")
	//@KafkaListener(topics = "#{'${my.kafka.topics}'.split(',')}", groupId = "kafkaGroupId123")
	@KafkaListener(topics = "#{kafkaConsumerConfig.getAllKafkaTopics()}", groupId = "kafkaGroupId123")
	public void consumer1(@Payload String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topicName) throws IOException {
	logger.info("Message from topic - " + message);
	logger.debug("Topic name - {} and message is {}", topicName, message);
	clientRequestProcessor.process(topicName, message);
	}
}
