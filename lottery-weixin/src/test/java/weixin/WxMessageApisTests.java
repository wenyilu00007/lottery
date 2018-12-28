package weixin;

import com.thoughtworks.xstream.XStream;
import com.wyl.lottery.weixin.api.messageapis.WxMessageApis;
import com.wyl.lottery.weixin.api.messageapis.bean.WxTempMsgRequest;
import com.wyl.lottery.weixin.api.messageapis.bean.messages.GraphicMessage;
import com.wyl.lottery.weixin.api.messageapis.bean.messages.MsgType;
import org.junit.Test;

public class WxMessageApisTests extends BaseTest{

    @Test
    public void testSendTemplateMessage() {
        WxTempMsgRequest wxTempMsgRequest = new WxTempMsgRequest();
        wxTempMsgRequest.setTemplate_id("253Bzno31fZ5eBrMP0bHXxvTV2LdhIYqbUA9A6pMveE");
//        wxTempMsgRequest.setUrl("https://www.baidu.com");
//        wxTempMsgRequest.setColor("#887766");
        wxTempMsgRequest.setTouser("oXdW9s7OCQS-rt0I6jN9uFOqRDHo");
        wxTempMsgRequest.addData("name","测试");
        wxTempMsgRequest.addData("age",22,"#776655");
        wxTempMsgRequest.addData("gender","男");
        System.out.println(WxMessageApis.sendTemplateMessage(ACCESS_TOKEN, wxTempMsgRequest));
    }

    @Test
    public void testCreateGraphicMessage(){
        GraphicMessage graphicMessage = new GraphicMessage();
        graphicMessage.setFromUserName("gh_a6db58308237");
        graphicMessage.setToUserName("oXdW9s6rWq8CUSfz3zSS85yce4YU");
        graphicMessage.setArticleCount(1);
        graphicMessage.setCreateTime(12344555L);
        graphicMessage.setMsgType(MsgType.NEWS);
        GraphicMessage.GraphicArticle article = graphicMessage.createArticle();
        article.setTitle("这里是标题");
        article.setUrl("https://www.baidu.com");
        article.setDescription("点击就打开一个网站，里面内容很丰富");
        article.setPicUrl("http://img0.imgtn.bdimg.com/it/u=1540179471,3642465379&fm=26&gp=0.jpg");

        XStream xStream = new XStream();
        //声明XStream注解来源
        xStream.processAnnotations(GraphicMessage.class);
        xStream.aliasSystemAttribute(null, "class");
        String s = xStream.toXML(graphicMessage);
        System.out.println(s);
    }
}
