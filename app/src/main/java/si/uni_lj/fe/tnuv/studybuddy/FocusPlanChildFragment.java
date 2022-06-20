package si.uni_lj.fe.tnuv.studybuddy;

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

public class FocusPlanChildFragment extends Fragment {

    ProgressBar timerBar;
    CountDownTimer countDownTimer;
    TextView timerText;
    Button startTimer, pauseTimer, stopTimer;
    int i = 0;


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
        Log.i("SC","sessionConfiguration1 " + sessionConfiguration1.taskType);


//        startTimer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                timerBar.setProgress(i);
//                countDownTimer = new CountDownTimer(5000, 1000) {
//                    @Override
//                    public void onTick(long l) {
//                            timerText.setText("00:00:"+ l/1000);
////                        Log.i("L", "l: " + l);
////                        Log.v("Log_tag", "Tick of Progress" + i + l);
//                            i++;
//                            timerBar.setProgress((int) i * 100 / (5000 / 1000));
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        //Do what you want
//                        timerText.setText("done");
//                        i++;
//                        timerBar.setProgress(100);
//                    }
//                };
//                countDownTimer.start();
//            }
//        });


    }
}
