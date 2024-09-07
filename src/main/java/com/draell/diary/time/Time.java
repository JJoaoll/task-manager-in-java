package com.draell.diary.time;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Time {

    // Separar Time de Clock

    private int second;
    private int minute;
    private int hour;


    // CONSTRUCTORS:
    // -------------
    
    // Default
    public Time(int newHour, int newMin, int newSec){
        hour = newHour;  minute = newMin;  second = newSec;
        fixTime();
    }

    // Null
    public Time(){ hour = -1; minute = -1; second = -1; }

    // Clone
    public Time(Time copyMe) {
      this.second = copyMe.getSec();
      this.minute = copyMe.getMin();
      this.hour = copyMe.getHour();
    }


    // getters:
    public int getHour(){ return hour; }
    public int getMin(){ return minute; }
    public int getSec(){ return second; }

    // setters:
    void setHour(int newHour){ hour = newHour; fixTime(); }
    void setMin(int newMin){ minute = newMin; fixTime(); }
    void setSec(int newSec){ second = newSec; fixTime(); }

    // resto:
    public void fixHour() {
        while (hour >= 24)
            hour -= 24;
    }

    public void fixMin() {
        // fix min >= 60
        while (minute >= 60) {
            minute -= 60;
            hour++;
        }
        // fix min < 0;
        while (minute < 0) {
            minute += 60;
            hour--;
        }
    }

    public void fixSec() {
        // fix sec >= 60
        while (second >= 60) {
            second -= 60;
            minute++;
        }
        // fix sec < 0;
        while (second < 0) {
            second += 60;
            minute--;
        }
    }

    // Precisei pensar em como lidar com "02:-42:05", por ex.
    public void fixTime(){
        fixSec(); fixMin(); fixHour();
        // time < 0 test:
        if (hour < 0) {
            hour = -1;
            minute = -1;
            second = -1; /* Padrão pra saber qnd deu algum erro. */
        }
    }

    public static Time sumTime(Time time1, Time time2) { return time1.sumTime(time2); }

    public Time sumTime(Time toSum){
        this.hour += toSum.getHour();
        this.minute += toSum.getMin();
        this.second += toSum.getSec();

        return this;
    }

    public boolean isEqualTo(Time thatTime) {
        fixTime(); thatTime.fixTime(); // <- fixers
        return hour == thatTime.getHour() && minute == thatTime.getMin() &&
                second == thatTime.getSec();
    }

    public boolean isBeforeThan(Time thatTime) {
        fixTime(); thatTime.fixTime(); // <- fixers
        if (this.hour < thatTime.getHour()) {
            return true;
        }
        if (this.hour == thatTime.getHour() && this.minute < thatTime.getMin()) {
            return true;
        }
        return this.hour == thatTime.getHour() && this.minute == thatTime.getMin() &&
                this.second < thatTime.getSec();
    }

    public boolean isAfterThan(Time thatTime){
        fixTime(); thatTime.fixTime();
        return !(this.isBeforeThan(thatTime) || this.isEqualTo(thatTime));
    }

    public static Time firsTime(Time time1, Time time2) { // "min" binário
        if (time1.isBeforeThan(time2)) {
            return time1;
        }
        return time2;
    }

    public static Time lastTime(Time time1, Time time2) { // "max" binário
        if (time1.isBeforeThan(time2)) {
            return time2;
        }
        return time1;
    }

    // Intervalo fechado (incluindo os time1 e time2)
    public boolean isBetween(Time time1, Time time2) {
        Time first = firsTime(time1, time2); Time last = lastTime(time1, time2);
        return (this.isBeforeThan(last) && this.isAfterThan(first)) || isEqualTo(first) ||
                isEqualTo(last);
    }

    public Time remainingTimeToTomorrow(){
        this.fixTime();
        int newHour = 23 - this.getHour();
        int newMin = 59 - this.getMin();
        int newSec = 60 - this.getSec();

        // Já tem fixer no construtor.
        return new Time(newHour, newMin, newSec);
    }

    // subtrai e pega o valor absoluto
    public static Time absoluteMinus(Time time1, Time time2) {
        time1.fixTime(); time2.fixTime();
        if(time1.isEqualTo(time2)) { return new Time(0, 0 ,0); }
        Time first = firsTime(time1, time2);
        Time last = lastTime(time1, time2);
        // first.getHour() < last.getHour() ctz.
        int newHour = last.getHour() - first.getHour();
        int newMin = last.getMin() - first.getMin();
        int newSec = last.getSec() - first.getSec();

        // Já tem fixer no construtor.
        return new Time(newHour, newMin, newSec);
    }

    public Time absoluteMinus(Time thatTime){ return absoluteMinus(this, thatTime); }

    @Override
    public String toString() { return String.format("%02d:%02d:%02d", getHour(), getMin(), getSec()); }

    public static List<String> splitString(String str, char delimiter) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenStream = new StringTokenizer(str, String.valueOf(delimiter));
        while (tokenStream.hasMoreTokens()) {
            tokens.add(tokenStream.nextToken());
        }
        return tokens;
    }


    public static Time stringToTime(String convertMe) {
        List<String> parts = splitString(convertMe, ':');

        if (parts.size() != 3) {
            throw new IllegalArgumentException("Invalid time format");
        }

        int hour   = Integer.parseInt(parts.get(0));
        int minute = Integer.parseInt(parts.get(1));
        int second = Integer.parseInt(parts.get(2));

        return new Time(hour, minute, second);
    }
}
