package com.example.service;

import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service implements TaskDAO {
    private Connection conn;

    public Service() {

        String url = "jdbc:mysql://localhost:3306/service";
        String databaseUser = "root";
        String databasePassword = "student";

        try {

            conn = DriverManager.getConnection(url, databaseUser, databasePassword);

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }
    @Override
    public List<ProductsData> getAllTasks() {

        List<ProductsData> tasks = new ArrayList<>();

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM products");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ProductsData task = new ProductsData(rs.getInt("id"),
                rs.getString("section"),
                rs.getString("item"),
                rs.getInt("av"),
                rs.getString("startp"),
                rs.getString("finish"), rs.getString("org"),
                rs.getString("statusp"));

                tasks.add(task);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return tasks;
    }

    @Override
    public ProductsData getTaskById(int id) {

        ProductsData task = null;

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                task = new ProductsData(rs.getInt("id"),
                rs.getString("section"),
                rs.getString("item"),
                rs.getInt("av"),
                rs.getString("startp"),
                rs.getString("finish"), rs.getString("org"),
                rs.getString("statusp"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public void addTask(ProductsData task) {
        try {

            PreparedStatement ps = conn.prepareStatement("INSERT INTO products (id, section, item, av, startp, finish, org, statusp) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setInt(1, task.getId());
            ps.setString(2, task.getSection());
            ps.setString(3, task.getItem());
            ps.setInt(4, task.getAv());
            ps.setString(5, task.getStartp());
            ps.setString(6, task.getFinish());
            ps.setString(7, task.getOrg());
            ps.setString(8, task.getStatusp());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTask(ProductsData task) {

        try {

            PreparedStatement ps = conn.prepareStatement("UPDATE products SET id = ?, section = ?, item = ?, av = ?, startp = ?, finish = ?, org = ?, statusp = ? WHERE id = ?");

            ps.setInt(1, task.getId());
            ps.setString(2, task.getSection());
            ps.setString(3, task.getItem());
            ps.setInt(4, task.getAv());
            ps.setString(5, task.getStartp());
            ps.setString(6, task.getFinish());
            ps.setString(7, task.getOrg());
            ps.setString(8, task.getStatusp());
            ps.setInt(9, task.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM products WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
