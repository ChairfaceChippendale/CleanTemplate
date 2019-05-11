package io.osav.ui.core

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    fun hideKeyboard() {
        currentFocus?.windowToken?.let {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(it, 0)
        }
    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun toast(@StringRes resId: Int) {
        toast(getString(resId))
    }
}