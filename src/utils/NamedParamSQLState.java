package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamedParamSQLState implements AutoCloseable {
    private Map<String, List<Integer>> parameterNaming = new HashMap<>();
    private PreparedStatement st = null;

    public NamedParamSQLState(Connection con, String sql) throws SQLException {
        Pattern namedParaPattern = Pattern.compile(":([a-zA-Z0-9_]*)");
        Matcher match = namedParaPattern.matcher(sql);

        StringBuilder parsedSql = new StringBuilder();

        int lastPos = 0;
        int paramCount = 0;

        while (match.find()) {
            paramCount++;
            String key = match.group(1);

            List<Integer> positions = parameterNaming.getOrDefault(key, new ArrayList<>());
            positions.add(paramCount);
            parameterNaming.put(key, positions);

            parsedSql.append(sql.substring(lastPos, match.start()));
            parsedSql.append("?");

            lastPos = match.end();
        }

        parsedSql.append(sql.substring(lastPos));

        this.st = con.prepareStatement(parsedSql.toString());
    }

    public PreparedStatement getSt() {
        return st;
    }

    public void close() throws SQLException {
        this.st.close();
    }

    public void setString(String name, String value) throws SQLException {
        List<Integer> paramPos = parameterNaming.get(name);

        if (paramPos == null) {
            return;
        }

        for (int p : paramPos) {
            this.st.setString(p, value);
        }
    }

    public void setInt(String name, int value) throws SQLException {
        List<Integer> paramPos = parameterNaming.get(name);

        if (paramPos == null) {
            return;
        }

        for (int p : paramPos) {
            this.st.setInt(p, value);
        }
    }

    public ResultSet executeQuery() throws SQLException {
        return this.st.executeQuery();
    }

    public int executeUpdate() throws SQLException {
        return this.st.executeUpdate();
    }
}
