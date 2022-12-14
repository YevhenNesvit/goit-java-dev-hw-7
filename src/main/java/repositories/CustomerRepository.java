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

public class CustomerRepository implements Repository<CustomerDto, List<CustomerDto>, CustomerDao> {
    CustomerConverter customerConverter = new CustomerConverter();
    private final HibernateProvider provider;

    public CustomerRepository(HibernateProvider provider) {
        this.provider = provider;
    }

    @Override
    public List<CustomerDto> findAll() throws SQLException {

        try (final Session session = provider.openSession()) {
            List<CustomerDao> list = session.createQuery("FROM Customer ORDER BY customerId", CustomerDao.class)
                    .list();

            return customerConverter.fromList(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public CustomerDto findById(Integer id) {

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

    @Override
    public void update(CustomerDao customer) {

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.merge(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
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

    @Override
    public void create(CustomerDao customer) {

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
