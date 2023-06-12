package _5_expressions

import kotlinx.coroutines.runBlocking

class PseudoClosableExample {
    lateinit var service: A
    lateinit var broker: B
    lateinit var producer: C

    fun appCleanup() {
        stopProducer()
        stopBroker()
        stopService()
    }

    fun stopProducer() {
        if (this::producer.isInitialized) {
            runBlocking {
                runCatching {
                    producer.stop()
                }.onFailure { println("failed to close queue producer: $it") }
            }
        }
    }

    fun stopBroker() {
        if (this::broker.isInitialized) {
            runBlocking {
                runCatching {
                    broker.shutdown()
                }.onFailure { println("failed to close queue producer: $it") }
            }
        }
    }

    fun stopService() {
        if (this::service.isInitialized) {
            runBlocking {
                runCatching {
                    service.close()
                }.onFailure { println("failed to close queue producer: $it") }
            }
        }
    }
}

class PseudoClosableExample2 {

    lateinit var service: A
    lateinit var broker: B
    lateinit var producer: C

    fun appCleanup() {
        stopProducer()
        stopBroker()
        stopService()
    }

    fun stopProducer() {
        cleanup(this::producer.isInitialized, producer)
    }

    fun stopBroker() {
        cleanup(this::broker.isInitialized, broker)
    }

    fun stopService() {
        cleanup(this::service.isInitialized, service)
    }

    fun cleanup(isInitialized: Boolean, closeable: Any) {
        if (isInitialized) {
            runBlocking {
                val className = closeable::class.java.simpleName
                runCatching {
                    when (closeable) {
                        is A -> closeable.close()
                        is B -> closeable.shutdown()
                        is C -> closeable.stop()
                        else -> throw IllegalArgumentException("unsupported class $className") }
                }.onFailure { println("failed to close $className: $it") }
            }
        }
    }
}


class PseudoClosableFunExample {
    lateinit var service: A
    lateinit var broker: B
    lateinit var producer: C

    fun appCleanup() {
        stopResource({ this::producer.isInitialized }) { producer.stop() }
        stopResource({ this::broker.isInitialized }) { broker.shutdown() }

        stopResource(
            predicate = { this::service.isInitialized },
            close = { service.close() }
        )
    }

    private fun stopResource(predicate: () -> Boolean, close: suspend () -> Unit) {
        if (predicate()) {
            runBlocking {
                runCatching {
                    close()
                }.onFailure {
                    println("failed to close the resource: $it")
                }
            }
        }
    }
}

// region fake resources
class A { suspend fun close() {} }
class B { suspend fun shutdown() {} }
class C { suspend fun stop() {} }
// endregion
