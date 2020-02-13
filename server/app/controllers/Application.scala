package controllers

import javax.inject._

import edu.trinity.videoquizreact.shared.SharedMessages
import play.api.mvc._

@Singleton
class Application @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.index(SharedMessages.itWorks))
  }

  def tempPage = Action {
    //Ok("<p>This is a page</p>").as("text/html")
    Ok(views.html.tempPage())
  }

  def temps(month: int, year:Int) = TODO

}
