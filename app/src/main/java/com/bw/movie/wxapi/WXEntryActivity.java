package com.bw.movie.wxapi;

import android.app.Activity;
import android.os.Bundle;

import com.bw.movie.base.App;
import com.bw.movie.bean.WXCodeBean;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.greenrobot.eventbus.EventBus;

/*
 *@Auther:cln
 *@Date: 2020/4/23
 *@Time:17:28
 *@Description:
 * */public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getWxApi().handleIntent(getIntent(),this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if(baseResp.errCode == BaseResp.ErrCode.ERR_OK){
            if(baseResp instanceof SendAuth.Resp){
                SendAuth.Resp resp = (SendAuth.Resp)baseResp;
                String code = resp.code;
                WXCodeBean wxCodeBean = new WXCodeBean();
                wxCodeBean.setCode(code);
                EventBus.getDefault().postSticky(wxCodeBean);
            }
        }
    }
}
