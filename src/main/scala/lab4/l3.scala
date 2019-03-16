package lab4

case class Film( name: String,
                 yearOfRelease: Int,
                 imdbRating: Double)
case class Director( firstName: String,
                     lastName: String,
                     yearOfBirth: Int,
                     films: Seq[Film])


object l3 extends App{

  val memento = Film("Memento", 2000, 8.5)
  val darkKnight = Film("Dark Knight", 2008, 9.0)
  val inception = Film("Inception", 2010, 8.8)
  val highPlainsDrifter = Film("High Plains Drifter", 1973, 7.7)
  val outlawJoseyWales = Film("The Outlaw Josey Wales", 1976, 7.9)
  val unforgiven = Film("Unforgiven", 1992, 8.3)
  val granTorino = Film("Gran Torino", 2008, 8.2)
  val invictus =  Film("Invictus", 2009, 7.4)
  val predator =  Film("Predator", 1987, 7.9)
  val dieHard =  Film("Die Hard", 1988, 8.3)
  val huntForRedOctober =  Film("The Hunt for Red October", 1990, 7.6)
  val thomasCrownAffair =  Film("The Thomas Crown Affair", 1999, 6.8)

  val eastwood =  Director("Clint", "Eastwood", 1930,
    Seq(highPlainsDrifter, outlawJoseyWales, unforgiven, granTorino, invictus))
  val mcTiernan =  Director("John", "McTiernan", 1951,
    Seq(predator, dieHard, huntForRedOctober, thomasCrownAffair))
  val nolan =  Director("Christopher", "Nolan", 1970,
    Seq(memento, darkKnight, inception))
  val someGuy =  Director("Just", "Some Guy", 1990,
    Seq())
  val directors = Seq(eastwood, mcTiernan, nolan, someGuy)




  val t1 = (numberOfFilms: Int) => directors.filter(d => d.films.size > numberOfFilms)

  val t2 = (year: Int) => directors.find(d => d.yearOfBirth < year)

  val t3 = (year: Int, numberOfFilms: Int) => directors.filter(d => d.films.size > numberOfFilms && d.yearOfBirth < year)

  val t4 = (ascending: Boolean) => {
    ascending match {
      case true => directors.sortWith(_.yearOfBirth < _.yearOfBirth)
      case _ => directors.sortWith(_.yearOfBirth > _.yearOfBirth)
    }
  }

  val t5 = nolan.films.map(f => f.name)

  val t6 = directors.flatMap(d => d.films.map(f => f.name))

  val t7 = mcTiernan.films.map(f => f.yearOfRelease).min

  val t8 = directors.flatten(d => d.films).sortWith((f1, f2) => (f1.imdbRating) < f2.imdbRating)


  def t9() : Double = {
    val films: Seq[Film] = directors.flatten(director => director.films)
    val sum: Double = films.foldLeft(0.0)((sum, film) => sum + film.imdbRating)
    sum / films.size
  }

  def t10 =  directors.foreach(d => d.films.foreach(f => println(s"Tonight only! ${f.name} by ${d.firstName} !")))

  def t11(): Option[Film] = {
    val sorted = directors.flatten(d => d.films).sortWith((f1, f2) => (f1.yearOfRelease) < f2.yearOfRelease)
    sorted.headOption
  }


        /// TEST///
  println(t1(3))
  println()

  println(t2(3000))
  println()

  println(t3(2005, 2))
  println()

  println(t4(true))
  println()

  println(t5)
  println()

  println(t6)
  println()

  println(t7)
  println()

  println(t8)
  println()

  println(t9)
  println()

  t10
  println()

  println(t11)
  println()



}
