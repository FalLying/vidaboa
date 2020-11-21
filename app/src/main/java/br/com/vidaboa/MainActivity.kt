package br.com.vidaboa

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val sqlHelper = SQLHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView4.text = ""

        button.setOnClickListener {
            if ((validateNotNull(inputEmailCooker, "Email inválido"))
                    && (validateNotNull(inputPasswordUser, "Informe a senha"))) {
                userLogin(inputEmailCooker.text.toString().toLowerCase().trim(), inputPasswordUser.text.toString())
            }

        }

        btnExit.setOnClickListener {
            finishAffinity()
        }

    }

    public fun userLogin(email: String, password: String) {
        val sql = "SELECT * FROM $TABLE_USER WHERE " +
                "$TABLE_USE_EMAIL = ? AND $TABLE_USE_PASSWORD = ?"

        val database = sqlHelper.readableDatabase
        val row = database.rawQuery(sql, arrayOf(
            email,
            password
        ))
        textView4.text = ""
        if (!row.moveToNext()) {
            textView4.text = "Email ou senha inválidos"
        } else {
            onClickLogin()
        }
    }

    public fun onClickCreateAccount(view: View?) {
        val intent = Intent(
            this,
            CreateAccount::class.java
        )
        startActivity(intent)
    }

    public fun onClickLogin() {
        val intent = Intent(
            this,
            ListUsers::class.java
        )
        startActivity(intent)
    }
}