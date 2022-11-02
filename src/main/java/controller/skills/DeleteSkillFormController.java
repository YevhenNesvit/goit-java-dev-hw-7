package controller.skills;

import config.HibernateProvider;
import repositories.SkillRepository;
import utils.CheckSkills;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteSkillForm")
public class DeleteSkillFormController extends HttpServlet {
    SkillRepository skillRepository;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        skillRepository = new SkillRepository(provider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/skills/deleteSkillForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckSkills checkSkills = new CheckSkills();

        try {
            Integer skillId = Integer.parseInt(req.getParameter("skillId"));
            if (checkSkills.IsSkillIdExists(skillId)) {
                skillRepository.delete(skillId);
                req.getRequestDispatcher("/WEB-INF/view/skills/skillDeleted.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/view/skills/skillIdNotExists.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            req.getRequestDispatcher("/WEB-INF/view/skills/invalidSkillIdFormat.jsp").forward(req, resp);
        }
    }
}
