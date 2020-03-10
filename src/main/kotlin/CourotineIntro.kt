import kotlinx.coroutines.*

fun main() {
    val fib = sequence {
        var cur = 1
        var next = 1
        while (true) {
            yield(cur)
            val temp = cur + next
            cur = next
            next = temp
        }
    }

    val iter = fib.iterator()

    repeat(10) {
        println(iter.next())
    }
}

suspend fun funA(): String {
    println("start....")
    val helloDeffered = GlobalScope.async { funB() }
    val worldDeffered = GlobalScope.async { funC() }
    println("......end")
    return helloDeffered.await() + " " + worldDeffered.await()
}

suspend fun funB(): String {
    println("funB Thread: ${Thread.currentThread()}")
    delay(1000)
    return "Hello"
}

suspend fun funC(): String {
    println("funC Thread: ${Thread.currentThread()}")
    delay(1000)
    return "World!"
}


class WelcomingScreen : Activity, CoroutineScope by CoroutineScope(Dispatchers.Default) {

    override fun onCreate() {
        launch(Dispatchers.IO) {
            println("Launch Thread: ${Thread.currentThread()}")
            val string = funA()
            withContext(Dispatchers.Default) {
                println("Logging Thread: ${Thread.currentThread()}")
                println(string)
            }

        }
    }

    override fun onDestroy() {
    }

}

//fake base activity
interface Activity {

    fun onCreate()

    fun onDestroy()
}