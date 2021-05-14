package com.zxy.zxyhttp.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.Toolbar;

import com.zxy.zxyhttp.R;


/**
 */
public class MyToolbar extends Toolbar implements View.OnClickListener {

    private LinearLayoutCompat mTitleLayout;
    private TextView mTitleTextView;
    private CharSequence mTitleText;
    private boolean mTitleVisible;

    private TextView mSubtitleTextView;
    private CharSequence mSubTitleText;
    private boolean mSubTitleVisible;

    private TextView mCloseTextView;
    private CharSequence mCloseText;
    private boolean mCloseVisible;


    private TextView mBackTextView;
    private CharSequence mBackText;
    private boolean mBackVisible;


    private LinearLayoutCompat menuLayout;

    //右侧菜单按钮
    private TextView mMenuTextView;
    private CharSequence mMenuText;
    private boolean mMenuVisible;

    private View menuView;


    private static final int DEFAULT_BACK_MARGIN_RIGHT = 8;
    protected OnOptionItemClickListener mOnOptionItemClickListener;

    public MyToolbar(Context context) {
        this(context, null);
    }

    public MyToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.toolbarStyle);
    }

    public MyToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    protected void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        initCustomView(context, attrs, defStyleAttr);
    }

    public boolean isChild(View view) {
        return view != null && view.getParent() == this;
    }

    public boolean isChild(View view, ViewParent parent) {
        return view != null && view.getParent() == parent;
    }

    public void setOnOptionItemClickListener(OnOptionItemClickListener listener) {
        mOnOptionItemClickListener = listener;
    }

    public interface OnOptionItemClickListener {
        void onOptionItemClick(View v);
    }

    public int dp2px(float dp) {
        return Math.round(dp * getResources().getDisplayMetrics().density);
    }

    protected void initCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                R.styleable.Toolbar, defStyleAttr, 0);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleToolbar);

        if (!isChild(mTitleLayout)) {
            mTitleLayout = new LinearLayoutCompat(context);
            mTitleLayout.setOrientation(LinearLayoutCompat.VERTICAL);
            mTitleLayout.setGravity(typedArray.getInt(
                    R.styleable.TitleToolbar_title_gravity, Gravity.CENTER_VERTICAL));

            addView(mTitleLayout, new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, Gravity.CENTER));
        }

        if (!isChild(mTitleTextView, mTitleLayout)) {
            mTitleTextView = new TextView(context);
            mTitleTextView.setSingleLine();
            mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
            mTitleTextView.setGravity(Gravity.CENTER);

            int titleTextAppearance = a.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0);
            if (titleTextAppearance != 0) {
                mTitleTextView.setTextAppearance(context, titleTextAppearance);
            }

            if (a.hasValue(R.styleable.Toolbar_titleTextColor)) {
                int titleColor = a.getColor(R.styleable.Toolbar_titleTextColor, Color.WHITE);
                mTitleTextView.setTextColor(titleColor);
            }

            if (typedArray.hasValue(R.styleable.TitleToolbar_titleTextSize)) {
                mTitleTextView.setTextSize(
                        typedArray.getDimensionPixelSize(R.styleable.TitleToolbar_backTextSize, 0));
            }

            setTitle(a.getText(R.styleable.Toolbar_title));
            setTitleVisible(typedArray.getBoolean(R.styleable.TitleToolbar_titleVisible, true));

            mTitleLayout.addView(mTitleTextView,
                    new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }

        if (!isChild(mSubtitleTextView, mTitleLayout)) {
            mSubtitleTextView = new TextView(context);
            mSubtitleTextView.setSingleLine();
            mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
            mSubtitleTextView.setGravity(Gravity.CENTER);

            int subTextAppearance = a.getResourceId(R.styleable.Toolbar_subtitleTextAppearance, 0);
            if (subTextAppearance != 0) {
                mSubtitleTextView.setTextAppearance(context, subTextAppearance);
            }

            if (a.hasValue(R.styleable.Toolbar_subtitleTextColor)) {
                int subTitleColor = a.getColor(R.styleable.Toolbar_subtitleTextColor, Color.WHITE);
                mSubtitleTextView.setTextColor(subTitleColor);
            }

            if (typedArray.hasValue(R.styleable.TitleToolbar_subtitleTextSize)) {
                mSubtitleTextView.setTextSize(
                        typedArray.getDimensionPixelSize(
                                R.styleable.TitleToolbar_subtitleTextSize, 0));
            }

            setSubtitle(a.getText(R.styleable.Toolbar_subtitle));
            setSubtitleVisible(
                    typedArray.getBoolean(R.styleable.TitleToolbar_subtitleVisible, false));

            mTitleLayout.addView(mSubtitleTextView,
                    new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }

        if (!isChild(mBackTextView)) {
            mBackTextView = new TextView(context);
            mBackTextView.setId(R.id.back);
            mBackTextView.setSingleLine();
            mBackTextView.setEllipsize(TextUtils.TruncateAt.END);
            mBackTextView.setGravity(Gravity.CENTER_VERTICAL);

            int backTextAppearance =
                    typedArray.getResourceId(R.styleable.TitleToolbar_backTextAppearance, 0);
            if (backTextAppearance != 0) {
                mBackTextView.setTextAppearance(context, backTextAppearance);
            }

            if (typedArray.hasValue(R.styleable.TitleToolbar_backTextColor)) {
                int backTextColor =
                        typedArray.getColor(R.styleable.TitleToolbar_backTextColor, Color.WHITE);
                mBackTextView.setTextColor(backTextColor);
            }

            if (typedArray.hasValue(R.styleable.TitleToolbar_backTextSize)) {
                mBackTextView.setTextSize(
                        typedArray.getDimensionPixelSize(R.styleable.TitleToolbar_backTextSize, 0));
            }



            Drawable drawable = typedArray.getDrawable(R.styleable.TitleToolbar_backIcon);
            if (drawable != null) {
                mBackTextView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
            }
            setBackText(typedArray.getText(R.styleable.TitleToolbar_backText));
            setBackVisible(typedArray.getBoolean(R.styleable.TitleToolbar_backVisible, false));

            mBackTextView.setClickable(true);
            mBackTextView.setOnClickListener(this);

            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT, Gravity.LEFT | Gravity.CENTER_VERTICAL);

            layoutParams.rightMargin = typedArray.getDimensionPixelSize(
                    R.styleable.TitleToolbar_backMarginRight, dp2px(DEFAULT_BACK_MARGIN_RIGHT));
            layoutParams.leftMargin = typedArray.getDimensionPixelSize(
                    R.styleable.TitleToolbar_backMarginLeft, dp2px(DEFAULT_BACK_MARGIN_RIGHT));
            layoutParams.topMargin = typedArray.getDimensionPixelSize(
                    R.styleable.TitleToolbar_backMarginTop, dp2px(DEFAULT_BACK_MARGIN_RIGHT));
            layoutParams.bottomMargin = typedArray.getDimensionPixelSize(
                    R.styleable.TitleToolbar_backMarginBottom, dp2px(DEFAULT_BACK_MARGIN_RIGHT));

//            mBackTextView.setBackgroundColor(Color.GREEN);
//            mBackTextView.setPadding(10 , 10 , 10 , 10);
            //扩大返回按钮点击热区
            expandViewTouchDelegate(mBackTextView ,10 , 10 , 15 , 80);
            addView(mBackTextView, layoutParams);
        }

        if (!isChild(mCloseTextView)) {
            mCloseTextView = new TextView(context);
            mCloseTextView.setId(R.id.close);
            mCloseTextView.setSingleLine();
            mCloseTextView.setEllipsize(TextUtils.TruncateAt.END);
            mCloseTextView.setGravity(Gravity.CENTER_VERTICAL);

            int closeTextAppearance =
                    typedArray.getResourceId(R.styleable.TitleToolbar_closeTextAppearance, 0);

            if (closeTextAppearance != 0) {
                mCloseTextView.setTextAppearance(context, closeTextAppearance);
            }

            if (typedArray.hasValue(R.styleable.TitleToolbar_closeTextColor)) {
                int closeTextColor =
                        typedArray.getColor(R.styleable.TitleToolbar_closeTextColor, Color.WHITE);
                mCloseTextView.setTextColor(closeTextColor);
            }

            if (typedArray.hasValue(R.styleable.TitleToolbar_closeTextSize)) {
                mCloseTextView.setTextSize(
                        typedArray.getDimensionPixelSize(
                                R.styleable.TitleToolbar_closeTextSize, 0));
            }

            setCloseText(typedArray.getText(R.styleable.TitleToolbar_closeText));
            setCloseVisible(typedArray.getBoolean(R.styleable.TitleToolbar_closeVisible, false));

            mCloseTextView.setClickable(true);
            mCloseTextView.setOnClickListener(this);

            addView(mCloseTextView, new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT, Gravity.LEFT | Gravity.CENTER_VERTICAL));
        }


