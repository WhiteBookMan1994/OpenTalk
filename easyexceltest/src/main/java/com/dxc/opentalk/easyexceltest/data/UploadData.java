package com.dxc.opentalk.easyexceltest.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author dingchenchen
 * @since 2021/2/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadData {
    /**
     * 客户姓名
     * */
    @ExcelProperty("客户姓名")
    private String customerName;
    /**
     * 客户手机号
     * */
    @ExcelProperty("客户手机号")
    private String customerPhone;

    /**
     * 意向车型
     * */
    @ExcelProperty("意向车型")
    private String modelName;

    /**
     * 线索来源
     * */
    @ExcelProperty("线索来源")
    private String clueChannel;

    /**
     * 日期，"yyyy年MM月"可以正常解析Excel中的"2020年2月"字符串数据
     * */
    @DateTimeFormat("yyyy年MM月")
    private Date date;

    /**
     * 返回的错误信息列
     * */
    @ExcelProperty("错误信息")
    private String errorInfo;
}
