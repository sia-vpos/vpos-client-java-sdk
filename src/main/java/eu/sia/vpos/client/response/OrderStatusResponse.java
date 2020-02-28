package eu.sia.vpos.client.response;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusResponse {
    public List<OrderStatusResponseDto> oSRElements = new ArrayList<>();
    public OrderStatusResponseDto oSRCommon = new OrderStatusResponseDto();
}
