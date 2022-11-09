package com.ae2dms.cw.model;

import com.ae2dms.cw.Main;
import com.ae2dms.cw.consts.WorldConst;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Score class stores the score and the time the score is made
 */
public class Score {
    private int mark;
    SimpleDateFormat ft;
    Date dNow;

    /**
     * initialize
     */
    Score(){
        mark = 0;
        ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    }

    /**
     * This method stores the score and time into a txt file with fixed format
     */
    public void store(){
        dNow = new Date();
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter((Main.class.getResource(WorldConst.score).getFile()),true));
            if(mark >=1000){
                out.write(mark+" "+ft.format(dNow)+'\n');
            }
            else if(mark >=100){
                out.write("0"+mark+" "+ft.format(dNow)+'\n');
            }
            else if(mark>=10){
                out.write("00"+mark+" "+ft.format(dNow)+'\n');
            }
            else{
                out.write("000"+mark+" "+ft.format(dNow)+'\n');
            }
            out.close();
        } catch (IOException ignored) {}
    }

    /**
     * get mark
     * @return amrk
     */
    public int getMark() {
        return mark;
    }

    /**
     * set mark
     * @param mark
     */
    public void setMark(int mark) {
        this.mark = mark;
    }
}
