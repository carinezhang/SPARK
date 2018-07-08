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

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, ObjectInputStream, ObjectOutputStream}


trait Record[V] {
  def topic: String
 // def key(value: V): Id[V]
 // def timestamp(value: V): Long
}

case class BasicProducer[V]() {
  val kafkaProps = new Properties()
  kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Config.BootstrapServers)

  // This is mandatory, even though we don't send keys
  kafkaProps.put("schema.registry.url", "http://localhost:8081")
  kafkaProps.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
  kafkaProps.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
  //def inner: KafkaAvroSerializer


  //this is our actual connection to Kafka!
    val producer = new KafkaProducer[V, V](kafkaProps)
  // val schemaParser = new Parser

  // def toBinary[V: SchemaFor : ToRecord](event: V): Array[Byte] = {
  //   val baos = new ByteArrayOutputStream()
  //   val output = AvroOutputStream.binary[V](baos)
  //   output.write(event)
  //   output.close()
  //   baos.toByteArray
  // }

  def send(value: V)(implicit record: Record[V]) = {
    //convert value to Avro format and replace "val"
    //implicit val UserFromRecord = FromRecord[V]
    //import com.sksamuel.avro4s.AvroSchema
   // case class Pizza(name: String, ingredients: Seq[Ingredient], vegetarian: Boolean, vegan: Boolean, calories: Int)

   //val schemaFor = SchemaFor[User]
  //val schema = AvroSchema[User]
   // println(schema)

    //val schema = AvroSchema[V]
    //println(schema)
    // Schema schema = ReflectData.get().getSchema(user.getClass());
    //    GenericRecord avroRecord = new GenericData.Record(schema);  


//    val format = RecordFormat[V]
//    val tmp = format.to(value)



    val data = new ProducerRecord[V, V](record.topic, (value))
    println("..................")
    println(data)
    println("..................")
    producer.send(data)

  }

  def close():Unit = producer.close()
}