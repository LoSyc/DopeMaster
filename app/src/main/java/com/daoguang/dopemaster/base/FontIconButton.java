package com.daoguang.dopemaster.base;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;
import com.daoguang.dopemaster.R;

/**
 * Created by Administrator on 2015/3/12.
 */
public class FontIconButton extends Button {

    private static final String FONT_ICON_TTF = "frist_font_icon.ttf";

    private static Typeface fontIconTypeface;

    public FontIconButton(Context context) {
        super(context);
        setTypeface(getFontIconTypeface(context, FONT_ICON_TTF));
        // 使用自定义的背景，默认的背景在这里不适合
        setBackgroundResource(R.drawable.image_button_background);
    }

    public FontIconButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(getFontIconTypeface(context, FONT_ICON_TTF));
        setBackgroundResource(R.drawable.image_button_background);
    }

    public FontIconButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setTypeface(getFontIconTypeface(context, FONT_ICON_TTF));
        setBackgroundResource(R.drawable.image_button_background);
    }

    // 从assets目录中读取字体文件
    public static Typeface getFontIconTypeface(Context context, String typeface) {
        if (fontIconTypeface == null) {
            fontIconTypeface = Typeface.createFromAsset(context.getAssets(), typeface);
        }
        return fontIconTypeface;
    }
}
