import org.apache.spark.sql.SparkSession

//In this we are having the examples using all the aggregate functions
object AggregateFunctionsExample {
  //here we are returning nothing so using the unit function
  def main(args: Array[String]): Unit = {
    //reading the spark session functions here
    val spark = SparkSession.builder().appName("groupbykey example using spark")
      .master("local[*]").getOrCreate()

    //reading the input data and splitting the data using the comma separation using the rdd's
    //instead of the data frames.
    val orderItems = spark.sparkContext.textFile("data/order_items_1.txt").
      map(orderItem => (orderItem.split(",")(1).toInt, orderItem.split(",")(4).toFloat))

    //here we are computing using the reduce by key functions
    print("Doing the Reduce by key operation here")
    // Compute revenue for each order reduceByKey((total, orderItemSubtotal) => total + orderItemSubtotal)
    orderItems.
      reduceByKey((x,y) => x+y).
      take(10).
      foreach(println)

    //here we are using the aggregate by key functions
    println("Doing the Aggregate by functions here")
    // Compute revenue and number of items for each order using aggregateByKey
    orderItems.
      aggregateByKey((0.0, 0))(
        (iTotal, oisubtotal) => (iTotal._1 + oisubtotal, iTotal._2 + 1),
        (fTotal, iTotal) => (fTotal._1 + iTotal._1, fTotal._2 + iTotal._2)
      ).
      take(10).
      foreach(println)

    println("Using the Reduce by key function to compute what the aggregate functions had did")
    // Compute revenue and number of items for each order using reduceByKey
    spark.sparkContext.textFile("data/order_items_1.txt").
      map(orderItem => (orderItem.split(",")(1).toInt, (orderItem.split(",")(4).toFloat, 1))).
      reduceByKey((total, element) => (total._1 + element._1, total._2 + element._2)).
      take(10).
      foreach(println)

  }

}
