package com.masai.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Address;
import com.masai.beans.Customer;
import com.masai.beans.Seller;
import com.masai.beans.User;
import com.masai.service.AddressService;

@RestController
@RequestMapping("/ecommerce/user")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	
			// Handle		 --> /ecommerce/sellerPortal/city/{city}
			// What is does? --> GET user with city
			// Request Type? --> GET Request
			// Input 		 --> String city
			/*
			 * @GetMapping("/city/{city}") public ResponseEntity<Address>
			 * listAllUserByCity(@PathVariable("city") String city) { city =
			 * city.toLowerCase(); Set<User> listOfUser =
			 * addressService.listAllUserByCity(city); return new ResponseEntity(listOfUser,
			 * HttpStatus.OK); }
			 */
			
			// Handle		 --> /ecommerce/sellerPortal/state/{state}
			// What is does? --> GET user with city
			// Request Type? --> GET Request
			// Input 		 --> String state
			//@GetMapping("/state/{state}")
			/*
			 * public ResponseEntity<Address> listAllUserByState(@PathVariable("state")
			 * String state) { state = state.toLowerCase(); Set<User> listOfUser =
			 * addressService.listAllUserByState(state); return new
			 * ResponseEntity(listOfUser, HttpStatus.OK); }
			 */
			
			
			// Handle		 --> /ecommerce/sellerPortal/pincode/{pincode}
			// What is does? --> GET user with city
			// Request Type? --> GET Request
			// Input 		 --> String state
			/*
			 * @GetMapping("/pincode/{pincode}") public ResponseEntity<Address>
			 * listAllUserByPincode(@PathVariable("pincode") String pincode) { Set<User>
			 * listOfUser = addressService.listAllUserByPincode(pincode); return new
			 * ResponseEntity(listOfUser, HttpStatus.OK); }
			 */
}
	
		//xxxxxxxxxxxxxxxxx Un-used endpoint xxxxxxxxxxxxxxxxxxxxxxxxxx
 			
			
		// Handle		 --> /ecommerce/sellerPortal/address
		// What is does? --> Adds a new Address
		// Request Type? --> POST Request
		// Input 		 --> Address object
//		@PostMapping("/address")
//		public ResponseEntity<Address> addAddress(@RequestBody Address address) {
//			Address addedAddress = addressService.addAddress(address);
//			return new ResponseEntity(addedAddress, HttpStatus.CREATED);
//		}
	
		// Handle		 --> /ecommerce/sellerPortal/address/{addressId}
		// What is does? --> Deletes addres with given ID
		// Request Type? --> DELETE Request
		// Input 		 --> Address ID
//		@DeleteMapping("/address/{addressId}")
//		public ResponseEntity<Address> removeAddressByAddressId(@PathVariable("addressId") Integer addressId) {
//			String removeAddress = addressService.removeAddressByAddressId(addressId);
//			return new ResponseEntity(removeAddress, HttpStatus.OK);
//		}
		
		


		// Handle		 --> /ecommerce/sellerPortal/address/{city}
		// What is does? --> GET user with city
		// Request Type? --> GET Request
		// Input 		 --> String state
//		@GetMapping("/state/{state}")
//		public ResponseEntity<Address> listAllUserByState(@PathVariable("state") String state) {
//			state = state.toLowerCase();
//			Set<User> listOfUser = addressService.listAllUserByState(state);
//			return new ResponseEntity(listOfUser, HttpStatus.OK);
//		}
		
		//ADD ENDPOINT TO GET ALL ADDRESSES OF THE USER


