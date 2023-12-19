import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import java.net.URLDecoder

val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json()
    }
    install(Logging)
//    install(ResponseObserver) {
//        onResponse {
//            parseAndDecodeString(it.bodyAsText())
//        }
//    }
    headers {
        append(HttpHeaders.ContentType, "application/json")
    }
}


fun parseAndDecodeString(inputString: String){
    println(formatJsonString(inputString))
}

fun formatJsonString(input: String): String {
    val jsonString = URLDecoder.decode(input, "UTF-8")
    val stringBuilder = StringBuilder()
    var indentLevel = 0
    var insideQuotes = false

    for (char in jsonString) {
        when (char) {
            '{', '[' -> {
                stringBuilder.append(char)
                stringBuilder.appendLine()
                indentLevel++
                stringBuilder.append("    ".repeat(indentLevel))
            }
            '}', ']' -> {
                stringBuilder.appendLine()
                indentLevel--
                stringBuilder.append("    ".repeat(indentLevel))
                stringBuilder.append(char)
            }
            ',' -> {
                stringBuilder.append(char)
                if (!insideQuotes) {
                    stringBuilder.appendLine()
                    stringBuilder.append("    ".repeat(indentLevel))
                }
            }
            '"' -> {
                stringBuilder.append(char)
                insideQuotes = !insideQuotes
            }
            else -> stringBuilder.append(char)
        }
    }

    return stringBuilder.toString()
}



