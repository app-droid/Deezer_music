package ru.alinadorozhkina.deezer_music.rx

import io.reactivex.Scheduler

interface ISchedulerProvider {
    val ui: Scheduler
    val io: Scheduler
}