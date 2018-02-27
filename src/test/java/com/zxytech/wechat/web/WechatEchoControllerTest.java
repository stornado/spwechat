package com.zxytech.wechat.web;

import com.zxytech.wechat.domain.WechatMp;
import com.zxytech.wechat.domain.WechatMpService;
import com.zxytech.wechat.domain.message.ReceivedMessageRepository;
import com.zxytech.wechat.domain.message.ReplyMessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author xwxia
 * @date 2018/2/27 10:09
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class WechatEchoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceivedMessageRepository receivedMessageRepository;
    @MockBean
    private ReplyMessageRepository replyMessageRepository;
    @MockBean
    private WechatMpService wechatMpService;

    @Before
    public void setUp() {
        WechatMp wechatMp = new WechatMp("wx34baa8d2cf0a8193",
                "f0f008b90585ea8eb91c9503fe5a167c",
                "9d70199f4ab3f6bb287c97db4c228562",
                "fQ6FDBtJ6brvSxkwdic6MOwzrTpn9Kl2OkLfL1aZ8KB");
        when(wechatMpService.get(anyString())).thenReturn(wechatMp);
    }

    @Test
    public void authorize() throws Exception {
        this.mockMvc.perform(get("/mp/5a93e6e2ce48510b78d4c1ba")
                .param("signature", "ffe83db8235711ee2c46c44b1995a8f94a443336")
                .param("timestamp", "1519696019")
                .param("nonce", "4271608171")
                .param("echostr", "11138335442772884479"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("11138335442772884479"));
    }

    @Test
    public void echo() throws Exception {
        this.mockMvc.perform(post("/mp/5a93e6e2ce48510b78d4c1ba")
                .param("signature", "f4f1d3b1ee39988f1f6087e320afe011408b9c88")
                .param("encrypt_type", "aes")
                .param("msg_signature", "9dbb8f8869ad17787eedab9c1ac591c136f8a597")
                .param("timestamp", "1519695276")
                .param("nonce", "957216316")
                .content("<xml>\n" +
                        "    <ToUserName><![CDATA[eee]]></ToUserName>\n" +
                        "    <Encrypt><![CDATA[Zc4K3Puf+vZeytZNPIWNT9FjVdKHSy9ouzokwrJJi+hGFussmeDUdGa09uSC5ER8cn22k1ZJJQzVSajE6GizRMDdC+7+5oGbjyztxOi9Y28UyLl3IiaFShHWm5dChRvjMOp3o5pwFpkPC9Ng6wgyKUizmVav/RrwthjAzSSDLDh2ijxYGKjt7HUN/M9fsAf7wtZNdyx+yehfdst+Tj9QIwehdQh49WGwNSOMJrbQFh6S0x2QzkTZIE90ch4iqf4aSb7x9h2WY+akyzkK/EpMK1mXEz/ZmLEq+ZRj2HYE0sJHKyjpmxOD9VrKS2s+nC8XUTdT5XKmE2zIkpjA56TEAZPMAyxHGhMd4eHHOkj9YHhpKrxPGgI7bqh+KJQuMrZnhWvbwJi58iUti1oHqnODl0/8zzwCDxxAvJARv8aN7RsW8PgBgdC0j8zBqUpTrR9oMx3UHyXsVFGUSJfpu76AONovwnZR7bayIgn9K1YVG9YkFWEdbsmGDhuAvZ824vX6]]></Encrypt>\n" +
                        "</xml>\n")
                .accept(MediaType.TEXT_XML)
                .contentType(MediaType.TEXT_XML))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_XML_VALUE));
    }
}