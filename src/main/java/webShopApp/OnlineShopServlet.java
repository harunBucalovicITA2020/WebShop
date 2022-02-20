package webShopApp;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author Harun
 */
public class OnlineShopServlet extends HttpServlet {

    static final String PRODUCT_KEY = "products";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        ServletContext servletContext = getServletContext();
        String realPath = servletContext.getRealPath("products.txt");
        try (Scanner scanner = new Scanner(new FileReader(realPath))) {
            List<Product> products = new ArrayList<>();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
                int productId = Integer.parseInt(stringTokenizer.nextToken());
                String productName = stringTokenizer.nextToken();
                BigDecimal unitPrice = new BigDecimal(stringTokenizer.nextToken());
                Product product = new Product(productId, productName, unitPrice);
                products.add(product);
            }
            servletContext.setAttribute(PRODUCT_KEY, products);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>WebShop</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Artikli u našem web shopu</h1>");
            List<Product> products = (List<Product>) getServletContext().getAttribute(PRODUCT_KEY);
            if (products != null && !products.isEmpty()) {
                out.println("Dostupni artikli..");
                out.println("<table cellspacing='0' cellpadding='3' border='1'>");
                out.println("<tr bgcolor='lightgray'>"
                        + "<th>Naziv</th>"
                        + "<th>Cijena</th>"
                        + "<th>Dodaj u korpu</th>"
                        + "</tr>");

                for (Product product : products) {
                    out.println("<tr>");
                    out.println("<td>" + product.getProductName() + "</td>");
                    out.println("<td>" + product.getUnitPrice().toPlainString() + "</td>");
                    out.println("<td>");
                    out.println("<form method='GET' action='card'>"
                            + "<input type='number' name='quantity' size='3'/>"
                            + "<input type='hidden' value='"+product.getProductId()+"' name='productId'/>"
                            + "<input type='submit' value='DODAJ'/>"
                            + "</form>");
                    out.println("</td>");
                    out.println("</tr>");

                }

            } else {
                out.println("Nema vise , nestalo...");
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
