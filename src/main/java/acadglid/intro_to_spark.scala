package acadglid

import org.apache.spark.sql.SparkSession

object intro_to_spark {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("intro to apache spark")
      .master("local")
      .getOrCreate()

    val num = spark.sparkContext.parallelize(Array(0,1,2))

    num.sum()
    num.count()
    num.mean()
    num.filter(x => x%2==0)
    num.filter(x => x%2==0).sum()

    val divisible = num.filter(x => x%3==0 || x%5==0)
    divisible.count()
    divisible.collect()
    divisible.foreach(println)
  }

}
