package si.uni_lj.fe.tnuv.studybuddy;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Sessions {

//    private static UUID id;
    private Integer intervals, focusTime;
    private String type;
    private long time;

    public Sessions (){
    }

    public Sessions(Integer intervals, Integer focusTime, String type, long time){
        this.intervals = intervals;
        this.focusTime = focusTime;
        this.type = type;
        this.time = time;
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

    public long getTime(){
        return time;
    }

    public void setTime(){
        this.time = time;
    }

}
