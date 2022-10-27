package repositories;

import config.HibernateProvider;
import converter.CustomerConverter;
import model.dao.CustomerDao;
import model.dto.CustomerDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    CustomerConverter customerConverter = new CustomerConverter();
    private final HibernateProvider provider;

    public CustomerRepository(HibernateProvider provider) {
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

    public CustomerDto customerById(Integer id) {

        try (final Session session = provider.openSession()) {
            CustomerDao customer = session.createQuery("FROM Customer WHERE customerId = :id", CustomerDao.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return customerConverter.from(customer);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new CustomerDto();
    }

    public void updateCustomer(Integer id, String name, String country) {
        CustomerDao customer = new CustomerDao();
        customer.setCustomerId(id);
        customer.setName(name);
        customer.setCountry(country);

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.merge(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
