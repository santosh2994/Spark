import org.apache.spark.sql.SparkSession

//hella..! has made a change here..!
//created an object sample
object sample {
  //defining the main function
  //here we are returning the final result as unit since we are not returning anything out of this function main
	def main(args: Array[String]): Unit = {
	//Spark context
		 val spark = SparkSession.builder().appName("santosh").master("local[*]").getOrCreate()
		 //reading the input data set sample data set
		val data = spark.read.option("header","true").csv("data/data.csv").toDF()
		 //printing the schema of the data
	    	data.printSchema()	
		//using pivot in apache scala
	    	val result = data.groupBy("city","name").pivot("age").count()
	    	//displaying the final result
	    	result.show()
	  }

}
