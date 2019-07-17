package io.osav.ui.core

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleRegistry

abstract class BaseActivity<out VIEW_MODEL : BaseViewModel?>(
    @LayoutRes layout: Int
): AppCompatActivity() {

    private val registry: LifecycleRegistry
        get() = LifecycleRegistry(this)

    private val disposableObserver: DisposablesObserver
        get() = DisposablesObserver(registry)

    private var rootView: View? = null




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