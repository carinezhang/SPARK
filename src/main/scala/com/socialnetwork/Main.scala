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
def menu() : Unit = {
  println("----")
    println("1- Add data")
    println("2- List all users")
    println("3- List all posts")
    println("4- Save as avro")
    println("5- Exit")
    print("-> ")
    scala.io.StdIn.readLine() match {
      case "Add data" | "1" => addMenu()
      case "List all users" | "2" => printUsers()
      case "List all posts" | "3" => listPosts()
      case "Save as avro" | "4" => addUser()
      case "Exit" | "5" => println("Exit...")
      case _ => println("Unknown Option")
      menu()
    }
}

def listPosts() : Unit = {
    val postConsumer = BasicConsumer[Post]()
  val l  = postConsumer.readAllPosts()
  l.foreach(println)
}

def addMenu() : Unit = {
  println("----")
    println("1- Add a User")
    println("2- Add a Post")
    println("3- Add a Comment")
    println("4- Exit to main menu")
    print("-> ")
     scala.io.StdIn.readLine() match {
      case "Add a User" | "1" => addUser(); addMenu()
      case "Add a Post" | "2" => addPost(); addMenu()
      case "Add a Comment" | "3" => addComment(); addMenu()
      case "Exit to main menu" | "4" => menu()
      case _ => println("Unknown Option") ; addMenu()
    }
}

def addPost() = {
  println("Enter id : ")
  val id = scala.io.StdIn.readLine()
  println("Enter author : ")
  val author = scala.io.StdIn.readLine()
  println("Enter text : ")
  val text = scala.io.StdIn.readLine()
  println("Enter image uri : ")
  val uri = scala.io.StdIn.readLine()
  val p = Post(id,author,text, uri, false)
  val postProducer = BasicProducer[User]()
  postProducer.send(p)
  menu()
}
def addComment() = {}

def addUser() = {
  println("Enter id : ")
  val id = scala.io.StdIn.readLine()
  println("Enter image uri : ")
  val uri = scala.io.StdIn.readLine()
  println("Enter username : ")
  val username = scala.io.StdIn.readLine()
  val u = User(id,uri,username, true, false)
  val userProducer = BasicProducer[User]()
  userProducer.send(u)
  menu()
}


def printUsers() = {
   val userConsumer = BasicConsumer[User]()
  userConsumer.read()
  userConsumer.close()
  menu()
}

menu()
 /* val userProducer = BasicProducer[User]()

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
  
  postConsumer.close()*/
}