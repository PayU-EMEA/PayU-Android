package com.payu.android.front.sdk.payment_library_core_android.base;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.payu.android.front.sdk.payment_library_core_android.R;
import com.payu.android.front.sdk.payment_library_core_android.about.AboutActivity;

import static com.payu.android.front.sdk.payment_library_core.translation.TranslationKey.INFORMATIONS;

public abstract class BaseMenuActivity extends BaseActivity {

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //todo stylize menu item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_about, menu);
        MenuItem item = menu.findItem(R.id.about);
        item.setTitle(translation.translate(INFORMATIONS));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int i = item.getItemId();
        if (i == R.id.about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
