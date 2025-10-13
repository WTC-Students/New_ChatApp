package com.example.chatapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.util.Objects

class SignIn2Activity : AppCompatActivity() {
    private var emailEditText: TextInputEditText? = null
    private var passwordEditText: TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin_2)

        emailEditText = findViewById<TextInputEditText>(R.id.emailEditText)
        passwordEditText = findViewById<TextInputEditText>(R.id.passwordEditText)

        val signInButton = findViewById<MaterialButton>(R.id.signInButton)
        signInButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                attemptSignIn()
            }
        })

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            }
        })

        val forgotPasswordText = findViewById<TextView>(R.id.forgotPasswordText)
        forgotPasswordText.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // TODO: Implement forgot password functionality
                Toast.makeText(
                    this@SignIn2Activity,
                    "Forgot Password feature coming soon",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        val signUpLink = findViewById<TextView>(R.id.signUpLink)
        signUpLink.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@SignIn2Activity, SignUp1Activity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        })
    }

    private fun attemptSignIn() {
        val email = Objects.requireNonNull<Editable?>(emailEditText!!.getText()).toString()
            .trim { it <= ' ' }
        val password = Objects.requireNonNull<Editable?>(passwordEditText!!.getText()).toString()
            .trim { it <= ' ' }

        if (TextUtils.isEmpty(email)) {
            emailEditText!!.setError("Please enter your email")
            return
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText!!.setError("Please enter your password")
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText!!.setError("Please enter a valid email address")
            return
        }

        // TODO: Implement actual authentication logic
        // For now, we just simulate successful login
        simulateSignIn(email, password)
    }

    @SuppressLint("SetTextI18n")
    private fun simulateSignIn(email: String?, password: String?) {
        val signInButton = findViewById<MaterialButton>(R.id.signInButton)
        signInButton.setText("Signing In...")
        signInButton.setEnabled(false)

        Handler().postDelayed(
            object : Runnable {
                override fun run() {
                    val intent = Intent(this@SignIn2Activity, MainActivity::class.java)
                    startActivity(intent)
                    finishAffinity()
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            },
            1500
        )
    }
}