package services;

import config.HibernateProvider;
import converter.ProjectConverter;
import model.dao.ProjectDao;
import model.dto.ProjectDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class ProjectService {
    private static final String SELECT_BY_ID = "SELECT project_id, name, customer_id, company_id, cost, creation_date " +
            "FROM projects WHERE project_id = ?";
    private static final String UPDATE_PROJECT = "UPDATE projects SET name = ?, customer_id = ?, company_id = ?, cost = ?," +
            "creation_date = ? WHERE project_id = ?";
    ProjectConverter projectConverter = new ProjectConverter();
    private final HibernateProvider provider;

    public ProjectService(HibernateProvider provider) {
        this.provider = provider;
    }

    public List<ProjectDto> projectsList() throws SQLException {

        try (final Session session = provider.openSession()) {
            List<ProjectDao> list = session.createQuery("FROM Project ORDER BY projectId", ProjectDao.class)
                    .list();

            return projectConverter.fromList(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
//
//    public ProjectDto projectById(Integer id) throws SQLException {
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
//        ProjectDao project = new ProjectDao();
//        while (resultSet.next()) {
//            project = new ProjectDao(resultSet.getInt("project_id"), resultSet.getString("name"),
//                    resultSet.getInt("customer_id"), resultSet.getInt("company_id"),
//                    resultSet.getInt("cost"), resultSet.getDate("creation_date"));
//        }
//
//        return projectConverter.from(project);
//    }
//
//    public void updateProject(Integer projectId, String name, Integer customerId, Integer companyId, Integer cost, Date creationDate) {
//        try (Connection connection = connector.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(UPDATE_PROJECT);
//            statement.setString(1, name);
//            statement.setInt(2, customerId);
//            statement.setInt(3, companyId);
//            statement.setInt(4, cost);
//            statement.setDate(5, creationDate);
//            statement.setInt(6, projectId);
//
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void deleteProject(Integer id) {

        ProjectDao project = new ProjectDao();
        project.setProjectId(id);

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.remove(project);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createProject(Integer id, String name, Integer customerId, Integer companyId, Integer cost,
                                Date creationDate) {

        ProjectDao project = new ProjectDao();
        project.setProjectId(id);
        project.setName(name);
        project.setCustomerId(customerId);
        project.setCompanyId(companyId);
        project.setCost(cost);
        project.setCreationDate(creationDate);

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(project);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
