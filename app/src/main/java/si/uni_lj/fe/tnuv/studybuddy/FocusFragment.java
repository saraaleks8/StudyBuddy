package si.uni_lj.fe.tnuv.studybuddy;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class FocusFragment extends Fragment {

    TabLayout tabLayout;

    NewSessionChildFragment newSession = new NewSessionChildFragment();
    FocusPlanChildFragment focusPlan = new FocusPlanChildFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_focus, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changeFragment(newSession);
    }


    public void changeFragment(Fragment fragment){
        getChildFragmentManager().beginTransaction().replace(R.id.childFragmentContainerFocus, fragment).commitAllowingStateLoss();
    }

}
