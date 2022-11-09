package com.ae2dms.cw.model;

/**
 * The timer class is a class for couting time duration
 */
public class Timer {
    private int minute;
    private int second;
    long start;
    long current;

    /**
     * Constructor
     * The time starts to count when a timer has been created
     * the start time is stores in 'start'
     */
    public Timer(){
        start = System.currentTimeMillis( );
    }

    /**
     * get formatted time duration
     * @return duration time in fixed format
     */
    public String getTimeString(){
        int duration = getDuration();
        minute = duration / 60;
        second = duration % 60;
        return ("TIME "+minute+": "+second);
    }

    /**
     * count time duration
     * @return duration in int form(second)
     */
    public int getDuration(){
        current = System.currentTimeMillis( );
        return (int)((current-start)/1000);
    }
}
