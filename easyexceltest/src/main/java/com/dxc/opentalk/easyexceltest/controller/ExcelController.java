package com.dxc.opentalk.easyexceltest.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.dxc.opentalk.easyexceltest.data.UploadData;
import com.dxc.opentalk.easyexceltest.listener.EasyExcelListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        if (!errList.isEmpty()){
            EasyExcel.write(response.getOutputStream(), UploadData.class).sheet("导入结果附带错误信息").doWrite(errList);
        }
        return "success";
    }
}
