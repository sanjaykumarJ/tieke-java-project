package controller;

import java.io.IOException;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ConnectorLinkedIn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "https://www.linkedin.com/login?trk=guest_homepage-basic_nav-header-signin";
		try {
			Response connect = Jsoup
					.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36")
					.method(Method.GET).execute();
			Document loginHtml = connect.parse();
			Element csrfValue=loginHtml.select("input[name=loginCsrfParam]")
                    .first();
			System.out.println(csrfValue.attr("value"));
			Response connection = Jsoup.connect("https://www.linkedin.com/checkpoint/lg/login-submit")
                    .cookies(connect.cookies())
                    .data("loginCsrfParam", csrfValue.attr("value"))
                    .data("session_key", "balasanjaykumar@gmail.com")
                    .data("session_password", "sanjjjawa@95")
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36")
                    .method(Method.POST)
                    .followRedirects(true)
                    .execute();
			Document document = connection.parse();

		    //            System.out.println(document)

		                System.out.println(document.html());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
