package com.foc.pmdm.u3.migrabadora;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.view.View;


/**
 * Created by javacasm on 02/03/2017.
 */

// Basado en https://developer.android.com/guide/topics/media/mediarecorder.html#example
// Basado en https://developer.android.com/guide/topics/media/mediarecorder.html#example

public class RecordButton extends AppCompatButton {
    boolean mStartRecording=true;

    MainActivity activity;
    public RecordButton(Context context) {
        super(context);
        activity=(MainActivity)context;

        setOnClickListener(clickReceiver);
    }

    OnClickListener clickReceiver=new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mStartRecording) {
                setText("Stop Recording");
                activity.startRecording();
            } else {
                setText("Start Recording");
                activity.stopRecording();
            }
            mStartRecording = !mStartRecording;
        }

    };

}
