package weixin;

import com.wyl.lottery.weixin.api.accountapis.WxAccountApis;
import com.wyl.lottery.weixin.api.accountapis.bean.QrcodeType;
import com.wyl.lottery.weixin.api.accountapis.bean.WxQrcodeRequest;
import org.junit.Test;

public class WxAccountApisTests extends BaseTest {
    @Test
    public void testCreateQrcode() {
        WxQrcodeRequest wxQrcodeRequest = new WxQrcodeRequest();
        wxQrcodeRequest.setAction_name(QrcodeType.QR_LIMIT_STR_SCENE);
        wxQrcodeRequest.setActionInfo("COMPANY_" + 100);
        System.out.println(WxAccountApis.createQrcode(ACCESS_TOKEN, wxQrcodeRequest));
        //https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQHt8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyTzJFdFJqdGxhWlAxMDAwMHcwN1MAAgQa5d9bAwQAAAAA
    }

    @Test
    public void testCreateCourierQrcode() {
        WxQrcodeRequest wxQrcodeRequest = new WxQrcodeRequest();
        wxQrcodeRequest.setAction_name(QrcodeType.QR_STR_SCENE);
        wxQrcodeRequest.setActionInfo("COURIER_" + 100);
        wxQrcodeRequest.setExpire_seconds(2592000);
        System.out.println(WxAccountApis.createQrcode(ACCESS_TOKEN, wxQrcodeRequest));
        //https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFp8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyNzhvLVJPdGxhWlAxYllmN2hzY3UAAgT8AuBbAwQAjScA
    }
}
