package si.uni_lj.fe.tnuv.studybuddy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class NewSessionChildFragment extends Fragment implements View.OnClickListener{

    private Button nextButton;
    private EditText taskType, intervalNumber, lengthHours, lengthMinutes;
    private FirebaseAuth mAuth;

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

        taskType = (EditText) view.findViewById(R.id.taskType);
        intervalNumber = (EditText) view.findViewById(R.id.interval);
        lengthHours = (EditText) view.findViewById(R.id.lengthHour);
        lengthMinutes = (EditText) view.findViewById(R.id.lengthMinute);

        nextButton = (Button) view.findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                goToBreakPlanning();
                }
        });

    }

    private void goToBreakPlanning(){

        int interval = Integer.parseInt(intervalNumber.getText().toString());
//        intervalNumber.setText(String.valueOf(interval));


//        Fragment breakPlanning = new BreakPlanningChildFragment();
//        getChildFragmentManager().beginTransaction().replace(R.id.childFragmentContainerFocus, breakPlanning).addToBackStack(null).commitAllowingStateLoss();


    }

    @Override
    public void onClick(View view) {

    }
}
