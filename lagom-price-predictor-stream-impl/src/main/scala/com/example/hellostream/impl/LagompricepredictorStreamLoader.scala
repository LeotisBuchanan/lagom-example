package com.example.hellostream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.example.hellostream.api.LagompricepredictorStreamService
import com.example.hello.api.LagompricepredictorService
import com.softwaremill.macwire._

class LagompricepredictorStreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new LagompricepredictorStreamApplication(context) {
      override def serviceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new LagompricepredictorStreamApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[LagompricepredictorStreamService])
}

abstract class LagompricepredictorStreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[LagompricepredictorStreamService](wire[LagompricepredictorStreamServiceImpl])

  // Bind the LagompricepredictorService client
  lazy val lagompricepredictorService = serviceClient.implement[LagompricepredictorService]
}
