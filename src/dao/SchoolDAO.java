package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;

public class SchoolDAO extends DAO {
    public School get(String cd) throws Exception {
        String sql = "select cd, name from school";
        School school = null;

        try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    school = new School();
                    school.setCd(cd);
                    school.setName(rs.getString("name"));
                }
            }
        }

        return school;
    }
}
