//package com.example.liquorshops.utils;
//
//import android.util.Log;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Locale;
//import java.util.TimeZone;
//
//public class HelperTime {
//
//
//    public interface Constants {
//        int TIMESTAMP_MONTH_START = 5;
//        int TIMESTAMP_MONTH_END = 7;
//        int TIMESTAMP_DATE_START = 8;
//        int TIMESTAMP_DATE_END = 10;
//        int TIMESTAMP_YEAR_START = 0;
//        int TIMESTAMP_YEAR_END = 4;
//        int TIMESTAMP_HOUR_START = 11;
//        int TIMESTAMP_HOUR_END = 13;
//        int TIMESTAMP_MINUTE_START = 14;
//        int TIMESTAMP_MINUTE_END = 16;
//        int TIMESTAMP_SEC_START = 17;
//        int TIMESTAMP_SEC_END = 19;
//        String SLASH = "/";
//        String COLON = ":";
//        String TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
//        String CONSTANT_TODAY = "Today";
//        String CONSTANT_YESTERDAY = "Yesterday";
//        int CALCULATE_MINUTES = 1000 * 60;
//        String HYPHEN = "-";
//    }
//
//    private static HelperTime helperTime;
//    private String[] months = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
//    private Calendar calendarNew, calendar;
//    private SimpleDateFormat simpleDateFormat, simpleDateFormatUTC;
//
//    public static HelperTime get() {
//        if (helperTime == null)
//            helperTime = new HelperTime();
//        return helperTime;
//    }
//
//    private HelperTime() {
//        simpleDateFormat = new SimpleDateFormat(Constants.TIMESTAMP, Locale.getDefault());
//        simpleDateFormatUTC = new SimpleDateFormat(Constants.TIMESTAMP, Locale.getDefault());
//        simpleDateFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
//        calendar = Calendar.getInstance();
//        calendarNew = Calendar.getInstance();
//    }
//
//    public String getTimeStamp(Calendar calendar) {
//        return calendar.get(Calendar.YEAR) + Constants.HYPHEN + getIntoTwo(calendar.get(Calendar.MONTH))
//                + Constants.HYPHEN + getIntoTwo(calendar.get(Calendar.DAY_OF_MONTH)) + " " + getIntoTwo(calendar.get(Calendar.HOUR_OF_DAY)) +
//                ":" + getIntoTwo(calendar.get(Calendar.MINUTE)) + ":" + getIntoTwo(calendar.get(Calendar.SECOND));
//    }
//
//    public String getTimeStampUtc(Calendar calendar) {
//        try {
//            calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
//            calendar.setTime(simpleDateFormatUTC.parse(getTimeStamp(calendar)));
//            return getTimeStamp(calendar);
//        } catch (ParseException ignored) {
//        }
//        return getTimeStamp(calendar);
//    }
//
//    public String getTimeStampUTC() {
//        return simpleDateFormatUTC.format(new Date());
//    }
//
//    public String getTimeStamp() {
//        calendar.setTimeZone(TimeZone.getDefault());
//        return calendar.get(Calendar.YEAR) + Constants.HYPHEN + getIntoTwo(calendar.get(Calendar.MONTH))
//                + Constants.HYPHEN + getIntoTwo(calendar.get(Calendar.DAY_OF_MONTH)) + " " + getIntoTwo(calendar.get(Calendar.HOUR_OF_DAY)) +
//                ":" + getIntoTwo(calendar.get(Calendar.MINUTE)) + ":" + getIntoTwo(calendar.get(Calendar.SECOND));
//    }
//
//    private String parseDateFromString(String entryTime) {
//        if (entryTime.length() != 10) {
//            if (entryTime.substring(TIMESTAMP_MONTH_END - 1).equals("/") || entryTime.substring(TIMESTAMP_MONTH_END - 1).equals("-")) {
//                entryTime = entryTime.substring(0, TIMESTAMP_MONTH_START) + "0" + entryTime.substring(TIMESTAMP_MONTH_START);
//            }
//            if (entryTime.length() != 10) {
//                entryTime = entryTime.substring(0, TIMESTAMP_DATE_START) + "0" + entryTime.substring(TIMESTAMP_DATE_START);
//            }
//        }
//        if (entryTime.length() == 10) {
//            int month = Integer.parseInt(entryTime.substring(TIMESTAMP_MONTH_START, Constants.TIMESTAMP_MONTH_END));
//            if (month > 12) {
//                month = 12;
//            } else if (month < 1) {
//                month = 1;
//            }
//            return entryTime.substring(Constants.TIMESTAMP_DATE_START, Constants.TIMESTAMP_DATE_END) + ", " + months[month].substring(0, 3)
//                    + ", " + entryTime.substring(Constants.TIMESTAMP_YEAR_START, Constants.TIMESTAMP_YEAR_END);
//        }
//        return entryTime;
//    }
//
//    public boolean after(Calendar c1, Calendar c2) {
//        int c1Month = c1.get(Calendar.MONTH);
//        int c1Day = c1.get(Calendar.DAY_OF_MONTH);
//
//        int c2Month = c2.get(Calendar.MONTH);
//        int c2Day = c2.get(Calendar.DAY_OF_MONTH);
//        return c1Day == (c2Day - 1) && c1Month == c2Month;
//    }
//
//    public int getCurrentYear() {
//        return calendar.get(Calendar.YEAR);
//    }
//
//    public int getCurrentMonth() {
//        return calendar.get(Calendar.MONTH);
//    }
//
//    public int getCurrentDate() {
//        return calendar.get(Calendar.DAY_OF_MONTH);
//    }
//
//    public Calendar getDateCalendar(String timeStamp) {
//        Calendar calendarNew = Calendar.getInstance();
//        try {
//            calendarNew.setTime(simpleDateFormat.parse(timeStamp));
//            return calendarNew;
//        } catch (ParseException e) {
//            Log.e("",e.getMessage());
//        }
//        return calendarNew;
//    }
//
//    public Calendar getLocalDateCalendar(String timeStamp) {
//        Calendar calendarNew = Calendar.getInstance();
//        try {
//            calendarNew.setTime(simpleDateFormatUTC.parse(timeStamp));
//            calendarNew.setTimeZone(TimeZone.getDefault());
//            return calendarNew;
//        } catch (ParseException e) {
//            Log.e("",e.getMessage());
//        }
//        return calendarNew;
//    }
//
//    public int subValue(int comeFor, int sub) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(comeFor, -sub);
//        return calendar.get(comeFor);
//    }
//
//    public Calendar subIntoDate(Calendar calendar, int comeFor, int sub) {
//        calendar.add(comeFor, -sub);
//        return calendar;
//    }
//
//    public Calendar addIntoDate(int comeFor, int add) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(comeFor, add);
//        return calendar;
//    }
//
//    public Calendar addIntoDate(Calendar calendar, int comeFor, int add) {
//        calendar.add(comeFor, add);
//        return calendar;
//    }
//
//    public Calendar getCalender() {
//        calendar = Calendar.getInstance();
//        return calendar;
//    }
//
//    public String getDateInFormat(int date, int month, int year) {
//        if (month > 11) {
//            month = 11;
//        } else if (month < 0) {
//            month = 0;
//        }
//        return getIntoTwo(date) + " " + months[month + 1].substring(0, 3) + ", " + year;
//    }
//
//    public String getDateInFormat(String date) {
//        return parseDateFromString(date.substring(Constants.TIMESTAMP_YEAR_START, Constants.TIMESTAMP_DATE_END));
//    }
//
//    public String getDateInFormatToPost(int date, int month, int year) {
//        return getIntoTwo(date) + Constants.HYPHEN + getIntoTwo(month + 1)
//                + Constants.HYPHEN + year + " 00:00:00";
//    }
//
//    public String getDateInFormatToPost(String date) {
//        if (date.length() == 10) {
//            return date.substring(Constants.TIMESTAMP_YEAR_START, Constants.TIMESTAMP_YEAR_END)
//                    + Constants.HYPHEN + getIntoTwo((Integer.parseInt(date.substring(TIMESTAMP_MONTH_START, Constants.TIMESTAMP_MONTH_END)) + 1))
//                    + Constants.HYPHEN + date.substring(Constants.TIMESTAMP_DATE_START, Constants.TIMESTAMP_DATE_END) + " 00:00:00";
//        } else {
//            return date.substring(Constants.TIMESTAMP_YEAR_START, Constants.TIMESTAMP_YEAR_END)
//                    + Constants.HYPHEN + getIntoTwo((Integer.parseInt(date.substring(TIMESTAMP_MONTH_START, Constants.TIMESTAMP_MONTH_END)) + 1))
//                    + Constants.HYPHEN + date.substring(Constants.TIMESTAMP_DATE_START, Constants.TIMESTAMP_DATE_END) + date.substring(Constants.TIMESTAMP_DATE_END, Constants.TIMESTAMP_SEC_END);
//        }
//    }
//
//    public String setDateInFormat(int date, int month, int year) {
//        return year + "/" + getIntoTwo(month)
//                + "/" + getIntoTwo(date) + " 00:00:00";
//    }
//
//
//    public String getIntoTwo(int month) {
//        if (month > 9) {
//            return "" + month;
//        } else
//            return 0 + "" + month;
//    }
//
//    public String getWithAmPm(String timestamp) {
//        int i = Integer.parseInt(timestamp.substring(Constants.TIMESTAMP_HOUR_START, Constants.TIMESTAMP_HOUR_END));
//        if (i == 12) {
//            return "00:" + timestamp.substring(Constants.TIMESTAMP_MINUTE_START, Constants.TIMESTAMP_MINUTE_END) + " PM";
//        } else if (i >= 12) {
//            int j = i - 12;
//            String s;
//            if (j < 10) {
//                s = "0" + j;
//            } else {
//                s = String.valueOf(j);
//            }
//            return s + ":" + timestamp.substring(Constants.TIMESTAMP_MINUTE_START, Constants.TIMESTAMP_MINUTE_END) + " PM";
//        } else {
//            return timestamp.substring(Constants.TIMESTAMP_HOUR_START, Constants.TIMESTAMP_MINUTE_END) + " AM";
//        }
//    }
//
//    public String getAmPm(String timestamp) {
//        int i = Integer.parseInt(timestamp.substring(0, 2));
//        if (i == 12) {
//            return "PM";
//        } else if (i >= 12) {
//            return "PM";
//        } else {
//            return "AM";
//        }
//    }
//
//    public String getDateFromDate(String date) {
//        return date.substring(Constants.TIMESTAMP_DATE_START, Constants.TIMESTAMP_DATE_END);
//    }
//
//    public String getExceptDateDetailsFromDate(String date) {
//        return months[Integer.parseInt(date.substring(TIMESTAMP_MONTH_START, Constants.TIMESTAMP_MONTH_END))].substring(0, 3) + ", " + date.substring(Constants.TIMESTAMP_YEAR_START, Constants.TIMESTAMP_YEAR_END);
//    }
//
//    public String getExceptDateDetailsFromDate(int month, int year) {
//        return months[month] + ", " + year;
//    }
//
//    public String getTimeFromDate(String timestamp) {
//        int i = Integer.parseInt(timestamp.substring(Constants.TIMESTAMP_HOUR_START, Constants.TIMESTAMP_HOUR_END));
//        if (i == 12) {
//            return "12:" + timestamp.substring(Constants.TIMESTAMP_MINUTE_START, Constants.TIMESTAMP_MINUTE_END);
//        } else if (i >= 12) {
//            int j = i - 12;
//            String s;
//            if (j < 10) {
//                s = "0" + j;
//            } else {
//                s = String.valueOf(j);
//            }
//            return s + ":" + timestamp.substring(Constants.TIMESTAMP_MINUTE_START, Constants.TIMESTAMP_MINUTE_END);
//        }
//        return timestamp.substring(Constants.TIMESTAMP_HOUR_START, Constants.TIMESTAMP_MINUTE_END);
//    }
//
//    public String getLocaleTimeStampNew(String utcTimeStamp) {
//        try {
//            utcTimeStamp = utcTimeStamp.replaceAll("T", " ");
//            calendarNew.setTimeZone(TimeZone.getTimeZone("UTC"));
//            calendarNew.setTime(simpleDateFormatUTC.parse(utcTimeStamp));
//            calendarNew.setTimeZone(TimeZone.getDefault());
//            return calendarNew.get(Calendar.YEAR) + Constants.HYPHEN + getIntoTwo(calendarNew.get(Calendar.MONTH))
//                    + Constants.HYPHEN + getIntoTwo(calendarNew.get(Calendar.DAY_OF_MONTH)) + " " +
//                    getIntoTwo(calendarNew.get(Calendar.HOUR_OF_DAY)) +
//                    Constants.COLON + getIntoTwo(calendarNew.get(Calendar.MINUTE))
//                    + Constants.COLON + getIntoTwo(calendarNew.get(Calendar.SECOND));
//        } catch (ParseException e) {
//            Log.e("",e.getMessage());
//        }
//        return utcTimeStamp;
//    }
//
//    public String getTimeUnitFromDate(String date) {
//        return getAmPm(date.substring(Constants.TIMESTAMP_HOUR_START, Constants.TIMESTAMP_SEC_END));
//    }
//
//    public int getMilliDifference(Calendar latestDate, Calendar oldDate) {
//        if ((int) (latestDate.getTimeInMillis() - oldDate.getTimeInMillis()) > 0) {
//            return (int) (latestDate.getTimeInMillis() - oldDate.getTimeInMillis());
//        } else {
//            return 1000;
//        }
//    }
//
//    public long getMilliDifference(long latest, long last) {
//        return latest - last;
//    }
//
//    public int getDateDifference(int type, Calendar latestDate, Calendar oldDate) {
//        if (latestDate.compareTo(oldDate) < 0) {
//            long different = latestDate.getTime().getTime() - oldDate.getTime().getTime();
//            long secondsInMilli = 1000;
//            if (type == Calendar.HOUR_OF_DAY) {
//                long minutesInMilli = secondsInMilli * 60;
//                long hoursInMilli = minutesInMilli * 60;
//                return (int) (different / hoursInMilli);
//            } else if (type == Calendar.SECOND) {
//                return (int) (different / secondsInMilli);
//            } else if (type == Calendar.MINUTE) {
//                long minutesInMilli = secondsInMilli * 60;
//                return (int) (different / minutesInMilli);
//            } else if (type == Calendar.MONTH) {
//                int diffYear = oldDate.get(Calendar.YEAR) - latestDate.get(Calendar.YEAR);
//                return diffYear * 12 + oldDate.get(Calendar.MONTH) - latestDate.get(Calendar.MONTH);
//            } else if (type == Calendar.YEAR) {
//                return oldDate.get(Calendar.YEAR) - latestDate.get(Calendar.YEAR);
//            } else {
//                long minutesInMilli = secondsInMilli * 60;
//                long hoursInMilli = minutesInMilli * 60;
//                long daysInMilli = hoursInMilli * 24;
//                return (int) (different / daysInMilli);
//            }
//        }
//        return 0;
//    }
//
//    public String getDateFromPostDate(String date) {
//        int month = Integer.parseInt(date.substring(3, 5));
//        month -= 1;
//        if (month > 11) {
//            month = 11;
//        } else if (month < 0) {
//            month = 0;
//        }
//        if (date.length() == 10)
//            return date.substring(6, 10) + "/" + getIntoTwo(month)
//                    + "/" + getIntoTwo(Integer.parseInt(date.substring(0, 2))) + " 00:00:00";
//        else {
//            return date.substring(6, 10) + "/" + getIntoTwo(month)
//                    + "/" + getIntoTwo(Integer.parseInt(date.substring(0, 2))) + date.substring(TIMESTAMP_DATE_END, date.length());
//        }
//    }
//
//
//    public String setTimeProperly(String timeStamp) {
//        if (timeStamp.length() == 3) {
//            timeStamp = "0" + timeStamp.charAt(1) + ":0" + timeStamp.charAt(1) + ":00";
//        } else if (timeStamp.length() == 4) {
//            if (timeStamp.charAt(1) == ':') {
//                timeStamp = "0" + timeStamp.charAt(0) + timeStamp.substring(1, 4) + ":00";
//            } else {
//                timeStamp = timeStamp.substring(0, 3) + timeStamp.charAt(3) + "0:00";
//            }
//        } else if (timeStamp.length() == 5) {
//            timeStamp = timeStamp + ":00";
//        }
//        return timeStamp;
//    }
//
//}
