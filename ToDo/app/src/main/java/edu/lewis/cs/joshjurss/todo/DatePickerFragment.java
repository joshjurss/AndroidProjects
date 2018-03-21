package edu.lewis.cs.joshjurss.todo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Josh Jurss on 2/23/2017.
 */

public class DatePickerFragment extends DialogFragment {
    private DatePicker datePicker;

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable("date", date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dialog_date, null);

        Date dueDate = (Date)getArguments().getSerializable("date");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dueDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePicker = (DatePicker)v.findViewById(R.id.date_picker);
        datePicker.init(year, month, day, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.due);
        builder.setView(v);
        builder.setPositiveButton(android.R.string.ok, new onDialogClickListener());
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

    private class onDialogClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            int year = datePicker.getYear();
            int month = datePicker.getMonth();
            int dayOfMonth = datePicker.getDayOfMonth();
            Date newDate = new GregorianCalendar(year, month, dayOfMonth).getTime();

            if(getTargetFragment() != null){
                Intent intent = new Intent();
                intent.putExtra("date", newDate);
                getTargetFragment().onActivityResult(getTargetRequestCode(),
                        Activity.RESULT_OK, intent);
            }
        }
    }
}
