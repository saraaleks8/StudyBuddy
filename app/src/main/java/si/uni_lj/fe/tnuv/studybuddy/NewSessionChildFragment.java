package si.uni_lj.fe.tnuv.studybuddy;

import android.annotation.SuppressLint;
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

    private Button nextButton;
    private EditText taskType, intervalNumber, lengthHours, lengthMinutes;
    private Slider focusBreakSlider;
    private TextView focusTimeInfo;
    private TextView breakTimeInfo;

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
                if (charSequence.length() != 0) {
                    if (lengthMinutes.getText().toString().matches("")) {
                        focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60);
                        Log.i("0 HC ONLY HOURS", "hours to minutes: " + Integer.parseInt(lengthHours.getText().toString().trim()) * 60);
                    } else if (!lengthMinutes.getText().toString().matches("")) {
                        focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
                        Log.i("0 HC ONLY HOURS", "hours to minutes: " + Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
                    }
                } else {
                    Log.i("hours is empty", "");
                    if (lengthMinutes.getText().toString().matches("")) {

                    } else if (!lengthMinutes.getText().toString().matches("")) { //PROBLEM HERE
                        focusBreakSlider.setValueTo(Integer.parseInt(lengthMinutes.getText().toString().trim()));
                        Log.i("0 HC ONLY HOURS", "hours to minutes: " + Integer.parseInt(lengthMinutes.getText().toString().trim()));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
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
                if (charSequence.length() != 0) {
                    //HOURS NOT EMPTY
//                    if(!lengthHours.getText().toString().matches("")){
//                        focusBreakSlider.setValueTo(Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                        Log.i("LENGTH MINUTES MATCHES", "minutes: " + Integer.parseInt(lengthHours.getText().toString().trim()) * 60 + Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                    //HOURS EMPTY
//                    } else {
//                        focusBreakSlider.setValueTo(Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                        Log.i("LENGTH MINUTES NO MATCH", "minutes: "+ Integer.parseInt(lengthMinutes.getText().toString().trim()));
//                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

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

        String task = taskType.getText().toString().trim(); //done
        String intervalString = intervalNumber.getText().toString().trim();
        int interval = Integer.parseInt(intervalString); //done

        Log.i("TASK TYPE", "Task Type: " + task);
        Log.i("INTERVAL", "Interval String: " + intervalString);
        Log.i("INTERVAL", "Interval Number: " + interval);

        //focus time
        String hoursString = lengthHours.getText().toString().trim();
        int hours = Integer.parseInt(hoursString); //of a whole interval

        String minutesString = lengthMinutes.getText().toString().trim();
        int minutes = Integer.parseInt(minutesString); //of a whole interval

        int focusTime = (int) focusBreakSlider.getValue(); //in minutes

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
//        Map<String,Object> updates = new HashMap<>();
//
//        updates.put("timestamp", new Timestamp(new Date()));
        sessions.put(UUID.randomUUID().toString(), new Sessions(interval, focusTime, task, new Date().getTime()));

//        DatabaseReference produitRef = reference.child(refernce).child("produit");
//        produitRef.updateChildren(values);

        mRef = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("sessions");
        mRef.updateChildren(sessions);

//        Fragment breakPlanning = new BreakPlanningChildFragment();
//        getChildFragmentManager().beginTransaction().replace(R.id.childFragmentContainerFocus, breakPlanning).addToBackStack(null).commitAllowingStateLoss();

    }


    @Override
    public void onClick(View view) {

    }
}
