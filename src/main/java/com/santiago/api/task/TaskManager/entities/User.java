package com.santiago.api.task.TaskManager.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @ManyToMany
    @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","role_id"})})
    private List<Role> roles;
    private boolean isAdmin;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task>tasks;
    private boolean enabled;
    public User( String name, String email, String password,boolean isAdmin) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.tasks = new ArrayList<Task>();
        this.isAdmin = isAdmin;
    }

    public User() {
        this.tasks= new ArrayList<>();
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        task.setUser(this);


    }

    public List<Task> getTasks() {
        return
                tasks;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
