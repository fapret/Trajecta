import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class WorkspaceTokenUtil {
    private static final String HMAC_ALGORITHM = "HmacSHA256";
    private static final long TOKEN_TTL_SECONDS = 3600;

    private WorkspaceTokenUtil() {
    }

    private static String getSecret() {
        String secret = System.getenv("WORKSPACE_TOKEN_SECRET");
        if (secret == null || secret.isBlank()) {
            secret = "trajecta-dev-secret-change-me";
        }
        return secret;
    }

    private static byte[] sign(byte[] payload) throws Exception {
        Mac mac = Mac.getInstance(HMAC_ALGORITHM);
        mac.init(new SecretKeySpec(getSecret().getBytes(StandardCharsets.UTF_8), HMAC_ALGORITHM));
        return mac.doFinal(payload);
    }

    public static String issueToken(String workspaceUuid) throws Exception {
        long issuedAt = Instant.now().getEpochSecond();
        long expiry = issuedAt + TOKEN_TTL_SECONDS;
        String payload = workspaceUuid + "." + issuedAt + "." + expiry;
        byte[] payloadBytes = payload.getBytes(StandardCharsets.UTF_8);
        String payloadPart = Base64.getUrlEncoder().withoutPadding().encodeToString(payloadBytes);
        String signaturePart = Base64.getUrlEncoder().withoutPadding().encodeToString(sign(payloadBytes));
        return payloadPart + "." + signaturePart;
    }

    public static boolean isTokenValidForWorkspace(String token, String workspaceUuid) {
        try {
            if (token == null || workspaceUuid == null) {
                return false;
            }
            String[] parts = token.split("\\.");
            if (parts.length != 2) {
                return false;
            }
            byte[] payloadBytes = Base64.getUrlDecoder().decode(parts[0]);
            byte[] givenSignature = Base64.getUrlDecoder().decode(parts[1]);
            byte[] expectedSignature = sign(payloadBytes);
            if (!MessageDigest.isEqual(givenSignature, expectedSignature)) {
                return false;
            }
            String payload = new String(payloadBytes, StandardCharsets.UTF_8);
            String[] payloadParts = payload.split("\\.");
            if (payloadParts.length != 3) {
                return false;
            }
            String tokenWorkspaceUuid = payloadParts[0];
            long expiry = Long.parseLong(payloadParts[2]);
            long now = Instant.now().getEpochSecond();

            return workspaceUuid.equals(tokenWorkspaceUuid) && now <= expiry;
        } catch (Exception ex) {
            return false;
        }
    }
}
