package com.kihyeonkim.apigateway.filter

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants

class InternalServerErrorFilter : ZuulFilter() {
	override fun shouldFilter(): Boolean {
		return RequestContext.getCurrentContext().responseStatusCode == 500
	}

	override fun run(): Any {
		val requestContext: RequestContext = RequestContext.getCurrentContext()
		requestContext.response.headerNames.addAll(mutableListOf("content-type", "application/json; charset=utf-8"))
		requestContext.responseBody = "{\"message\":\"error\"}"

		return requestContext
	}

	override fun filterType(): String {
		return FilterConstants.POST_TYPE
	}

	override fun filterOrder(): Int {
		return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1
	}

}