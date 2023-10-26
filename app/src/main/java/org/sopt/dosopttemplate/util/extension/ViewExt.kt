package org.sopt.dosopttemplate.util.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.R

fun View.showSnackbar(message: String, isShort: Boolean = true) {
    val duration = if (isShort) Snackbar.LENGTH_SHORT else Snackbar.LENGTH_LONG
    Snackbar.make(this, message, duration).show()
}

fun View.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT)
        .show()
}

fun ImageView.loadImage(image: Int?) {
    load(image) {
        placeholder(R.drawable.rect_grey_radius_10)
            .transformations(RoundedCornersTransformation(20f, 20f, 20f, 20f))
            .error(R.drawable.ic_launcher_background)
    }
}

fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}
