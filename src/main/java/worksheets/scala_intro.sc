//ctrl+Shift+X to run this sheet in scala console

List(1, 2, 3) size

def isEven(n: Int) = (n%2) == 0
List(1,2,3,4).filter(isEven).foreach(println)

//precedence rules in scala
2.0 * 4.0 / 3.0 * 5.0
(((2.0 * 4.0) / 3.0) * 5.0)

//list operations
val list = List('b', 'c', 'd')
val list1 = 'a' :: list

list1 ++ List('e', 'f')

//filtring
val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
  "Scottish Terrier", "Great Dane", "Portuguese Water Dog")
for (breed <- dogBreeds)
  println(breed)

for (breed <- dogBreeds
     if breed.contains("Terrier")
) println(breed)

//yielding
//yield is the keyword using which we will be creating another collection
val filteredBreeds = for {
  breed <- dogBreeds
  if breed.contains("Terrier")
  if !breed.startsWith("Yorkshire")
} yield breed


//traversal
List(1, 2, 3, 4, 5) foreach { i => println("Int: " + i) }


val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska" -> "Juneau",
  "Wyoming" -> "Cheyenne")
stateCapitals foreach { kv => println(kv._1 + ": " + kv._2) }

//Mapping
val lengths = stateCapitals map { kv => (kv._1, kv._2.length) }
println(lengths)

//filtering
val map2 = stateCapitals filter { kv => kv._1 startsWith "A" }
println( map2 )

//Folding and Reducing
List(1,2,3,4,5,6) reduceLeft(_ + _)
List(1,2,3,4,5,6).foldLeft(10)(_ * _)

List(1, 2, 3, 4, 5, 6).foldLeft(List[String]()) {
  (list, x) => ("<" + x + ">") :: list
}

List(1, 2, 3, 4, 5, 6).foldRight(List[String]()) {
  (x, list) => ("<" + x + ">") :: list
}

println((1 to 1000000) reduceLeft(_ + _))
println((1 to 1000000) reduceRight(_ + _))

println((1 to 1000000).foldRight(0)(_ + _))