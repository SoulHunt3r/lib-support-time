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

/**
 * Timestamp API implementation
 *
 * @author Fedorov Konstantin (mr.fedorov.konstantin@mail.ru)
 * @version 0.2-SNAPSHOT
 * Created on 17.01.2018.
 */
public class Timestamp {
    public static long getTimestampMillis() {
        return System.currentTimeMillis();
    }

    public static String getTimestampFormatted(String formatDate) {
        long timestampMillis = getTimestampMillis();

        return getFormatedDate(timestampMillis, formatDate);
    }

    public static String getFormatedDate(long millis, String formatDateString) {
        Date date = new Date(millis);

        return getFormatedDate(date, formatDateString);
    }

    public static String getFormatedDate(Date date, String formatDateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDateString);

        return simpleDateFormat.format(date);
    }
}
