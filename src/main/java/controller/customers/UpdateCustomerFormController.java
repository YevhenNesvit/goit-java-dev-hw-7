package controller.customers;

import config.HibernateProvider;
import model.dao.CustomerDao;
import repositories.CustomerRepository;
import utils.CheckCustomers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/updateCustomerForm")
public class UpdateCustomerFormController extends HttpServlet {
    CustomerRepository customerRepository;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        customerRepository = new CustomerRepository(provider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/customers/updateCustomerForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckCustomers checkCustomers = new CheckCustomers();

        try {
            Integer customerId = Integer.parseInt(req.getParameter("customerId"));
            CustomerDao customer = new CustomerDao();
            customer.setCustomerId(customerId);
            customer.setName(req.getParameter("customerName"));
            customer.setCountry(req.getParameter("country"));
            if (checkCustomers.IsCustomerIdExists(customerId)) {
                customerRepository.update(customer);
                req.getRequestDispatcher("/WEB-INF/view/customers/customerUpdated.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/view/customers/customerIdNotExists.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            req.getRequestDispatcher("/WEB-INF/view/customers/invalidCustomerIdFormat.jsp").forward(req, resp);
        }
    }
}
