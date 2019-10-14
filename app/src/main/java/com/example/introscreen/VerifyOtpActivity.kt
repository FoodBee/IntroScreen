package com.example.introscreen

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class VerifyOtpActivity : AppCompatActivity() {


    lateinit var editText1: EditText
    lateinit var editText2: EditText
    lateinit var editText3: EditText
    lateinit var editText4: EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)


        editText1 = findViewById(R.id.et1);
        editText2 = findViewById(R.id.et2);
        editText3 = findViewById(R.id.et3);
        editText4 = findViewById(R.id.et4);
        button = findViewById(R.id.button)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        button.setOnClickListener {
            //                if(button.getAlpha()==1.0f){
            //                    startActivity(new Intent(VerifyOtpActivity.this,MapsActivity.class));
            //                }else{
            //                    Toast.makeText(VerifyOtpActivity.this,"please enter otp",Toast.LENGTH_SHORT).show();
            //                }
        }

        editText1.requestFocus()

        val mInputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mInputMethodManager.showSoftInput(editText1, InputMethodManager.SHOW_IMPLICIT)


        editText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.length == 1) {
                    editText2.requestFocus()

                } else if (s.length == 0) {

                }
                checkForVerify()
            }
        })

        editText2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.length == 1) {
                    editText3.requestFocus()

                } else if (s.length == 0) {
                    editText1.requestFocus()
                }
                checkForVerify()


            }
        })

        editText3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.length == 1) {
                    editText4.requestFocus()

                } else if (s.length == 0) {
                    editText2.requestFocus()
                }
                checkForVerify()
            }
        })

        editText4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.length == 1) {

                } else if (s.length == 0) {
                    editText3.requestFocus()
                }
                checkForVerify()

            }
        })


    }

    private fun checkForVerify() {

        if ((editText1.text.toString() + editText2.text.toString() + editText3.text.toString() + editText4.text.toString()).length == 4) {
            button.alpha = 1.0f
            button.text = "VERIFY OTP"
        } else {
            button.alpha = 0.5f
            button.text = "ENTER OTP"
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
