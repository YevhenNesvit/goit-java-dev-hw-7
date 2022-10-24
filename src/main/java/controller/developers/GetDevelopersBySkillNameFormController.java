package controller.developers;

import model.dto.DeveloperDto;
import services.DeveloperService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/getDevelopersBySkillNameForm")
public class GetDevelopersBySkillNameFormController extends HttpServlet {
//    DeveloperService developerService;
//
//    @Override
//    public void init() {
//        ServiceConnection connection = new ServiceConnection();
//        developerService = new DeveloperService(connection.connect());
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        req.getRequestDispatcher("/WEB-INF/view/developers/getDevelopersBySkillNameForm.jsp").forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        try {
//            String skillName = req.getParameter("skillName");
//            List<DeveloperDto> developers = developerService.developersBySkillName(skillName);
//            req.setAttribute("developers", developers);
//            req.getRequestDispatcher("/WEB-INF/view/developers/developersBySkillLevel.jsp").forward(req, resp);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
