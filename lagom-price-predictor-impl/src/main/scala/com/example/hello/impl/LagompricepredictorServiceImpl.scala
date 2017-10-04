package com.example.hello.impl

import akka.NotUsed
import com.example.hello.api.LagompricepredictorService
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.{PersistentEntityRegistry}

import scala.concurrent.Future
import sys.process._


/**
  * Implementation of the LagompricepredictorService.
  */
class LagompricepredictorServiceImpl() extends LagompricepredictorService {

  /**
    * Example: curl http://localhost:9000/api/search/query
    */
  override def search(query: String): ServiceCall[NotUsed, String] = ServiceCall { request =>

    Future.successful{
      "python  /home/yard/development/scala-projects/model/plot_ols.py" !!
    }
  }

}
