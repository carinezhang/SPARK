#!/bin/bash
wget http://apache.crihan.fr/dist/kafka/1.1.0/kafka_2.11-1.1.0.tgz
tar -xf "kafka_2.11-1.1.0.tgz"
cd kafka_2.11-1.1.0
bin/zookeeper-server-start.sh config/zookeeper.properties &
bin/kafka-server-start.sh config/server.properties &
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic users
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic posts
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic comments



