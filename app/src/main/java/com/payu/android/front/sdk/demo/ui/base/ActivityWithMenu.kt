package com.payu.android.front.sdk.demo.ui.base

import android.view.Menu
import android.view.MenuItem
import com.payu.android.front.sdk.frontsdk.R

abstract class ActivityWithMenu : MainActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_demo_samples, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_change_theme -> {
            ChangeThemeDialog.showThemeDialog(supportFragmentManager)
            true
        }
        R.id.action_change_language -> {
            ChangeLanguageDialog.showLanguageDialog(supportFragmentManager)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}