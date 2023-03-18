package connection;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionPool {

    private String _url;
    private String _user;
    private String _password;
    private ArrayList<Connection> _conns = new ArrayList<Connection>();

    public ConnectionPool(String url, String user, String password) {
        this._url = url;
        this._user = user;
        this._password = password;
    }

    public ConnectionPool() {
        this(Environment.URL, Environment.USER, Environment.PASS);
    }

    public Connection getConnection() throws SQLException {
        Connection _conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            _conn = DriverManager.getConnection(this._url, this._user, this._password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this._conns.add(_conn);
        return _conn;
    }

    public void closeAll() throws SQLException {
        for (Connection conn : this._conns) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }

}
