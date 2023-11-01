/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dominhson.dao;

import java.util.List;

/**
 *
 * @author 24dom
 */
public abstract class EduSysDAO<EntityType, KeyType> {

    public abstract void add(EntityType entity);

    public abstract void update(EntityType entity, KeyType id);

    public abstract void delete(KeyType id);

    public abstract List<EntityType> getAll();

    public abstract EntityType getByID(KeyType id);

    public abstract List<EntityType> getBySql(String sql, Object... args);

    public abstract void updateMK(EntityType entity, KeyType id);

    public abstract EntityType getByMa(KeyType id);
}
