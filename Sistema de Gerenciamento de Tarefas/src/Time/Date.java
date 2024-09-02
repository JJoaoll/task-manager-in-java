package Time;

public class Date {
    private int days;
    private int year;
    private boolean isLeapYear;


// COMO organizo melhor as pastas igual no c++?
// COMO LIDAR COM NúMEROS NEGATIVOS??

// ------------------------------------------------------------------------------
// construtores
// ------------------------------------------------------------------------------

    // Objeto nulo!
    public Date() { days = -1; year = -1; isLeapYear = false; }

    public Date(int newDays, int newYear) {
        this.days = newDays; this.year = newYear; this.fixDate();
        this.isLeapYear = leapYearTest(this.year);
    }

    public Date(int newDay, int newMonth, int newYear) {
        int[] myDate = dateToMyDateBF(newDay, newMonth, newYear);
        this.days = myDate[0]; this.year = myDate[1]; fixDate();
        this.isLeapYear = leapYearTest(this.year);
    }

// ------------------------------------------------------------------------------
// Métodos:
// ------------------------------------------------------------------------------

    static boolean leapYearTest(int year) {
        // Verifica se o ano é divisível por 4
        if (year % 4 == 0) {
            // Verifica se o ano não é divisível por 100, a menos que seja divisível por 400
            return year % 100 != 0 || year % 400 == 0;
        } else {
            return false;
        }
    }

    // retorna a tripla (day, month, year)
    static int[] daysToDateBF(int newDays, int newYear) {
        boolean isLeapYear = leapYearTest(newYear);
        int[] daysInMonths = {31, 28, 31, 30, 31, 30,
                31, 31, 30, 31, 30, 31};
        int jan = 0, dec = 11;
        if (isLeapYear) { daysInMonths[1] = 29; }

        int month = jan;
        while (newDays > daysInMonths[month]) {
            newDays -= daysInMonths[month];
            month++;
            if(month > dec) { month = jan; newYear++; }
        }

        return new int[]{newDays, month + 1, newYear};
    }

    // retorna a tupla (days, years)
    static int[] dateToMyDateBF(int day, int month, int year) {
        while(month > 12) { month -= 12; year++;}
        boolean isLeapYear = leapYearTest(year);
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear) { daysInMonths[1] = 29; }



        int totalDays = day;
        for (int i = 0; i < month - 1; ++i) {
            totalDays += daysInMonths[i];
        }

        return new int[]{totalDays, year};
    }

    public int getDay() { return daysToDateBF(this.days, this.year)[0]; }
    public int getMonth() { return daysToDateBF(this.days, this.year)[1]; }
    public int getYear() { return this.year; }
    public int getDays() { return this.days; }
    public Date tomorrow() { return new Date(this.days + 1, this.year); }

// 100SETTERS.

    public void fixDate() {
        int daysInYear = this.isLeapYear ? 366 : 365;
        while (this.days > daysInYear) {
            this.days -= daysInYear;
            this.year++;
            daysInYear = leapYearTest(this.year) ? 366 : 365;
        }
        this.isLeapYear = daysInYear == 366;
    }

    boolean isEqualTo(Date thatDate) {
        this.fixDate(); thatDate.fixDate();
        return this.days == thatDate.getDays() && this.year == thatDate.getYear();
    }

    public boolean isBeforeThan(Date thatDate) {
        this.fixDate();  thatDate.fixDate();
        if (this.year > thatDate.getYear()) { return false; }
        else if (this.year == thatDate.getYear()) {
            return this.days < thatDate.getDays();
        }
        return true;
    }

    public boolean isAfterThan(Date thatDate) {
        this.fixDate(); thatDate.fixDate();
        boolean isBeforeOrEqual = this.isBeforeThan(thatDate) || this.isEqualTo(thatDate);
        return !isBeforeOrEqual;
    }

    static Date firstDate(Date date1, Date date2) { return date1.isBeforeThan(date2) ? date1 : date2; }
    static Date lastDate(Date date1, Date date2) { return date1.isBeforeThan(date2) ? date2 : date1; }

    public boolean isBetween(Date date1, Date date2) { // Intervalo fechado (incluindo data 1 e data 2)
        Date first = firstDate(date1, date2);
        Date last = lastDate(date1, date2);
        return (this.isAfterThan(first) && this.isBeforeThan(last)) || isEqualTo(first) || isEqualTo(last);
    }

    @Override
    public String toString() { return String.format("%02d/%02d/%04d", getDay(), getMonth(), getYear()); }

    public static Date stringToDate(String str) {
        if (str == null || str.length() != 10) {
            throw new IllegalArgumentException("Formato de data inválido. Deve ser dd/MM/yyyy.");
        }

        try {
            short day = Short.parseShort(str.substring(0, 2));
            short month = Short.parseShort(str.substring(3, 5));
            short year = Short.parseShort(str.substring(6, 10));
            return new Date(day, month, year);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato de data inválido. Deve ser dd/MM/yyyy.");
        }
    }


}
