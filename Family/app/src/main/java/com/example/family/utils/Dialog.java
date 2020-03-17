package com.example.family.utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class Dialog {
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
}
