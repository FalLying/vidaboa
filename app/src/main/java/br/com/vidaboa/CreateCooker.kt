package br.com.vidaboa

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_cooker.*
import kotlinx.android.synthetic.main.activity_create_cooker.inputEmailCooker
import kotlinx.android.synthetic.main.activity_create_cooker.inputNameUser
import kotlinx.android.synthetic.main.activity_create_cooker.inputPasswordUser
import kotlinx.android.synthetic.main.activity_create_cooker.inputPhoneUser
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlinx.android.synthetic.main.activity_main.*

class CreateCooker : AppCompatActivity() {
    private val sqlHelper = SQLHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_cooker)

        btnBack2.setOnClickListener {
            finish()
        }

    }

    fun createCooker() {
        val contentValue = ContentValues().apply {
            put(TABLE_USE_NAME, inputNameUser.text.toString())
            put(TABLE_USE_CELLPHONE, inputPhoneUser.text.toString())
            put(TABLE_USE_EMAIL, inputEmailCooker.text.toString().toLowerCase().trim())
            put(TABLE_USE_PASSWORD, inputPasswordUser.text.toString())
            put(TABLE_USE_TYPE, 2)
        }

        val database = sqlHelper.writableDatabase
        val id = database.insert(
            TABLE_USER,
            null,
            contentValue
        )

        if (id == -1L) {
            Toast.makeText(
                this,
                "Não foi possível finalizar o cadastro.",
                Toast.LENGTH_LONG
            ).show()
        } else {
            Toast.makeText(
                this,
                "Cadastro finalizado com sucesso.",
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
        database.close()
    }

    fun onClick(view: View?) {
        if (
            validateNotNull(inputNameUser, "Nome inválido")
            && validateNotNull(inputPhoneUser, "Número inválido")
            && validateNotNull(inputEmailCooker, "Email inválido")
            && validateNotNull(inputPasswordUser, "Informe uma senha")
            ) {
            createCooker()
        }

    }

}