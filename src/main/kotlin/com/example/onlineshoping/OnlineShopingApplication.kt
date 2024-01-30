package com.example.onlineshoping

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

//@EnableAspectJAutoProxy
@SpringBootApplication
class OnlineShopingApplication

fun main(args: Array<String>) {
    runApplication<OnlineShopingApplication>(*args)
}
