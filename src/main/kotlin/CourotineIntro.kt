import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun main() {
    val timeMillis = measureTimeMillis {
        funA()
        Thread.sleep(1300) //blocking main
    }
    println(timeMillis)
    println("Exiting main...")
}

fun funA() {
    println("start....")
    thread { funB() } //thread{} is an extension function to // create a thread and start it
    thread { funC() }
    println("......end")
}

fun funB() {
    Thread.sleep(1000)
    println("Hello")
}

fun funC() {
    Thread.sleep(1000)
    println("World!")
}