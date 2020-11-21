package br.com.vidaboa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_users.*

class ListUsers : AppCompatActivity() {
    private val sqlHelper = SQLHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_users)

        btnUpdateUser.setOnClickListener {
            if (validateNotNull(inputId, "Informe o ID do usuário")) {
                val intent = Intent(
                    this,
                    UpdateUser::class.java
                )
                intent.putExtra("id", inputId.text.toString())
                startActivity(intent)
            }
        }

    }

    fun listUsers(view: View?) {
        val sql = "SELECT * FROM $TABLE_USER ORDER BY $TABLE_USE_NAME"

        val database = sqlHelper.readableDatabase
        val row = database.rawQuery(sql, null)

        var users = ""

        val c = row.count
        Toast.makeText(
            this,
            "Total de $c registros.",
            Toast.LENGTH_LONG
        ).show()

        while (row.moveToNext()) {
            val id = row.getInt(
                row.getColumnIndex(TABLE_USE_ID)
            )
            val name = row.getString(
                row.getColumnIndex(TABLE_USE_NAME)
            )
            val email = row.getString(
                row.getColumnIndex(TABLE_USE_EMAIL)
            )
            val kind = row.getString(
                row.getColumnIndex(TABLE_USE_TYPE)
            )
            val data = row.getString(
                row.getColumnIndex(TABLE_USE_DATE)
            )
            users += "Usuário: $id\nNome: $name - $email | Tipo: $kind - $data\n\n"
        }

        txtUsers.text = users

        row.close()
        database.close()
    }

}

