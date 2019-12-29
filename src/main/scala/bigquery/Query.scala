package bigquery

import java.util.UUID

import com.google.cloud.bigquery.{BigQuery, JobId, QueryJobConfiguration}

case class Query private (query: String, jobId: String, service: BigQuery) {
  val id = JobId.of(jobId)
  lazy val results =
    queryToServer(
      QueryJobConfiguration.newBuilder(query).setUseLegacySql(false).build(),
      id,
      service
    )
}

case object Query {
  def apply(query: String)(implicit service: BigQuery): Query =
    Query(query, UUID.randomUUID.toString, service)
}
