package controller.customers;

import config.HibernateProvider;
import repositories.CustomerRepository;
import utils.CheckCustomers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteCustomerForm")
public class DeleteCustomerFormController extends HttpServlet {
    CustomerRepository customerRepository;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        customerRepository = new CustomerRepository(provider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/customers/deleteCustomerForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckCustomers checkCustomers = new CheckCustomers();

        try {
            Integer customerId = Integer.parseInt(req.getParameter("customerId"));
            if (checkCustomers.IsCustomerIdExists(customerId)) {
                customerRepository.delete(customerId);
                req.getRequestDispatcher("/WEB-INF/view/customers/customerDeleted.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/view/customers/customerIdNotExists.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            req.getRequestDispatcher("/WEB-INF/view/customers/invalidCustomerIdFormat.jsp").forward(req, resp);
        }
    }
}
