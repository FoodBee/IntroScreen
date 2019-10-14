package com.example.introscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val phoneEditText: TextInputEditText = findViewById(R.id.phoneNumber)
        val textInputLayout: TextInputLayout = findViewById(R.id.textInputLayout)
        val button: Button = findViewById(R.id.smsVerificationButton)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }



        button.setOnClickListener {
            if (isValid(phoneEditText.text!!.toString())) {
                //TODO
                startActivity(Intent(this@LoginActivity, VerifyOtpActivity::class.java))

            } else {
                textInputLayout.error = "please enter 10 digits"
            }
        }

        phoneEditText.requestFocus()
        val mInputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mInputMethodManager.showSoftInput(phoneEditText, InputMethodManager.SHOW_IMPLICIT)
        phoneEditText.setText("+91 ")
        Selection.setSelection(phoneEditText.text, phoneEditText.text!!.length)

        phoneEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (!s.toString().startsWith("+91 ")) {
                    phoneEditText.setText("+91 ")
                    Selection.setSelection(phoneEditText.text, phoneEditText.text!!.length)
                }
                if (isValid(s.toString())) {
                    button.text = "continue"
                    textInputLayout.isErrorEnabled = false
                    button.alpha = 1f
                } else {
                    button.text = "Enter phone number"
                    button.alpha = 0.5f
                }

            }
        })
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun isValid(s: String): Boolean {
        var s = s
        s = s.replace("+91 ", "")
        val p = Pattern.compile("[5-9][0-9]{9}")

        val m = p.matcher(s)
        return m.find() && m.group() == s
    }

}
