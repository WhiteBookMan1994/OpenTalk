package com.dxc.opentalk.easyexceltest.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.dxc.opentalk.easyexceltest.data.UploadData;
import com.dxc.opentalk.easyexceltest.listener.EasyExcelListener;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author dingchenchen
 * @since 2021/2/19
 */
@RestController
public class ExcelController {

    /**
     * 导入文件，并且导出错误数据原因生成Excel导出
     * 测试结果：
     * 1、Postman
     * 使用 Postman 测试接口可以导出错误数据，但是"Send and Download" 保存文件的路径名是".xlsx"后缀
     */
    @PostMapping("import")
    public String importExcel(HttpServletResponse response, @RequestParam MultipartFile file) throws IOException {
        EasyExcelListener easyExcelListener = new EasyExcelListener();
        EasyExcelFactory.read(file.getInputStream(), UploadData.class, easyExcelListener).sheet().doRead();
        List<UploadData> errList = easyExcelListener.getErrorList();
        //如果包含错误信息就导出错误信息
        if (!errList.isEmpty()) {
            EasyExcel.write(response.getOutputStream(), UploadData.class).sheet("导入结果附带错误信息").doWrite(errList);
        }
        return "success";
    }

    @SneakyThrows
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws MalformedURLException {
        URL url = new URL("http://xxxx.aliyuncs.com/6033258452aef377111fc2d8.xlsx");
        HttpURLConnection httpConn = null;
        try {
            httpConn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            httpConn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream cin = null;
        try {
            cin = httpConn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            EasyExcel.read(cin, UploadData.class, new EasyExcelListener()).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟下载本地Excel文件
     * @param response
     * @throws IOException
     */
    @GetMapping("/downloadLocal")
    public void downloadFromLocalFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String exportFileName = URLEncoder.encode("潜客导出数据文件", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + exportFileName + ".xlsx");
        String fileName = "/Users/dingchenchen" + File.separator + "Downloads" + File.separator + "K2KbendiTest.xlsx";
        File file = new File(fileName);
        if (file.exists()) {
            byte[] b = new byte[100];
            int len;
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);
                while ((len = inputStream.read(b)) > 0)
                    response.getOutputStream().write(b, 0, len);
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }
    }
    /*
    * 采用 hutool 框架写法：
    * try {
            IoUtil.copy(classPathResource.getInputStream(), response.getOutputStream());
        }finally {
            IoUtil.close(classPathResource.getInputStream());
            IoUtil.close(response.getOutputStream());
            IoUtil.flush(response.getOutputStream());
        }
    * */
}
