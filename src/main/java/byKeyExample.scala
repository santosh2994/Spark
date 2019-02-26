import org.apache.spark.sql.SparkSession

object byKeyExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("by key operations in spark")
      .master("local[*]")
      .getOrCreate()

    //doing the by key operations here
    val orders = spark.sparkContext.textFile("data/orders_sample.txt")

    // orders sorted by status
    orders.
      map(order => {
        val o = order.split(",")
        (o(3), order)
      }).
      sortByKey().
      map(_._2).
      take(10).
      foreach(println)

    // orders sorted by status and date in descending order
    orders.
      map(order => {
        val o = order.split(",")
        ((o(3), o(1)), order)
      }).
      sortByKey(false).
      map(_._2).
      take(10).
      foreach(println)

    // let us get top 5 products in each category from products
    val products = spark.sparkContext.textFile("data/products_sample.txt")

    val productsGroupByCategory = products.
      filter(product => product.split(",")(4) != "").
      map(product => {
        val p = product.split(",")
        (p(1).toInt, product)
      }).
      groupByKey

    productsGroupByCategory.foreach(println)

    productsGroupByCategory.
      sortByKey().
      flatMap(rec => {
        rec._2.toList.sortBy(r => -r.split(",")(4).toFloat).take(5)
      }).
      take(10).
      foreach(println)

  }

}
