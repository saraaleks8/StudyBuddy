package si.uni_lj.fe.tnuv.studybuddy;

public class Session {

    private Number intervals, focusTime;
    private String type;

    public Session(){
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
