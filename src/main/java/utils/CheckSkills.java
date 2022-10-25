package utils;

import config.HibernateProvider;
import services.SkillService;

import java.sql.SQLException;

public class CheckSkills {
    private final HibernateProvider provider = new HibernateProvider();
    SkillService skillService = new SkillService(provider);

    public boolean IsSkillIdExists(Integer id) throws SQLException {
        for (int i = 0; i < skillService.skillsList().size(); i++) {
            if(skillService.skillsList().get(i).getSkillId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
