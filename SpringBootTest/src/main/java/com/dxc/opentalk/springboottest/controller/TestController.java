package com.dxc.opentalk.springboottest.controller;

import com.dxc.opentalk.springboottest.service.TestService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author dingchenchen
 * @since 2020-05-01
 */
@RestController
@RequestMapping("/contract")
@EnableAsync
public class TestController implements ApplicationContextAware {

    @Autowired
    private TestService testService;

    @GetMapping(value = "/testAsync")
    public void print() {
        System.out.println("ThreadName:" + Thread.currentThread().getName());
        System.out.println("当前线程开始执行测试函数......");
        CompletableFuture<String> s = testService.test();
        try {
            System.out.println("s="+s.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= 100; i++) {
            System.out.print(i + " ");
            if (i % 10 == 0) {
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("当前线程测试函数执行完毕......");
    }
    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext =applicationContext;
        applicationContext.toString();
    }

    @GetMapping("/getContractUrl")
    public Result<String> getContractUrl(HttpServletResponse response) {
        System.out.println("llllllllllll");
        String fileName = "链金系统合作协议" + ".pdf";
        response.addHeader("Content-disposition",
                "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/pdf");
        return Result.success("https://filestore.dasouche.net/file/aGcVtm69jQ.pdf?accessToken=Ws4mczIbw3");
        }

        @RequestMapping(value = "/download")
        @ResponseBody
        public void download(HttpServletRequest request, HttpServletResponse response) {
            InputStream in = null;
            OutputStream out = null;
            try {
                URL url = new URL("https://filestore.dasouche.net/file/aGcVtm69jQ.pdf?accessToken=Ws4mczIbw3");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时间为30秒
            conn.setConnectTimeout(30000);
            conn.setDoOutput(true);
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            conn.setDoInput(true);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            in = conn.getInputStream();

            //根据时间设置文件名
            String fileName = "链金系统合作协议" + ".pdf";
            //响应输出流，让用户自己选择保存路径 报文头 可以根据自己下载的文件格式去查询响应的报文头
            response.addHeader("Content-disposition",
                    "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/pdf");

            byte[] buffer = new byte[1024];
            int bytesRead = -1;
            while ((bytesRead = in.read(buffer)) != -1) {
                out = response.getOutputStream();
                out.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
               // log.error(ex.getMessage(), ex);
            }
            try {
                out.close();
            } catch (IOException ex) {
               // log.error(ex.getMessage(), ex);
            }
        }
    }
}
