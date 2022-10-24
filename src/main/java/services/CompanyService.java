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
    private static final String DELETE_COMPANY = "DELETE FROM companies where company_id = ?";
    private static final String SELECT_BY_ID = "SELECT company_id, name, country FROM companies " +
            "WHERE company_id = ?";
    private static final String UPDATE_COMPANY = "UPDATE companies SET name = ?, country = ? WHERE company_id = ?";
    CompanyConverter companyConverter = new CompanyConverter();
    HibernateProvider provider;

    public CompanyService(HibernateProvider provider) {
        this.provider = provider;
    }

    public List<CompanyDto> companiesList() throws SQLException {

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            List<CompanyDao> list = session.createQuery("FROM CompanyDao cd ORDER BY cd.companyId", CompanyDao.class)
                    .list();
            return companyConverter.fromList(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public CompanyDto companyById(Integer id) throws SQLException {
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
//        CompanyDao company = new CompanyDao();
//        while (resultSet.next()) {
//            company = new CompanyDao(resultSet.getInt("company_id"),
//                    resultSet.getString("name"), resultSet.getString("country"));
//        }

        return null;//companyConverter.from(company);
    }

    public void updateCompany(String name, String country, Integer id) {
//        try (Connection connection = connector.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(UPDATE_COMPANY);
//            statement.setString(1, name);
//            statement.setString(2, country);
//            statement.setInt(3, id);
//
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void deleteCompany(Integer id) {

//        try (Connection connection = connector.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(DELETE_COMPANY);
//            statement.setInt(1, id);
//
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void createCompany(Integer companyId, String name, String country) {
        CompanyDao company = new CompanyDao();
        company.setCompanyId(companyId);
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
