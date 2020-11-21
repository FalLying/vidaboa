package br.com.vidaboa

import android.app.DatePickerDialog
import android.os.Build
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import java.util.*

class Validator {

}

fun validateNotNull(pView: View?, pMessage: String?): Boolean {
    if (pView is EditText) {
        val edText = pView
        val text = edText.text
        if (text != null) {
            val strText = text.toString()
            if (!TextUtils.isEmpty(strText)) {
                return true
            }
        }
        // em qualquer outra condição é gerado um erro
        edText.error = pMessage
        edText.isFocusable = true
        edText.requestFocus()
        return false
    }
    return false
}