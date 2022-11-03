package repositories;

import config.HibernateProvider;
import converter.ProjectConverter;
import model.dao.ProjectDao;
import model.dto.ProjectDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository implements Repository<ProjectDto, List<ProjectDto>, ProjectDao> {
    ProjectConverter projectConverter = new ProjectConverter();
    private final HibernateProvider provider;

    public ProjectRepository(HibernateProvider provider) {
        this.provider = provider;
    }

    @Override
    public List<ProjectDto> findAll() throws SQLException {

        try (final Session session = provider.openSession()) {
            List<ProjectDao> list = session.createQuery("FROM Project ORDER BY projectId", ProjectDao.class)
                    .list();

            return projectConverter.fromList(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public ProjectDto findById(Integer id) {

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

    @Override
    public void update(ProjectDao project) {

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.merge(project);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
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

    @Override
    public void create(ProjectDao project) {

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(project);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
