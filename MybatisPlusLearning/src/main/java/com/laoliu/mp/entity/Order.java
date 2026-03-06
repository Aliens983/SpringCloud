package com.laoliu.mp.entity;

import java.io.Serial;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * Order Table
 * </p>
 *
 * @author 25516
 * @since 2026-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order")
@ApiModel(value="Order对象", description="Order Table")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Primary Key ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Business Order Number (Unique)")
    private String orderNo;

    @ApiModelProperty(value = "Associated User ID")
    private Long userId;

    @ApiModelProperty(value = "Total Price")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "Order Status: 0-Pending, 1-Paid, 2-Shipped")
    private Integer status;

    @ApiModelProperty(value = "Creation Time")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "Update Time")
    private LocalDateTime updatedAt;


}
