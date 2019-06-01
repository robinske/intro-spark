//package me.krobinson.swapispark

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.apache.spark.sql.functions._

/**
  * More detailed Setup instructions can be found in the README
  * To use this example, open the SBT console:
  *
  *   $ ./sbt.sh console // Mac/Linux
  *   > sbt.bat console // Windows
  *
  * and type:
  *
  *   scala> Example.wordCount.show()
  *
  */
object SparkSessionSingleton {

  // lazy initializer
  var instance: SparkSession = _

  def getInstance(): SparkSession = {
    if (instance == null) {
      instance = SparkSession
        .builder()
        .master("local") // have to set a local master
        .appName("Intro Spark Example App")
        .getOrCreate()
    }
    instance
  }
}

object Example {

  val spark: SparkSession =
    SparkSessionSingleton.getInstance()

  import spark.sqlContext.implicits._

  val films: DataFrame = spark
    .read
    .json("src/main/resources/swapi/films.json")

  val openingCrawls: Dataset[String] =
    films.select($"opening_crawl").as[String]

  val words: Dataset[String] =
    openingCrawls
      .flatMap { crawl =>
        crawl.split("\\s+")
      }
      .map { word =>
        word.toLowerCase.filter { char =>
          char.isLetter
        }
      }

  val stopWords = spark
    .read
    .text("src/main/resources/stop-words.txt")
    .as[String]
    .flatMap(line => line.split(","))

  val joined: Dataset[String] = words.join(
    stopWords,
    words("value") === stopWords("value"),
    "left_anti"
  ).select(words("value")).as[String]

  val wordsToCount: DataFrame = joined
    .map { word => (word, 1) }
    .withColumnRenamed("_1", "word")
    .withColumnRenamed("_2", "count")

  val wordCount: DataFrame =
    wordsToCount
      .groupBy($"word")
      .count()
      .orderBy(desc("count"))

  def main(args: Array[String]): Unit = {
    Example.wordCount.show()
    spark.stop()
  }

}

// at the end of the file so object contents are reloaded on
// :load src/main/scala/Example.scala from the console
import Example._
