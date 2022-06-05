package si.uni_lj.fe.tnuv.studybuddy;

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
    ViewPager viewPager;

    NewSessionChildFragment newSession = new NewSessionChildFragment();
    BreakPlanningChildFragment breakPlan = new BreakPlanningChildFragment();
    FocusPlanChildFragment focusPlan = new FocusPlanChildFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_focus, container, false);
        addFragment(view);
        return view;
    }

    private void addFragment(View view) {
//        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(newSession, "New Session");
        adapter.addFragment(breakPlan, "Break Plan");
        adapter.addFragment(focusPlan, "Focus Plan");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        changeFragment();
    }

    private void insertNestedFragment() {
        Fragment childFragment = new NewSessionChildFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.childFragmentContainerFocus, childFragment).commit();
    }

    private void changeFragment(Fragment fragment){
        getChildFragmentManager().beginTransaction().replace(R.id.childFragmentContainerFocus, fragment).commitAllowingStateLoss();
    }
}
