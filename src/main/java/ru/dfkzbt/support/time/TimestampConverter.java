/*
 *    Copyright 2019 Konstantin Fedorov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package ru.dfkzbt.support.time;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.Objects;

/**
 * Generic description
 *
 * @author Fedorov Konstantin (mr.fedorov.konstantin@mail.ru)
 * @version 0.1.0 [MAJOR.MINOR.PATCH]
 * Created on 22.03.2019.
 */
public class TimestampConverter {
    private Instant instant;

    private TimestampConverter() {
    }

    /**
     * create class using 4-byte timestamp (seconds)
     *
     * @param timestamp
     * @return
     */
    public static TimestampConverter createSeconds(long timestamp) {
        return new TimestampConverter()
                .setTimestampSeconds(timestamp);
    }

    /**
     * create class using 4-byte timestamp (seconds)
     */
    public static TimestampConverter createSeconds(int first, int second) {
        return createSeconds(convertSeconds(first, second));
    }

    /**
     * create class using 4-byte timestamp (seconds)
     */
    public static TimestampConverter createSeconds(byte[] array) {
        return createSeconds(convertSeconds(array));
    }

    /**
     * create class using 8-byte Unix timestamp (milliseconds)
     *
     * @param timestamp
     * @return
     */
    public static TimestampConverter createMillis(long timestamp) {
        return new TimestampConverter()
                .setTimestampMillis(timestamp);
    }

    public long getSeconds() {
        return instant.getEpochSecond();
    }

    public long getMillis() {
        return instant.toEpochMilli();
    }

    public Instant getInstant() {
        return instant;
    }

    /**
     * create 4-byte timestamp limited to seconds from 2 words, reverted order
     *
     * @param first  2 byte word value that comes first
     * @param second 2 byte word value that comes second
     * @return timestamp as long
     */
    public static long convertSeconds(int first, int second) {
        return ByteBuffer.allocate(8).putInt(0).putShort((short) second).putShort((short) first).getLong(0);
    }

    /**
     * create 4-byte timestamp limited to seconds from 4-byte array
     *
     * @param array 4 byte array containing timestamp
     * @return timestamp as long
     */
    public static long convertSeconds(byte[] array) {
        if (array.length != 4) throw new IllegalArgumentException("array size must be 4");

        return ByteBuffer.allocate(8)
                .putInt(0)
                .put(array[2])
                .put(array[3])
                .put(array[0])
                .put(array[1])
                .getLong(0);
    }

    private TimestampConverter setTimestampSeconds(Long timestamp) {
        this.instant = Instant.ofEpochSecond(timestamp);
        return this;
    }

    private TimestampConverter setTimestampMillis(Long timestamp) {
        this.instant = Instant.ofEpochMilli(timestamp);
        return this;
    }

    @Override
    public String toString() {
        return instant.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimestampConverter)) return false;
        TimestampConverter that = (TimestampConverter) o;
        return Objects.equals(getInstant(), that.getInstant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInstant());
    }
}
