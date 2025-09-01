package example.실습.ex1;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TaskDao {
    Connection conn;
    private TaskDao() { connect(); }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb2", "root", "1234");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void task1() {
        try {
            for (int i=1; i<=5; i++) {
                String sql = "update products set stock_quantity = stock_quantity - 5 where product_id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, i);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map<String, Object>> task2() {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            String sql = "select * from products";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("product_id", rs.getInt("product_id"));
                map.put("product_name", rs.getString("product_name"));
                map.put("stock_quantity", rs.getInt("stock_quantity"));
                list.add(map);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void task3() {
        try {
            String sql = "update products set stock_quantity = stock_quantity + 20 where stock_quantity <= 10";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
