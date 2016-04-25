package news.caughtup.rest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import news.caughtup.s3.S3Proxy;


public class TestS3Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        S3Proxy proxy = new S3Proxy("caughtup.news.profile.pictures/dimitris", "mypic.png");
        FileInputStream stream;
        try {
            stream = new FileInputStream("/home/ubuntu/mypic.png");
            if (proxy.deletePicture()) {
                proxy.uploadPicture(stream);
                InputStream stream2 = (InputStream) proxy.downloadPicture();
                Path outFile = Paths.get("/home/ubuntu/mypic2.png");
                Files.copy(stream2, outFile);
            } 
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}