package si.uni_lj.fe.tnuv.studybuddy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.slider.Slider;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NewSessionChildFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigation = (FocusNavigation) context;
    }

    FocusNavigation navigation;

    private Button nextButton;
    private EditText taskType, intervalNumber, lengthHours, lengthMinutes;
    private Slider focusBreakSlider;
    private TextView focusTimeInfo;
    private TextView breakTimeInfo;

    private int focusTimeInterval;
    private int breakTimeInterval;

    private FirebaseAuth mAuth;
    private DatabaseReference mRef;

    //idk if we need this
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_2_ble, container, false);
        return inflater.inflate(R.layout.child_fragment_new_session, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //setContentView?

//        TASK TYPE
        taskType = (EditText) view.findViewById(R.id.taskType);
        taskType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        getActivity(), R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getActivity().getApplicationContext())
                        .inflate(
                                R.layout.layout_bottom_sheet,
                                (LinearLayout) view.findViewById(R.id.bottomSheetContainer)
                        );
                bottomSheetView.findViewById(R.id.studyTask).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        taskType.setText("Study");
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetView.findViewById(R.id.workTask).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        taskType.setText("Work");
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetView.findViewById(R.id.choresTask).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        taskType.setText("Chores");
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetView.findViewById(R.id.otherTask).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        taskType.setText("Other");
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

//        OTHERS
        intervalNumber = (EditText) view.findViewById(R.id.interval);
        lengthHours = (EditText) view.findViewById(R.id.lengthHour);
        lengthMinutes = (EditText) view.findViewById(R.id.lengthMinute);
        focusBreakSlider = (Slider) view.findViewById(R.id.focusBreakSlider);
        // i only need hours and minutes for the focus time

        focusTimeInfo = (TextView) view.findViewById(R.id.focusTimeInfo);
        breakTimeInfo = (TextView) view.findViewById(R.id.breakTimeInfo);

        //change the max value of the slider for it to be as long as there are minutes in the length of one interval
        lengthHours.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                Log.i("Erases", "erases before");

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//                if(lengthHours.getText().toString().matches("")){
//                    focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + 0);
//                }
//                if(lengthMinutes.getText().toString().matches("")){
//                    focusBreakSlider.setValueTo(0 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                }
//                focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//

//                if(lengthHours.getText().toString().matches("")){
//                    lengthHours.setText("0");
//                }
//                if(lengthMinutes.getText().toString().matches("")){
//                    lengthMinutes.setText("0");
//                }
//                focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + Integer.parseInt(lengthMinutes.getText().toString().trim()));


//                if (charSequence.length() != 0) {
//                    if (lengthMinutes.getText().toString().matches("")) {
//                        lengthMinutes.setText("0");
//                        focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60);
//                        Log.i("0 HC ONLY HOURS", "hours to minutes: " + Integer.parseInt(lengthHours.getText().toString().trim()) * 60);
//                    } else if (!lengthMinutes.getText().toString().matches("")) {
//                        focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                        Log.i("0 HC ONLY HOURS", "hours to minutes: " + Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                    }
//                } else {
//                    lengthHours.setText("0");
//                    Log.i("hours is empty", "");
//                    if (lengthMinutes.getText().toString().matches("")) {
//                        lengthMinutes.setText("0");
//                    } else if (!lengthMinutes.getText().toString().matches("")) { //PROBLEM HERE
//                        focusBreakSlider.setValueTo(Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                        Log.i("0 HC ONLY HOURS", "hours to minutes: " + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                    }
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                changeSlider(editable.toString().trim(), lengthMinutes.getText().toString().trim());

//               if (!lengthMinutes.getText().toString().matches("") && lengthHours.getText().toString().matches(""))
//                {
//                    //if minutes = 0, we cant let the set value to be 0
//                    focusBreakSlider.setValueTo(0 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//
//                } else if (lengthMinutes.getText().toString().matches("") && !lengthHours.getText().toString().matches(""))
//                {
//                    focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + 0);
//                } else if (!lengthHours.getText().toString().matches("") && !lengthMinutes.getText().toString().matches(""))
//                {
//                    focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                }

