package com.example.spring_boot.dao;

import com.example.spring_boot.model.Role;

import java.util.List;

public interface RoleDao {
    public Role getRoleById (Long id);
    public List<Role> listRoles();
}
