import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun numbers(): Flow<Int> = flow {
    //    try {
    emit(1)
    emit(2)
    emit(3)
//    } catch (e : Exception) {
//        println(e.message)
//    } finally {
//        println("Finally in numbers")
//    }
}

fun main() = runBlocking<Unit> {
    //    numbers()
//        .map { it * 2 }
//        .catch {}
//        .scan(0) { o1, o2 -> o1 + o2 }
//        .collect {
//            println(it)
//        }
//
//    val toList = flowOf(1, 2, 3).scan(emptyList<Int>()) { acc, value -> acc + value }.toList()
//    println(toList)

    (0..100).asFlow()
        .filter { it % 2 == 0 }
        .flatMapConcat {
            (0..it).asFlow().map { "." }
                .scan("") { accumulator, value -> accumulator + value }
        }
        .onEach {  }
        .onCompletion { it}
        .launchIn(this)


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