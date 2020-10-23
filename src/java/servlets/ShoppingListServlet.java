package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 727045
 */
public class ShoppingListServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        if (action != null)
        {
            if (action.equals("logout"))
            {
                session.invalidate();
                session = request.getSession();
            }
        }

        String username = (String) session.getAttribute("user");

        if (username != null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        } else
        {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        ArrayList<String> shoppingList = (ArrayList) session.getAttribute("shoppingList");

        if (shoppingList == null)
        {
            shoppingList = new ArrayList<>();
        }

        if (action.equals("register"))
        {
            String username = request.getParameter("user");
            session.setAttribute("user", username);
        }
        if (action.equals("add"))
        {
            String item = request.getParameter("item");
            if (!item.equals("") || item != null)
            {
                shoppingList.add(item);
                request.setAttribute("message", "added " + item);
            }
            
        }
        
        if (action.equals("delete"))
        {
            String deleteItem = request.getParameter("items");
            if (deleteItem == null)
            {
                request.setAttribute("message", "no Item chosen");
            } else if (!deleteItem.equals("") || deleteItem != null)
            {
                shoppingList.remove(deleteItem);
                request.setAttribute("message", "deleted " + deleteItem);
            }
        }
        session.setAttribute("shoppingList", shoppingList);
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
    }

}
