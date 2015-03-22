package com.slava.myweatherapp.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.slava.myweatherapp.R;
import com.slava.myweatherapp.Settings;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SettingsActivity extends ActionBarActivity {

    @InjectView(R.id.settingsLayout) RelativeLayout mSettingsLayout;
    @InjectView(R.id.settingsTopicLabel) TextView mSettingTopicLabel;
    @InjectView(R.id.colorLabel) TextView mColorLabel;
    @InjectView(R.id.choice1) RadioButton mChoice1;
    @InjectView(R.id.choice2) RadioButton mChoice2;
    @InjectView(R.id.applyButton) Button mApplyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.inject(this);

        updateLayoutSettings();
        syncronize();
    }

    private void updateLayoutSettings() {
        int backgroundColor = Settings.backgroudColor;
        mSettingsLayout.setBackgroundColor(backgroundColor);
        int textColor = Settings.textColor;
        mSettingTopicLabel.setTextColor(textColor);
        mColorLabel.setTextColor(textColor);
        mChoice1.setTextColor(textColor);
        mChoice2.setTextColor(textColor);
        mApplyButton.setTextColor(backgroundColor);
    }

    void syncronize() {
        int backgroundColor = Settings.backgroudColor;
        if(backgroundColor == Color.BLACK)
            mChoice1.setChecked(true);
        else if(backgroundColor == Color.parseColor("#FFFF9E33"))
            mChoice2.setChecked(true);
    }

    @OnClick(R.id.applyButton) void onClickApplyButton() {
        if(mChoice1.isChecked()) {
            Settings.backgroudColor = Color.BLACK;
            Settings.textColor = Color.parseColor("#80ffffff");
        } else if(mChoice2.isChecked()) {
            Settings.backgroudColor = Color.parseColor("#FFFF9E33"); // Orange
            Settings.textColor = Color.BLACK;
        }
        updateLayoutSettings();
        Toast.makeText(this, "Settings applied", Toast.LENGTH_SHORT).show();
    }

}
