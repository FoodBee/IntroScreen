package com.example.introscreen;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText phoneEditText;
    TextInputLayout textInputLayout;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        phoneEditText = findViewById(R.id.phoneNumber);
        textInputLayout = findViewById(R.id.textInputLayout);
        button = findViewById(R.id.smsVerificationButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid(phoneEditText.getText().toString())){
                    //TODO
                    startActivity(new Intent(LoginActivity.this,VerifyOtpActivity.class));

                }else{
                    textInputLayout.setError("please enter 10 digits");
                }
            }
        });

        phoneEditText.requestFocus();
        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.showSoftInput(phoneEditText, InputMethodManager.SHOW_IMPLICIT);
        phoneEditText.setText("+91 ");
        Selection.setSelection(phoneEditText.getText(), phoneEditText.getText().length());

        phoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().startsWith("+91 ")) {
                    phoneEditText.setText("+91 ");
                    Selection.setSelection(phoneEditText.getText(), phoneEditText.getText().length());
                }
                if(isValid(s.toString())){
                    button.setText("continue");
                    textInputLayout.setErrorEnabled(false);
                    button.setAlpha(1);
                }else{
                    button.setText("Enter phone number");
                    button.setAlpha(0.5f);
                }

            }
        });
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return true;
    }

    private boolean isValid(String s) {
        s = s.replace("+91 ", "");
        Pattern p = Pattern.compile("[5-9][0-9]{9}");

        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

}
