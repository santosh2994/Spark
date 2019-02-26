import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.explode
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.functions.split

object SplittingDataIntoMultipleRows {
  def main(args: Array[String]): Unit = {
    /*creating the spark driver for instantiating the spark with master , and the master can be
    either local or YARN or MESOS This has both the client mode as well as the cluster mode. */

    val spark = SparkSession.builder().master("local").getOrCreate()

    //reading the input from the data source
    val data = spark.read.option("header","true").csv("data/MovieData")
    //printing the schema of the data
    data.printSchema()
    //Displaying the data
    data.show(truncate = false)
    //This is how we will be splitting our data that is having multiple delimiters in our data.
    data.withColumn("genre",explode(split(col("genre"),"[|]")) ).show()

    /*explode is the function that we can use in any of the situation to make our data visually very strong and appealing
    * what ever kind of data we may be having we can solve the inner data using the explode function,
    * we also need to import some of the packages here to complete the process */
  }
}
