package acadglid

import org.apache.spark.sql.SparkSession

object usaDataAnlaysis {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local")
      .getOrCreate()

    val data = spark.read.csv("data/usa_crime_data/Crime_dataset")
    val sample = data.toDF("Id","case_no","date","block","IUCR","Primary_type",
        "description","Loc_des","arrest","domestic","beat","district","ward",
        "community","fbicode","XCor","YCor","year","Updated_on","lattitude","longi","loc")
    sample.printSchema()

    println("count of the data is :"+sample.count())
    sample.registerTempTable("usaData")
    spark.sql("select * from usaData").show(10)

    spark.sql("select fbicode , count(fbicode) as count from usaData group by fbicode").show(10)

    spark.sql("select * from usaData where primary_type='NARCOTICS' and year = '2015'").show(10)

    spark.sql("select district ,count(*) as count from usaData where Primary_type =’THEFT’ and arrest = ‘true’ group by district").show(10)
  }
}
