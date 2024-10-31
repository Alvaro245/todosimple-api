package com.alvarotrindade.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name =User.TABLE_NAME)

public class User {
     public interface CreateUser {}
    public interface UpdateUser {}

  public static final String TABLE_NAME = "user";


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;


    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull(groups = CreateUser.class)
    @NotEmpty (groups = CreateUser.class)
    @Size(groups = CreateUser.class,min = 2, max = 100)

    private String username;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @Column(name = "password",   length = 60, nullable = false)
   @NotNull ( groups = {CreateUser.class, UpdateUser.class})
   @NotEmpty (groups = {CreateUser.class, UpdateUser.class})
   @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60)
   private String password;

   @OneToMany(mappedBy = "user")
   private List<Task> tasks = new ArrayList<Task>();


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof User))
            return false;
        User other =(User) o;
        if (this.id == null)
            if (other.id != null)
                return false;
        else if (!this.id.equals(other.id))
            return false;
        return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username) && Objects.equals(this.password, other.password);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
           int result = 1;
           result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
           return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(groups = CreateUser.class) @NotEmpty(groups = CreateUser.class) @Size(groups = CreateUser.class, min = 2, max = 100) String getUsername() {
        return username;
    }

    public void setUsername(@NotNull(groups = CreateUser.class) @NotEmpty(groups = CreateUser.class) @Size(groups = CreateUser.class, min = 2, max = 100) String username) {
        this.username = username;
    }

    public @NotNull(groups = {CreateUser.class, UpdateUser.class}) @NotEmpty(groups = {CreateUser.class, UpdateUser.class}) @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(groups = {CreateUser.class, UpdateUser.class}) @NotEmpty(groups = {CreateUser.class, UpdateUser.class}) @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60) String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
