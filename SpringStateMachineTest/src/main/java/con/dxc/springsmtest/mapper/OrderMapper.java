package con.dxc.springsmtest.mapper;

import con.dxc.springsmtest.model.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author dingchenchen
 * @since 2020/8/4
 */
@Mapper
public interface OrderMapper {

    OrderDO selectByOrderNo(@Param("orderNo") String orderNo);

    int insert(OrderDO orderDO);

    int update(OrderDO orderDO);
}
