package mvp.android.com.br.androidmvppoc.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;

/**
 * Created by rafael.alves on 18/07/2018.
 */

public class ColorUtil {
    public static int getColor(Context context, int color) {

        try {
            return ContextCompat.getColor(context, color);

        } catch (Exception e) {
            return color;
        }
    }
}
