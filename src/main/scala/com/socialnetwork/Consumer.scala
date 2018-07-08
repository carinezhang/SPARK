package com.socialnetwork

import java.net.URI
import java.time.Instant
import java.util.Collections
import java.util.Properties

import scala.collection.JavaConverters._

import com.sksamuel.avro4s._
import java.io._

import org.apache.kafka.clients.consumer.{KafkaConsumer, ConsumerConfig, ConsumerRecords}

case class BasicConsumer[V](implicit record: Record[V]) {

      // val records: ConsumerRecords[String, Array[Byte]] = consumer.poll(1000)
			// records.asScala.foreach(l => 
			// println(AvroInputStream.binary[User](new ByteArrayInputStream(l.value)).iterator.toSeq)
			// )

  val kafkaProps = new Properties()
  kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Config.BootstrapServers)
	kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  kafkaProps.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer")
  kafkaProps.put("group.id", "something")
	kafkaProps.put("schema.registry.url", "http://localhost:2181")

  val consumer = new KafkaConsumer[String, Array[Byte]](kafkaProps)
	def read() {
					println("*************()()()********")
		consumer.subscribe(Collections.singletonList(record.topic))
					println("******()()()()****************")
      val records: ConsumerRecords[String, Array[Byte]] = consumer.poll(1000)
			records.asScala.foreach(l => 
			{
			if (record.topic.equals("users")) println(AvroInputStream.binary[User](new ByteArrayInputStream(l.value)).iterator.toSeq)
			if (record.topic.equals("posts")) println(AvroInputStream.binary[Post](new ByteArrayInputStream(l.value)).iterator.toSeq)
			if (record.topic.equals("comments")) println(AvroInputStream.binary[Comment](new ByteArrayInputStream(l.value)).iterator.toSeq)
			}
			)
	}
  def close() = consumer.close()
}