package br.com.vidaboa

import android.content.ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_list_users.*
import kotlinx.android.synthetic.main.activity_update_user.*


class UpdateUser : AppCompatActivity() {
    private val sqlHelper = SQLHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)

        val extras = intent.extras
        var id =""
        if (extras != null) {
            val r = extras.getString("id")
            id = r.toString()
            updateFileds(id)
        }

        btnVoltarUpdate.setOnClickListener {
            finish()
        }

        btnUpdate.setOnClickListener {
            updateUser(id)
        }

        btnDelete.setOnClickListener {
            deleteUser(id)
        }
    }

    fun updateFileds(id: String?) {
        val sql = "SELECT * FROM $TABLE_USER WHERE $TABLE_USE_ID = ?"

        val database = sqlHelper.readableDatabase
        val row = database.rawQuery(sql, arrayOf(id))

        if (!row.moveToNext()) {

            Toast.makeText(
                this,
                "Nenhum usuário foi encontrado com o ID: $id",
                Toast.LENGTH_LONG
            ).show()
            finish()
        } else {
            val name = row.getString(
                row.getColumnIndex(TABLE_USE_NAME)
            )
            val email = row.getString(
                row.getColumnIndex(TABLE_USE_EMAIL)
            )
            val cell = row.getString(
                row.getColumnIndex(TABLE_USE_CELLPHONE)
            )
            inpName.setText(name)
            inpNumber.setText(cell)
            inpEmail.setText(email)
        }

        row.close()
        database.close()
    }

    fun updateUser (id: String) {
        val contentValues = ContentValues().apply {
            put(TABLE_USE_NAME, inpName.text.toString())
            put(TABLE_USE_CELLPHONE, inpNumber.text.toString())
            put(TABLE_USE_EMAIL, inpEmail.text.toString())
        }

        val database = sqlHelper.writableDatabase

        val result = database.update(TABLE_USER, contentValues, "$TABLE_USE_ID = ?", arrayOf(id))

        if (result >= 1) {
            Toast.makeText(
                this,
                "Usuário atualizado.",
                Toast.LENGTH_LONG
            ).show()
        }

        database.close()
    }

    fun deleteUser(id: String) {
        val database = sqlHelper.writableDatabase

        val result = database.delete(TABLE_USER, "$TABLE_USE_ID = ?", arrayOf(id))

        if (result >= 1) {
            Toast.makeText(
                this,
                "Usuário removido.",
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }
}