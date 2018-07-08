package com.socialnetwork
 
 
 
import org.apache.spark.rdd.RDD
 
import org.apache.spark.mllib.feature.HashingTF
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.classification.{NaiveBayes, NaiveBayesModel}
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.classification.{SVMModel, SVMWithSGD}
import org.apache.spark.mllib.classification.{LogisticRegressionModel, LogisticRegressionWithSGD}
 
object SentimentBayes {
 
  def loadData(): SparkContext = {
    val conf = new SparkConf()
      .setAppName("Sentiment")
      .setMaster("local[*]")
    SparkContext.getOrCreate(conf)
  }
 
  def sentimentBayes(sc: SparkContext, text: String): Int = {
 
    val neg_sentence = sc.textFile("./neg_sentence")
    val pos_sentence = sc.textFile("./pos_sentence")
 
    val features = new HashingTF(1000)
    val hash_neg_sentence = neg_sentence.map(sentence => features.transform(sentence.split(" ")))
    val hash_pos_sentence = pos_sentence.map(sentence => features.transform(sentence.split(" ")))
 
    val pos_data = hash_pos_sentence.map(features => LabeledPoint(1, features))
    val neg_data = hash_neg_sentence.map(features => LabeledPoint(0, features))
    val data = pos_data.union(neg_data)
    data.cache()
    val logistic_Learner = new LogisticRegressionWithSGD()
    val model = logistic_Learner.run(data)
 
    val spam = Array(text)
 
    val spam_split = spam.map(mail => features.transform(mail.split(" ")))
    val prediction = spam_split.map(x=> model.predict(x))
    prediction(0).toInt
    }
  }