package si.uni_lj.fe.tnuv.studybuddy;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database {
    private static DatabaseReference mDatabase = null;

    public static DatabaseReference get(){
      if (mDatabase == null){
          mDatabase = FirebaseDatabase.getInstance("https://study-buddy-bb84b-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
      }
      return mDatabase;
    }
}
