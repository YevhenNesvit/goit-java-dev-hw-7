package controller.customers;

import config.HibernateProvider;
import model.dto.CustomerDto;
import repositories.CustomerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/getCustomers")
public class GetCustomersController extends HttpServlet {
    CustomerRepository customerRepository;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        customerRepository = new CustomerRepository(provider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            List<CustomerDto> customers = customerRepository.findAll();
            req.setAttribute("customers", customers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/view/customers/getCustomers.jsp").forward(req, resp);
    }
}
