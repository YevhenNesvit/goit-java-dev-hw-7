package controller.skills;

import config.HibernateProvider;
import model.dao.SkillDao;
import repositories.SkillRepository;
import utils.CheckSkills;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/createSkillForm")
public class CreateSkillFormController extends HttpServlet {
    SkillRepository skillRepository;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        skillRepository = new SkillRepository(provider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/skills/createSkillForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckSkills checkSkills = new CheckSkills();

        try {
            SkillDao skill = new SkillDao();
            skill.setSkillId(Integer.parseInt(req.getParameter("skillId")));
            skill.setName(req.getParameter("skillName"));
            skill.setSkillLevel(req.getParameter("skillLevel"));
            if (checkSkills.IsSkillIdExists(skill.getSkillId())) {
                req.getRequestDispatcher("/WEB-INF/view/skills/skillIdAlreadyExists.jsp").forward(req, resp);
            } else {
                skillRepository.create(skill);
                req.getRequestDispatcher("/WEB-INF/view/skills/skillCreated.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            req.getRequestDispatcher("/WEB-INF/view/skills/invalidSkillIdFormat.jsp").forward(req, resp);
        }
    }
}
