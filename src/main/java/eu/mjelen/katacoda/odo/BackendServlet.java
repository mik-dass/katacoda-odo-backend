package eu.mjelen.katacoda.odo;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BackendServlet extends HttpServlet {
 
	@Override
	protected void doGet(HttpServletRequest reqest, HttpServletResponse response) 
			throws ServletException, IOException {
      checkFile();

      BufferedReader reader = new BufferedReader(new FileReader(fileName()));
      String data = reader.readLine();
      reader.close();

      Integer counter = Integer.valueOf(data);

      counter += 1;

      BufferedWriter writer = new BufferedWriter(new FileWriter(fileName()));
      writer.write(counter.toString());
      writer.close();
  
      response.getWriter().println(counter.toString());
	}
  
  public String fileName() throws IOException {
    File path = new File("/data");
    if(path.exists()) {
      return "/data/counter.txt";
    } else {
      return "counter.txt";
    }
  }

	public void checkFile() throws IOException {
    File file = new File(fileName());
    if(!file.exists()) {
      file.createNewFile();
      BufferedWriter writer = new BufferedWriter(new FileWriter(file));
      writer.write("0");
      writer.close();
    }
	}
}
