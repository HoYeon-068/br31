package mvc.command.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class StoreAddressProxyHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setContentType("application/json; charset=UTF-8");

        String sido = request.getParameter("sido");
        if (sido == null) sido = "";

        String target =
            "https://www.baskinrobbins.co.kr/api/store-address.php?sido="
            + URLEncoder.encode(sido, "UTF-8");

        HttpURLConnection conn =
            (HttpURLConnection) new URL(target).openConnection();

        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        try (BufferedReader br =
                 new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {

            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            response.getWriter().write(sb.toString());
        }

        return null; // JSON 직접 응답
    }
}
