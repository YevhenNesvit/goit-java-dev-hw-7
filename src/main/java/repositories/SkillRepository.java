package repositories;

import config.HibernateProvider;
import converter.SkillConverter;
import model.dao.SkillDao;
import model.dto.SkillDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillRepository {
    SkillConverter skillConverter = new SkillConverter();
    private final HibernateProvider provider;

    public SkillRepository(HibernateProvider provider) {
        this.provider = provider;
    }

    public List<SkillDto> skillsList() throws SQLException {

        try (final Session session = provider.openSession()) {
            List<SkillDao> list = session.createQuery("FROM Skill ORDER BY skillId", SkillDao.class)
                    .list();

            return skillConverter.fromList(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public SkillDto skillById(Integer id) {

        try (final Session session = provider.openSession()) {
            SkillDao skill = session.createQuery("FROM Skill WHERE skillId = :id", SkillDao.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return skillConverter.from(skill);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new SkillDto();
    }

    public void updateSkill(Integer id, String name, String skillLevel) {
        SkillDao skill = new SkillDao();
        skill.setSkillId(id);
        skill.setName(name);
        skill.setSkillLevel(skillLevel);

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.merge(skill);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSkill(Integer id) {
        SkillDao skill = new SkillDao();
        skill.setSkillId(id);

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.remove(skill);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createSkill(Integer id, String name, String skillLevel) {
        SkillDao skill = new SkillDao();
        skill.setSkillId(id);
        skill.setName(name);
        skill.setSkillLevel(skillLevel);

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(skill);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
