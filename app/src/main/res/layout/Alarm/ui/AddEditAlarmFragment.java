package com.example.dell.alarmapp.Alarm.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dell.alarmapp.Alarm.service.LoadAlarmsService;
import com.example.dell.alarmapp.R;

import java.util.Calendar;

public final class AddEditAlarmFragment extends Fragment {

    private TimePicker mTimePicker;
    private EditText mLabel;
    private CheckBox mMon, mTues, mWed, mThurs, mFri, mSat, mSun;

    public static com.example.dell.alarmapp.Alarm.ui.AddEditAlarmFragment newInstance(com.example.dell.alarmapp.Alarm.model.Alarm alarm) {

        Bundle args = new Bundle();
        args.putParcelable(AddEditAlarmActivity.ALARM_EXTRA, alarm);

        com.example.dell.alarmapp.Alarm.ui.AddEditAlarmFragment fragment = new com.example.dell.alarmapp.Alarm.ui.AddEditAlarmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_add_edit_alarm, container, false);

        setHasOptionsMenu(true);

        final com.example.dell.alarmapp.Alarm.model.Alarm alarm = getAlarm();

        mTimePicker = (TimePicker) v.findViewById(R.id.edit_alarm_time_picker);
        com.example.dell.alarmapp.Alarm.util.ViewUtils.setTimePickerTime(mTimePicker, alarm.getTime());

        mLabel = (EditText) v.findViewById(R.id.edit_alarm_label);
        mLabel.setText(alarm.getLabel());

        mMon = (CheckBox) v.findViewById(R.id.edit_alarm_mon);
        mTues = (CheckBox) v.findViewById(R.id.edit_alarm_tues);
        mWed = (CheckBox) v.findViewById(R.id.edit_alarm_wed);
        mThurs = (CheckBox) v.findViewById(R.id.edit_alarm_thurs);
        mFri = (CheckBox) v.findViewById(R.id.edit_alarm_fri);
        mSat = (CheckBox) v.findViewById(R.id.edit_alarm_sat);
        mSun = (CheckBox) v.findViewById(R.id.edit_alarm_sun);

        setDayCheckboxes(alarm);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.edit_alarm_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                save();
                break;
            case R.id.action_delete:
                delete();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private com.example.dell.alarmapp.Alarm.model.Alarm getAlarm() {
        return getArguments().getParcelable(AddEditAlarmActivity.ALARM_EXTRA);
    }

    private void setDayCheckboxes(com.example.dell.alarmapp.Alarm.model.Alarm alarm) {
        mMon.setChecked(alarm.getDay(com.example.dell.alarmapp.Alarm.model.Alarm.MON));
        mTues.setChecked(alarm.getDay(com.example.dell.alarmapp.Alarm.model.Alarm.TUES));
        mWed.setChecked(alarm.getDay(com.example.dell.alarmapp.Alarm.model.Alarm.WED));
        mThurs.setChecked(alarm.getDay(com.example.dell.alarmapp.Alarm.model.Alarm.THURS));
        mFri.setChecked(alarm.getDay(com.example.dell.alarmapp.Alarm.model.Alarm.FRI));
        mSat.setChecked(alarm.getDay(com.example.dell.alarmapp.Alarm.model.Alarm.SAT));
        mSun.setChecked(alarm.getDay(com.example.dell.alarmapp.Alarm.model.Alarm.SUN));
    }

    private void save() {

        final com.example.dell.alarmapp.Alarm.model.Alarm alarm = getAlarm();

        final Calendar time = Calendar.getInstance();
        time.set(Calendar.MINUTE, com.example.dell.alarmapp.Alarm.util.ViewUtils.getTimePickerMinute(mTimePicker));
        time.set(Calendar.HOUR_OF_DAY, com.example.dell.alarmapp.Alarm.util.ViewUtils.getTimePickerHour(mTimePicker));
        alarm.setTime(time.getTimeInMillis());

        alarm.setLabel(mLabel.getText().toString());

        alarm.setDay(com.example.dell.alarmapp.Alarm.model.Alarm.MON, mMon.isChecked());
        alarm.setDay(com.example.dell.alarmapp.Alarm.model.Alarm.TUES, mTues.isChecked());
        alarm.setDay(com.example.dell.alarmapp.Alarm.model.Alarm.WED, mWed.isChecked());
        alarm.setDay(com.example.dell.alarmapp.Alarm.model.Alarm.THURS, mThurs.isChecked());
        alarm.setDay(com.example.dell.alarmapp.Alarm.model.Alarm.FRI, mFri.isChecked());
        alarm.setDay(com.example.dell.alarmapp.Alarm.model.Alarm.SAT, mSat.isChecked());
        alarm.setDay(com.example.dell.alarmapp.Alarm.model.Alarm.SUN, mSun.isChecked());

        final int rowsUpdated = com.example.dell.alarmapp.Alarm.data.DatabaseHelper.getInstance(getContext()).updateAlarm(alarm);
        final int messageId = (rowsUpdated == 1) ? R.string.update_complete : R.string.update_failed;

        Toast.makeText(getContext(), messageId, Toast.LENGTH_SHORT).show();

        com.example.dell.alarmapp.Alarm.service.AlarmReceiver.setReminderAlarm(getContext(), alarm);

        getActivity().finish();

    }

    private void delete() {

        final com.example.dell.alarmapp.Alarm.model.Alarm alarm = getAlarm();

        final AlertDialog.Builder builder =
                new AlertDialog.Builder(getContext(), R.style.DeleteAlarmDialogTheme);
        builder.setTitle(R.string.delete_dialog_title);
        builder.setMessage(R.string.delete_dialog_content);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //Cancel any pending notifications for this alarm
                com.example.dell.alarmapp.Alarm.service.AlarmReceiver.cancelReminderAlarm(getContext(), alarm);

                final int rowsDeleted = com.example.dell.alarmapp.Alarm.data.DatabaseHelper.getInstance(getContext()).deleteAlarm(alarm);
                int messageId;
                if(rowsDeleted == 1) {
                    messageId = R.string.delete_complete;
                    Toast.makeText(getContext(), messageId, Toast.LENGTH_SHORT).show();
                    LoadAlarmsService.launchLoadAlarmsService(getContext());
                    getActivity().finish();
                } else {
                    messageId = R.string.delete_failed;
                    Toast.makeText(getContext(), messageId, Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton(R.string.no, null);
        builder.show();

    }

}
