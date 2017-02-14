package cn.ucai.superwechat.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.hyphenate.easeui.domain.User;

import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.net.OnCompleteListener;
import cn.ucai.superwechat.ui.AddContactActivity;
import cn.ucai.superwechat.ui.ApplyActivity;
import cn.ucai.superwechat.ui.FriendActivity;
import cn.ucai.superwechat.ui.LoginActivity;
import cn.ucai.superwechat.ui.MainActivity;
import cn.ucai.superwechat.ui.RegisterActivity;
import cn.ucai.superwechat.ui.SettingsActivity;
import cn.ucai.superwechat.ui.UserProfileActivity;


/**
 * Created by Administrator on 2017/1/10 0010.
 */
public class MFGT {
    public static void startActivity(Activity activity, Class<?> clz) {
        activity.startActivity(new Intent(activity,clz));
        activity.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
    public static void startActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }

    public static void finish(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }

    public static void gotoLogin(Activity activity) {
        startActivity(activity, LoginActivity.class);
    }

    public static void gotoLoginClenTask(Activity activity) {
        startActivity(activity,new Intent(activity,LoginActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
    public static void gotoRegister(Activity activity) {
        startActivity(activity, RegisterActivity.class);
    }

    public static void gotoUserProfile(Activity activity) {
       startActivity(activity,UserProfileActivity.class);
    }

    public static void gotoSetting(Activity activity) {
        startActivity(activity, SettingsActivity.class);
    }

    public static void gotoAddContact(Activity activity) {
        startActivity(activity, AddContactActivity.class);
    }

    public static void gotoFriend(Activity activity, User user) {
        Intent intent = new Intent(activity, FriendActivity.class);
        intent.putExtra("user", user);
        startActivity(activity,intent);
    }
    public static void gotoFriend(Activity activity, String username) {
        Intent intent = new Intent(activity, FriendActivity.class);
        intent.putExtra(I.User.USER_NAME, username);
        startActivity(activity,intent);
    }

    public static void gotoApply(Activity activity, String userName) {
        startActivity(activity,new Intent(activity, ApplyActivity.class).putExtra(I.User.USER_NAME,userName));
    }
}
