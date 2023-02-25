package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }

    @Test
    void dataSourceDriverManager() throws SQLException {
        // DriverManagerDataSource - 항상 새로운 커넥션을 획득
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(dataSource);
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        // 커넥션 풀링
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");
        //dataSource.setMinimumIdle(10);
        useDataSource(dataSource);
        // 커넥션 풀을 채울 때 별도의 Thread를 사용한다.
        // 커넥션 풀이 생성하는 초기 커넥션 개수와는 상관이 없다. 설정이 필요하다면 setMinimumIdle()을 사용.
        Thread.sleep(1000);
    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        /**
         *  DriverManager는 Connection을 획득할 때마다 DB정보를 매번 넘겨주어야 한다.
         *  DriverManagerDataSource에서 DB정보를 설정해줬기 때문에 getConnection()만 사용하면 됨.
         *  관심사의 분리를 생각하면 됨.
         */
//        Connection con1 = dataSource.getConnection();
//        Connection con2 = dataSource.getConnection();

        Connection[] connections = new Connection[10];
        for (int i = 0; i < 15; i++) {
            System.out.println("i = " + i);
            connections[i] = dataSource.getConnection();
        }
        // pool size를 넘었기에 block을 하고 몇 초 뒤에 timeout을 할 것인지 설정가능.
//        log.info("connection={}, class={}", con1, con1.getClass());
//        log.info("connection={}, class={}", con2, con2.getClass());
    }
}
