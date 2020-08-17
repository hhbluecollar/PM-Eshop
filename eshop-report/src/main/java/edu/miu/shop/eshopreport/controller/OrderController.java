package edu.miu.shop.eshopreport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.miu.shop.eshopreport.domain.Order;
import edu.miu.shop.eshopreport.domain.VendorRevenue;
import edu.miu.shop.eshopreport.service.OrderService;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/reports")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	
	@GetMapping("/revenue/{vID}")
	public List<VendorRevenue> listOrders(@PathVariable String vID)
	{
		//parameterVariable // ?id=1
		//PathVariable // /1
         
		return  orderService.listRevenueByDay(vID);
	}
	
	
}
