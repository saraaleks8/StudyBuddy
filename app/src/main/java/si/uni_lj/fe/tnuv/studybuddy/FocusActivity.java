package si.uni_lj.fe.tnuv.studybuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class FocusActivity extends AppCompatActivity {

    ProfileFragment profileFragment= new ProfileFragment();
    FocusFragment focusFragment = new FocusFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);
        changeFragment(profileFragment);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_UNLABELED);
        bottomNav.setItemIconSize(74);

        bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.profileFragment:
                    changeFragment(profileFragment);
                    break;
                case R.id.focusFragment:
                    changeFragment(focusFragment);
                    break;
            }
            return true;
        });
    }

    private void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commitAllowingStateLoss();
    }
}