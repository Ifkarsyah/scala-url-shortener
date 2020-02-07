package controllers

import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import services.URLService

@Singleton
class URLController @Inject()(cc: ControllerComponents,
                              urlService: URLService)
  extends AbstractController(cc) {

  def shorten: Action[AnyContent] = Action { implicit request =>
    request.body.asJson match {
      case None => BadRequest("Request body must be application/json")
      case Some(jsonBody) =>
        val cleanedLongURL = (jsonBody \ "originalURL").as[String]
        val shortenServiceResponse = urlService.shorten(cleanedLongURL)
        shortenServiceResponse match {
          case Some(shortURL) => Ok(Json.toJson(Map("shortURL" -> shortURL)))
          case None => InternalServerError("Something wrong")
        }
    }
  }

  def getOriginal(shortURL: String): Action[AnyContent] = Action { implicit request =>
    urlService.getOriginal(shortURL) match {
      case None => InternalServerError("Something wrong")
      case Some(jsValue) => Ok(jsValue)
    }
  }
}
