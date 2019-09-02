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
 * @version 0.3-SNAPSHOT
 * Created on 22.03.2019.
 */
public class TimestampConverter {
    private Instant instant;

    // blocking ctor
    private TimestampConverter() {
    }

    /**
     * create instance of TimestampConverter from timestamp containing seconds
     *
     * @param timestamp containing seconds
     * @return instance of TimestampConverter
     */
    public static TimestampConverter createSeconds(long timestamp) {
        return new TimestampConverter()
                .setTimestampSeconds(timestamp);
    }

    /**
     * create instance of TimestampConverter from timestamp containing milliseconds
     *
     * @param timestamp containing milliseconds
     * @return instance of TimestampConverter
     */
    public static TimestampConverter createMillis(long timestamp) {
        return new TimestampConverter()
                .setTimestampMillis(timestamp);
    }

    /**
     * create instance of TimestampConverter from timestamp containing seconds
     * splitted in two words,
     *
     * @param first
     * @param second
     * @return instance of TimestampConverter
     */
    public static TimestampConverter createSecondsDirect(int first, int second) {
        return createSeconds(directSeconds(first, second));
    }

    /**
     * create instance of TimestampConverter from timestamp containing seconds
     * splitted in two words,
     * using reversed word order
     *
     * @param first
     * @param second
     * @return instance of TimestampConverter
     */
    public static TimestampConverter createSecondsReversed(int first, int second) {
        return createSeconds(convertSeconds(first, second));
    }

    /**
     * create class using 4-byte timestamp (seconds)
     *
     * @param array
     * @return instance of TimestampConverter
     */
    public static TimestampConverter createSecondsDirect(byte[] array) {
        return createSeconds(convertSeconds(swapWords4(array)));
    }

    /**
     * create class using 4-byte timestamp (seconds)
     *
     * @param array
     * @return instance of TimestampConverter
     */
    public static TimestampConverter createSecondsReversed(byte[] array) {
        return createSeconds(convertSeconds(array));
    }

    /**
     * create long timestamp limited to seconds from 2 words
     * using direct word order
     *
     * @param first  2 byte word value that comes first
     * @param second 2 byte word value that comes second
     * @return timestamp as long
     */
    public static long directSeconds(int first, int second) {
        return ByteBuffer.allocate(8).putInt(0).putShort((short) first).putShort((short) second).getLong(0);
    }

    /**
     * create long timestamp limited to seconds from 2 words
     * using reversed word order
     *
     * @param first  2 byte word value that comes first
     * @param second 2 byte word value that comes second
     * @return timestamp as long
     */
    public static long convertSeconds(int first, int second) {
        return ByteBuffer.allocate(8).putInt(0).putShort((short) second).putShort((short) first).getLong(0);
    }

    /**
     * create long timestamp limited to seconds from 4-byte array
     * using reversed word order
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

    /**
     * swap words in array
     * @param array
     * @return
     */
    public static byte[] swapWords4(byte[] array){
        if (array.length != 4) throw new IllegalArgumentException("array size must be 4");

        return ByteBuffer.allocate(4)
                .put(array[2])
                .put(array[3])
                .put(array[0])
                .put(array[1])
                .array();
    }

    public long getSeconds() {
        return instant.getEpochSecond();
    }

    public byte[] getSecondsArray(){
        byte[] temp = ByteBuffer.allocate(8)
                .putLong(getSeconds())
                .array();

        return ByteBuffer.allocate(4).put(temp,4,4).array();
    }

    public long getMillis() {
        return instant.toEpochMilli();
    }

    public byte[] getMillisArray(){
        return ByteBuffer.allocate(8)
                .putLong(getMillis())
                .array();
    }

    public Instant getInstant() {
        return instant;
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


    // DEPRECATIONS

    /**
     * create instance of TimestampConverter from timestamp containing seconds
     * splitted in two words,
     * using reversed word order
     *
     * @param first
     * @param second
     * @return instance of TimestampConverter
     */
    @Deprecated
    public static TimestampConverter createSeconds(int first, int second) {
        return createSecondsReversed(first, second);
    }

    /**
     * create class using 4-byte timestamp (seconds)
     *
     * @param array
     * @return instance of TimestampConverter
     */
    @Deprecated
    public static TimestampConverter createSeconds(byte[] array) {
        return createSecondsReversed(array);
    }
}
