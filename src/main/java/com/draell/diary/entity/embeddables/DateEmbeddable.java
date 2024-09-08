package com.draell.diary.entity.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import com.draell.diary.time.Date;

@Embeddable
public class DateEmbeddable {

    @Column
    private int days;

    @Column
    private int year;

    public DateEmbeddable() {}

    public DateEmbeddable(int newDays, int newYear) {
      Date d = new Date(newDays, newYear);
      this.days = d.getDays();
      this.year = d.getYear();
    }

    public DateEmbeddable(DateEmbeddable copyMe) {
      this.days = copyMe.getDays();
      this.year = copyMe.getYear();
    }

    public int getDays() { return this.days; }
    public int getYear() { return this.year; }

    @Override
    public String toString() {
      Date d = new Date(this.days, this.year);
      return d.toString();
    }
}
