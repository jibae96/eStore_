package kr.ac.jungin.cse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jungin.cse.model.Cart;
import kr.ac.jungin.cse.model.CartItem;
import kr.ac.jungin.cse.model.Product;
import kr.ac.jungin.cse.model.User;
import kr.ac.jungin.cse.service.CartItemService;
import kr.ac.jungin.cse.service.CartService;
import kr.ac.jungin.cse.service.ProductService;
import kr.ac.jungin.cse.service.UserService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/cart")
public class CartRestController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/{cartId}", method=RequestMethod.GET)
	public ResponseEntity<Cart> getCartById(@PathVariable(value="cartId") int cartId){
		
		Cart cart = cartService.getCartById(cartId);
		
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{cartId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> clearCart(@PathVariable(value="cartId") int cartId){
		
		Cart cart = cartService.getCartById(cartId);
		cartItemService.removeAllCartItems(cart);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/cartItem/{productId}", method=RequestMethod.PUT)
	public ResponseEntity<Void> addItem(@PathVariable(value="productId") int productId){
		
		Product product = productService.getProductById(productId);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userService.getUserByUsername(username);
		Cart cart = user.getCart();
		
		// check if cartitem for a given product already exists
		List<CartItem> cartItems = cart.getCartItems();
		
		for(int i=0; i<cartItems.size(); i++) {
			if(product.getId() == cartItems.get(i).getProduct().getId()) {
				CartItem cartItem = cartItems.get(i);
				cartItem.setQuantity(cartItem.getQuantity()+1);
				cartItem.setTotalPrice(product.getPrice()*cartItem.getQuantity());
				
				cartItemService.addCartItem(cartItem);
				
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}
		
		// create new cartItem
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(1);
		cartItem.setTotalPrice(product.getPrice()*cartItem.getQuantity());
		
		cartItem.setProduct(product);
		cartItem.setCart(cart);
		
		cartItemService.addCartItem(cartItem);
		
		// bidirectional
		cart.getCartItems().add(cartItem);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/cartItem/{productId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> removeItem(@PathVariable(value="productId") int productId){
		
		//Cart cart = cartService.getCartById(1); // temporary
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userService.getUserByUsername(username);
		Cart cart = user.getCart();
		
		CartItem cartItem = cartItemService.getCartItemByProductId(cart.getId(), productId);
		cartItemService.removeCartItem(cartItem);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/cartItem/plus/{productId}", method=RequestMethod.PUT)
	public ResponseEntity<Void> plusItem(@PathVariable(value="productId") int productId){
		
		Product product = productService.getProductById(productId);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userService.getUserByUsername(username);
		Cart cart = user.getCart();
		
		CartItem cartItem = cartItemService.getCartItemByProductId(cart.getId(), productId);
		
		if(cartItem.getQuantity() < product.getUnitInStock()) {
			cartItem.setQuantity(cartItem.getQuantity()+1);
			cartItem.setTotalPrice(product.getPrice()*cartItem.getQuantity());
			cartItemService.addCartItem(cartItem);
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void> (HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/cartItem/minus/{productId}", method=RequestMethod.PUT)
	public ResponseEntity<Void> minusItem(@PathVariable(value="productId") int productId){
		
		Product product = productService.getProductById(productId);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userService.getUserByUsername(username);
		Cart cart = user.getCart();
		
		CartItem cartItem = cartItemService.getCartItemByProductId(cart.getId(), productId);
		
		if(cartItem.getQuantity() > 1) {
			cartItem.setQuantity(cartItem.getQuantity()-1);
			cartItem.setTotalPrice(product.getPrice()*cartItem.getQuantity());
			cartItemService.addCartItem(cartItem);
			
			return new ResponseEntity<>(HttpStatus.OK);
		}else if(cartItem.getQuantity()==1) {
			cartItemService.removeCartItem(cartItem);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void> (HttpStatus.NO_CONTENT);
	}
}
