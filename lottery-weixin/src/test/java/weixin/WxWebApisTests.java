package weixin;

import com.wyl.lottery.weixin.api.webapis.WxWebApis;
import org.junit.Test;

public class WxWebApisTests extends BaseTest {

    @Test
    public void getJsapiTicket() {
        System.out.println(WxWebApis.getJsapiTicket(ACCESS_TOKEN));
    }
}