//        int menuViewlayout = a.getResourceId(R.styleable.TitleToolbar_menuViewLayout, 0);
        if (!isChild(menuLayout)) {
            menuLayout = new LinearLayoutCompat(context);
            menuLayout.setOrientation(LinearLayout.HORIZONTAL);
            menuLayout.setGravity(Gravity.CENTER_VERTICAL);
            addView(menuLayout, new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, Gravity.RIGHT));

//            if(menuViewlayout >0){
//                menuView = LayoutInflater.from(context).inflate(menuViewlayout , null);
//                if(menuView != null) {
//                    menuLayout.addView(menuView, new LayoutParams(
//                            LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, Gravity.CENTER));
//                }else{
//                    XLog.e("menuLayoutView is null !!!");
//                }
//            }
        }

        if (!isChild(mMenuTextView, menuLayout)) {
            mMenuTextView = new TextView(context);
            mMenuTextView.setId(R.id.menu);
            mMenuTextView.setSingleLine();
            mMenuTextView.setEllipsize(TextUtils.TruncateAt.END);
            mMenuTextView.setGravity(Gravity.CENTER_VERTICAL);

            int backTextAppearance =
                    typedArray.getResourceId(R.styleable.TitleToolbar_backTextAppearance, 0);
            if (backTextAppearance != 0) {
                mMenuTextView.setTextAppearance(context, backTextAppearance);
            }

            if (typedArray.hasValue(R.styleable.TitleToolbar_backTextColor)) {
                int backTextColor =
                        typedArray.getColor(R.styleable.TitleToolbar_backTextColor, Color.WHITE);
                mMenuTextView.setTextColor(backTextColor);
            }

            if (typedArray.hasValue(R.styleable.TitleToolbar_backTextSize)) {
                mMenuTextView.setTextSize(
                        typedArray.getDimensionPixelSize(R.styleable.TitleToolbar_backTextSize, 0));
            }

            Drawable drawable = typedArray.getDrawable(R.styleable.TitleToolbar_menuIcon);
            if (drawable != null) {
                mMenuTextView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
            }

            setMenuText(typedArray.getText(R.styleable.TitleToolbar_menuText));
            setMenuVisible(typedArray.getBoolean(R.styleable.TitleToolbar_menuVisible, false));

            mMenuTextView.setClickable(true);
            mMenuTextView.setOnClickListener(this);

            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT, Gravity.CENTER_VERTICAL);

            layoutParams.rightMargin = typedArray.getDimensionPixelSize(
                    R.styleable.TitleToolbar_menuMarginRight, dp2px(DEFAULT_BACK_MARGIN_RIGHT));
            layoutParams.leftMargin = typedArray.getDimensionPixelSize(
                    R.styleable.TitleToolbar_menuMarginLeft, dp2px(DEFAULT_BACK_MARGIN_RIGHT));
            layoutParams.topMargin = typedArray.getDimensionPixelSize(
                    R.styleable.TitleToolbar_backMarginTop, dp2px(DEFAULT_BACK_MARGIN_RIGHT));
            layoutParams.bottomMargin = typedArray.getDimensionPixelSize(
                    R.styleable.TitleToolbar_backMarginBottom, dp2px(DEFAULT_BACK_MARGIN_RIGHT));

            mMenuTextView.setPadding(0, 0, layoutParams.rightMargin, 0);

            menuLayout.addView(mMenuTextView, layoutParams);
        }

        typedArray.recycle();
        a.recycle();
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitleText = title;
        if (mTitleTextView != null) {
            mTitleTextView.setText(title);
            mTitleTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
    }


    public void setContentView(View view) {
        addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void removeAllView() {
        removeAllViews();
    }

    @Override
    public CharSequence getTitle() {
        return mTitleText;
    }

    @Override
    public void setTitleTextAppearance(Context context, int resId) {
        if (mTitleTextView != null) {
            mTitleTextView.setTextAppearance(context, resId);
        }
    }

    @Override
    public void setTitleTextColor(int color) {
        if (mTitleTextView != null) {
            mTitleTextView.setTextColor(color);
        }
    }

    public void setTitleTextSize(float size) {
        if (mTitleTextView != null) {
            mTitleTextView.setTextSize(size);
        }
    }


    public void setTitleVisible(boolean visible) {
        mTitleVisible = visible;
        mTitleTextView.setVisibility(mTitleVisible ? VISIBLE : GONE);
    }

    public boolean getTitleVisible() {
        return mTitleVisible;
    }

    @Override
    public void setSubtitle(CharSequence subtitle) {
        mSubTitleText = subtitle;
        if (mSubtitleTextView != null) {
            mSubtitleTextView.setText(subtitle);
        }
    }

    @Override
    public CharSequence getSubtitle() {
        return mSubTitleText;
    }

    @Override
    public void setSubtitleTextAppearance(Context context, int resId) {
        if (mSubtitleTextView != null) {
            mSubtitleTextView.setTextAppearance(context, resId);
        }
    }

    @Override
    public void setSubtitleTextColor(int color) {
        if (mSubtitleTextView != null) {
            mSubtitleTextView.setTextColor(color);
        }
    }

    public void setSubtitleTextSize(float size) {
        if (mSubtitleTextView != null) {
            mSubtitleTextView.setTextSize(size);
        }
    }


    public void setSubtitleVisible(boolean visible) {
        mSubTitleVisible = visible;
        mSubtitleTextView.setVisibility(visible ? VISIBLE : GONE);
    }

    public boolean getSubtitleVisible() {
        return mSubTitleVisible;
    }

    public void setCloseText(int resId) {
        setCloseText(getContext().getText(resId));
    }

    public void setCloseText(CharSequence closeText) {
        mCloseText = closeText;
        if (mCloseTextView != null) {
            mCloseTextView.setText(closeText);
        }
    }

    public void setMenuText(int resId) {
        setMenuText(getContext().getText(resId));
    }

    public void setMenuText(CharSequence closeText) {
        mMenuText = closeText;
        if (mMenuTextView != null) {
//            mMenuTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            mMenuTextView.setText(closeText);
        }
    }

    public View getMenuView() {
        return mMenuTextView;
    }

    public void setMenuTextColor(int rId) {
        if (mMenuTextView != null) {
            mMenuTextView.setTextColor(getResources().getColor(rId));
        }
    }


    public CharSequence getMenuText() {
        return mMenuText;
    }

    public void setMenuVisible(boolean visible) {
        mMenuVisible = visible;
//        mMenuTextView.setVisibility(visible ? VISIBLE : GONE);
        mMenuTextView.setVisibility(VISIBLE);
    }

    public boolean getMenuVisible() {
        return mMenuVisible;
    }

    public void setMenuDrawable(int rId) {
        Drawable drawable = getResources().getDrawable(rId);
        setMenuDrawable(drawable);
    }

    public void setMenuDrawable(Drawable drawable) {
        if (drawable != null) {
            mMenuTextView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        }
    }


    public CharSequence getCloseText() {
        return mCloseText;
    }

    public void setCloseTextColor(int color) {
        mCloseTextView.setTextColor(color);
    }

    public void setCloseVisible(boolean visible) {
        mCloseVisible = visible;
        mCloseTextView.setVisibility(mCloseVisible ? VISIBLE : GONE);
    }

    public boolean isCloseVisible() {
        return mCloseVisible;
    }

    public void setBackText(int resId) {
        setBackText(getContext().getText(resId));
    }

    public void setBackText(CharSequence backText) {
        mBackText = backText;
        if (mBackTextView != null) {
            mBackTextView.setText(backText);
        }
    }

    public void setBackTextDrawable(int rId) {
        Drawable drawable = getResources().getDrawable(rId);
        setBackTextDrawable(drawable);
    }

    public void setBackTextDrawable(Drawable drawable) {
        if (drawable != null) {
            mBackTextView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        }
    }

    /**
     * 设置Menu
     *
     * @param layoutId
     */
    public void setMenuView(int layoutId) {
        menuView = LayoutInflater.from(getContext()).inflate(layoutId, null);
        if (menuView != null) {
            menuLayout.removeAllViews();
            ViewGroup parentView = (ViewGroup) menuView;
            int count = parentView.getChildCount();
            for (int i = 0; i < count; i++) {
                View childView = parentView.getChildAt(i);
                if (childView != null) {
                    childView.setOnClickListener(this);
                }
            }
            menuLayout.addView(menuView, new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, Gravity.CENTER));
        }
    }


    public CharSequence getBackText() {
        return mBackText;
    }

    public void setBackTextColor(int color) {
        mBackTextView.setTextColor(color);
    }

    public void setBackVisible(boolean visible) {
        mBackVisible = visible;
        mBackTextView.setVisibility(mBackVisible ? VISIBLE : GONE);
    }

    public boolean isBackVisible() {
        return mBackVisible;
    }

    @Override
    public void onClick(View v) {
        if (mOnOptionItemClickListener != null) {
            mOnOptionItemClickListener.onOptionItemClick(v);
        }
    }



    /**
     * 扩大View的触摸和点击响应范围,最大不超过其父View范围
     *
     * @param view
     * @param top
     * @param bottom
     * @param left
     * @param right
     */
    public static void expandViewTouchDelegate(final View view, final int top, final int bottom, final int left, final int right) {
        ((View) view).post(new Runnable() {
            @Override
            public void run() {
                Rect bounds = new Rect();
                view.setEnabled(true);
                view.getHitRect(bounds);
                bounds.top -= top;
                bounds.bottom += bottom;
                bounds.left -= left;
                bounds.right += right;
                TouchDelegate touchDelegate = new TouchDelegate(bounds, view);
                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }


    /**
     * 还原View的触摸和点击响应范围,最小不小于View自身范围
     *
     * @param view
     */
    public static void restoreViewTouchDelegate(final View view) {
        ((View) view.getParent()).post(new Runnable() {
            @Override
            public void run() {
                Rect bounds = new Rect();
                bounds.setEmpty();
                TouchDelegate touchDelegate = new TouchDelegate(bounds, view);
                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }

}
