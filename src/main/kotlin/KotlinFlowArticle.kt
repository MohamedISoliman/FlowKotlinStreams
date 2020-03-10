import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
fun main() {


//    println("RxJava +++++++")
//
//    Observable.fromArray("Madara", "Kakashi", "Naruto", "Jiraya", "Itachi")
//        .flatMap { string ->
//            Observable.just(string)
//                .delay(1000, TimeUnit.MILLISECONDS)
//                .map { it.length }
//        }
//        .filter { it > 4 }
//        .subscribe {
//            println()
//        }


    val sampleData = listOf("Madara", "Kakashi", "Naruto", "Jiraya", "Itachi")
//
//    sampleData
//        .map { it.length }
//        .filter { it > 4 }
//        .forEach {
//            println(it)
//        }
//
//    println("asSequence +++++++")
//
//    sampleData
//        .asSequence()
//        .map { it.length }
//        .filter { it > 4 }
//        .forEach {
//            println(it)
//        }

    println("Flow +++++++")



    runBlocking {
        flowOfAnimeCharacters()
            .flowOn(Dispatchers.IO)
            .catch {
                emitAll(flowOf("Minato", "Hashirama"))
            }
            .onCompletion {
                println("done")
                it?.let { throwable -> println(throwable) }
            }
            .collect {
                println(it)
            }
    }
}

private fun flowOfAnimeCharacters() = flow {
    emit("Madara")
    emit("Kakashi")
    throw IllegalStateException()
    emit("Jiraya")
    emit("Itachi")
    emit("Naruto")
}

private suspend fun stringToLength(it: String): Int {
    delay(1000)
    return it.length
}