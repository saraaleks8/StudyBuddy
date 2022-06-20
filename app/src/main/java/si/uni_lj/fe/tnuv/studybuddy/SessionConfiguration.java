package si.uni_lj.fe.tnuv.studybuddy;
// Java program implementing Singleton class
// with using  getInstance() method

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.slider.Slider;

// Class 1
// Helper class
class SessionConfiguration {
    // Static variable reference of single_instance
    // of type Singleton
    private static SessionConfiguration single_instance = null;

    // Declaring a variable of type String

    public String taskType;
    public int intervalNumber, lengthHours, lengthMinutes;
    public String focusTimeInfo;
    public String breakTimeInfo;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private SessionConfiguration(){

    }

    public void init(String taskType, int intervalNumber, int lengthHours, int lengthMinutes, String focusTimeInfo, String breakTimeInfo)
    {
        this.taskType = taskType;
        this.intervalNumber = intervalNumber;
        this.lengthHours = lengthHours;
        this.lengthMinutes = lengthMinutes;
        this.focusTimeInfo = focusTimeInfo;
        this.breakTimeInfo = breakTimeInfo;
    }

    // Static method
    // Static method to create instance of Singleton class
    public static SessionConfiguration getInstance()
    {
        if (single_instance == null)
            single_instance = new SessionConfiguration();

        return single_instance;
    }
}