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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * Generic description
 *
 * @author Fedorov Konstantin (mr.fedorov.konstantin@mail.ru)
 * @version 0.1.0 [MAJOR.MINOR.PATCH]
 * Created on 22.03.2019.
 */
public class TimestampConverterTest {
    private final static Logger logger = LoggerFactory.getLogger(TimestampConverterTest.class);

    @Test
    public void createAsLong() {
        String expectedStr = "2017-01-18T15:28:11Z"; // utc

        long timestampSeconds = 0x587f898b;
        logger.debug("timestampSeconds:\t{}", timestampSeconds);

        long timestampMillis = TimestampConverter.createSeconds(timestampSeconds).getMillis();
        logger.debug("timestampMillis:\t\t{}", timestampMillis);

        long timestampSeconds2 = TimestampConverter.createMillis(timestampMillis).getSeconds();
        logger.debug("timestampSeconds2:\t{}", timestampSeconds2);

        assertEquals(timestampSeconds, timestampSeconds2);
        assertEquals(1484753291000L, timestampMillis);

        assertEquals(expectedStr, TimestampConverter.createSeconds(timestampSeconds).toString());
        assertEquals(expectedStr, TimestampConverter.createMillis(timestampMillis).toString());
        assertEquals(expectedStr, TimestampConverter.createSeconds(timestampSeconds2).toString());
    }

    @Test
    public void createFromSecondsAsLong() {
        String expectedStr = "2017-01-18T15:28:11Z"; // utc
        long timestampSeconds = 0x587f898b;

        TimestampConverter timestampConverter = TimestampConverter.createSeconds(timestampSeconds);
        logger.debug("timestampConverter:\t{}", timestampConverter);

        assertEquals(1484753291000L, timestampConverter.getMillis());
        assertEquals(expectedStr, timestampConverter.toString());
    }

    @Test
    public void createFromSecondsAsWords() {
        int high = 0x898b;
        int low = 0x587f;
        long expected = 1484753291000L;
        String expectedStr = "2017-01-18T15:28:11Z"; // utc

        TimestampConverter timestampConverter = TimestampConverter.createSeconds(high, low);
        logger.debug("timestampConverter:\t{}", timestampConverter);

        long actual = timestampConverter.getMillis();
        logger.debug("expected: {}", expected);
        logger.debug("actual: {}", actual);

        assertEquals(expected, actual);
        assertEquals(expectedStr, timestampConverter.toString());
    }

    @Test
    public void createFromSecondsAsByteArray() {
        byte[] array = new byte[]{(byte) 0x89, (byte) 0x8b, 0x58, 0x7f};
        long expected = 1484753291000L;
        String expectedStr = "2017-01-18T15:28:11Z"; // utc

        TimestampConverter timestampConverter = TimestampConverter.createSeconds(array);
        logger.debug("timestampConverter:\t{}", timestampConverter);

        long actual = timestampConverter.getMillis();
        logger.debug("expected: {}", expected);
        logger.debug("actual: {}", actual);

        assertEquals(expected, actual);
        assertEquals(expectedStr, timestampConverter.toString());
    }
}