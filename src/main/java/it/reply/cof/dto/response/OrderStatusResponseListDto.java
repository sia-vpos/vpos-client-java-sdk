package it.reply.cof.dto.response;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusResponseListDto {
    public List<OrderStatusResponseDto> oSRElements = new ArrayList<>();
    public OrderStatusResponseDto oSRCommon = new OrderStatusResponseDto();
}
