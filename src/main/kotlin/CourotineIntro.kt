import kotlinx.coroutines.*

fun main() {
    GlobalScope.launch {
        val millisCoroutine = measureTimeMillisCoroutine { funA() }
        println("Took $millisCoroutine")
    }
    Thread.sleep(2300)
    println("Exiting main...")
}

suspend fun funA() {
    println("start....")

    val helloDeffered = GlobalScope.async { funB() }
    val worldDeffered = GlobalScope.async { funC() }
    println("${helloDeffered.await()} ${worldDeffered.await()}")
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