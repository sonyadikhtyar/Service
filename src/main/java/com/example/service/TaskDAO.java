package com.example.service;

import java.util.List;

public interface TaskDAO {
    List<ProductsData> getAllTasks();

    ProductsData getTaskById(int id);

    void addTask(ProductsData task);

    void updateTask(ProductsData task);

    void deleteTask(int id);
}
