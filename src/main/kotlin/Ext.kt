import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

fun HttpResponse.ok() = status == HttpStatusCode.OK