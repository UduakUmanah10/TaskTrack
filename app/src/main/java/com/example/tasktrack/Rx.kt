package com.example.tasktrack

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

fun main(){



    val stringBbservable = Observable.just("hello ")
    val intobservable: Observable<Int> = Observable.just(1,2,3,4, 5,6,7,8,9,10)

    stringBbservable.subscribeBy (
        onComplete = {
                     println("finished")
        },
        onNext = {
                 println("Inside_onNext $it")
        },
        onError = {}

            )
}