import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    GlobalScope.launch {
        funA()
    }
    Thread.sleep(2300)
    println("Exiting main...")
}

suspend fun funA() {
    println("start....")
    println(funB())
    println(funC())
    println("......end")
}

suspend fun funB(): String {
    delay(1000)
    return "Hello"
}

suspend fun funC(): String {
    delay(1000)
    return "World!"
}