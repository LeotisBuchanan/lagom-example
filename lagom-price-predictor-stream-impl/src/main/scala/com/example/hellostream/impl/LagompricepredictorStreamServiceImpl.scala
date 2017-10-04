package com.example.hellostream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.example.hellostream.api.LagompricepredictorStreamService
import com.example.hello.api.LagompricepredictorService

import scala.concurrent.Future

/**
  * Implementation of the LagompricepredictorStreamService.
  */
class LagompricepredictorStreamServiceImpl(lagompricepredictorService: LagompricepredictorService) extends LagompricepredictorStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(lagompricepredictorService.hello(_).invoke()))
  }
}
