package si.uni_lj.fe.tnuv.studybuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class FocusActivity extends AppCompatActivity implements FocusNavigation {


    ProfileFragment profileFragment= new ProfileFragment();
    FocusFragment focusFragment = new FocusFragment();
    HistoryFragment historyFragment = new HistoryFragment();


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
                case R.id.historyFragment:
                    changeFragment(historyFragment);
                    break;
            }
            return true;
        });
    }

    //if you are there, you wont be able to do a back press, super on back press should not be called
    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getFragments().size()-1) instanceof FocusFragment){
            FocusFragment fragment = (FocusFragment) getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getFragments().size()-1);
            if(fragment.getChildFragmentManager().getFragments().get(fragment.getChildFragmentManager().getFragments().size()-1) instanceof FocusPlanChildFragment){
                return;
            }
        }
        super.onBackPressed();
    }

    private void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commitAllowingStateLoss();
    }

    @Override
    public void changeChildFragment(Fragment fragment) {
        focusFragment.changeFragment(fragment);
    }
}