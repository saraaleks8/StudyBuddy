package si.uni_lj.fe.tnuv.studybuddy;

import java.util.HashMap;
import java.util.UUID;

public class Sessions {

//    private static UUID id;
    private Integer intervals, focusTime;
    private String type;

    public Sessions (){
    }

    public Sessions(Integer intervals, Integer focusTime, String type){
        this.intervals = intervals;
        this.focusTime = focusTime;
        this.type = type;
    }

    public Number getIntervals(){
        return intervals;
    }

    public void setIntervals(){
        this.intervals = intervals;
    }

    public Number getFocusTime(){
        return focusTime;
    }

    public void setFocusTime(){
        this.focusTime = focusTime;
    }

    public String getType(){
        return type;
    }

    public void setType(){
        this.type = type;
    }
}
