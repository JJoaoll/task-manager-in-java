package com.draell.diary.entity.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

import com.draell.diary.time.Time;

@Embeddable
public class TimeEmbeddable {

  @Column
  private int hour;

  @Column
  private int min;

  @Column
  private int sec;

  public TimeEmbeddable() {
  }

  public TimeEmbeddable(int newHour, int newMin, int newSec) {
    Time t = new Time(newHour, newMin, newSec); // trata os valores internamente
    this.hour = t.getHour();
    this.min =  t.getMin();
    this.sec =  t.getSec();
  }

  public TimeEmbeddable(TimeEmbeddable copyMe) {
    this.hour = copyMe.getHour();
    this.min =  copyMe.getMin();
    this.sec =  copyMe.getSec();
  } 

  public int getHour() { return this.hour; }
  public int getMin() { return this.min; }
  public int getSec() { return this.sec; }

  @Override
  public String toString() {
    Time t = new Time(this.hour, this.min, this.sec);
    return t.toString();
  }
}

