package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    static final Map<String, String> users = new HashMap<>();
    static final Map<String, String> roles = new HashMap<>(); // Дополнительная карта для хранения ролей пользователей

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("new-username");
        String password = req.getParameter("new-password");
        String role = req.getParameter("new-role");

        if (username != null && password != null && role != null && !users.containsKey(username)) {
            users.put(username, password);
            roles.put(username, role); // Сохраняем роль пользователя
            resp.getWriter().write("Registration successful! Welcome, " + username + "!");
            resp.sendRedirect("/login");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid username or password, or user already exists.");
        }
    }
}
