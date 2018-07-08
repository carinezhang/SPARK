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

def ntm(t: Array[Byte]) = {
			val in = new ByteArrayInputStream(t)
			val input = AvroInputStream.binary[User](in)
			input.iterator.toSeq
}
  val kafkaProps = new Properties()
  kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Config.BootstrapServers)
	kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  kafkaProps.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer")
  kafkaProps.put("group.id", "something")
	  kafkaProps.put("schema.registry.url", "http://localhost:2181")


  val consumer = new KafkaConsumer[String, Array[Byte]](kafkaProps)

	// consumer.assign(tps);
	// consumer.seekToBeginning(tps);
	def read() {
					println("**********************")

		consumer.subscribe(Collections.singletonList(record.topic))
					println("**********************")

      val records: ConsumerRecords[String, Array[Byte]] = consumer.poll(1000)
      records.asScala.foreach(record => println(ntm(record.value)))
	}

    // implicit class StreamsBuilderSOps(streamsBuilder: StreamsBuilderS) {
  //   def streamFromRecord[V] = new StreamBuilder[V]

  //   class StreamBuilder[V] {
  //     def apply[K]()(implicit record: Record[K, V], consumed: Consumed[K, V]): KStreamS[K, V] =
  //       streamsBuilder.stream[K, V](record.topic)
  //   }
  // }

  // implicit class KStreamSOps[K, V](stream: KStreamS[K, V]) {
  //   def toTopic(implicit record: Record[K, V], produced: Produced[K, V]) = stream.to(record.topic)
  // }

  def close() = consumer.close()
}