  
package models

case class TempData(day: Int, dayOfYear: Int, month: Int, stateId: String,
  year: Int, precip: Double, tave: Double, tmin: Double, tmax: Double)

object TempModel {
  val data = readData()

  def readData(): Seq[TempData] = {
    val file = if (new java.io.File("data/SanAntonioTemps.csv").exists) "data/SanAntonioTemps.csv" else "../data/SanAntonioTemps.csv"
    val source = scala.io.Source.fromFile(file)
    val lines = source.getLines().drop(2)
    val ret = lines.map { line =>
      val p = line.split(",")
      TempData(p(0).toInt, p(1).toInt, p(2).toInt, p(3), p(4).toInt,
        p(5).toDouble, p(6).toDouble, p(7).toDouble, p(8).toDouble)
    }.toArray
    source.close()
    ret
  }
}