package com.kihyeonkim.apigateway.fallback

import com.netflix.zuul.context.RequestContext
import org.apache.commons.lang.StringUtils
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpResponse
import java.io.ByteArrayInputStream
import java.io.InputStream

class UserFallbackProvider : FallbackProvider {
	override fun getRoute(): String {
		return "user-service"
	}

	override fun fallbackResponse(route: String?, cause: Throwable?): ClientHttpResponse {
		val requestContext: RequestContext = RequestContext.getCurrentContext()

		val responseBody = "{\"result\":\"defaultResult\", \"errorCode\":null, \"errorMessage\":null }"

		println("test: ${requestContext.request.requestURI}")

		if (StringUtils.equals(requestContext.request.requestURI, "/user-service/user/getInfo")) {
			return makeResponse(responseBody)
		}

		return makeResponse(responseBody)
	}

	private fun makeResponse(responseBody: String): ClientHttpResponse {
		return object:ClientHttpResponse{
			override fun getHeaders(): HttpHeaders {
				val headers = HttpHeaders()
				headers.contentType = MediaType.APPLICATION_JSON_UTF8
				return headers
			}

			override fun getBody(): InputStream {
				return ByteArrayInputStream(responseBody.toByteArray())
			}

			override fun close() {

			}

			override fun getStatusCode(): HttpStatus {
				return HttpStatus.OK
			}

			override fun getRawStatusCode(): Int {
				return HttpStatus.OK.value()
			}

			override fun getStatusText(): String {
				return HttpStatus.OK.reasonPhrase
			}
		}
	}
}