package com.draell.diary.time;
import java.util.List;

public class Session {
  private Date date; 
  private Time time;


  public Session(Time newTime, Date newDate) { time = newTime; date = newDate; }
  public Session(Date newDate, Time newTime) { time = newTime; date = newDate; }
  // teste valores nulos
  public Session() {
    time = new Time(-1, -1, -1);
    date = new Date(-1, -1);
  }

  public Session(Session copyMe) {
    this.date = new Date(copyMe.getDate());
    this.time = new Time(copyMe.getTime());
  }

  // Getters:
  public Time getTime() { return new Time(time); }
  public Date getDate() { return new Date(date); }

  // Setters:
  public void setTime(Time newTime) { time = newTime; }
  void setDate(Date newDate) { date = newDate; }

  // Another Methods
  public String getDateString() { return this.date.toString(); }
  public String getTimeString() { return this.time.toString(); }    

  public void fixSession() { time.fixTime(); date.fixDate(); }


  public boolean isEqualTo(Session thatSession) { // diferenciando do "this->"
    this.fixSession(); thatSession.fixSession(); // fixers
    return date.isEqualTo(thatSession.getDate()) &&
      time.isEqualTo(thatSession.getTime());
  }

  public boolean isBeforeThan(Session thatSession) {
    this.fixSession(); thatSession.fixSession(); // fixers
    if (date.isBeforeThan(thatSession.getDate())) {
      return true;
    }
    return date.isEqualTo(thatSession.getDate()) &&
      time.isBeforeThan(thatSession.getTime());
  }

  public boolean isAfterThan(Session thatSession) {
    this.fixSession(); thatSession.fixSession();
    return !(this.isBeforeThan(thatSession) || this.isEqualTo(thatSession));
  }

  // Retorna o mais
  public static Session nextSessionFrom(List<Session> sessions) {
    Time timeC = new Time(-1, -1, -1);
    Date dataC = new Date(-1, -1);
    Session sessionC = new Session(timeC, dataC);
    if (sessions.isEmpty()) {
      return sessionC;
    }

    sessionC = sessions.getFirst();

    for (Session session : sessions) {
      if (session.isBeforeThan(sessionC)) {
        sessionC = session;
      }
    }
    return sessionC;
  }

  public boolean isIn(List<Session> sessions) {
    for(Session session : sessions) {
      if(this.isEqualTo(session)) {
        return true;
      }
    }
    return false;
  }

  public static Session firstSession(Session session1, Session session2) { // "min" binário
    if(session1.isBeforeThan(session2)) {
      return session1;
    }
    return session2;
  }

  public static Session lastSession(Session session1, Session session2) { // "max" binário
    if(session1.isBeforeThan(session2)) {
      return session2;
    }
    return session1;
  }

  // Intervalo fechado 
  public boolean isBetween(Session session1, Session session2) { 
    Session first = firstSession(session1, session2); 
    Session last = lastSession(session1, session2);
    return ((this.isAfterThan(first) && this.isBeforeThan(last)) || isEqualTo(first) ||
        isEqualTo(last));              
  }

  @Override
  public String toString() {
    String dateString = this.date.toString();
    String timeString = this.time.toString();

    return dateString + " " + timeString;
  }

  public String toTimeDatetring() {
    String dateString = this.date.toString();
    String timeString = this.time.toString();

    return timeString + " " + dateString;
  }

  public static String getFirstPart(String firstSecond) {
    int pos = firstSecond.indexOf(" ");
    return firstSecond.substring(0, pos);
  }

  public static String getSecondPart(String firstSecond) {
    int pos = firstSecond.indexOf(" ");
    return firstSecond.substring(pos + 1);
  }

  // Espera um formato dateTime.
  public static Session stringToSession(String convertMe) {
    String firstPart = getFirstPart(convertMe);
    String secondPart = getSecondPart(convertMe);
    //Data e Hora
    Date newDate = Date.stringToDate(firstPart);
    Time newTime = Time.stringToTime(secondPart);
    return new Session(newTime, newDate);
  }

