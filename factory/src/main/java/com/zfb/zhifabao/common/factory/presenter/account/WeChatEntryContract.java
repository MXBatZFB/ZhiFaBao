package com.zfb.zhifabao.common.factory.presenter.account;

import com.zfb.zhifabao.common.factory.presenter.BaseContract;

/**
 * 1.若 access_token 已超时，那么进行 refresh_token 会获取一个新的 access_token，新的超时时间；
 * 2.若 access_token 未超时，那么进行 refresh_token 不会改变 access_token，但超时时间会刷新，相当于续期 access_token。
 * refresh_token 拥有较长的有效期（30 天）且无法续期，当 refresh_token 失效的后，需要用户重新授权后才可以继续获取用户头像昵称。
 */
public class WeChatEntryContract {
    interface View extends BaseContract.View<Presenter>{
        //用户第一次授权时，拉取后台参数AppSecret，成功后，获取access_token,同时存储
        void onFirstUserAuthorization();


        //授权成功时，需在本地存储openid (授权用户唯一标识)，且通过code
        //获取access_token,保存到后台
        void onUserAuthorizationSuccess(int ErrCode ,String code);


        //已授权用户，但是access_token 有效时
        void onUserAccessTokenEffective();


        //当刷新token完成时，更新后台的
        void onRefreshTokenComplete();


        //已授权用户但是access_token 无效时access_token
        //需要进行refresh_token
        void onUserAccessTokenInvalid();
    }

    interface Presenter extends BaseContract.Presenter{
        void getAppSecret();
        //通过code获取access_token
        //GET https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        void  getAccessTokenForCode(String code);
        //如果access_token无效时，进行 refresh_token
        //GET https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
        void  refreshToken(String refresh_token);
        //检验授权凭证（access_token）是否有效
        // GET https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID
        void checkAccessToken(String access_token);
    }

}
