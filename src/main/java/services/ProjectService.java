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

    public ProjectDto projectById(Integer id) {

        try (final Session session = provider.openSession()) {
            ProjectDao project = session.createQuery("FROM Project WHERE projectId = :id", ProjectDao.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return projectConverter.from(project);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ProjectDto();
    }

    public void updateProject(Integer id, String name, Integer customerId, Integer companyId, Integer cost, Date creationDate) {
        ProjectDao project = new ProjectDao();
        project.setProjectId(id);
        project.setName(name);
        project.setCustomerId(customerId);
        project.setCompanyId(companyId);
        project.setCost(cost);
        project.setCreationDate(creationDate);

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.merge(project);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
