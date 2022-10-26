package controller.developers;

import config.HibernateProvider;
import model.dto.DeveloperDto;
import services.DeveloperService;
import utils.CheckProjects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/getSalaryByProjectForm")
public class GetSalaryByProjectFormController extends HttpServlet {
    DeveloperService developerService;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        developerService = new DeveloperService(provider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/view/developers/getSalaryByProjectForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckProjects checkProjects = new CheckProjects();

        try {
            Integer projectId = Integer.parseInt(req.getParameter("projectId"));
            List<DeveloperDto> developers = new ArrayList<>();
            if (checkProjects.IsProjectIdExists(projectId)) {
                developers.add(developerService.salaryByProjectId(projectId));
                req.setAttribute("developers", developers);
                req.getRequestDispatcher("/WEB-INF/view/developers/salaryByProject.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/view/projects/projectIdNotExists.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            req.getRequestDispatcher("/WEB-INF/view/projects/invalidInputsFormat.jsp").forward(req, resp);
        }
    }
}
