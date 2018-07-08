package com.socialnetwork

import java.time.Instant
import java.net.URI
import java.util.Properties

import org.apache.kafka.clients.producer.{ProducerConfig}

import com.sksamuel.avro4s._

import java.io._ //{ByteArrayInputStream, ByteArrayOutputStream, ObjectInputStream, ObjectOutputStream}



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

  val user = User("user8", "CONSUMER", "wocaoni.cn", false, false)
  val user2 = User("user1", "CONSUMER", "wocaoni.cn", false, false)
  val post1 = Post("4", "String", "Et", "Un", false)

  val schemaFor = SchemaFor[User]
  val schema = AvroSchema[User]

val os = AvroOutputStream.data[User](new File("/tmp/user.avro"))
os.write(user)
os.flush()
os.close()

val is = AvroInputStream.data[User](new File("/tmp/user.avro"))
val deserialize = is.iterator.toSet
is.close()
println(deserialize.mkString("\n"))

  println("----------------")
  println(user)
  println("----------------")

  userProducer.send(user)
  userProducer.send(user2)
  userProducer.send(post1)
 println("a----------------")
  userProducer.close()
 println("b----------------")

  val userConsumer = BasicConsumer[User]()
  userConsumer.read()
   println("c----------------")
   println("d----------------")
  userConsumer.close()
  
  val postConsumer = BasicConsumer[Post]()
  postConsumer.read()
  
  postConsumer.close()
}