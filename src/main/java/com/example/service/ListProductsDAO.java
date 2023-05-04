package com.example.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListProductsDAO implements TaskDAO {
    private List<ProductsData> tasks;

    public void ListProductsDAO() {
        tasks = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/service";
        String user = "root";
        String password = "student";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String sql = "SELECT id, section, item, av, startp, finish, org, statusp FROM products";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String section = rs.getString("section");
                String item = rs.getString("item");
                int av = rs.getInt("av");
                String startp = rs.getString("startp");
                String finish = rs.getString("finish");
                String org = rs.getString("org");
                String statusp = rs.getString("statusp");

                ProductsData task = new ProductsData(id, section, item, av, startp, finish, org, statusp);
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing resources
            try { rs.close(); } catch (Exception e) {}
            try { stmt.close(); } catch (Exception e) {}
            try { conn.close(); } catch (Exception e) {}
        }
    }

    public List<ProductsData> getAllTasks() {
        return tasks;
    }

    public ProductsData getTaskById(int id) {
        for (ProductsData task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public void addTask(ProductsData task) {
        tasks.add(task);
    }

    public void updateTask(ProductsData task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == task.getId()) {
                tasks.set(i, task);
                return;
            }
        }
    }

    public void deleteTask(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(i);
                return;
            }
        }
    }
}
