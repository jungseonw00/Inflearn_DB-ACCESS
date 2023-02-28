package hello.jdbc.other;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.*;

public class ConnectionTest {

    private static final String URL = "https://www.google.com";
    private static final String GET = "GET";
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String DATA = "test data";

    @Test
    void connectionHttps() throws IOException {
        // 소켓 및 입출력 스트림 준비
        Socket socket = new Socket("www.google.com", 80);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream out = new PrintStream(socket.getOutputStream());

        // 요청라인
        out.println("GET / HTTP/1.1");

        // 헤더정보
        out.println("Host: www.google.com");
        out.println("User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0)"
            + " AppleWebKit/537.36 (KHTML, like Gecko)"
            + " Chrome/30.0.1599.101 Safari/537.36");
        // 공백라인
        out.println();

        // 응답 내용
        String line = null;
        while((line = in.readLine()) != null) {
            System.out.println(line);
        }

        in.close();
        out.close();
        socket.close();
    }
}
