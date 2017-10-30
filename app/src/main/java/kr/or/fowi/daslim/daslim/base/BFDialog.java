package kr.or.fowi.daslim.daslim.base;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.Calendar;

/**
 * Created by 이주영 on 2016-12-02.
 */

public class BFDialog {
    private Context mContext;
    private Resources mResources;

    private BFDialog(Context context) {
        this.mContext = context;
        mResources = mContext.getResources();
    }

    public static BFDialog newInstance(Context context) {
        return new BFDialog(context);
    }

    public Dialog showSimpleDialog(String msg) {
        return showDialog(null, null, msg, null, "확인", null, null, null, null, null);
    }

    public Dialog showDialog(String msg) {
        return showDialog(null, null, msg, null, "확인", null, null, null, null, null);
    }

    public Dialog showDialog(Object message, Object positiveButtonText, DialogInterface.OnClickListener positiveListener) {
        return showDialog(null, null, message, null, positiveButtonText, positiveListener, null, null, null, null);
    }

    public Dialog showDialog(Object title, Object icon, Object message, View view//
            , Object positiveButtonText, DialogInterface.OnClickListener positiveListener//
            , Object neutralButtonText, DialogInterface.OnClickListener neutralListener //
            , Object negativeButtonText, DialogInterface.OnClickListener negativeListener//
    ) {
        final Dialog dlg = getDialog(title, icon, message, view, positiveButtonText, positiveListener, neutralButtonText, neutralListener, negativeButtonText, negativeListener);
        dlg.show();
        return dlg;
    }

    public Dialog showDialog(Object message,//
                             Object positiveButtonText, DialogInterface.OnClickListener positiveListener//
            , Object negativeButtonText, DialogInterface.OnClickListener negativeListener) {
        return showDialog(null, null, message, null, positiveButtonText, positiveListener, null, null, negativeButtonText, negativeListener);
    }

    public Dialog getDialog(Object title, Object icon, Object message, View view//
            , Object positiveButtonText, DialogInterface.OnClickListener positiveListener//
            , Object neutralButtonText, DialogInterface.OnClickListener neutralListener //
            , Object negativeButtonText, DialogInterface.OnClickListener negativeListener//
    ) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        if (title != null)
            builder.setTitle(title instanceof String ? (String) title : mResources.getString((Integer) title));
        if (message != null)
            builder.setMessage(message instanceof String ? (String) message : mResources.getString((Integer) message));
        if (icon != null)
            builder.setIcon(icon instanceof Drawable ? (Drawable) icon : mResources.getDrawable((Integer) icon));
        if (view != null)
            builder.setView(view);
        if (positiveButtonText != null)
            builder.setPositiveButton(positiveButtonText instanceof String ? (String) positiveButtonText : mResources.getString((Integer) positiveButtonText), positiveListener);
        if (negativeButtonText != null)
            builder.setNegativeButton(negativeButtonText instanceof String ? (String) negativeButtonText : mResources.getString((Integer) negativeButtonText), negativeListener);
        if (neutralButtonText != null)
            builder.setNeutralButton(neutralButtonText instanceof String ? (String) neutralButtonText : mResources.getString((Integer) neutralButtonText), neutralListener);

        final AlertDialog dlg = builder.create();

        dlg.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                dlg.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                dlg.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                dlg.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.BLACK);

            }
        });

        return dlg;
    }

    public Dialog showTimePicker(TimePickerDialog.OnTimeSetListener onTimeSetListener, int hourOfDay, int minutOfHour) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, onTimeSetListener, hourOfDay, minutOfHour, false);
        timePickerDialog.updateTime(hourOfDay, minutOfHour);
        timePickerDialog.show();
        return timePickerDialog;
    }

    public Dialog showDatePicker(DatePickerDialog.OnDateSetListener onDateSetListener, int year, int monthOfYear, int dayOfMonth) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, onDateSetListener, year, monthOfYear, dayOfMonth);
        datePickerDialog.updateDate(year, monthOfYear, dayOfMonth);
        datePickerDialog.show();
        return datePickerDialog;
    }

    public void showDatePicker(Calendar calendar, DatePickerDialog.OnDateSetListener onUpdateDueSetListener) {
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        showDatePicker(onUpdateDueSetListener, year, monthOfYear, dayOfMonth);
    }
}
