package com.portifolio.customerConnect

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<CustomerConnectApplication>().with(TestcontainersConfiguration::class).run(*args)
}
