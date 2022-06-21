package si.uni_lj.fe.tnuv.studybuddy;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FocusPlanChildFragment extends Fragment {

    ProgressBar timerBar;
    CountDownTimer countDownTimer;
    TextView timerText;
    Button startTimer, pauseTimer, stopTimer;
    int i = 0;
    //    int i = 0;
    int intervalsLeft;
    int focusLeft;
    int focusLength;
    int breakLeft;
    int breakLength;
    boolean focus;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.child_fragment_focus, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        timerBar = (ProgressBar) view.findViewById(R.id.timerBar);
        timerText = (TextView) view.findViewById(R.id.timerText);
        startTimer = (Button) view.findViewById(R.id.startTimer);
        pauseTimer = (Button) view.findViewById(R.id.pauseTimer);
        stopTimer = (Button) view.findViewById(R.id.stopTimer);

        SessionConfiguration sessionConfiguration1 = SessionConfiguration.getInstance();
        Log.i("SC", "sessionConfiguration1 " + sessionConfiguration1.taskType);

        intervalsLeft = sessionConfiguration1.intervalNumber;
        focusLeft = sessionConfiguration1.intervalNumber;
        focusLength = sessionConfiguration1.focusTimeInterval * 60 * 1000; //*1000 cuz milliseconds
        breakLeft = sessionConfiguration1.intervalNumber - 1;
        breakLength = sessionConfiguration1.breakTimeInterval * 60 * 1000;
        focus = true;

        startTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTimer(focusLength);
                Log.i("START TIMER","I came to start timer");
            }
        });
    }


    private void setTimer(int timeLength) {

//        int timeLengthUnchanged = timeLength;
//        timerBar.setProgress(i); //i = 0

        Log.i("SET TIMER","I came to before setting up the countdown timer, this is how long: "+ timeLength);

        countDownTimer = new CountDownTimer(timeLength, 1000) {

            @Override
            public void onTick(long l) {
//                timerText.setText("00:00:" + l / 1000);

                long hour = (l / 3600000) % 24;
                long min = (l / 60000) % 60;
                long sec = (l / 1000) % 60;

                timerText.setText(modifyNumber(hour) + ":" + modifyNumber(min) + ":" + modifyNumber(sec));

//                i++;
//                timerBar.setProgress((int) i * 100 / (timeLengthUnchanged / 1000));
            }

            @Override
            public void onFinish() {
                if (intervalsLeft > 0) {
                    if (focus) {
                        Log.i("FINISH","Focus is set to : "+ focus);
                        focus = false;
                        Log.i("FINISH","Focus is set to : "+ focus);
                        Log.i("FINISH","This is how many focses are left before -- : "+ focusLeft);
                        focusLeft--;
                        Log.i("FINISH","This is how many focses are left after -- : "+ focusLeft);
                        Log.i("FINISH","This is how many intervals are left before -- : "+ intervalsLeft);
                        intervalsLeft--;
                        Log.i("FINISH","This is how many intervals are left after -- : "+ intervalsLeft);
                        setTimer(breakLength);
                    } else {
                        Log.i("FINISH","This is how many breaks are left before -- : "+ breakLeft);
                        breakLeft--;
                        Log.i("FINISH","This is how many breaks are left after -- : "+ breakLeft);
                        setTimer(focusLength);
                    }
                }

                timerText.setText("Good job!");
                //Do what you want

//                i++;
//                timerBar.setProgress(100);
            }
        };
        countDownTimer.start();
    }

    private String modifyNumber(long number){
        if (number < 10){
            return "0"+number;
        }
        return number+"";
    }
}
