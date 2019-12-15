package com.github.swapbook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "swapbook.opinions")
public class Opinion {

    @Id
    @PrimaryKeyJoinColumn
    @Column
    private int opinion_id;

    @Column
    private int giver_id;

    @Column
    private int receiver_id;

    @Column
    private String text;

    @Column
    private int mark;

    @Column
    private Date date;

    public Opinion() {
    }

    public int getOpinion_id() {
        return opinion_id;
    }

    public int getGiver_id() {
        return giver_id;
    }

    public void setGiver_id(int giver_id) {
        this.giver_id = giver_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Opinion(int opinion_id, int giver_id, int receiver_id, String text, int mark, Date date) {
        this.opinion_id = opinion_id;
        this.giver_id = giver_id;
        this.receiver_id = receiver_id;
        this.text = text;
        this.mark = mark;
        this.date = date;
    }

}
