package services;

import config.HibernateProvider;
import converter.SkillConverter;
import model.dao.SkillDao;
import model.dto.SkillDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillService {
    private static final String SELECT_BY_ID = "SELECT skill_id, name, skill_level FROM skills WHERE skill_id = ?";
    private static final String UPDATE_SKILL = "UPDATE skills SET name = ?, skill_level = ? WHERE skill_id = ?";
    SkillConverter skillConverter = new SkillConverter();
    private final HibernateProvider provider;

    public SkillService(HibernateProvider provider) {
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

    public SkillDto skillById(Integer id) throws SQLException {
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
//        SkillDao skill = new SkillDao();
//        while (resultSet.next()) {
//            skill = new SkillDao(resultSet.getInt("skill_id"),
//                    resultSet.getString("name"), resultSet.getString("skill_level"));
//        }

        return null;//skillConverter.from(skill);
    }

    public void updateSkill(String name, String skillLevel, Integer id) {
//        try (Connection connection = connector.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(UPDATE_SKILL);
//            statement.setString(1, name);
//            statement.setString(2, skillLevel);
//            statement.setInt(3, id);
//
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
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
