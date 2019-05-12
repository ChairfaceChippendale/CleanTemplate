package io.osav.ui.extension

import android.view.View
import androidx.constraintlayout.widget.Group

fun Group.onClick(listener: (view: View) -> Unit) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }
}