package si.uni_lj.fe.tnuv.studybuddy;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.se.omapi.Session;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class HistoryFragment extends Fragment {

    private FirebaseUser user;
    private String userID;
    RecyclerView recyclerView;
    DatabaseReference database;
    DatabaseReference dbSessions;
    MyAdapter myAdapter;
    ArrayList<Sessions> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*recyclerView = view.findViewById(R.id.sessionsList);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        dbSessions = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("sessions");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(getContext(), list);
        recyclerView.setAdapter(myAdapter);


        final TextView labelTextView = (TextView) view.findViewById(R.id.tvLabel);
        final TextView focusTimeTextView = (TextView) view.findViewById(R.id.tvFocusTime);
        final TextView intervalsTextView = (TextView) view.findViewById(R.id.tvIntervals);

        dbSessions.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot postSnapshot: snapshot.getChildren()){
                    String sessionID = postSnapshot.getKey();

                    dbSessions.child(sessionID).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                Sessions session = dataSnapshot.getValue(Sessions.class);
                                list.add(session);
                            }

                            myAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(ProfileFragment.this, "Something wrong happened.", Toast.LENGTH_LONG).show();
                throw error.toException();
            }
        }); */
    }


}