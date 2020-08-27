package con.dxc.springsmtest.model;

import lombok.Data;

/**
 * @author dingchenchen
 * @since 2020/8/2
 */
@Data
public class OrderDO {
    private String userId;
    private String phone;
    private String address;
    private Integer amount;
    private String orderNo;
    private Integer orderStatus;
}