  public Session absoluteMinusInSession(Session thatSession) {
    this.fixSession(); thatSession.fixSession();

    if(this.getDate().isEqualTo(thatSession.getDate())) {
      Date returnDate = new Date(0, 0);
      return new Session(this.getTime().absoluteMinus(thatSession.getTime()), returnDate);
    }

    else {
      // SIDE EFFECTS AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      // ODEIO PROGRAMACAO NAO FUNCIONAL AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA!!!!!!
      Session first0 = firstSession(this, thatSession);
      Session first = new Session(first0.getTime(), first0.getDate());
      Session last = lastSession(this, thatSession);
      // now lets return last - first

      int days = 0;
      while (first.getDate().tomorrow().isBeforeThan(last.getDate())) {
        first.setDate(first.getDate().tomorrow());
        days++;
      }
      System.out.println("daysInFunction: "+days);
      Time sum = Time.sumTime(first.getTime().remainingTimeToTomorrow(), last.getTime());
      /* O 23 é o -1 módulo 24, por exemplo. Por isso,
         o caso onde qualquer dos dois tempos(remaining(first) ou last) forem maiores
         do que a soma já é um caso onde a soma passou de 24.*/
      if(last.getTime().isAfterThan(sum)){
        Date returnDate = new Date(days + 1, 0);
        return new Session(sum, returnDate);

      }
      else{
        Date returnDate = new Date(days, 0);
        return new Session(sum, returnDate);
      }

    }
  }


  public int absoluteMinusInDays(Session thatSession) {
    this.fixSession(); thatSession.fixSession();

    if(this.getDate().isEqualTo(thatSession.getDate())) { return 0; }

    else {
      // SIDE EFFECTS AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      // ODEIO PROGRAMACAO NAO FUNCIONAL AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA!!!!!!
      Session first0 = firstSession(this, thatSession);
      Session first = new Session(first0.getTime(), first0.getDate());
      Session last = lastSession(this, thatSession);
      // now lets return last - first

      int days = 0;
      while (first.getDate().tomorrow().isBeforeThan(last.getDate())) {
        first.setDate(first.getDate().tomorrow());
        days++;
      }

      return days;
    }
  }



  public int[] absoluteMinusInVector(Session thatSession) {
    Session ses = this.absoluteMinusInSession(thatSession);
    int[] result = new int[6];
    result[0] = ses.getDate().getYear();
    result[1] = ses.getDate().getMonth() - 1;;
    result[2] = ses.getDate().getDay();
    result[3] = ses.getTime().getHour();
    result[4] = ses.getTime().getMin();
    result[5] = ses.getTime().getSec();

    return result;
  }

  public String absoluteMinusInString(Session thatSession) {
    String result = "";
    int[] convertMe = absoluteMinusInVector(thatSession);
    if(convertMe[0] > 0){
      result += convertMe[0] + "y";
    }
    if(convertMe[1] > 0){
      result += convertMe[1] + "m";
    }
    if(convertMe[2] > 0){
      result += convertMe[2] + "d";
    }
    if(convertMe[3] > 0){
      result += convertMe[3] + "h";
    }
    if(convertMe[4] > 0){
      result += convertMe[4] + "m";
    }
    if(convertMe[5] > 0){
      result += convertMe[5] + "s";
    }
    return result;
  }

  public static Session absoluteMinusInSession(Session ses1, Session ses2) { return ses1.absoluteMinusInSession(ses2); }
  public static int[] absoluteMinusInVector(Session ses1, Session ses2) { return ses1.absoluteMinusInVector(ses2); }

  public Session timeBeforeInSession(Session thatSession){
    if(this.isAfterThan(thatSession)) { return new Session(); }
    else if(this.isEqualTo(thatSession)) {
      Date returnDate = new Date(0, 0);
      Time returnTime = new Time(0, 0, 0);
      return new Session(returnTime, returnDate);
    }
    else{
      return this.absoluteMinusInSession(thatSession);
    }
  }

  public Session timeAfter(Session thatSession){
    if(this.isBeforeThan(thatSession)) { return new Session(); }
    else if(this.isEqualTo(thatSession)) {
      Date returnDate = new Date(0, 0);
      Time returnTime = new Time(0, 0, 0);
      return new Session(returnTime, returnDate);
    }
    else{
      return this.absoluteMinusInSession(thatSession);
    }
  }


} // Final da classe!!
