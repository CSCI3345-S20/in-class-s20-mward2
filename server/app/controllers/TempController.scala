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
class TempController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def tempPage = Action { implicit request =>
    Ok(views.html.tempPage(models.TempModel.data.map(_.year).distinct))
  }
  
  def temps(month: Int, year: Int) = Action { implicit request =>
    Ok(views.html.tempMonth(month, year, models.TempModel.data.
      filter(td => td.year == year && td.month == month)))
  }

  def tempsPost = Action.async { implicit request => Future {
    val oparams = request.body.asFormUrlEncoded
    oparams.map { params =>
      try {
        val month = params("month")(0).toInt
        val year = params("year")(0).toInt
        Ok(views.html.tempMonth(month, year, models.TempModel.data.
          filter(td => td.year == year && td.month == month)))
      } catch {
        case ex: NumberFormatException => Ok("NaN")
      }
    }.getOrElse(Ok("You screwed up.")) }
  }

  def tempsPlotPage = Action { implicit request =>
    val plot = makeTempPlot()
    val svg = JVMSVGInterface.stringValue(plot, 500, 500)
    Ok(views.html.tempsPlotPage(svg))
  }

  def tempsPlotPNG = Action { implicit request =>
    val img = SwingRenderer.renderToImage(makeTempPlot(), 500, 500)
    val stream = new ByteArrayOutputStream
    javax.imageio.ImageIO.write(img, "png", stream)
    Ok(stream.toByteArray()).as("image/png")
  }

  def makeTempPlot(): Plot = {
    Plot.simple(styles.ScatterStyle(1 to 10, 1 to 10))
  }
}