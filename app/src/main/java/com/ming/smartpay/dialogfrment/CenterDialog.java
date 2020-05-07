package com.ming.smartpay.dialogfrment;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ming.smartpay.R;
import com.ming.smartpay.utils.Tools;

import java.lang.reflect.Field;


/**
 * @Author luoming
 * @Date 2019/3/13 10:09 PM
 */
public abstract class CenterDialog extends DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.DialogStyle);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setDimAmount(0.65f);
        }
        ViewDataBinding binding = getLayoutBind();
        View view = binding.getRoot();
        dialog.setContentView(view);
        initView(binding);
        initWindowParams(dialog);
        return dialog;
    }

    public int getWindowWidth() {
        return (Tools.getScreenWidth(getActivity()) * 5 / 6);
    }

    public int getScreenHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }

    public int getGravity() {
        return Gravity.CENTER;
    }

    // 设置Dialog属性
    protected void initWindowParams(Dialog dialog) {
        Window win = dialog.getWindow();
        if (win != null) {
            WindowManager.LayoutParams lp = win.getAttributes();
            if (isBottom()) {
                win.setWindowAnimations(R.style.DialogTheme_BottomAnimation);
                lp.width = Tools.getScreenWidth(getActivity());
                lp.gravity = Gravity.BOTTOM;
            } else {
                lp.gravity = Gravity.CENTER;
                lp.width = (int) (Tools.getScreenWidth(getActivity()) * (1 - 0.267f));
            }
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

            win.setAttributes(lp);
        }
    }

    public ViewDataBinding getLayoutBind() {
        return DataBindingUtil.inflate(getActivity().getLayoutInflater(), getLayoutId(), null
                , false);
    }

    public abstract int getLayoutId();

    public abstract void initView(ViewDataBinding dataBinding);


    public void show(Object object) {
        show(object, this.toString());
    }

    public void show(Object object, String tag) {
        FragmentManager manager = null;
        if (object instanceof Activity) {
            manager = ((Activity) object).getFragmentManager();
            if (isAdded()) {
                dismiss();
            }
        } else if (object instanceof Fragment) {
            manager = ((Fragment) object).getActivity().getFragmentManager();
            if (isAdded()) {
                dismiss();
            }

        }
        try {
            Field dismissed = DialogFragment.class.getDeclaredField("mDismissed");
            dismissed.setAccessible(true);
            dismissed.set(this, false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            Field shown = DialogFragment.class.getDeclaredField("mShownByMe");
            shown.setAccessible(true);
            shown.set(this, true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

    public boolean isBottom() {
        return false;
    }
}
