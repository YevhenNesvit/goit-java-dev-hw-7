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

public class SkillRepository implements Repository<SkillDto, List<SkillDto>, SkillDao> {
    SkillConverter skillConverter = new SkillConverter();
    private final HibernateProvider provider;

    public SkillRepository(HibernateProvider provider) {
        this.provider = provider;
    }

    @Override
    public List<SkillDto> findAll() throws SQLException {

        try (final Session session = provider.openSession()) {
            List<SkillDao> list = session.createQuery("FROM Skill ORDER BY skillId", SkillDao.class)
                    .list();

            return skillConverter.fromList(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public SkillDto findById(Integer id) {

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

    @Override
    public void update(SkillDao skill) {

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.merge(skill);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
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

    @Override
    public void create(SkillDao skill) {

        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(skill);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
