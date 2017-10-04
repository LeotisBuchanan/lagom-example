package com.example.hello.api

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import play.api.libs.json.{Format, Json}

object LagompricepredictorService  {
  val TOPIC_NAME = "greetings"
}

/**
  * The lagom-price-predictor service interface.
  * <p>
  * This describes everything that Lagom needs to know about how to serve and
  * consume the LagompricepredictorService.
  */
trait LagompricepredictorService extends Service {


  /**
    * Example: curl http://localhost:9000/api/search/query
    */
  def search(query: String): ServiceCall[NotUsed, String]

  override final def descriptor = {
    import Service._
    // @formatter:off
    named("lagom-price-predictor")
      .withCalls(
        pathCall("/api/search/:id", search _)

      )
      .withAutoAcl(true)
    // @formatter:on
  }
}


/**
  * The query string message class.
  */
case class QueryMessage(message: String)

object QueryMessage {
  /**
    * Format for converting greeting messages to and from JSON.
    *
    * This will be picked up by a Lagom implicit conversion from Play's JSON format to Lagom's message serializer.
    */
  implicit val format: Format[QueryMessage] = Json.format[QueryMessage]
}








