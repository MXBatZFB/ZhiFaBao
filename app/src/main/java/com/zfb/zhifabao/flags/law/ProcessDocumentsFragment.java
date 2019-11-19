package com.zfb.zhifabao.flags.law;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zfb.zhifabao.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProcessDocumentsFragment extends Fragment {


    public ProcessDocumentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_process_documents, container, false);
    }

}
