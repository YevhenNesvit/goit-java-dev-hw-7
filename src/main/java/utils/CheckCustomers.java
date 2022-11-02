package utils;

import config.HibernateProvider;
import repositories.CustomerRepository;

import java.sql.SQLException;

public class CheckCustomers {
    private final HibernateProvider provider = new HibernateProvider();
    CustomerRepository customerRepository = new CustomerRepository(provider);

    public boolean IsCustomerIdExists(Integer id) throws SQLException {
        for (int i = 0; i < customerRepository.findAll().size(); i++) {
            if(customerRepository.findAll().get(i).getCustomerId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
