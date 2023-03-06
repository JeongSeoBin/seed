package com.innowireless.web.util;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.UUID;

public class UuidUtil {

    private static final int UUID_LENGTH = 16;

    // 성능이 적절하게 나온다는 parameter들.
    private static final int INTERVAL_LENGTH_SEC = 60;
    private static final int INTERVAL_COUNT = 65536;
    private static final int NUM_PREFIX_BYTES = 2;  // INTERVAL_COUNT를 표현하는 byte 수.

    private static volatile SecureRandom random = null;

    private UuidUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 일반 DBMS(예: PostgreSQL)에서 key로 사용하면 좋은 uuid를 생성한다.
     * 일정 주기 내에서 대략 sequential 해서, random uuid에 비해 index 관리에 부담을 훨씬 덜 준다.
     * 참고: https://www.2ndquadrant.com/en/blog/sequential-uuid-generators/
     * 참고: https://github.com/tvondra/sequential-uuids/blob/master/sequential_uuids.c
     *
     * @return UUID
     */
    public static UUID createSequentialTimeUuid() {
        SecureRandom rnd = random;
        if (rnd == null)
            random = rnd = new SecureRandom();

        ByteBuffer buffer = ByteBuffer.allocate(UUID_LENGTH);

        long timeVal = Instant.now().getEpochSecond() / INTERVAL_LENGTH_SEC;
        ByteBuffer timeValBuffer = ByteBuffer.allocate(Long.BYTES);
        timeValBuffer.putLong(timeVal);
        for (int i = 0; i < NUM_PREFIX_BYTES; i++)
            buffer.put(timeValBuffer.get(Long.BYTES - NUM_PREFIX_BYTES + i));

        byte[] randomBytes = new byte[UUID_LENGTH - NUM_PREFIX_BYTES];
        rnd.nextBytes(randomBytes);
        buffer.put(randomBytes);

        buffer.put(6, (byte) ((buffer.get(6) & 0x0f) | 0x40));
        buffer.put(8, (byte) ((buffer.get(8) & 0x3f) | 0x80));

        long msb = buffer.getLong(0);
        long lsb = buffer.getLong(Long.BYTES);
        return new UUID(msb, lsb);
    }

    /**
     * @param s - length 32의 hex string
     * @return UUID
     */
    public static UUID fromRawStr(String s) {
        return new UUID(
            Long.parseUnsignedLong(s.substring(0, 16), 16),
            Long.parseUnsignedLong(s.substring(16, 32), 16));
    }

    public static String toRawStr(UUID uuid) {
        return uuid.toString().replaceAll("-", "");
    }
}
