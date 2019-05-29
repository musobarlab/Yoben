package com.wuriyanto.yoben.databases

import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig
import java.time.Duration

class Redis(private val host: String,
            private val password: String,
            private val port: Int,
            private val timeout: Int,
            private val database: Int,
            private val ssl: Boolean){

    var jedisPool: JedisPool? = null

    init {
        val jedisPoolConfig = buildConfig()
        jedisPool = JedisPool(jedisPoolConfig, host, port, timeout, password, database, ssl)
    }

    private fun buildConfig(): JedisPoolConfig {
        val poolConfig = JedisPoolConfig()
        poolConfig.maxTotal = 128
        poolConfig.maxIdle = 128
        poolConfig.minIdle = 16
        poolConfig.testOnBorrow = true
        poolConfig.testOnReturn = true
        poolConfig.testWhileIdle = true
        poolConfig.minEvictableIdleTimeMillis = Duration.ofSeconds(60).toMillis()
        poolConfig.timeBetweenEvictionRunsMillis = Duration.ofSeconds(30).toMillis()
        poolConfig.numTestsPerEvictionRun = 3
        poolConfig.blockWhenExhausted = true
        return poolConfig
    }

}

//fun main() {
//    val redis = Redis("localhost", "devpass", 6379, 0, 0, false)
//    val jedis = redis.jedisPool.resource
//    try {
//        jedis.set("1", "lopes")
//    } catch(e: Exception) {
//        println(e.message)
//    } finally {
//        jedis.close()
//    }
//}