package controllers

import javax.inject._

import edu.trinity.videoquizreact.shared.SharedMessages
import play.api.mvc._
import swiftvis2.plotting._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import swiftvis2.plotting.renderer.JVMSVGInterface
import swiftvis2.plotting.renderer.SwingRenderer
import java.io.ByteArrayOutputStream

@Singleton
class Application @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action { implicit request =>
    Ok(views.html.index("<b>Testing</b>"))//SharedMessages.itWorks))
  }

  def enterName = Action { implicit request =>
    Ok(views.html.enterName(request.session.get("username")))
  }
  
  def rememberName(name: String) = Action { implicit request =>
    Redirect(routes.Application.enterName).
      withSession("username" -> name, "userid" -> "000")
  }
  
  def forgetName = Action { implicit request =>
    Redirect(routes.Application.enterName).withNewSession
  }

}
