package controllers
// app/controllers/ApiController.scala
// NEW - import JSON functionality and our data repository
import javax.inject.Inject
import javax.inject.Singleton
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import repositories.DataRepository

@Singleton
class ApiController @Inject()(cc: ControllerComponents,
                              dataRepository: DataRepository // NEW
                             )
  extends AbstractController(cc) {

  // Create a simple 'ping' endpoint for now, so that we
  // can get up and running with a basic implementation
  def ping: Action[AnyContent] = Action { implicit request =>
    Ok("Hello, Scala!")
  }

  // NEW - Get a single post
  def getPost(postId: Int): Action[AnyContent] = Action { implicit request =>
    dataRepository.getPost(postId) map { post =>
      // If the post was found, return a 200 with the post data as JSON
      Ok(Json.toJson(post))
    } getOrElse NotFound    // otherwise, return Not Found
  }

  // NEW - Get comments for a post
  def getComments(postId: Int): Action[AnyContent] = Action { implicit request =>
    // Simply return 200 OK with the comment data as JSON.
    Ok(Json.toJson(dataRepository.getComments(postId)))
  }
}