package utils;

import config.HibernateProvider;
import services.ProjectService;

import java.sql.SQLException;

public class CheckProjects {
    private final HibernateProvider provider = new HibernateProvider();
    ProjectService projectService = new ProjectService(provider);

    public boolean IsProjectIdExists(Integer id) throws SQLException {
        for (int i = 0; i < projectService.projectsList().size(); i++) {
            if(projectService.projectsList().get(i).getProjectId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
