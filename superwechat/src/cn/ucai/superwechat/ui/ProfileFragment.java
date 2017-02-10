package cn.ucai.superwechat.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.easemob.redpacketui.utils.RedPacketUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.Constant;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.utils.MFGT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    @BindView(R.id.iv_avatar)
    ImageView mivAvatar;
    @BindView(R.id.tv_userNick)
    TextView mtvUserNick;
    @BindView(R.id.tv_username)
    TextView mtvUsername;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
            return;
        initData();
    }

    private void initData() {
        String username = EMClient.getInstance().getCurrentUser();
        mtvUsername.setText("微信号:"+username);
        EaseUserUtils.setAppUserNick(username,mtvUserNick);
        EaseUserUtils.setAppUserAvatar(getContext(),username,mivAvatar);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (((MainActivity) getActivity()).isConflict) {
            outState.putBoolean("isConflict", true);
        } else if (((MainActivity) getActivity()).getCurrentAccountRemoved()) {
            outState.putBoolean(Constant.ACCOUNT_REMOVED, true);
        }
    }

    @OnClick({R.id.layout_private_info, R.id.txt_money_package, R.id.txt_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_private_info:
                MFGT.gotoUserProfile(getActivity(), EMClient.getInstance().getCurrentUser());
                break;
            case R.id.txt_money_package:
                RedPacketUtil.startChangeActivity(getActivity());
                break;
            case R.id.txt_setting:
                MFGT.gotoSetting(getActivity());
                break;
        }
    }

}
