package news.caughtup.rest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import news.caughtup.database.NewsSourceDBAdapter;
import news.caughtup.model.NewsSource;
import news.caughtup.model.NewsSourceList;
import news.caughtup.s3.S3Proxy;
import news.caughtup.util.RSSReader;
import news.caughtup.util.TestFeed;


public class TestS3Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*S3Proxy proxy = new S3Proxy("caughtup.news.profile.pictures/dimitris", "mypic.png");
        FileInputStream stream;
        String pictureURL = null;
        try {
            stream = new FileInputStream("/home/ubuntu/mypic.png");
            if (proxy.deletePicture()) {
                pictureURL = proxy.uploadPicture(stream);
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
        PrintWriter out = resp.getWriter();
        out.println(pictureURL);
        */
        /* Insert test news sources */
        try {
            NewsSourceDBAdapter.saveNewsSource(new NewsSource(-1, "cnn", "http://www.cnn.com", "CNN news", 
                    "http://rss.cnn.com/rss/cnn_topstories.rss", new Date(1)));
            NewsSourceDBAdapter.saveNewsSource(new NewsSource(-1, "bbc", "http://www.bbc.com", "BBC news", 
                    "http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml?edition=int", new Date(1)));
        } catch (SQLException e) {
           System.err.println("Failed while trying to insert a news source");
           System.err.println(e);
        }
        
        /* Just for testing the RSS feed */
        NewsSourceList newsSourceList = NewsSourceList.getNewsSourceList();
        try {
            newsSourceList.setNewsSourcesMap(NewsSourceDBAdapter.getNewsSources());
        } catch (SQLException e) {
            System.err.println("Failed to get available news sources");
            System.err.println(e);
        }
        TimerTask rss = new RSSReader(newsSourceList);
        Timer timer = new Timer(false);
        timer.scheduleAtFixedRate(rss, 0, 5*1000);
        
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        timer.cancel();
    }
}