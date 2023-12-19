import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.coroutineScope


const val issuanceUrl = "http://localhost:7001/openid4vc/jwt/issue"
const val verificationUrl = "http://localhost:7002/openid4vc/verify"


suspend fun main() = coroutineScope {
    val response: HttpResponse = client.post(issuanceUrl) {
        contentType(ContentType.Application.Json)
        setBody(gsonCredential)
    }

    if (response.ok()) {
        println("Issuance Process Completed!")
        initiateVerification(response.bodyAsText())
    } else {
        println("Issuance request failed. Code: ${response.status}")
    }

}


suspend fun initiateVerification(issuanceUrl: String?) {
    if (issuanceUrl != null) {
        val verificationRequestMap = mapOf(
            "request_credentials" to listOf("OpenBadgeCredential")
        )

        val response: HttpResponse = client.post(verificationUrl) {
            contentType(ContentType.Application.Json)
            setBody(verificationRequestMap)
        }
        if (response.ok()) {
            println("Verification Process Completed!")
        } else {
            println("Verification request failed. Code: ${response.status}")
        }
    }
}

