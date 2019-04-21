package arkaa.health.user.arkaahealthcare.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] tab = {"Home", "user profile"};


    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HomeScreenFragment();
        } else {
            return new UserProfileFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

}

