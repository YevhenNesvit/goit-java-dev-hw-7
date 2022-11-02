package controller.skills;

import config.HibernateProvider;
import model.dto.SkillDto;
import repositories.SkillRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/getSkills")
public class GetSkillsController extends HttpServlet {
    SkillRepository skillRepository;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        skillRepository = new SkillRepository(provider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<SkillDto> skills = skillRepository.findAll();
            req.setAttribute("skills", skills);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/view/skills/getSkills.jsp").forward(req, resp);
    }
}
