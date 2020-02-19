import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun numbers(): Flow<Int> = flow {
    //    try {
    emit(1)
    emit(2)
    println("This line will not execute")
    emit(3)
//    } catch (e : Exception) {
//        println(e.message)
//    } finally {
//        println("Finally in numbers")
//    }
}

fun main() = runBlocking<Unit> {
    numbers()
        .take(2) // take only the first two.
        .onEach {
            throw IllegalStateException()
            it
        }
        .onCompletion { println("Completed $it") }
        .catch { println(it) }
        .collect { println(it) }


}

//--------------------- Flow - comparison to Rxjava -------
// flowOn ---> subscribeOn
//buffer  ---> Flowable (Buffer) // concurrent processing in Kotlin
//Zip ----> Zip
//combine  ---> concat
//catch  ----> onError
//collect ---> observe/subscribe
//onCompletion ---> onComplete
//catch and collect are some how like operators, their order matters
//launchIn(this) ---> subscribe() -> it immediately subscribe and launch the stream

suspend inline fun measureTimeMillisCoroutine(crossinline block: suspend () -> Unit): Long {
    val start = System.currentTimeMillis()
    block()
    return System.currentTimeMillis() - start
}