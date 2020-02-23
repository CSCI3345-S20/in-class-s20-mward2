package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class TaskList1 @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
    val tasks = List("task1", "task2", "task3", "sleep", "eat ")
    def taskList = Action {
        Ok(views.html.taskList1(tasks))
    }

    def product(prodType: String, prodNum: Int) = Action {
        Ok("Product Type is: $prodType, Product Number is: $prodNum ")
    }
}