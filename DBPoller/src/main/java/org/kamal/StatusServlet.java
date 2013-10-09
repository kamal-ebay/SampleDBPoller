package org.kamal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by kmuralidharan on 9/25/13.
 */
public class StatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body><table border='1'>");

        for(Map.Entry<String, List<JobResult>> entry : DataStore.getMapInstance().entrySet()) {
            out.println("<tr><td>" + entry.getKey().toString() + "</td><td>" +entry.getValue().get(0).getStatus() + "</td></tr>");
        }

        out.println("</table></body></html>");
    }
}
