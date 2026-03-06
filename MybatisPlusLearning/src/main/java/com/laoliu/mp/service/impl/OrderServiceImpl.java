package com.laoliu.mp.service.impl;

import com.laoliu.mp.entity.Order;
import com.laoliu.mp.mapper.OrderMapper;
import com.laoliu.mp.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Order Table 服务实现类
 * </p>
 *
 * @author 25516
 * @since 2026-03-06
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