//                Log.i("EDITABLE", "Editable: "+ editable);
//                Log.i("Erases", "erases after");
            }
        });

        lengthMinutes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//                if(!lengthHours.getText().toString().matches("") && lengthMinutes.getText().toString().matches("")){
//                    if(lengthHours.getText().toString().matches("")){
//                        focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + 0);
//                    }
//                    if(lengthMinutes.getText().toString().matches("")){
//                        focusBreakSlider.setValueTo(0 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                    }
//                    focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                }


//                if(lengthHours.getText().toString().matches("")){
//                    lengthHours.setText("0");
//                }
//                if(lengthMinutes.getText().toString().matches("")){
//                    lengthMinutes.setText("0");
//                }
//                focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + Integer.parseInt(lengthMinutes.getText().toString().trim()));

//                if (charSequence.length() != 0) {
//                    if(!lengthHours.getText().toString().matches("")){
//                        focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                        Log.i("LENGTH MINUTES MATCHES", "minutes: " + Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                    } else {
//                        focusBreakSlider.setValueTo(Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                       }
//                } else {
//                    lengthMinutes.setText("0");
//                    if (lengthHours.getText().toString().matches("")) {
//                    lengthHours.setText("0");
//                    focusBreakSlider.setValueTo(60);
//                } else if (!lengthHours.getText().toString().matches("")) { //PROBLEM HERE
//                    focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60);
//                }
//                }
            }

