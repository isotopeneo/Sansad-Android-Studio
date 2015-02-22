package com.jujitsutech.sansad.fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jujitsutech.sansad.MainActivity;
import com.jujitsutech.sansad.R;
import com.jujitsutech.sansad.util.LoggerClass;

/**
 * Created by Jayesh on 2/21/2015.
 */
public class SearchDialog extends DialogFragment {

    private static Context context;
    private EditText title;

    public static SearchDialog newInstance(Context contextReference) {
        context = contextReference;
        return new SearchDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_dialog, container, false);
        title = (EditText) v.findViewById(R.id.et_title);

        ((Button) v.findViewById(R.id.searchButton)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LoggerClass.log("SearchDialog onClick");
                searchForData();
            }
        });
        return v;
    }

    private void searchForData() {
        if (null != context && context instanceof MainActivity) {
            ((MainActivity) context).searchForData(title.getText().toString());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);
    }
}
