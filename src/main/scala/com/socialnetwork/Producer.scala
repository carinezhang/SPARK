package com.socialnetwork

import java.net.URI
import java.time.Instant
import java.util.Properties

//import org.apache.avro.Schema.Parser
//import org.apache.avro.generic.GenericData
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}


import com.sksamuel.avro4s.{FromRecord, RecordFormat, ToRecord}
import io.confluent.kafka.serializers.KafkaAvroSerializer
import org.apache.avro.generic.GenericRecord

import com.sksamuel.avro4s._
import org.apache.avro.Schema.Field
import org.apache.avro.{JsonProperties, LogicalTypes, Schema, SchemaBuilder}

import java.io._

import org.apache.kafka.clients.producer.{ProducerConfig}

trait Record[V] {
  def topic: String
 // def key(value: V): Id[V]
 // def timestamp(value: V): Long
}

case class BasicProducer[V]() {
  val kafkaProps = new Properties()
  kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Config.BootstrapServers)

  // This is mandatory, even though we don't send keys
  kafkaProps.put("schema.registry.url", "http://localhost:2181")
  kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer")
  //def inner: KafkaAvroSerializer


  //this is our actual connection to Kafka!
    val producer = new KafkaProducer[V, Array[Byte]](kafkaProps)
  // val schemaParser = new Parser

  // def toBinary[V: SchemaFor : ToRecord](event: V): Array[Byte] = {
  //   val baos = new ByteArrayOutputStream()
  //   val output = AvroOutputStream.binary[V](baos)
  //   output.write(event)
  //   output.close()
  //   baos.toByteArray
  // }

  // val os = AvroOutputStream.data[User](new File("/tmp/carine.avro"))
   // os.write(value)
   // os.flush()
   // os.close()
 

  def send(value: User)(implicit record: Record[User]) = {
    implicit val schemaFor = SchemaFor[User]
    val schema = AvroSchema[User]
    val baos = new ByteArrayOutputStream()
    val output = AvroOutputStream.binary[User](baos)
    output.write(value)
    output.close()
    val data = new ProducerRecord[V, Array[Byte]](record.topic, baos.toByteArray)
    println("..................")
    println(data)
    println("..................")
    producer.send(data)
  }


  def send(value: Post)(implicit record: Record[Post]) = {
    implicit val schemaFor = SchemaFor[Post]
    val schema = AvroSchema[Post]
    val baos = new ByteArrayOutputStream()
    val output = AvroOutputStream.binary[Post](baos)
    output.write(value)
    output.close()
    val data = new ProducerRecord[V, Array[Byte]](record.topic, baos.toByteArray)
    println(".........().........")
    println(data)
    println("..........()........")
    producer.send(data)
  }

def send(value: Comment)(implicit record: Record[Comment]) = {
    implicit val schemaFor = SchemaFor[Comment]
    val schema = AvroSchema[Comment]
    val baos = new ByteArrayOutputStream()
    val output = AvroOutputStream.binary[Comment](baos)
    output.write(value)
    output.close()
    val data = new ProducerRecord[V, Array[Byte]](record.topic, baos.toByteArray)
    println(".........().........")
    println(data)
    println("..........()........")
    producer.send(data)
  }

  def close():Unit = producer.close()
}