package kr.ac.jungin.cse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jungin.cse.dao.CartDao;
import kr.ac.jungin.cse.model.Cart;

@Service
public class CartService {

	@Autowired
	private CartDao cartDao;
	
	public Cart getCartById(int cartId) {
		return cartDao.getCartById(cartId);
	}
	
	public void saveCart(Cart cart) {
		cartDao.saveCart(cart);
	}
}
