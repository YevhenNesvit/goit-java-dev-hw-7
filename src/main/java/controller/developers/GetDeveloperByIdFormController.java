package controller.developers;

import config.HibernateProvider;
import model.dto.DeveloperDto;
import repositories.DeveloperRepository;
import utils.CheckDevelopers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/getDeveloperByIdForm")
public class GetDeveloperByIdFormController extends HttpServlet {
    DeveloperRepository developerRepository;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        developerRepository = new DeveloperRepository(provider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/view/developers/getDeveloperByIdForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckDevelopers checkDevelopers = new CheckDevelopers();

        try {
            List<DeveloperDto> developers = new ArrayList<>();
            Integer developerId = Integer.parseInt(req.getParameter("developerId"));
            if (checkDevelopers.IsDeveloperIdExists(developerId)) {
                developers.add(developerRepository.findById(developerId));
                req.setAttribute("developers", developers);
                req.getRequestDispatcher("/WEB-INF/view/developers/developerById.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/view/developers/developerIdNotExists.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            req.getRequestDispatcher("/WEB-INF/view/developers/invalidInputsFormat.jsp").forward(req, resp);
        }
    }
}
