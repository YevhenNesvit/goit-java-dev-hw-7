package services;

import config.HibernateProvider;
import converter.CompanyConverter;
import model.dao.CompanyDao;
import model.dto.CompanyDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyService {
    CompanyConverter companyConverter = new CompanyConverter();
    private final HibernateProvider provider;

    public CompanyService(HibernateProvider provider) {
        this.provider = provider;
    }

    public List<CompanyDto> companiesList() throws SQLException {

        try (final Session session = provider.openSession()) {
            List<CompanyDao> list = session.createQuery("FROM CompanyDao ORDER BY companyId", CompanyDao.class)
                    .list();

            return companyConverter.fromList(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public CompanyDto companyById(Integer id) {

        try (final Session session = provider.openSession()) {
            CompanyDao company = session.createQuery("FROM CompanyDao WHERE companyId = :id", CompanyDao.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return companyConverter.from(company);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new CompanyDto();
    }

    public void updateCompany(String name, String country, Integer id) {
        CompanyDao company = new CompanyDao();
        company.setCompanyId(id);
        company.setName(name);
        company.setCountry(country);

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.update(company);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCompany(Integer id) {
        CompanyDao company = new CompanyDao();
        company.setCompanyId(id);

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.delete(company);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createCompany(Integer id, String name, String country) {
        CompanyDao company = new CompanyDao();
        company.setCompanyId(id);
        company.setName(name);
        company.setCountry(country);

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(company);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
