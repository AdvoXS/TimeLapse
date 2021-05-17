package com.example.timelapse.activity;

import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.timelapse.R;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<TimeShiftChangeActivity> activityRule
            = new ActivityTestRule<>(TimeShiftChangeActivity.class, true,
            false);

    @Rule
    public ActivityTestRule<TimeShiftMainActivity> mainRule
            = new ActivityTestRule<>(TimeShiftMainActivity.class, true,
            false);

    @Test
    public void changeText_sameActivity() {
        launchActivity();
        int position = 0;
        onView(withId(R.id.time_shift_change_day_type_spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(position).perform(click());

        if (position == 0) {
            onView(withId(R.id.time_shift_change_start_edit)).perform(click());
            onView(withId(R.id.time_shift_change_start_edit)).perform(typeText("10:00"), closeSoftKeyboard());

            onView(withId(R.id.time_shift_change_end_edit)).perform(click());
            onView(withId(R.id.time_shift_change_end_edit)).perform(typeText("19:00"), closeSoftKeyboard());
        }

        Spinner spinner = activityRule.getActivity().findViewById(R.id.time_shift_change_day_type_spinner);
        String dayTypeChanged = spinner.getSelectedItem().toString();

        //------------for 0 item selected-----------
        EditText editStart = activityRule.getActivity().findViewById(R.id.time_shift_change_start_edit);
        EditText editEnd = activityRule.getActivity().findViewById(R.id.time_shift_change_end_edit);
        String editStartTime = editStart.getText().toString();
        String editEndTime = editEnd.getText().toString();
        //-------------------------------------------

        onView(withId(R.id.time_shift_change_save_button)).perform(click()); //save click

        TextView textViewDayType = mainRule.getActivity().findViewById(R.id.time_shift_type_day);
        String dayType = (String) textViewDayType.getText();
        TextView textViewStart = mainRule.getActivity().findViewById(R.id.time_shift_time_start);
        TextView textViewEnd = mainRule.getActivity().findViewById(R.id.time_shift_time_end);

        String timeStart = textViewStart.getText().toString();
        String timeEnd = textViewEnd.getText().toString();

        if (position == 0) {
            Assert.assertEquals(editStartTime, timeStart);
            Assert.assertEquals(editEndTime, timeEnd);
        }
        Assert.assertEquals(dayTypeChanged, dayType);
    }

    private void launchActivity() {
        LocalDate date = LocalDate.now();
        Intent intent = new Intent();
        Intent mainIntent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        mainIntent.setAction(Intent.ACTION_SEND);
        intent.putExtra("DATE", date);
        mainIntent.putExtra("DATE", date);
        mainRule.launchActivity(mainIntent);
        activityRule.launchActivity(intent);
    }

}