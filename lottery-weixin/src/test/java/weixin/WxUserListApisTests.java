package weixin;

import com.wyl.lottery.weixin.api.userapis.WxUserApis;
import org.junit.Test;

public class WxUserListApisTests extends BaseTest{
    @Test
    public void testGet() {
        System.out.println(WxUserApis.getUsers(ACCESS_TOKEN));
    }

    @Test
    public void testGetInfo() {
        System.out.println(WxUserApis.getUserInfo(ACCESS_TOKEN,"oXdW9s7OCQS-rt0I6jN9uFOqRDHo"));
    }
    @Test
    public void testGetInfos() {
        System.out.println(WxUserApis.getUserInfos(ACCESS_TOKEN,"oXdW9s7OCQS-rt0I6jN9uFOqRDHo","oXdW9s-wMQXOYLDVxc20Nq3U0OMI"));
    }



}
