import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author dingchenchen
 * @since 2020-05-06
 */
public class Test {
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
