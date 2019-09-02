/*
 *    Copyright 2018 Konstantin Fedorov
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

import java.util.Date;

/**
 * Timestamp API implementation
 *
 * @author Fedorov Konstantin (mr.fedorov.konstantin@mail.ru)
 * @version 0.3-SNAPSHOT
 * Created on 17.01.2018.
 */
public class Timestamp {
    public static long getTimestampMillis() {
        return System.currentTimeMillis();
    }

    public static String getTimestampFormatted(String formatDate) {
        long timestampMillis = getTimestampMillis();

        return DateUtils.getFormatedDate(timestampMillis, formatDate);
    }

    @Deprecated
    public static String getFormatedDate(long millis, String formatDateString) {
        return DateUtils.getFormatedDate(millis, formatDateString);
    }

    @Deprecated
    public static String getFormatedDate(Date date, String formatDateString) {
        return DateUtils.getFormatedDate(date, formatDateString);
    }

    // timestamp representing

    public final static long msecInOneDay = 86400000;
    public final static long msecInOneHour = 3600000;
    public final static long msecInOneMinute = 60000;
    public final static long msecInOneSecond = 1000;

    /**
     * get amount of full days contained in timestamp
     *
     * @param timestamp
     * @return
     */
    public static long getAsFullDays(long timestamp) {
        if (timestamp < msecInOneDay) return 0;
        return timestamp / msecInOneDay;
    }

    /**
     * get amount of full hours contained in timestamp
     *
     * @param timestamp
     * @return
     */
    public static long getAsFullHours(long timestamp) {
        if (timestamp < msecInOneHour) return 0;
        return timestamp / msecInOneHour;
    }

    /**
     * get amount of full minutes contained in timestamp
     *
     * @param timestamp
     * @return
     */
    public static long getAsFullMinutes(long timestamp) {
        if (timestamp < msecInOneMinute) return 0;
        return timestamp / msecInOneMinute;
    }

    /**
     * get amount of full seconds contained in timestamp
     *
     * @param timestamp
     * @return
     */
    public static long getAsFullSeconds(long timestamp) {
        if (timestamp < msecInOneSecond) return 0;
        return timestamp / msecInOneSecond;
    }

    /**
     * get amount of full hours after removing all days
     *
     * @param timestamp
     * @return
     */
    public static long getAsTailHours(long timestamp) {
        long temp = timestamp;
        // remove days
        temp = temp - (getAsFullDays(temp) * msecInOneDay);

        return getAsFullHours(temp);
    }

    /**
     * get amount of full minutes after removing all days and hours
     *
     * @param timestamp
     * @return
     */
    public static long getAsTailMinutes(long timestamp) {
        long temp = timestamp;
        // remove days
        temp = temp - (getAsFullDays(temp) * msecInOneDay);
        // remove hours
        temp = temp - (getAsFullHours(temp) * msecInOneHour);

        return getAsFullMinutes(temp);
    }

    /**
     * get amount of full seconds after removing all days, hours and minutes
     *
     * @param timestamp
     * @return
     */
    public static long getAsTailSeconds(long timestamp) {
        long temp = timestamp;
        // remove days
        temp = temp - (getAsFullDays(temp) * msecInOneDay);
        // remove hours
        temp = temp - (getAsFullHours(temp) * msecInOneHour);
        // remove minutes
        temp = temp - (getAsFullMinutes(temp) * msecInOneMinute);

        return getAsFullSeconds(temp);
    }

    /**
     * get remaining milliseconds after removing all days, hours, minutes and seconds
     *
     * @param timestamp
     * @return
     */
    public static long getAsTailMilliSeconds(long timestamp) {
        long temp = timestamp;
        // remove days
        temp = temp - (getAsFullDays(temp) * msecInOneDay);
        // remove hours
        temp = temp - (getAsFullHours(temp) * msecInOneHour);
        // remove minutes
        temp = temp - (getAsFullMinutes(temp) * msecInOneMinute);
        // remove seconds
        temp = temp - (getAsFullSeconds(temp) * msecInOneSecond);

        return temp;
    }

    // -- timestamp representing

}
