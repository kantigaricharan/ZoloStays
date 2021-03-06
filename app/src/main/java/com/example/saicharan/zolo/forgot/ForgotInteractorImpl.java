package com.example.saicharan.zolo.forgot;

import android.text.TextUtils;
import android.util.Patterns;

import com.example.saicharan.zolo.DatabaseHelper;
import com.example.saicharan.zolo.SessionManagement;

import javax.inject.Inject;

/**
 * Created by NaNi on 07/08/17.
 */

public class ForgotInteractorImpl implements ForgotInteractor {

    private final DatabaseHelper dHelper;
    private final SessionManagement sManager;
    ForgotPresenterImpl mForgotPresenter;

    @Inject
    public ForgotInteractorImpl(DatabaseHelper dHelper,SessionManagement sManager){

        this.dHelper=dHelper;
        this.sManager=sManager;
    }
    @Override
    public void forgot(String phone,String newpass, onEmailSentListener listener) {
        if(!TextUtils.isEmpty(phone) && Patterns.EMAIL_ADDRESS.matcher(phone).matches()) {
            if (dHelper.checkUserEmail(phone)) {
                if (dHelper.updatePass(phone, newpass) == 1) {
                        listener.onSuccess(newpass);
                } else {
                    listener.onFailure("Unable to update password");
                }
            } else {
                    listener.onFailure("User not registered");
            }

        }else{
                listener.onFailure("please enter a valid email id");
        }
    }

}
