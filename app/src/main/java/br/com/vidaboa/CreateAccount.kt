package br.com.vidaboa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        btnBack.setOnClickListener {
            finish()
        }

    }

    public fun onClickUser(view: View?) {
        val intent = Intent(
            this,
            CreateUser::class.java
        )
        startActivity(intent)
    }

    public fun onClickCooker(view: View?) {
        val intent = Intent(
            this,
            CreateCooker::class.java
        )
        startActivity(intent)
    }

}