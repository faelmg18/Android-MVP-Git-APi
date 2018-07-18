package mvp.android.com.br.androidmvppoc.views.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;

import mvp.android.com.br.androidmvppoc.R;
import mvp.android.com.br.androidmvppoc.util.ColorUtil;

/**
 * Created by rafael.alves on 18/07/2018.
 */

public class CustomLinearLayoutRoundedBorder extends LinearLayout {

    Context context;
    GradientDrawable shape;
    private float cornerRadiusLeftBottom;
    private float cornerRadiusLeftTop;
    private float cornerRadiusRightBottom;
    private float cornerRadiusRightTop;
    private float cornerRadius;
    private int currentColor;
    private int borderColor;
    private int borderWidth;

    public CustomLinearLayoutRoundedBorder(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public CustomLinearLayoutRoundedBorder(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUp(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public CustomLinearLayoutRoundedBorder(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setUp(Context context, @Nullable AttributeSet attrs) {
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomLinearLayoutRoundedBorder);
        int color = a.getInt(R.styleable.CustomLinearLayoutRoundedBorder_colorCustom, android.R.color.transparent);

        cornerRadius = a.getFloat(R.styleable.CustomLinearLayoutRoundedBorder_cornerRadius, 0.0f);

        cornerRadiusLeftBottom = a.getFloat(R.styleable.CustomLinearLayoutRoundedBorder_cornerRadiusLeftBottom, 0.0f);
        cornerRadiusLeftTop = a.getFloat(R.styleable.CustomLinearLayoutRoundedBorder_cornerRadiusLeftTop, 0.0f);
        cornerRadiusRightBottom = a.getFloat(R.styleable.CustomLinearLayoutRoundedBorder_cornerRadiusRightBottom, 0.0f);
        cornerRadiusRightTop = a.getFloat(R.styleable.CustomLinearLayoutRoundedBorder_cornerRadiusRightTop, 0.0f);
        borderColor = a.getColor(R.styleable.CustomLinearLayoutRoundedBorder_cornerBorderColor, ColorUtil.getColor(context, android.R.color.transparent));
        borderWidth = a.getInt(R.styleable.CustomLinearLayoutRoundedBorder_cornerBorderWidth, 0);

        setCorners(color, cornerRadius, cornerRadiusLeftBottom, cornerRadiusLeftTop, cornerRadiusRightBottom, cornerRadiusRightTop, borderColor, borderWidth);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setCorners(int color, float cornerRadius) {
        setCorners(color, cornerRadius, 0, 0, 0, 0, android.R.color.transparent, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setCorners(int color, float cornerRadius, float cornerRadiusLeftBottom, float cornerRadiusLeftTop, float cornerRadiusRightBottom, float cornerRadiusRightTop, int borderColor, int borderWidth) {
        shape = new GradientDrawable();

        currentColor = ColorUtil.getColor(getContext(), color);

        if (cornerRadius > 0.0f) {
            changeColor(currentColor, cornerRadius, borderColor, borderWidth);
            return;
        }

        ArrayList<Float> values = new ArrayList<>();
        // esquerda topo
        values.add(cornerRadiusLeftTop);
        values.add(cornerRadiusLeftTop);

        //Direita topo
        values.add(cornerRadiusRightTop);
        values.add(cornerRadiusRightTop);

        //direita baixo
        values.add(cornerRadiusRightBottom);
        values.add(cornerRadiusRightBottom);

        //esquerda baixo
        values.add(cornerRadiusLeftBottom);
        values.add(cornerRadiusLeftBottom);

        float[] arrValues = convertIntegers(values);
        shape.setCornerRadii(arrValues);
        shape.setColor(currentColor);
        shape.setStroke(borderWidth, ColorUtil.getColor(getContext(), ColorUtil.getColor(context, borderColor)));

        super.setBackground(shape);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void changeColor(int color, float cornerRadius, int borderColor, int borderWidth) {
        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(cornerRadius);
        shape.setStroke(borderWidth, ColorUtil.getColor(getContext(), ColorUtil.getColor(context, borderColor)));
        shape.setColor(ColorUtil.getColor(getContext(), ColorUtil.getColor(context, color)));

        super.setBackground(shape);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void changeColor(int color, float cornerRadius) {
        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(cornerRadius);
        shape.setStroke(borderWidth, ColorUtil.getColor(getContext(), ColorUtil.getColor(context, borderColor)));
        shape.setColor(ColorUtil.getColor(getContext(), ColorUtil.getColor(context, color)));

        super.setBackground(shape);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void changeColor(int color) {
        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(cornerRadius);
        shape.setStroke(borderWidth, ColorUtil.getColor(getContext(), ColorUtil.getColor(context, borderColor)));
        shape.setColor(ColorUtil.getColor(getContext(), ColorUtil.getColor(context, color)));

        super.setBackground(shape);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void setBackgroundColor(@ColorInt int color) {

        int newColor = ColorUtil.getColor(getContext(), color);

        setCorners(newColor, cornerRadius, cornerRadiusLeftBottom, cornerRadiusLeftTop, cornerRadiusRightBottom, cornerRadiusRightTop, borderColor, borderWidth);
    }

    public static float[] convertIntegers(ArrayList<Float> integers) {
        float[] ret = new float[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }
}
