package services;

import config.HibernateProvider;
import converter.CustomerConverter;
import model.dao.CustomerDao;
import model.dto.CustomerDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private static final String SELECT_BY_ID = "SELECT customer_id, name, country FROM customers WHERE customer_id = ?";
    private static final String UPDATE_CUSTOMER = "UPDATE customers SET name = ?, country = ? WHERE customer_id = ?";
    CustomerConverter customerConverter = new CustomerConverter();
    private final HibernateProvider provider;

    public CustomerService(HibernateProvider provider) {
        this.provider = provider;
    }

    public List<CustomerDto> customerList() throws SQLException {

        try (final Session session = provider.openSession()) {
            List<CustomerDao> list = session.createQuery("FROM Customer ORDER BY customerId", CustomerDao.class)
                    .list();

            return customerConverter.fromList(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public CustomerDto customerById(Integer id) throws SQLException {
//        ResultSet resultSet = null;
//        try (Connection connection = connector.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
//            statement.setInt(1, id);
//
//            resultSet = statement.executeQuery();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        CustomerDao customer = new CustomerDao();
//        while (resultSet.next()) {
//            customer = new CustomerDao(resultSet.getInt("customer_id"),
//                    resultSet.getString("name"), resultSet.getString("country"));
//        }
//
        return null;//customerConverter.from(customer);
    }

    public void updateCustomer(String name, String country, Integer id) {
//        try (Connection connection = connector.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER);
//            statement.setString(1, name);
//            statement.setString(2, country);
//            statement.setInt(3, id);
//
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void deleteCustomer(Integer id) {
        CustomerDao customer = new CustomerDao();
        customer.setCustomerId(id);

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.remove(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createCustomer(Integer id, String name, String country) {
        CustomerDao customer = new CustomerDao();
        customer.setCustomerId(id);
        customer.setName(name);
        customer.setCountry(country);

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
