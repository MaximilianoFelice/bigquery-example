import com.google.cloud.bigquery.{BigQuery, JobId, JobInfo, QueryJobConfiguration, TableResult}

import scala.util.Try
import scala.jdk.CollectionConverters._

package object bigquery {

  def queryToServer(query: QueryJobConfiguration, id: JobId, bq: BigQuery): Try[TableResult] =
    Try {
      bq.create(JobInfo.newBuilder(query).setJobId(id).build).waitFor().getQueryResults()
    }

  def stringify(table: TableResult): String = {
    val header = table.getSchema.getFields.asScala.map(_.getName).mkString("; ")
    val res = table.iterateAll.asScala.map(_.iterator().asScala.map(_.getValue.toString).mkString("; ")).mkString("\n")
    s"$header\n$res"
  }
}
