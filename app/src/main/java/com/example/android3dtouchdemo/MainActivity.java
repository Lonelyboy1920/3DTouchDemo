package com.example.android3dtouchdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import java.util.Arrays;

/**
 * @author :Sea
 * Date :2020-4-7 13:10
 * PackageName:com.example.android3dtouchdemo
 * Desc:
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //要确保API Level 大于等于 25才可以创建动态shortcut，否则会报异常。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            initDynamicShortcuts();
        }

    }


    /**
     * 为App创建动态Shortcuts
     */
    @TargetApi(Build.VERSION_CODES.N_MR1)
    private void initDynamicShortcuts() {
        //①、创建动态快捷方式的第一步，创建ShortcutManager
        ShortcutManager scManager = getSystemService(ShortcutManager.class);
        //②、构建动态快捷方式的详细信息
        ShortcutInfo scInfoOne = new ShortcutInfo.Builder(this, "dynamic_one")
                .setShortLabel("跳系统浏览器")
                .setLongLabel("to open Dynamic Web Site")
                .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com")))
                .build();

        ShortcutInfo scInfoTwo = new ShortcutInfo.Builder(this, "dynamic_two")
                .setShortLabel("跳系统内Activity")
                .setLongLabel("to open dynamic one activity")
                .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                .setIntents(
                        // this dynamic shortcut set up a back stack using Intents, when pressing back, will go to MainActivity
                        // the last Intent is what the shortcut really opened
                        new Intent[]{
                                new Intent(Intent.ACTION_MAIN, Uri.EMPTY, this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK),
                                new Intent(TestActivity.ACTION_OPEN_DYNAMIC)
                                // intent's action must be set
                        })
                .build();
        //③、为ShortcutManager设置动态快捷方式集合
        scManager.setDynamicShortcuts(Arrays.asList(scInfoOne, scInfoTwo));

        //如果想为两个动态快捷方式进行排序，可执行下面的代码
        ShortcutInfo dynamicWebShortcut = new ShortcutInfo.Builder(this, "dynamic_one")
                .setRank(0)
                .build();
        ShortcutInfo dynamicActivityShortcut = new ShortcutInfo.Builder(this, "dynamic_two")
                .setRank(1)
                .build();

        //④、更新快捷方式集合
        scManager.updateShortcuts(Arrays.asList(dynamicWebShortcut, dynamicActivityShortcut));
    }
}
