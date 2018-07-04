package com.socialnetwork

import java.time.Instant
import java.net.URI
import java.util.Properties

import org.apache.kafka.clients.producer.{ProducerConfig}

object Config {
  val BootstrapServers = "localhost:9092"
}

object Main extends App {
  val config = new Properties()
  config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Config.BootstrapServers)

		//config.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "stream-starter-project")
		// config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092")
	//	config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
  //  config.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
		//config.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
		//config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass())
		//config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass())
  
 val userProducer = BasicProducer[User]()

  val user = User(
    Id[User]("user0"),
    Instant.now(),
    URI.create("https://some-uri"),
    "Test",
    verified = false,
    deleted = false
  )
  println("----------------")
  println(user)
  println("----------------")

  userProducer.send(user)

  userProducer.close()
	}