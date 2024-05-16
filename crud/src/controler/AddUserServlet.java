package controler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.userdao;
import BEANS.user;

/**
 * Servlet implementation class AjouterUtilisateur
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	userdao dao = new userdao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterpersonne.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Get other form data
	    String name = request.getParameter("name");
	    String prenom = request.getParameter("prenom");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");

	    // Get the image part
	    Part filePart = request.getPart("image");

	    // Check if the file part is not null
	    if (filePart != null) {
	        // Extract file name from content-disposition header of part
	        String fileName = getFileName(filePart);

	        // Define the directory where you want to save the uploaded image
	        String uploadDir = getServletContext().getRealPath("/image");

	        // If the directory does not exist, create it
	        File uploadDirFile = new File(uploadDir);
	        if (!uploadDirFile.exists()) {
	            uploadDirFile.mkdirs();
	        }

	        // Save the file to the upload directory
	        String filePath = uploadDir + File.separator + fileName;
	        try (InputStream inputStream = filePart.getInputStream();
	             OutputStream outputStream = new FileOutputStream(filePath)) {
	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	        }

	        // Create user object with file path
	        String imagePath = "image/" + fileName;

	        // Create user object and add it to the database
	        user us = new user(name, prenom, email, password, imagePath);
	        dao.addUser(us);

	        // Redirect to display users page
	        response.sendRedirect(request.getContextPath() + "/DisplayUserServlet");
	    } else {
	        // Handle case where filePart is null
	        // For example, show an error message
	        response.getWriter().println("File part is null");
	    }
	}

	private String getFileName(Part part) {
	    String contentDispositionHeader = part.getHeader("content-disposition");
	    for (String content : contentDispositionHeader.split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}

}