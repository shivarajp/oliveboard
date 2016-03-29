package com.oliveboardapp.oliveboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Shivam on 3/29/2016.
 */
public class ExamDataFragment extends Fragment {

    private TextView textView;
    private String data;

    public ExamDataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        data = getArguments().getString("data");
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_examdata, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.desc);
        textView.setText(data);
    }
}
