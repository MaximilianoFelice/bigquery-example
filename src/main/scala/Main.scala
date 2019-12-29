import bigquery.Query
import com.google.cloud.bigquery.BigQueryOptions
import scala.util.{Failure, Success}
import bigquery._

object Main extends App {
  implicit val bigquery = BigQueryOptions.getDefaultInstance.getService

  val query = Query("SELECT * FROM `terranova-260818.pangea_global.enriched_payments` LIMIT 10;")

  query.results match {
    case Success(results) => println(stringify(results))
    case Failure(exception) => {
      print("Something went bad")
      exception.printStackTrace()
      System.exit(1)
    }
  }




}