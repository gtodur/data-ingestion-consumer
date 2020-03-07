package com.df.data.ingestion.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
	
	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> consumerConfig = new HashMap<>();
		consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "kafkaGroupId123");
		consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		
		return new DefaultKafkaConsumerFactory<String, String>(consumerConfig);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	
//	@Bean
//	public ConsumerFactory<String, User> userConsumerFactory() {
//		Map<String, Object> userConsumerConfig = new HashMap<>();
//		userConsumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		userConsumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "kafkaJsonGroupId456");
//		userConsumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		userConsumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//		
//		return new DefaultKafkaConsumerFactory<String, User>(userConsumerConfig, new StringDeserializer(), new JsonDeserializer<>(User.class));
//	}
//	
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, User> userKafkaListenerContainerFactory() {
//		ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
//		factory.setConsumerFactory(userConsumerFactory());
//		return factory;
//	}
	
	@PostConstruct
	public static String[] getAllKafkaTopics() {
		System.out.println("from postContruct method of DefaultKafkaListener");
		List<String> topics = new ArrayList<>();
		try {
			ZooKeeper zk = new ZooKeeper("localhost:2181", 10000, null);
			topics = zk.getChildren("/brokers/topics", false);
			topics.remove("__consumer_offsets");
			topics.remove("topics");
			System.out.print("Topics obtained from kafka cluster : ");
			topics.stream().forEach(topic -> System.out.println(topic));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return topics.stream().toArray(String[]::new);
	}
	
}
