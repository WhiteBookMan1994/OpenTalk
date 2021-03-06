package com.dxc.opentalk.easyexceltest.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.dxc.opentalk.easyexceltest.data.UploadData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * EasyExcelListener使用示例参见 easyexcel 的测试用例
 * 需要注意的是 EasyExcelListener 一般不能设计成单例的
 *
 * see https://github.com/alibaba/easyexcel/blob/master/src/test/java/com/alibaba/easyexcel/test/demo/web/UploadDataListener.java
 *
 * @author dingchenchen
 * @since 2021/2/19
 */
@Data
@Slf4j
public class EasyExcelListener extends AnalysisEventListener<UploadData> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<UploadData> list = new ArrayList<UploadData>();

    /**
     * 错误数据列表
     */
    public List<UploadData> errorList = new ArrayList<>();

    @Override
    public void invoke(UploadData data, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", data);
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        for (UploadData data : list) {
            if (Objects.isNull(data.getCustomerPhone())) {
                data.setErrorInfo("客户手机号为空");
                errorList.add(data);
                continue;
            }
        }
        log.info("存储数据库成功！");
        log.error("处理失败的数据:"+ errorList);
    }
}
