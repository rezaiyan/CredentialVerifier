import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.*

/*TODO
*  I encountered difficulty resolving Waltid libraries, unless I opted to generate
*  the JSON response body using the Data Function provided by Waltid on GitHub
*  https://github.com/walt-id/waltid-identity/blob/main/waltid-verifiable-credentials/src/commonMain/kotlin/id/walt/credentials/issuance/DataFunctions.kt
*/
@Serializable
data class Issuer(
    val type: List<String>,
    val id: String,
    val name: String,
    val url: String,
    val image: String? = null
)

@Serializable
data class Achievement(
    val id: String,
    val type: List<String>,
    val name: String,
    val description: String,
    val criteria: Criteria,
    val image: Image
)

@Serializable
data class Criteria(
    val type: String,
    val narrative: String
)

@Serializable
data class Image(
    val id: String,
    val type: String
)

@Serializable
data class CredentialSubject(
    val id: String,
    val type: List<String>,
    val achievement: Achievement
)

@Serializable
data class Vc(
    val context: List<String>,
    val id: String,
    val type: List<String>,
    val name: String,
    val issuer: Issuer,
    val issuanceDate: String,
    val expirationDate: String,
    val credentialSubject: CredentialSubject
)

@Serializable
data class Mapping(
    val id: String,
    val issuer: Issuer,
    val credentialSubject: CredentialSubject,
    val issuanceDate: String,
    val expirationDate: String
)

@Serializable
data class Root(
    val issuanceKey: IssuanceKey,
    val issuerDid: String,
    val vc: Vc,
    val mapping: Mapping
)

@Serializable
data class IssuanceKey(
    val type: String,
    val jwk: String
)

fun generateJson(): Root {
    // Generate dynamic data
    val dynamicData = generateDynamicData()

    return Root(
        issuanceKey = IssuanceKey(
            type = "local",
            jwk = "{\"kty\":\"OKP\",\"d\":\"mDhpwaH6JYSrD2Bq7Cs-pzmsjlLj4EOhxyI-9DM1mFI\",\"crv\":\"Ed25519\",\"kid\":\"Vzx7l5fh56F3Pf9aR3DECU5BwfrY6ZJe05aiWYWzan8\",\"x\":\"T3T4-u1Xz3vAV2JwPNxWfs4pik_JLiArz_WTCvrCFUM\"}"
        ),
        issuerDid = "did:key:z6MkjoRhq1jSNJdLiruSXrFFxagqrztZaXHqHGUTKJbcNywp",
        vc = Vc(
            context = listOf(
                "https://www.w3.org/2018/credentials/v1",
                "https://purl.imsglobal.org/spec/ob/v3p0/context.json"
            ),
            id = dynamicData["vcId"]!!,
            type = listOf("VerifiableCredential", "OpenBadgeCredential"),
            name = "JFF x vc-edu PlugFest 3 Interoperability",
            issuer = Issuer(
                type = listOf("Profile"),
                id = dynamicData["issuerDid"]!!,
                name = "Jobs for the Future (JFF)",
                url = "https://www.jff.org/",
                image = "https://w3c-ccg.github.io/vc-ed/plugfest-1-2022/images/JFF_LogoLockup.png"
            ),
            issuanceDate = dynamicData["issuanceDate"]!!,
            expirationDate = dynamicData["expirationDate"]!!,
            credentialSubject = CredentialSubject(
                id = dynamicData["subjectDid"]!!,
                type = listOf("AchievementSubject"),
                achievement = Achievement(
                    id = "urn:uuid:ac254bd5-8fad-4bb1-9d29-efd938536926",
                    type = listOf("Achievement"),
                    name = "JFF x vc-edu PlugFest 3 Interoperability",
                    description = "This wallet supports the use of W3C Verifiable Credentials and has demonstrated interoperability during the presentation request workflow during JFF x VC-EDU PlugFest 3.",
                    criteria = Criteria(
                        type = "Criteria",
                        narrative = "Wallet solutions providers earned this badge by demonstrating interoperability during the presentation request workflow. This includes successfully receiving a presentation request, allowing the holder to select at least two types of verifiable credentials to create a verifiable presentation, returning the presentation to the requestor, and passing verification of the presentation and the included credentials."
                    ),
                    image = Image(
                        id = "https://w3c-ccg.github.io/vc-ed/plugfest-3-2023/images/JFF-VC-EDU-PLUGFEST3-badge-image.png",
                        type = "Image"
                    )
                )
            )
        ),
        mapping = Mapping(
            id = UUID.randomUUID().toString(),
            issuer = Issuer(
                type = listOf("Profile"),
                id = dynamicData["issuerDid"]!!,
                name = "Jobs for the Future (JFF)",
                url = "https://www.jff.org/"),
            credentialSubject = CredentialSubject(
                id = dynamicData["subjectDid"]!!,
                type = listOf("AchievementSubject"),
                achievement = Achievement(
                    id = "urn:uuid:ac254bd5-8fad-4bb1-9d29-efd938536926",
                    type = listOf("Achievement"),
                    name = "JFF x vc-edu PlugFest 3 Interoperability",
                    description = "This wallet supports the use of W3C Verifiable Credentials and has demonstrated interoperability during the presentation request workflow during JFF x VC-EDU PlugFest 3.",
                    criteria = Criteria(
                        type = "Criteria",
                        narrative = "Wallet solutions providers earned this badge by demonstrating interoperability during the presentation request workflow. This includes successfully receiving a presentation request, allowing the holder to select at least two types of verifiable credentials to create a verifiable presentation, returning the presentation to the requestor, and passing verification of the presentation and the included credentials."
                    ),
                    image = Image(
                        id = "https://w3c-ccg.github.io/vc-ed/plugfest-3-2023/images/JFF-VC-EDU-PLUGFEST3-badge-image.png",
                        type = "Image"
                    )
                )
            ),
            issuanceDate = dynamicData["issuanceDate"]!!,
            expirationDate = dynamicData["expirationDate"]!!
        )
    )
}

fun generateDynamicData(): Map<String, String> {
    val vcId = "urn:uuid:${UUID.randomUUID()}"
    val issuerDid = "did:key:${UUID.randomUUID()}"
    val subjectDid = "did:key:${UUID.randomUUID()}"
    val issuanceDate = Instant.now().toString()
    val expirationDate = Instant.now().plusSeconds(365 * 24 * 60 * 60).toString()

    return mapOf(
        "vcId" to vcId,
        "issuerDid" to issuerDid,
        "subjectDid" to subjectDid,
        "issuanceDate" to issuanceDate,
        "expirationDate" to expirationDate
    )
}
