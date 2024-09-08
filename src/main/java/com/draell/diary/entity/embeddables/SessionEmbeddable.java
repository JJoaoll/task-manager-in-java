package com.draell.diary.entity.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import com.draell.diary.time.Date;
import com.draell.diary.time.Time;
import com.draell.diary.time.Session;

@Embeddable
public class SessionEmbeddable {

  @Embedded
  private DateEmbeddable date;

  @Embedded 
  private TimeEmbeddable time;

  // Constructor's
  public SessionEmbeddable() {}

  public SessionEmbeddable(DateEmbeddable newDate, TimeEmbeddable newTime) {
    this.date = new DateEmbeddable(newDate.getDays(), newDate.getYear());
    this.time = new TimeEmbeddable(newTime.getHour(), newTime.getMin(),
        newTime.getSec());
  }

  public SessionEmbeddable(SessionEmbeddable copyMe) {
    this.date = new DateEmbeddable(copyMe.getDateEmbeddable());
    this.time = new TimeEmbeddable(copyMe.getTimeEmbeddable());
  }

  // Getter's
  public DateEmbeddable getDateEmbeddable() { return new DateEmbeddable(this.date); }
  public TimeEmbeddable getTimeEmbeddable() { return new TimeEmbeddable(this.time); }

  @Override
  public String toString() {
    Date d = new Date(this.date.getDays(), this.date.getYear());
    Time t = new Time(this.time.getHour(), this.time.getMin(),
        this.time.getSec());
    return (new Session(d, t)).toString();
  }
}

