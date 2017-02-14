package cn.ucai.superwechat.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.widget.EaseAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.utils.MFGT;
import cn.ucai.superwechat.utils.PreferenceManager;

public class ApplyActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.et_apply_msg)
    EditText metApplyMsg;

    String username=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        username=getIntent().getStringExtra(I.User.USER_NAME);
        metApplyMsg.setText("我是"+PreferenceManager.getInstance().getCurrentUserNick());
    }

    private void initView() {
        imgBack.setVisibility(View.VISIBLE);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText("验证申请");
        btnSend.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.img_back, R.id.btn_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MFGT.finish(this);
                break;
            case R.id.btn_send:
                sendMsg();
                break;
        }
    }

    private void sendMsg() {
        if(EMClient.getInstance().getCurrentUser().equals(username)){
            new EaseAlertDialog(this, R.string.not_add_myself).show();
            return;
        }
//
//        if(SuperWeChatHelper.getInstance().getContactList().containsKey(username)){
//            //let the user know the contact already in your contact list
//            if(EMClient.getInstance().contactManager().getBlackListUsernames().contains(nameText.getText().toString())){
//                new EaseAlertDialog(this, R.string.user_already_in_contactlist).show();
//                return;
//            }
//            new EaseAlertDialog(this, R.string.This_user_is_already_your_friend).show();
//            return;
//        }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        String stri = getResources().getString(R.string.Is_sending_a_request);
        progressDialog.setMessage(stri);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        new Thread(new Runnable() {
            public void run() {

                try {
                    //demo use a hardcode reason here, you need let user to input if you like
                    String s=metApplyMsg.getText().toString();
                    EMClient.getInstance().contactManager().addContact(username, s);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            String s1 = getResources().getString(R.string.send_successful);
                            Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_LONG).show();
                            MFGT.finish(ApplyActivity.this);
                        }
                    });
                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            String s2 = getResources().getString(R.string.Request_add_buddy_failure);
                            Toast.makeText(getApplicationContext(), s2 + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }
}
