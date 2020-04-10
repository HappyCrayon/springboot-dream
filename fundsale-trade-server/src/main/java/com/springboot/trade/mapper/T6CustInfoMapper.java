package com.springboot.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.trade.entity.T6CustInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HappyCrayon
 * @since 2019-05-05
 */
public interface T6CustInfoMapper extends BaseMapper<T6CustInfo> {

    List<T6CustInfo> selectCustInfo();

    int deleteCustInfo(@Param("cust_no") String custNo);
}
