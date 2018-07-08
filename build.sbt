name := "SPARK"

version := "1.0"

scalaVersion := "2.11.8"

resolvers ++= Seq(
  Classpaths.typesafeReleases,
  "confluent" at "http://packages.confluent.io/maven/"
)


libraryDependencies ++= Seq(
    "org.apache.kafka" % "kafka-streams" % "0.10.0.0",
    "org.apache.spark" %% "spark-core" % "2.0.0",
    "com.sksamuel.avro4s" %% "avro4s-core" % "1.8.3",
    "io.confluent" % "kafka-avro-serializer" % "3.2.1",
    "org.apache.avro" % "avro" % "1.8.2"


)
