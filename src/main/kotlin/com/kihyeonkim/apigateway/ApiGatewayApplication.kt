package com.kihyeonkim.apigateway

import com.kihyeonkim.apigateway.fallback.UserFallbackProvider
import com.kihyeonkim.apigateway.filter.InternalServerErrorFilter
import com.kihyeonkim.apigateway.filter.NotFoundErrorFilter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
class ApiGatewayApplication() {
	@Bean
	fun internalServerErrorFilter(): InternalServerErrorFilter {
		return InternalServerErrorFilter()
	}

	@Bean
	fun notFoundErrorFilter(): NotFoundErrorFilter {
		return NotFoundErrorFilter()
	}

	@Bean
	fun userFallbackProvider(): UserFallbackProvider {
		return UserFallbackProvider()
	}
}

fun main(args: Array<String>) {
	runApplication<ApiGatewayApplication>(*args)
}
