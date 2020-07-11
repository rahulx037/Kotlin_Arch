package dragor.international.util;

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.animation.AnimationUtils
import dragor.international.R

object AppUtils {
    fun openPlayStoreForApp(context: Context) {
        val appPackageName = context.packageName
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .resources
                            .getString(R.string.app_market_link) + appPackageName)))
        } catch (e: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .resources
                            .getString(R.string.app_google_play_store_link) + appPackageName)))
        }
    }

    fun checkIfStringNotEmptyAndNull(string: String?): Boolean {
        return string != null && !string.isEmpty() && string != "null" && string != "Select"
    }

    fun getNonNullValue(string: String?): String {
        return if (string != null && !string.isEmpty() && string != "null") string else ""
    }

    /**
     * Name:rahuls2
     * Descr:Bounce Animation.
     * Created Date:2/5/2019
     * Updated Date:2/5/2019
     */
    fun bounceAnimate(view: View) {
        val shake = AnimationUtils.loadAnimation(view.context, R.anim.create_shake)
        view.startAnimation(shake)
    }
}