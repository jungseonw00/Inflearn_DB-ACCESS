package hello.jdbc.exception.basic;

import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

public class PracticeTest {

    static class Repository {
        public void runSQL() throws SQLException {
            throw new SQLException("SQLException");
        }

        public void connection() throws ConnectException {
            throw new ConnectException("ConnectException");
        }
    }

    static class Service {
        public void conAndRun() throws Exception {
            Repository repository = new Repository();
            repository.runSQL();
            repository.connection();
        }
    }

    static class Controller {
        public void request() throws Exception {
            Service service = new Service();
            service.conAndRun();
        }
    }

    @Test
    void main() {
        try {
            Controller controller = new Controller();
            controller.request();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
