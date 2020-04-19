package com.jayden.study.batch.jobs.reader

import org.springframework.batch.item.ItemReader
import java.util.*

open class QueueItemReader<T> : ItemReader<T> {

    private var queue: Queue<T>

    constructor(data: List<T>) {
        queue = LinkedList(data)
    }

    override fun read(): T? {
        return this.queue.poll()
    }
}
