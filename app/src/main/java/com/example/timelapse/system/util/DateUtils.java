package com.example.timelapse.system.util;
/*******************************************************************************
 * Copyright 2008 Mjrz.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author Mjrz contact@mjrz.net
 */
public class DateUtils {
    public static final String FORMAT_YYYYMMDD = "yyyy-MM-dd";
    public static final String FORMAT_DDMMYYYY = "dd-MM-yyyy";
    public static final String FORMAT_YYYYMMDD_SLASHES = "yyyy/MM/dd";
    public static final String GENERIC_DISPLAY_FORMAT = "E, dd MMM yyyy";
    public static final String TIME_DISPLAY_FORMAT = "HH mm ss";
    public static final int LAST_WEEK = 1;
    public static final int LAST_MONTH = 2;

    public static final String formatDate(Date dt, String format) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dt);

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getDefault());
        return (sdf.format(cal.getTime()));
    }

    public static final String getCurrentDate(String format) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getDefault());
        return (sdf.format(cal.getTime()));
    }

    public static final String dateToString(Date dt, String dateformat) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dt);

        StringBuffer ret = new StringBuffer();
        String separator = new String();
        if (dateformat.equals(DateUtils.FORMAT_DDMMYYYY)) {
            separator = "-";
        }
       /* if(dateformat.equals(DateUtils.FORMAT_YYYYMMDD_SLASHES) ) {
            separator = "/";
        }*/
        ret.append(cal.get(Calendar.DATE));
        ret.append(separator);
        ret.append(cal.get(Calendar.MONTH));
        ret.append(separator);
        ret.append(cal.get(Calendar.YEAR));

        return ret.toString();
    }

    public static final String dateToString(Date dt, String tzString, String dateformat) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dt);
        cal.setTimeZone(TimeZone.getTimeZone(tzString));

        StringBuffer ret = new StringBuffer();
        String separator = new String();
        if (dateformat.equals(DateUtils.FORMAT_YYYYMMDD)) {
            separator = "-";
        }
        if (dateformat.equals(DateUtils.FORMAT_YYYYMMDD_SLASHES)) {
            separator = "/";
        }
        ret.append(cal.get(Calendar.YEAR));
        ret.append(separator);
        ret.append(cal.get(Calendar.MONTH) + 1);
        ret.append(separator);
        ret.append(cal.get(Calendar.DATE));

        return ret.toString();
    }


    public static final String dateToDisplayString(Date dt) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dt);
        StringBuilder ret = new StringBuilder();
        String separator = " ";

        ret.append(cal.get(Calendar.DATE));
        ret.append(separator);
        ret.append(getMonthFromNumber(cal.get(Calendar.MONTH)));
        ret.append(separator);
        ret.append(cal.get(Calendar.YEAR));

        return ret.toString();
    }

    public static final String getTimeFromDate(Date dt) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(dt);

        StringBuffer ret = new StringBuffer();
        ret.append(cal.get(Calendar.HOUR));
        ret.append(":");
        ret.append(cal.get(Calendar.MINUTE));

        return ret.toString();
    }

    public static final String getTimeFromDate(Date dt, String tzString) {
        try {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(dt);
            gc.setTimeZone(TimeZone.getTimeZone(tzString));
            StringBuffer ret = new StringBuffer();
            ret.append(gc.get(Calendar.HOUR));
            ret.append(":");
            ret.append(gc.get(Calendar.MINUTE));
            ret.append(" ");
            if (gc.get(Calendar.AM_PM) == 0) {
                ret.append("AM");
            } else {
                ret.append("PM");
            }
            return ret.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static final String getDateTimeFromDate(Date dt, String tzString) {
        try {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(dt);
            gc.setTimeZone(TimeZone.getTimeZone(tzString));
            StringBuffer ret = new StringBuffer();
            ret.append(gc.get(Calendar.YEAR));
            ret.append("-");
            ret.append(gc.get(Calendar.MONTH) - 1);
            ret.append("-");
            ret.append(gc.get(Calendar.DATE));
            ret.append(" ");
            ret.append(gc.get(Calendar.HOUR));
            ret.append(":");
            ret.append(gc.get(Calendar.MINUTE));
            ret.append(" ");
            if (gc.get(Calendar.AM_PM) == 0) {
                ret.append("AM");
            } else {
                ret.append("PM");
            }
            return ret.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static final String calendarToString(Calendar cal, String dateformat) {
        StringBuffer ret = new StringBuffer();
        if (dateformat.equals(FORMAT_YYYYMMDD)) {
            ret.append(cal.get(Calendar.YEAR));
            ret.append("-");

            String month = null;
            int mo = cal.get(Calendar.MONTH) + 1; /* Calendar month is zero indexed, string months are not */
            if (mo < 10) {
                month = "0" + mo;
            } else {
                month = "" + mo;
            }
            ret.append(month);

            ret.append("-");

            String date = null;
            int dt = cal.get(Calendar.DATE);
            if (dt < 10) {
                date = "0" + dt;
            } else {
                date = "" + dt;
            }
            ret.append(date);
        }

        return ret.toString();
    }


    public static final GregorianCalendar getCurrentCalendar(String utimezonestring) {
        try {
            GregorianCalendar gc = new GregorianCalendar();

            gc.setTimeZone(TimeZone.getTimeZone(utimezonestring));
            return gc;
        } catch (Exception e) {
            //If exception, return server TimeStamp
            return new GregorianCalendar();
        }
    }

    public static String[] getDateRange(int cmd) {
        GregorianCalendar gc = new GregorianCalendar();
        GregorianCalendar gc2 = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //$NON-NLS-1$
        String ret[] = new String[2];
        ret[1] = sdf.format(gc.getTime());

        if (cmd == LAST_WEEK) {
            for (int i = 0; i < 7; i++) {
                gc2.add(Calendar.DATE, -1);
            }

        }
        if (cmd == LAST_MONTH) {
            gc2.add(Calendar.MONTH, -1);
        }
        ret[0] = sdf.format(gc2.getTime());
        return ret;
    }


    public static final String getDayString(int day) {
        switch (day) {
            case Calendar.SUNDAY:
                return "SUNDAY";
            case Calendar.MONDAY:
                return "MONDAY";
            case Calendar.TUESDAY:
                return "TUESDAY";
            case Calendar.WEDNESDAY:
                return "WEDNESDAY";
            case Calendar.THURSDAY:
                return "THURSDAY";
            case Calendar.FRIDAY:
                return "FRIDAY";
            case Calendar.SATURDAY:
                return "SATURDAY";
        }
        return "";
    }

    public static final String getMonthFromNumber(int number) {
        switch (number) {
            case 0:
                return "Января";
            case 1:
                return "Февраля";
            case 2:
                return "Марта";
            case 3:
                return "Апреля";
            case 4:
                return "Мая";
            case 5:
                return "Июня";
            case 6:
                return "Июля";
            case 7:
                return "Августа";
            case 8:
                return "Сентября";
            case 9:
                return "Октября";
            case 10:
                return "Ноября";
            case 11:
                return "Декабря";
        }
        return "";
    }

    public static String getRussianMonth(String month) {
        String formattedMonth = month.toLowerCase().trim();
        switch (formattedMonth) {
            case "january":
                return "Январь";
            case "february":
                return "Февраль";
            case "march":
                return "Март";
            case "april":
                return "Апрель";
            case "may":
                return "Май";
            case "june":
                return "Июнь";
            case "july":
                return "Июль";
            case "august":
                return "Август";
            case "september":
                return "Сентябрь";
            case "october":
                return "Октябрь";
            case "november":
                return "Ноябрь";
            case "december":
                return "Декабрь";
        }
        return "";
    }
}
