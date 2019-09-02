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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Generic description
 *
 * @author Fedorov Konstantin (mr.fedorov.konstantin@mail.ru)
 * @version 0.3-SNAPSHOT
 * Created on 18.05.2018.
 */
public class DateUtils {
    public static String getFormatedDate(long millis, String formatDateString) {
        return getFormatedDate(new Date(millis), formatDateString);
    }

    public static String getFormatedDate(Date date, String formatDateString) {
        return getFormatedDate(date, new SimpleDateFormat(formatDateString));
    }

    public static String getFormatedDate(Date date, SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(date);
    }

    public static String getFormatedDate(Date date, SimpleDateFormat simpleDateFormat, TimeZone timeZone) {
        simpleDateFormat.setTimeZone(timeZone);

        return getFormatedDate(date, simpleDateFormat);
    }

    @Deprecated
    public static String getFormatDate(Date date, SimpleDateFormat simpleDateFormat) {
        return getFormatedDate(date, simpleDateFormat);
    }

    @Deprecated
    public static String getFormatDate(Date date, SimpleDateFormat simpleDateFormat, TimeZone timeZone) {
        return getFormatedDate(date, simpleDateFormat, timeZone);
    }

    public static short getYear(Date date) {
        //SimpleDateFormat format = new SimpleDateFormat("YYYY");
        SimpleDateFormat format = new SimpleDateFormat("yyyy");

        return Short.parseShort(getFormatDate(date, format));
    }

    public static byte getMonth(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM");

        return Byte.parseByte(getFormatDate(date, format));
    }

    public static byte getDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd");

        return Byte.parseByte(getFormatDate(date, format));
    }

    public static byte getHour(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH");

        return Byte.parseByte(getFormatDate(date, format));
    }

    public static byte getMinute(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("mm");

        return Byte.parseByte(getFormatDate(date, format));
    }

    public static byte getSecond(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("ss");

        return Byte.parseByte(getFormatDate(date, format));
    }

    public static short getMSecond(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("SSS");

        return Short.parseShort(getFormatDate(date, format));
    }
}
