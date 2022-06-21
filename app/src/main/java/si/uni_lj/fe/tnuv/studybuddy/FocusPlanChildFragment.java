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
    boolean timerPlayed = false;
    boolean timerIsPaused = false;
    boolean timerIsStopped = false;
    int paused = -1;


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

        //TODO text view add type of task

        intervalsLeft = sessionConfiguration1.intervalNumber;
        focusLeft = sessionConfiguration1.intervalNumber;
        focusLength = sessionConfiguration1.focusTimeInterval * 60 * 1000; //*1000 cuz milliseconds
        breakLeft = sessionConfiguration1.intervalNumber - 1;
        breakLength = sessionConfiguration1.breakTimeInterval * 60 * 1000;
        focus = true;

        pauseTimer.setEnabled(false);
        stopTimer.setEnabled(false);
        timerBar.setProgress(i);

        startTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timerIsPaused){
                    timerIsPaused = false;
                    Log.i("PAUSED","paused on (in play): "+paused);
                    setTimer(paused);
                } else {
                    setTimer(focusLength);
                }
                startTimer.setEnabled(false);
                pauseTimer.setEnabled(true);
                stopTimer.setEnabled(true);
                Log.i("START TIMER","I came to start timer");
            }
        });

        pauseTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseTimer.setEnabled(false);
                startTimer.setEnabled(true);
                stopTimer.setEnabled(false);
                timerIsPaused = true;
                int hours = minutesPaused(timerText.getText().toString().trim(), 0, 2);
                int minutes = minutesPaused(timerText.getText().toString().trim(), 3, 5);
                int seconds = minutesPaused(timerText.getText().toString().trim(), 6, 8);
                paused = 0;
                if(hours > 0){
                    paused += hours * 3600000;
                }
                if(minutes > 0){
                    paused += minutes * 60000;
                }
                if(seconds > 0){
                    paused += seconds * 1000;
                }
                Log.i("PAUSED","paused on (in pause): "+paused);
            countDownTimer.cancel();
            }
        });

        stopTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerIsStopped = true;
                startTimer.setEnabled(false);
                pauseTimer.setEnabled(false);
                countDownTimer.cancel();
                timerText.setText("00:00:00");
            }
        });
    }


    private void setTimer(int timeLength) {

        int timeLengthUnchanged;
        if(focus){
            timeLengthUnchanged = focusLength;
        } else {
            timeLengthUnchanged = breakLength;
        }

        //i = 0

        Log.i("SET TIMER","I came to before setting up the countdown timer, this is how long: "+ timeLength);

        countDownTimer = new CountDownTimer(timeLength, 1000) {

            @Override
            public void onTick(long l) {
//                timerText.setText("00:00:" + l / 1000);

                long hour = (l / 3600000) % 24;
                long min = (l / 60000) % 60;
                long sec = (l / 1000) % 60;

                timerText.setText(modifyNumber(hour) + ":" + modifyNumber(min) + ":" + modifyNumber(sec));

                i++;
                timerBar.setProgress((int) i * 100 / (timeLengthUnchanged / 1000));
            }

            @Override
            public void onFinish() {
                if (intervalsLeft > 0) {
                    if (focus && focusLeft>0) {
                        Log.i("FINISH","Focus is set to : "+ focus);
                        focus = false;
                        Log.i("FINISH","Focus is set to : "+ focus);
                        Log.i("FINISH","This is how many focses are left before -- : "+ focusLeft);
                        focusLeft--;
                        Log.i("FINISH","This is how many focses are left after -- : "+ focusLeft);
                        Log.i("FINISH","This is how many intervals are left before -- : "+ intervalsLeft);
                        intervalsLeft--;
                        Log.i("FINISH","This is how many intervals are left after -- : "+ intervalsLeft);
                        i = 0;
                        setTimer(breakLength);
                    } else {
                        Log.i("FINISH","This is how many breaks are left before -- : "+ breakLeft);
                        focus = true;
                        breakLeft--;
                        Log.i("FINISH","This is how many breaks are left after -- : "+ breakLeft);
                        i = 0;
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

    private int minutesPaused(String str, int first, int last){
        String time =  str.length() < 2 ? str : str.substring(first, last);
        return Integer.parseInt(time);
    }
}
