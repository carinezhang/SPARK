package com.socialnetwork

import java.net.URI
import java.time.Instant
import java.util.Collections
import java.util.Properties

import scala.collection.JavaConverters._

import org.apache.kafka.clients.consumer.{KafkaConsumer, ConsumerConfig, ConsumerRecord}

case class BasicConsumer[V](implicit record: Record[V]) {
  val kafkaProps = new Properties()
  kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Config.BootstrapServers)
  kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  kafkaProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  kafkaProps.put("group.id", "something")

  val consumer = new KafkaConsumer[String, String](kafkaProps)

	// consumer.assign(tps);
	// consumer.seekToBeginning(tps);
	def read() {
		consumer.subscribe(Collections.singletonList(record.topic))
		//while(true){
			val records=consumer.poll(100)
			
			println("**********************")
			println(records)
			println("**********************")
			for (record<-records.asScala){
				println(record)
			}
			println("**********************")
		//}
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