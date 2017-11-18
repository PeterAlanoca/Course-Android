package org.proingesistinfor.ventasapp.logic;

import android.content.Context;
import android.util.Log;

import org.proingesistinfor.ventasapp.database.DataAdapter;
import org.proingesistinfor.ventasapp.model.Ad;
import org.proingesistinfor.ventasapp.model.User;

import java.util.List;

/**
 * Created by peter on 05-11-17.
 */

public class BusinessLogic {

    private DataAdapter dataAdapter;

    public BusinessLogic(Context context) {
        dataAdapter = new DataAdapter(context);
    }

    public User getUser(int id) {
        return dataAdapter.getUser(id);
    }

    public boolean userRegister(User user) {
        if (dataAdapter.addUser(user) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public User userAuthentication(String username, String password) {
        return dataAdapter.getUser(username, password);
    }

    public boolean adRegister(Ad ad) {
        if (dataAdapter.addAd(ad) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Ad> getAdAll() {
        return dataAdapter.getAdAll();
    }

    public List<Ad> getAdAll(int idUser) {
        return dataAdapter.getAdAll(idUser);
    }

    public List<Ad> getAdAll(int idUser, int limit) {
        return dataAdapter.getAdAll(idUser, limit);
    }
}
