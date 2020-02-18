import kotlinx.coroutines.*

fun main() {
    WelcomingScreen().onCreate()
    Thread.sleep(2000)
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