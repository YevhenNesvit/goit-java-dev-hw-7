package repositories;

import config.HibernateProvider;
import converter.CompanyConverter;
import model.dao.CompanyDao;
import model.dto.CompanyDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository implements Repository<CompanyDto, List<CompanyDto>, CompanyDao> {
    CompanyConverter companyConverter = new CompanyConverter();
    private final HibernateProvider provider;

    public CompanyRepository(HibernateProvider provider) {
        this.provider = provider;
    }

    @Override
    public List<CompanyDto> findAll() throws SQLException {

        try (final Session session = provider.openSession()) {
            List<CompanyDao> list = session.createQuery("FROM Company ORDER BY companyId", CompanyDao.class)
                    .list();

            return companyConverter.fromList(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public CompanyDto findById(Integer id) {

        try (final Session session = provider.openSession()) {
            CompanyDao company = session.createQuery("FROM Company WHERE companyId = :id", CompanyDao.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return companyConverter.from(company);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new CompanyDto();
    }

    public void updateCompany(Integer id, String name, String country) {
        CompanyDao company = new CompanyDao();
        company.setCompanyId(id);
        company.setName(name);
        company.setCountry(country);

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.merge(company);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        CompanyDao company = new CompanyDao();
        company.setCompanyId(id);

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.remove(company);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(CompanyDao company) {

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(company);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
