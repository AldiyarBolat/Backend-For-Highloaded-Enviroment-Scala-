package lab2

// Theoretical questions: why do we need abstraction
// to hide from the outside world the things that outside world don't need to know, in order to reduce risk of bug

// How `traits` in Scala are used?
//to extend in classes

trait Animal {
  // Is this abstract or concrete (implemented) member?
  // abstract because method is no complete
  def name: String

  // Is this abstract or concrete (implemented) member?
  // abstract because method is no complete
  def makeSound(): String
}

trait Walks {

  // What does this line mean?
  // only class with type animal or child can inherit this trait
  // this is done to mixin 2 traits event they dont extend each other
  this: Animal =>

  // Is this abstract or concrete (implemented) member?
  // concrete because our method is complete

  // Why `name` parameter is available here?
  // because of syntax we used in line 24
  def walk: String = s"$name is walking"

}


// Can Dog only extend from `Walks`?
// no, we can extend infinite amount of times

// Try to fix Dog, so it extends proper traits
// Implement Dog class so it passes tests
case class Dog(_name: String, sound: String = "Whooof") extends Animal with Walks{
  override def name: String = _name

  override def makeSound(): String = sound
}

// Implement Cat class so it passes tests
case class Cat(_name: String, sound: String = "Miiyaaau") extends Animal with Walks{
  override def name: String = _name

  override def makeSound(): String = sound
}


object part_1 extends App{

  // Here we will test Dog and Cat classes

  val dog1 = Dog("Ceasar")
  val dog2 = Dog("Laika")

  assert(dog1.name == "Ceasar")
  assert(dog2.name == "Laika")

  assert(dog1.makeSound() == "Whooof")
  assert(dog2.makeSound() == "Whooof")

  assert(dog1.walk == "Ceasar is walking")
  assert(dog2.walk == "Laika is walking")

  val cat1 = Cat("Tosha")
  val cat2 = Cat("Chocolate")

  assert(cat1.name == "Tosha")
  assert(cat2.name == "Chocolate")

  assert(cat1.makeSound() == "Miiyaaau")
  assert(cat2.makeSound() == "Miiyaaau")

  assert(cat1.walk == "Tosha is walking")
  assert(cat2.walk == "Chocolate is walking")

}
