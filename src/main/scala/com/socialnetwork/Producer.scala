package com.socialnetwork

import java.net.URI
import java.time.Instant
import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

trait Record[V] {
  def topic: String
  def key(value: V): Id[V]
  def timestamp(value: V): Long
}

case class BasicProducer[V]() {
  val kafkaProps = new Properties()
  kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Config.BootstrapServers)

  // This is mandatory, even though we don't send keys
  kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  //this is our actual connection to Kafka!
  private val producer = new KafkaProducer[String, String](kafkaProps)

  def send(value: V)(implicit record: Record[V]) = {
   //convert value to Avro format and replace "val"
    val data = new ProducerRecord[String, String](record.topic, "val")
    producer.send(data)

  }

  def close():Unit = producer.close()
}