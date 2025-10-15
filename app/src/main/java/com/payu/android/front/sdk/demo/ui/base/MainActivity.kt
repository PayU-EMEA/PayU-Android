package com.payu.android.front.sdk.demo.ui.base

import android.content.res.Configuration
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.payu.android.front.sdk.frontsdk.R


abstract class MainActivity : AppCompatActivity() {

    @CallSuper
    override fun onStart() {
        super.onStart()
        fixEdgeToEdge()
    }

    protected fun fixEdgeToEdge() {
        val view = findViewById<View>(R.id.container)

        if (view != null) {
            ViewCompat.setOnApplyWindowInsetsListener(
                view
            ) { v, insets ->
                val innerPadding = insets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
                            or WindowInsetsCompat.Type.displayCutout()
                )
                v.setPadding(
                    innerPadding.left,
                    innerPadding.top,
                    innerPadding.right,
                    innerPadding.bottom
                )

                val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

                WindowCompat.getInsetsController(window, window.decorView)
                    .isAppearanceLightStatusBars = nightModeFlags == Configuration.UI_MODE_NIGHT_NO

                insets
            }
        }
    }

}