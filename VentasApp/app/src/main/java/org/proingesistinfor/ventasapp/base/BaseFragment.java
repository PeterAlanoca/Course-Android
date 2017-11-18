package org.proingesistinfor.ventasapp.base;

import android.app.Fragment;
import org.proingesistinfor.ventasapp.activity.menu.MenuActivity;
import org.proingesistinfor.ventasapp.logic.BusinessLogic;
import org.proingesistinfor.ventasapp.model.Ad;
import org.proingesistinfor.ventasapp.model.User;

import java.util.List;

/**
 * Created by peter on 05-11-17.
 */

public class BaseFragment extends Fragment {

    protected void showToast(String message) {
        ((MenuActivity)getActivity()).showToast(message);
    }

    protected void replaceActivity(Class<?> cls, boolean finish) {
        ((MenuActivity)getActivity()).replaceActivity(cls, finish);
    }

    protected void replaceActivity(Class<?> cls, Ad ad, boolean finish) {
        ((MenuActivity)getActivity()).replaceActivity(cls, ad, finish);
    }

    protected void replaceFragment(Fragment fragment) {
        ((MenuActivity)getActivity()).replaceFragment(fragment);
    }

    public void setTitle(String title) {
        getActivity().setTitle(title);
    }

    public BusinessLogic getBusinessLogic() {
        return ((MenuActivity)getActivity()).getBusinessLogic();
    }

    public void setBusinessLogic(BusinessLogic businessLogic) {
        ((MenuActivity)getActivity()).setBusinessLogic(businessLogic);
    }

    public User getUser() {
        return ((MenuActivity)getActivity()).getUser();
    }

    public void setUser(User user) {
        ((MenuActivity)getActivity()).setUser(user);
    }

    public List<Ad> getAds() {
        return ((MenuActivity)getActivity()).getAds();
    }

    public void setAds(List<Ad> ads) {
        ((MenuActivity)getActivity()).setAds(ads);
    }
}

