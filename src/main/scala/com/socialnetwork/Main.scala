package com.socialnetwork

import java.time.Instant
import java.net.URI
import java.util.Properties

import org.apache.kafka.clients.producer.{ProducerConfig}

import com.sksamuel.avro4s._

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, ObjectInputStream, ObjectOutputStream}



object Config {
  val BootstrapServers = "localhost:9092"
}

object Main extends App {
		//config.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "stream-starter-project")
		// config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092")
	//	config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
  //  config.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
		//config.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
		//config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass())
		//config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass())

  val userProducer = BasicProducer[User]()

  val user = User("user0", "Test", "wocaoni.cn", false, false)

  val schemaFor = SchemaFor[User]
  val schema = AvroSchema[User]
  val baos = new ByteArrayOutputStream()
val output = AvroOutputStream.binary[User](baos)
output.write(user)
output.close()
val bytes = baos.toByteArray

val in = new ByteArrayInputStream(bytes)
val input = AvroInputStream.binary[User](in)
val result = input.iterator.toSeq
println(result(0))

  println("----------------")
  println(user)
  println("----------------")

  userProducer.send(user)

  userProducer.close()


  val userConsumer = BasicConsumer[User]()
  userConsumer.read()
  userConsumer.close()
}