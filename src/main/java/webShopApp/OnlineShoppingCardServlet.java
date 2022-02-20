/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//POGLEDATI PREDAVANJE WEB APP 4/5 NA 2:35 ZADATAK
package webShopApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Product;
import model.ShoppingCard;
import model.ShoppingCardItem;
import static webShopApp.OnlineShopServlet.PRODUCT_KEY;

/**
 *
 * @author Harun
 */
public class OnlineShoppingCardServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Korpa</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Artikli u korpi</h1>");
            HttpSession session = request.getSession();
            ShoppingCard shoppingCard = (ShoppingCard) session.getAttribute("card");
            if (shoppingCard == null) {
                shoppingCard = new ShoppingCard();
                session.setAttribute("card", shoppingCard);
            }
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int productId = Integer.parseInt(request.getParameter("productId"));
            List<Product> products = (List<Product>) getServletContext().getAttribute(PRODUCT_KEY);
            for (Product product : products) {
                if (product.getProductId() == productId) {
                    System.out.println("Produkt postoji .. Dodajem ga u shoping ");
                    shoppingCard.addItem(product, quantity);
                    break;
                }
            }
             List<ShoppingCardItem> shoppingCardItems = shoppingCard.getShoppingCardItems();
            if (shoppingCard.getShoppingCardItems().isEmpty()) {
                out.println("<h3>Nema artikala u korpi</h3>");
            } else {
                out.println("<table cellspacing='0' cellpadding='3' border='1'>");
                out.println("<tr bgcolor='lightgray'><th>Naziv</th><th>Jedinična cijena</th><th>Količina</th><th>Cijena</th></tr>");
                for (ShoppingCardItem item : shoppingCard.getShoppingCardItems()) {
                    System.out.println("Kreiram tabelu sa dodatim itemima...");
                    out.println("<tr>");
                    out.println("<td>" + item.getProduct().getProductName() + "</td>");
                    out.println("<td>" + item.getProduct().getUnitPrice().toPlainString() + "</td>");
                    out.println("<td>" + item.getQuantity() + "</td>");
                    out.println("<td>" + item.getTotalPrice().toPlainString() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
