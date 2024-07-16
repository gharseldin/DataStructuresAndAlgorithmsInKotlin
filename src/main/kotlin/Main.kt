import java.util.Scanner

fun main(args: Array<String>) {

    // Immutable variable declaration
    val height = 60

    // Mutable variable declaration
    var size = 5
    size = 4

    // You can define a variable and give it a type
    // then assign it later in the code
    var score: Int
    score = 10

    // Optional values can be take a null value
    var car: Car? = null
    car = Car("Mercedes-Benz")

    // You cannot use optional values without making sure they are not null
    car.drive()

    // You should use a null safety check when calling anything on an optional
    car?.drive()

    // The elvis operator will run here in case the value of car is null
    val immutableCar: Car = car ?: Car("Porche")


    // The null assertion operator !! will enable you to make forced
    // calls on optional values
    car!!.drive()

    // Conditional if else statements
    val a = 5
    val b = 12
    var max = -1
    if (a > b) {
        max = a
    } else {
        max = b
    }
    println(max) // prints 12

    // Conditional when statements
    val groupSize = 3
    when (groupSize) {
        1 -> println("Single")
        2 -> println("Pair")
        3 -> { // Note the block
            println("Trio")
        }

        in 4..10 -> println("Medium group")
        else -> println("This is either nobody or a big crowd")
    }

    // For loops
    for (i in 1..3) {
        println(i)
    }

    val collection = setOf("key", "chain", "wallet")
    for (item in collection) println(item)

    // while loops
    var x = 10
    while (x > 0) {
        x--
    }

    // do - while loops
    var y = 10
    do {
        y--
    } while (y > 0)

    // defining and using a Generic class
    val integerBox = BoxGeneric<Int>()
    integerBox.put(15)
    println(integerBox.isEmpty())

    // Mutable Lists
    val mutablePlaces = mutableListOf(
        "Paris",
        "London",
        "Bucharest"
    )
    mutablePlaces[0] // Paris
    mutablePlaces[1] // London
    mutablePlaces[2] // Bucharest

    // Mutable Maps
    val scores = mutableMapOf(
        "Eric" to 9,
        "Mark" to 12,
        "Wayne" to 1
    )
    scores["Andrew"] = 0
}

// Function definition with a return value
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

// Function definition without a return value
fun printMax(c: Int, d: Int) {
    val maxValue = max(c, d)
    println(maxValue)
}

// Non Generic class
class Box {
    var content: Any? = null
    fun put(content: Any?) {
        this.content = content
    }

    fun retrieve(): Any? {
        return content
    }

    fun isEmpty(): Boolean {
        return content == null
    }
}

// Can be transformed into a Generic class as follows
class BoxGeneric<T> {
    var content: T? = null
    fun put(content: T) {
        this.content = content
    }

    fun retrieve(): T? {
        return content
    }

    fun isEmpty(): Boolean {
        return content == null
    }
}

// Scope Functions
// let: The let function helps you with null-checks and
// creates a new local scope to safely perform operations
fun printCar(car: Car?) {
    val isCoupe = car?.let {
        (it.doors <= 2)
    }
    if (isCoupe == true) {
        println("Coupes are awesome")
    }
}

// run: run is similar to let, but it’s more focused on
// the target object — the one you’re using to call the function.
// Inside the block, run passes the target object as this and
// isolates the block from the outer scope.
fun printCar2(car: Car?) {
    val isCoupe = car?.run {
        (doors <= 2)
    }
    if (isCoupe == true) {
        println("Coupes are awesome")
    }
}
// These two functions are “transformational” functions. They’re called
// "transformational" because the object they return can be different
// from the object you call the function on

// also: the also function returns the original object. also uses it to
// refer to the object inside of the block and has access to the outer
// scope using this
fun printCar3(car: Car?) {
    car?.also {
        it.doors = 4
    }.let {
        if (it?.doors != null && it.doors <= 2) {
            println("Coupes are awesome")
        }
    }
}

// apply:It’s an also that is isolated like a run. It returns the same
// object as the target, and it uses this inside the block
fun printCar4(car: Car?) {
    car?.apply {
        doors = 4
    }.let {
        if (it?.doors != null && it.doors <= 2) {
            println("Coupes are awesome")
        }
    }
}


// TODO function to never forget TODOs
// This will give an error if its used and never implemented
fun testingTODOs() {
    TODO()
}

data class Car(
    val brand: String,
    var doors: Int
) {
    fun drive() {
        println("driving")
    }
}