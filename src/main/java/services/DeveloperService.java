package services;

import config.HibernateProvider;
import converter.DeveloperConverter;
import model.dao.DeveloperDao;
import model.dto.DeveloperDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.*;

public class DeveloperService {
    DeveloperConverter developerConverter = new DeveloperConverter();
    private final HibernateProvider provider;

    public DeveloperService(HibernateProvider provider) {
        this.provider = provider;
    }

    public DeveloperDto salaryByProjectId(Integer id) {

        try (final Session session = provider.openSession()) {
            Long salary = session.createQuery("SELECT sum(salary) FROM Developer JOIN projects " +
                            "WHERE projectId = :id", Long.class)
                    .setParameter("id", id)
                    .getSingleResult();
            DeveloperDao developer = new DeveloperDao();
            developer.setSalary(salary.intValue());

            return developerConverter.from(developer);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new DeveloperDto();
    }

    public List<DeveloperDto> developersByProjectId(Integer id) {

        try (final Session session = provider.openSession()) {
            List<DeveloperDao> developer = session.createQuery("FROM Developer JOIN projects WHERE projectId = " +
                            ":id ORDER BY developerId", DeveloperDao.class)
                    .setParameter("id", id)
                    .list();

            return developerConverter.fromList(developer);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<DeveloperDto> developersBySkillName(String name) throws SQLException {

        try (final Session session = provider.openSession()) {
            List<DeveloperDao> developer = session.createQuery("FROM Developer JOIN skills WHERE name = " +
                            ":name ORDER BY developerId", DeveloperDao.class)
                    .setParameter("name", name)
                    .list();

            return developerConverter.fromList(developer);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<DeveloperDto> developersBySkillLevel(String level) throws SQLException {

        try (final Session session = provider.openSession()) {
            List<DeveloperDao> developer = session.createQuery("FROM Developer JOIN skills WHERE skillLevel = " +
                            ":level ORDER BY developerId", DeveloperDao.class)
                    .setParameter("level", level)
                    .list();

            return developerConverter.fromList(developer);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<DeveloperDto> developersList() throws SQLException {

        try (final Session session = provider.openSession()) {
            List<DeveloperDao> list = session.createQuery("FROM Developer ORDER BY developerId", DeveloperDao.class)
                    .list();

            return developerConverter.fromList(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public DeveloperDto developerById(Integer id) {

        try (final Session session = provider.openSession()) {
            DeveloperDao developer = session.createQuery("FROM Developer WHERE developerId = :id", DeveloperDao.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return developerConverter.from(developer);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new DeveloperDto();
    }

    public void updateDeveloper(Integer id, String firstName, String lastName, String gender, Integer age, Integer companyId,
                                Integer salary) {
        DeveloperDao developer = new DeveloperDao();
        developer.setDeveloperId(id);
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setGender(gender);
        developer.setAge(age);
        developer.setCompanyId(companyId);
        developer.setSalary(salary);

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.merge(developer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDeveloper(Integer id) {
        DeveloperDao developer = new DeveloperDao();
        developer.setDeveloperId(id);

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.remove(developer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createDeveloper(Integer id, String firstName, String lastName, String gender, Integer age,
                                Integer companyId, Integer salary) {
        DeveloperDao developer = new DeveloperDao();
        developer.setDeveloperId(id);
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setGender(gender);
        developer.setAge(age);
        developer.setCompanyId(companyId);
        developer.setSalary(salary);

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(developer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
