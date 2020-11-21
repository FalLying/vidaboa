package br.com.vidaboa

import android.app.DatePickerDialog
import android.content.ContentValues
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_create_user.*
import java.util.*

class CreateUser : AppCompatActivity() {
    private val sqlHelper = SQLHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        btnBack.setOnClickListener {
            finish()
        }

        inputDateUser.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            var d = ""
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay -> inputDateUser.setText("$mDay/$mMonth/$mYear")}, year, month, day
            )
            datePicker.show()
        }

        fun createUser() {
            val contentValue = ContentValues().apply {
                put(TABLE_USE_NAME, inputNameUser.text.toString())
                put(TABLE_USE_CELLPHONE, inputPhoneUser.text.toString())
                put(TABLE_USE_DATE, inputDateUser.text.toString())
                put(TABLE_USE_EMAIL, inputEmailCooker.text.toString().toLowerCase().trim())
                put(TABLE_USE_PASSWORD, inputPasswordUser.text.toString())
                put(TABLE_USE_TYPE, 1)
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

        btnCreate.setOnClickListener{
            if (
                validateNotNull(inputDateUser, "Nome inválido")
                && validateNotNull(inputDateUser, "Data inválida")
                && validateNotNull(inputPhoneUser, "Número inválido")
                && validateNotNull(inputEmailCooker, "Email inválido")
                && validateNotNull(inputPasswordUser, "Informe uma senha")
                    ) {
                createUser()
            }
        }

    }
}