package controller.developers;

import config.HibernateProvider;
import repositories.DeveloperRepository;
import utils.CheckDevelopers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteDeveloperForm")
public class DeleteDeveloperFormController extends HttpServlet {
    DeveloperRepository developerRepository;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        developerRepository = new DeveloperRepository(provider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/developers/deleteDeveloperForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckDevelopers checkDevelopers = new CheckDevelopers();

        try {
            Integer developerId = Integer.parseInt(req.getParameter("developerId"));
            if (checkDevelopers.IsDeveloperIdExists(developerId)) {
                developerRepository.delete(developerId);
                req.getRequestDispatcher("/WEB-INF/view/developers/developerDeleted.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/view/developers/developerIdNotExists.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            req.getRequestDispatcher("/WEB-INF/view/developers/invalidInputsFormat.jsp").forward(req, resp);
        }
    }
}
