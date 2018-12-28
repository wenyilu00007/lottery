package weixin;

import com.alibaba.fastjson.JSONObject;
import com.wyl.lottery.weixin.api.base.WxResponse;
import com.wyl.lottery.weixin.api.menuapis.WxMenuApis;
import com.wyl.lottery.weixin.api.menuapis.bean.WxMenuRequest;
import com.wyl.lottery.weixin.api.menuapis.bean.buttons.BaseButton;
import com.wyl.lottery.weixin.api.menuapis.bean.buttons.ViewButton;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WxMenuApisTests extends BaseTest{
    @Test
    public void testCreate(){
        ViewButton viewButton1 = new ViewButton("寄快递", "http://www.ksudi.org/weixin/menu/placeorderrouter");
        ViewButton viewButton2 = new ViewButton("查询", "http://www.ksudi.org/weixin/menu/search");
        BaseButton BaseButton = new BaseButton("惊喜盒子");
        ViewButton viewButton3 = new ViewButton("在线客服", "https://www.sobot.com/chat/h5/index.html?sysNum=8ad7acc4a7a9415aa2b0f1f5fda560cf&source=1");
        ViewButton viewButton4 = new ViewButton("1元快递","http://mp.weixin.qq.com/mp/homepage?__biz=MzU1NDAzNzcwOA==&hid=1&sn=7cbb96eb1c9f3492fd6bc13fe7a0bc8f&scene=18#wechat_redirect");
        ViewButton viewButton5 = new ViewButton("企业官网","http://www.ksudi.com");
        List<Object> subButtons = new ArrayList<>();
        subButtons.add(viewButton3);
        subButtons.add(viewButton4);
        subButtons.add(viewButton5);
        BaseButton.setSub_button(subButtons);

        WxMenuRequest wxMenuRequest = new WxMenuRequest();
        List<Object> buttons = new ArrayList<>();
        buttons.add(viewButton1);
        buttons.add(viewButton2);
        buttons.add(BaseButton);
        wxMenuRequest.setButton(buttons);
        System.out.println(JSONObject.toJSONString(wxMenuRequest));

        WxResponse wxResponse = WxMenuApis.createMenu(ACCESS_TOKEN, wxMenuRequest);
        System.out.println(wxResponse);
    }

    @Test
    public void testGet(){
        System.out.println(WxMenuApis.getMenu(ACCESS_TOKEN));
    }

    @Test
    public void testDelete(){
        System.out.println(WxMenuApis.deleteMenu(ACCESS_TOKEN));
    }

}
