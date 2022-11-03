package controller.projects;

import config.HibernateProvider;
import repositories.ProjectRepository;
import utils.CheckProjects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteProjectForm")
public class DeleteProjectFormController extends HttpServlet {
    ProjectRepository projectRepository;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        projectRepository = new ProjectRepository(provider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/projects/deleteProjectForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckProjects checkProjects = new CheckProjects();

        try {
            Integer projectId = Integer.parseInt(req.getParameter("projectId"));
            if (checkProjects.IsProjectIdExists(projectId)) {
                projectRepository.delete(projectId);
                req.getRequestDispatcher("/WEB-INF/view/projects/projectDeleted.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/view/projects/projectIdNotExists.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            req.getRequestDispatcher("/WEB-INF/view/projects/invalidInputsFormat.jsp").forward(req, resp);
        }
    }
}
