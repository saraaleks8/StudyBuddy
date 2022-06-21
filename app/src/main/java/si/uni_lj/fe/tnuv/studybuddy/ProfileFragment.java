package si.uni_lj.fe.tnuv.studybuddy;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    private Button logout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        logout = (Button) view.findViewById(R.id.logOut);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                //startActivity(new Intent(FocusActivity.this, MainActivity.class));
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView nameTextView = (TextView) view.findViewById(R.id.name);
        final TextView emailTextView = (TextView) view.findViewById(R.id.email);
        final TextView professionTextView = (TextView) view.findViewById(R.id.profession);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String name = userProfile.getName();
                    String email = userProfile.getEmail();
                    String profession = userProfile.getProfession();

                    nameTextView.setText(name);
                    emailTextView.setText(email);
                    professionTextView.setText(profession);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(ProfileFragment.this, "Something wrong happened.", Toast.LENGTH_LONG).show();
            }
        });

    }
}
