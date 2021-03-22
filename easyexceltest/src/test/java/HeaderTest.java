import com.alibaba.excel.EasyExcel;
import com.dxc.opentalk.easyexceltest.data.ComplexHeadData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author dingchenchen
 * @since 2021/3/16
 */
public class HeaderTest {
    /**
     * 复杂头写入
     * <p>1. 创建excel对应的实体对象 参照{@link ComplexHeadData}
     * <p>2. 使用{@link ExcelProperty}注解指定复杂的头
     * <p>3. 直接写即可
     */
    @Test
    public void complexHeadWrite() {
        String fileName = "complexHeadWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, ComplexHeadData.class).sheet("模板").doWrite(data());
    }

    private List<ComplexHeadData> data() {
        List<ComplexHeadData> list = new ArrayList<ComplexHeadData>();
        ComplexHeadData data = new ComplexHeadData();
        data.setDate(new Date());
        data.setDoubleData(0.0);
        data.setString("字符串");
        data.setDate1(new Date());
        data.setDoubleData1(1.0);
        data.setString1("字符串1");
        data.setRemark("备注");
        list.add(data);
        return list;
    }
}
