package controllers

import io.swagger.annotations._
import play.api._
import play.api.mvc._

@Api(value = "/api", description = "Operations about Swagger")
class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  @ApiOperation(value = "swagger", hidden=true)
  def swagger = Action {
    request =>
      Ok(views.html.swagger())
  }

  @ApiOperation(value = "List All",
    nickname = "list",
    notes = "Returns all items",
    response = classOf[Items],
    responseContainer = "List",
    httpMethod = "GET")
  def list = Action {
    request => Ok("test case")
  }

}

case class Items(id: Long, name: String)