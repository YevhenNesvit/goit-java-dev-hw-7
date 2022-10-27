package utils;

import config.HibernateProvider;
import repositories.SkillRepository;

import java.sql.SQLException;

public class CheckSkills {
    private final HibernateProvider provider = new HibernateProvider();
    SkillRepository skillRepository = new SkillRepository(provider);

    public boolean IsSkillIdExists(Integer id) throws SQLException {
        for (int i = 0; i < skillRepository.skillsList().size(); i++) {
            if(skillRepository.skillsList().get(i).getSkillId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
