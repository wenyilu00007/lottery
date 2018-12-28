package weixin;

import com.wyl.lottery.weixin.api.tokenapis.WxTokenApis;
import com.wyl.lottery.weixin.api.tokenapis.bean.AccessToken;
import com.wyl.lottery.weixin.api.tokenapis.bean.WxAccessTokenRequest;
import org.junit.Test;

public class WxAccessTokenApisTests extends BaseTest {
    @Test
    public void testGetAccessToken() {
        WxAccessTokenRequest wxAccessTokenRequest = new WxAccessTokenRequest(APPID, SECRET);
        AccessToken accesstoken = WxTokenApis.getAccessToken(wxAccessTokenRequest);
        System.out.println(accesstoken.getAccess_token());
    }
}
