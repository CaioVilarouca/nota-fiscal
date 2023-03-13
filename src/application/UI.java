package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

import entitites.Product;
import entitites.Purchase;
import entitites.ShoppingCart;

public class UI {
	Scanner scanner = new Scanner(System.in);
	ShoppingCart shoppingCart = new ShoppingCart();

	String[] menu = { "Adicionar produto", "Valor total", "Sair" };
	int option, optionVerification, amountPurchase;
	double valueProduct, getTotalValueOfProduct;
	boolean verification;

	public void systemUI() {
		do {
			option = JOptionPane.showOptionDialog(null, "Menu de opções", "Menu", JOptionPane.DEFAULT_OPTION, 3, null,
					menu, menu[0]);

			switch (option) {
			case 0:
				systemAddProduct();
				break;
			case 1:
				valueTotalCart();
				break;
			default:
				break;
			}
		} while (true);
	}

	public void systemAddProduct() {

		// Add product
		do {
			Product product = new Product();
			product.setNameProduct(JOptionPane.showInputDialog(null, "Nome do Produto :", "Carrinho", 3));
			try {
				product.setValueProduct(valueProduct = Double.parseDouble((String) JOptionPane.showInputDialog(null,
						"Produto [" + product.getNameProduct() + "] R$:", "Preço", 3)));
				amountPurchase = Integer.parseInt((String) JOptionPane.showInputDialog(null,
						"Quantidade de " + product.getNameProduct() + " R$:[" + product.getValueProduct() + "] ?",
						"Preço", 3));
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Esse campo só aceita números.", "ERRO", 0, null);
			}

			// Add no carrinho
			Purchase purchase = new Purchase(amountPurchase, product);
			shoppingCart.addPurchase(purchase);

			// Resultado de quantidade + valor do produto
			getTotalValueOfProduct = purchase.getTotalValueOfProduct();
			JOptionPane.showMessageDialog(null, "Preço R$:[" + getTotalValueOfProduct + "], Produto ["
					+ product.getNameProduct() + "], quatidade [" + amountPurchase + "]", "Valor final", 1);

			// Verificação
			optionVerification = JOptionPane.showConfirmDialog(null, "Adicionar mais produtos ?", "Sair", 0);
			verification = optionVerification == 0 ? true : false;
		} while (true == verification);
		valueTotalCart();
	}

	public void valueTotalCart() {
		// Valor final do carrinho
		JOptionPane.showMessageDialog(null, "Valor final R$:[" + shoppingCart.getValueTotalPurchase() + "]",
				"Valor final", 1);
	}

	public void WriteCart() {
		// Print no bloco de notas
		try {
			BufferedWriter url = new BufferedWriter(new FileWriter("./Nota-Fiscal/Comprovante.txt"));
			url.write(String.format(" Valor do debito :[R$%.2f] \n X  Data do debito  :%s", shoppingCart.getValueTotalPurchase(), shoppingCart.datePerchase()));
			
			for (Purchase list : shoppingCart.getListPurchase()){
				url.write("\n X  "+ list);
			}
			
			url.close();			
		url.close();
			
		} catch (Exception e) {
		}
	}
}