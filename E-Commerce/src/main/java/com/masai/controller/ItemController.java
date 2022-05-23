//package com.masai.controller;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.masai.beans.Item;
//import com.masai.beans.Product;
//import com.masai.exception.ProductNotFoundException;
//import com.masai.repository.ProductCrudRepo;
//import com.masai.service.ItemServiceInterface;
//import com.masai.service.ProductService;
//import com.masai.service.ProductServiceInterface;
//




// No Use but Don't delete it (Needed for reference)






//@RestController
//public class ItemController {
//
//	@Autowired
//	private ItemServiceInterface itemService;
//	
//	@Autowired
//	private ProductService productService;
//	
//	@PostMapping(value = "/item")
//	public ResponseEntity<Item> addItem(@RequestBody Item item){
//		
//		Product productCheck=productService.getProductRepo().findByProductId(item.getProduct().getProductId());
//		
//		//Product productCheck=productService.getProductRepo().findByProductName(item.getProduct().getProductName());
//		//System.out.println("producr check-> "+productCheck.getProductName()+productCheck.getProductId());
//		
//		if(productCheck != null) {
//			
//			if(productCheck.getQuantity()>=item.getRequiredQuantity()) {
//				
//				System.out.println((productCheck.getPrice()*item.getRequiredQuantity()));
//				
//				//Setting the item Price
//				item.setItemPrice(productCheck.getPrice()*item.getRequiredQuantity());
//				
//				item.setProduct(productCheck);
//				
//				//saving item to DB
//				Item newItem=itemService.addItem(item);
//				return new ResponseEntity<>(newItem,HttpStatus.ACCEPTED);
//			}
//			
//			else {
//				throw new ProductNotFoundException("Product quantity is not enough");
//			}
//			
//		}
//		
//		else {
//			throw new ProductNotFoundException("Product does not exist");
//		}
//	}
//}
