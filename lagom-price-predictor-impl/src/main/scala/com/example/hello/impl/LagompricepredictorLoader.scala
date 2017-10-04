package com.example.hello.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraPersistenceComponents
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.example.hello.api.LagompricepredictorService
import com.lightbend.lagom.scaladsl.broker.kafka.LagomKafkaComponents
import com.softwaremill.macwire._

class LagompricepredictorLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new LagompricepredictorApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new LagompricepredictorApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[LagompricepredictorService])
}

abstract class LagompricepredictorApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with CassandraPersistenceComponents
    with LagomKafkaComponents
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[LagompricepredictorService](wire[LagompricepredictorServiceImpl])

  // Register the JSON serializer registry
  override lazy val jsonSerializerRegistry = LagompricepredictorSerializerRegistry

  // Register the lagom-price-predictor persistent entity
  //persistentEntityRegistry.register(wire[LagompricepredictorEntity])
}
