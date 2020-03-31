package com.example.family.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.family.R;
import com.example.family.model.CalendarDate;

public class Dialog {

    public  interface onDateChangeListener {
        void onDateChange(int year, int month, int day);
    }
    public static void showErrorDialog(Context ctx, String error) {
        ///  создаем всплывающий диалог с ошибкой
        AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
        alert.setMessage(error);  // сюда передается ошибка
        alert.setTitle("Ошибка");  /// заголовок ошибки
        alert.setCancelable(true);   //
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {  // создаем кнопку
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ///   если пустой окно закрывается
            }
        });
        //alert.setIcon(R.drawable.ic_launcher_foreground)  - создание иконки

        alert.create().show();  //  метод вывода ошибки на экран
    }

    public static void showDialog(Context ctx, String message) {
        ///  создаем всплывающий диалог с ошибкой
        AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
        alert.setMessage(message);  // сюда передается ошибка
        //alert.setTitle("УРА!");  /// заголовок здесь можно убрать
        alert.setCancelable(true);   //
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {  // создаем кнопку
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ///   если пустой окно закрывается
            }
        });////   кнопку тоже можно убрать
        //alert.setIcon(R.drawable.ic_launcher_foreground)  - создание иконки

        alert.create().show();  //  метод вывода ошибки на экран
    }

    public static void showCalendarDialog(Context ctx, final onDateChangeListener listener) {
        ///  создаем всплывающий диалог с ошибкой
        AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
        alert.setView(R.layout.calendar_layout);
        alert.setCancelable(true);   //

        final CalendarDate date = new CalendarDate();

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!date.isEmpty())
                listener.onDateChange(date.year, date.month, date.day);
            }
        });

        AlertDialog dialog = alert.create();  //  метод вывода ошибки на экран

        dialog.show();

        CalendarView  calendar = dialog.findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int y, int m, int d) {
                date.day = d;
                date.month = m;
                date.year = y;
            }
        });
    }
}
