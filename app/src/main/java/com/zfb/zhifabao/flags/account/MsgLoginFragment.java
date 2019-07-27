package com.zfb.zhifabao.flags.account;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zfb.zhifabao.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MsgLoginFragment extends Fragment {


    public MsgLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_msg_login, container, false);
    }

}