//             if (charSequence.length() != 0) {
//                if (lengthMinutes.getText().toString().matches("")) {
//                    lengthMinutes.setText("0");
//                    focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60);
//                    Log.i("0 HC ONLY HOURS", "hours to minutes: " + Integer.parseInt(lengthHours.getText().toString().trim()) * 60);
//                } else if (!lengthMinutes.getText().toString().matches("")) {
//                    focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                    Log.i("0 HC ONLY HOURS", "hours to minutes: " + Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                }
//            } else {
//                lengthHours.setText("0");
//                Log.i("hours is empty", "");
//                if (lengthMinutes.getText().toString().matches("")) {
//                    lengthMinutes.setText("0");
//                } else if (!lengthMinutes.getText().toString().matches("")) { //PROBLEM HERE
//                    focusBreakSlider.setValueTo(Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                    Log.i("0 HC ONLY HOURS", "hours to minutes: " + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                }
//            }

            @Override
            public void afterTextChanged(Editable editable) {

                changeSlider(lengthHours.getText().toString().trim(), editable.toString().trim());

//                if (lengthHours.getText().toString().matches("") && lengthMinutes.getText().toString().matches(""))
//                {
//                    return;
//                } else if (lengthHours.getText().toString().matches(""))
//                {
//                    focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + 0);
//                } else if (lengthMinutes.getText().toString().matches(""))
//                {
//                    focusBreakSlider.setValueTo(0 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                } else
//                {
//                    focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                }


//                if (lengthHours.getText().toString().matches("") && !lengthMinutes.getText().toString().matches(""))
//                {
//                    focusBreakSlider.setValueTo(0 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//
//                } else if (lengthMinutes.getText().toString().matches("") && !lengthHours.getText().toString().matches(""))
//                {
//                    focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + 0);
//                } else if (!lengthHours.getText().toString().matches("") && !lengthMinutes.getText().toString().matches(""))
//                {
//                    focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                }

            }
        });

        //depending on where the value is, set focus time and break time
        focusBreakSlider.addOnChangeListener(new Slider.OnChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                int focusTimeLength = (int) value;
                int breakValue = (int) focusBreakSlider.getValueTo();
                int breakTimeLength = breakValue - focusTimeLength;

                //TODO make this a method
                int focusMinutes = focusTimeLength % 60;
                int focusHours = 0; //TODO check if this works heh

                if (focusTimeLength > 60) {
                    focusHours = focusTimeLength / 60;
                }

                int breakMinutes = breakTimeLength % 60;
                int breakHours = 0;

                if (breakTimeLength > 60) {
                    breakHours = breakTimeLength / 60;
                }

                focusTimeInfo.setText("Focus Time: " + focusHours + "h " + focusMinutes + "m");
                breakTimeInfo.setText("Break Time: " + breakHours + "h " + breakMinutes + "m");
            }
        });


        nextButton = (Button) view.findViewById(R.id.nextButton);

        //try setOnClickListener(this) and do that separately
        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                goToBreakPlanning();
            }
        });

    }


    private void goToBreakPlanning() {

        String task = taskType.getText().toString().trim();
        String intervalString = intervalNumber.getText().toString().trim();
        int interval = (intervalString.length()==0) ? 0 : Integer.parseInt(intervalString);

        String hoursString = lengthHours.getText().toString().trim();
        int hours = (hoursString.length()==0) ? 0 : Integer.parseInt(hoursString);; //of a whole interval

        String minutesString = lengthMinutes.getText().toString().trim();
        int minutes = (minutesString.length()==0) ? 0 : Integer.parseInt(minutesString); //of a whole interval

        int focusTime = (int) focusBreakSlider.getValue(); //in minutes
        int breakTime = minutes - focusTime;

        if(hours > 1){
            breakTime += hours * 60;
        }

        SessionConfiguration sessionConfiguration = SessionConfiguration.getInstance();
        sessionConfiguration.init(task, interval, hours, minutes, focusTime, breakTime);

        if (task.isEmpty()) {
            taskType.setError("Task type is required!");
            taskType.requestFocus();
            return;
        }

        if (intervalString.isEmpty()) {
            intervalNumber.setError("Interval number is required!");
            intervalNumber.requestFocus();
            return;
        }

        //TODO you have to make sure they dont put more than 24 hours
        if (hoursString.isEmpty()) {
            lengthHours.setError("Length of interval is required!");
            lengthHours.requestFocus();
            return;
        }

        //TODO you have to make sure they dont put more than 60 min
        if (minutesString.isEmpty()) {
            lengthMinutes.setError("Length of interval is required!");
            lengthMinutes.requestFocus();
            return;
        }

        //TODO some waiting time?

        Map<String, Object> sessions = new HashMap<>();
        sessions.put(UUID.randomUUID().toString(), new Sessions(interval, focusTime, task, new Date().getTime()));

        mRef = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("sessions");
        mRef.updateChildren(sessions);

        navigation.changeChildFragment(new FocusPlanChildFragment());

    }


    @Override
    public void onClick(View view) {

    }

    private void changeSlider(String h, String m){
        if(h.length() == 0){
            if (m.length() == 0){
                focusBreakSlider.setValueTo(1);
                return;
            }
            if(Integer.parseInt(m) < 1){
                focusBreakSlider.setValueTo(1);
                return;
            }
            focusBreakSlider.setValueTo(Integer.parseInt(m));
            return;
        }

        if(m.length() == 0){
            if(Integer.parseInt(h) < 1){
                focusBreakSlider.setValueTo(1);
                return;
            }
            focusBreakSlider.setValueTo(Integer.parseInt(h) * 60);
            return;
        }

        if(Integer.parseInt(h) < 1){
            if(Integer.parseInt(m) < 1){
                focusBreakSlider.setValueTo(1);
                return;
            }
            focusBreakSlider.setValueTo(Integer.parseInt(m));
        } else {
            if(Integer.parseInt(m) < 1){
                focusBreakSlider.setValueTo(Integer.parseInt(h)*60);
                return;
            }
            focusBreakSlider.setValueTo(Integer.parseInt(h) * 60 + Integer.parseInt(m));
        }
    }
}
