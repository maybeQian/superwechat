package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.utils.L;
import cn.ucai.superwechat.utils.MFGT;

public class FriendActivity extends BaseActivity {
    private static final String TAG = "FriendActivity";
    @BindView(R.id.iv_avatar)
    ImageView mivAvatar;
    @BindView(R.id.tv_userNick)
    TextView mtvUserNick;
    @BindView(R.id.tv_username)
    TextView mtvUsername;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    User user;
    @BindView(R.id.btn_add_to_group)
    Button btnAddToGroup;
    @BindView(R.id.btn_send_message)
    Button btnSendMessage;
    @BindView(R.id.btn_video_call)
    Button btnVideoCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        imgBack.setVisibility(View.VISIBLE);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText("详细资料");
    }

    private void initData() {
        user = (User) getIntent().getSerializableExtra("user");
        if (user != null) {
            L.e(TAG, "user=" + user);
            showUserInfo();
        } else {
            finish();
        }
    }

    private void showUserInfo() {
        mtvUserNick.setText(user.getMUserNick());
        mtvUsername.setText("微信号:" + user.getMUserName());
        EaseUserUtils.setAppUserAvatarByPath(this, user.getAvatar(), mivAvatar);
        if (isFriend()) {
            btnSendMessage.setVisibility(View.VISIBLE);
            btnVideoCall.setVisibility(View.VISIBLE);
        } else {
            btnAddToGroup.setVisibility(View.VISIBLE);
        }
    }

    private boolean isFriend() {
        User u = SuperWeChatHelper.getInstance().getAppContactList().get(user.getMUserName());
        if (u == null) {
            return false;
        } else {
            return true;
        }
    }

    @OnClick(R.id.img_back)
    public void onBack() {
        MFGT.finish(this);
    }

    @OnClick({R.id.btn_add_to_group, R.id.btn_send_message, R.id.btn_video_call})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_to_group:
                MFGT.gotoApply(this,user.getMUserName());
                break;
            case R.id.btn_send_message:
                break;
            case R.id.btn_video_call:
                break;
        }
    }
}
