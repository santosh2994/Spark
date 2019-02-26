import org.apache.spark.sql.SparkSession

object StudentMarksCalculator {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local").getOrCreate()
    val data = spark.sparkContext.textFile("data/students.csv")

    val stu = data.map(x => (x.split(',')(0),x.split(',')(2).toFloat))
    val grpdData = stu.groupByKey()

    grpdData.foreach(println)
    val result = grpdData.mapValues(x => x.sum/x.toList.length)
    result.foreach(println)

    //calculating the sum using the reduce by key operation
    stu.reduceByKey(_+_).foreach(println)

    //calculating the sum using the aggregate by key
  }

}
