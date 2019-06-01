// spark doesn't yet support scala 2.12
scalaVersion := "2.11.8"

initialCommands in console := """
      | import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
      | import org.apache.spark.sql.functions._
      |
      | import me.krobinson.swapispark.Example
      | import me.krobinson.swapispark.Example._
      |
    """.trim.stripMargin

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core"  % "2.2.0",
  "org.apache.spark" %% "spark-sql"   % "2.2.0"
)
