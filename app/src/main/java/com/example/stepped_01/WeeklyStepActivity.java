package com.example.stepped_01;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.stepped_01.Util.SharedPrefUtility;
import com.example.stepped_01.Util.WeeklyStepsCalculator;

import java.util.ArrayList;
import java.util.List;

public class WeeklyStepActivity extends AppCompatActivity {

    List<Integer> weeklySteps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_step);

        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progressBar));
        weeklySteps = new ArrayList<>();

        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();

        loadSteps();

        data.add(new ValueDataEntry("Mon", weeklySteps.get(0)));
        data.add(new ValueDataEntry("Tue", weeklySteps.get(1)));
        data.add(new ValueDataEntry("Wed", weeklySteps.get(2)));
        data.add(new ValueDataEntry("Thu", weeklySteps.get(3)));
        data.add(new ValueDataEntry("Fri", weeklySteps.get(4)));
        data.add(new ValueDataEntry("Sat", weeklySteps.get(5)));
        data.add(new ValueDataEntry("Sun", weeklySteps.get(6)));

        Column column = cartesian.column(data);
        column.color("#e12525");

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");

        cartesian.animation(true);

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Day");
        cartesian.yAxis(0).title("Steps");

        anyChartView.setChart(cartesian);
    }

    private void loadSteps(){
        SharedPreferences sharedPreferences = getSharedPreferences(SharedPrefUtility.SHARED_PREF, MODE_PRIVATE);

        weeklySteps.add(sharedPreferences.getInt(SharedPrefUtility.MONDAY, 0));
        weeklySteps.add(sharedPreferences.getInt(SharedPrefUtility.TUESDAY, 0));
        weeklySteps.add(sharedPreferences.getInt(SharedPrefUtility.WEDNESDAY, 0));
        weeklySteps.add(sharedPreferences.getInt(SharedPrefUtility.THURSDAY, 0));
        weeklySteps.add(sharedPreferences.getInt(SharedPrefUtility.FRIDAY, 0));
        weeklySteps.add(sharedPreferences.getInt(SharedPrefUtility.SATURDAY, 0));
        weeklySteps.add(sharedPreferences.getInt(SharedPrefUtility.SUNDAY, 0));


    }
}
