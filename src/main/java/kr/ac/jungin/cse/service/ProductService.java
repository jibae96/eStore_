package kr.ac.jungin.cse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jungin.cse.dao.ProductDao;
import kr.ac.jungin.cse.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;

	public Product getProductById(int id) {

		return productDao.getProductById(id);
	}

	public List<Product> getProducts() {

		return productDao.getProducts();
	}

	public void addProduct(Product product) {

		productDao.addProduct(product);
	}

	public void deleteProduct(Product product) {

		productDao.deleteProduct(product);
	}

	public void updateProduct(Product product) {

		productDao.updateProduct(product);
	}
}
