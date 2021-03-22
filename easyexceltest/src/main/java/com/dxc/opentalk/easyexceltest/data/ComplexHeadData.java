package com.dxc.opentalk.easyexceltest.data;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author dingchenchen
 * @since 2021/3/16
 */
@Data
public class ComplexHeadData {

    @ExcelProperty("序号")
    private Long id;

    @ExcelProperty({"主标题", "字符串标题"})
    private String string;
    @ExcelProperty({"主标题", "日期标题"})
    private Date date;
    @ExcelProperty({"主标题", "数字标题"})
    private Double doubleData;

    @ExcelProperty({"主标题1", "字符串标题"})
    private String string1;
    @ExcelProperty({"主标题1", "日期标题"})
    private Date date1;
    @ExcelProperty({"主标题1", "数字标题"})
    private Double doubleData1;

    @ExcelProperty("备注")
    private String remark;
}
