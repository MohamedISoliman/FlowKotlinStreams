import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.*

fun main() {


    val disposable = Observable.fromIterable(0..100)
        .filter { it % 2 != 0 }
        .flatMap {
            Observable.fromIterable(0..it)
                .map { "." }
                .reduce("") { acc, dot -> acc + dot }.toObservable()
        }
        .subscribe({
            //data
            println(it)
        }, {
            //error
        }, {
            //completion

        })

    disposable.dispose()


}
