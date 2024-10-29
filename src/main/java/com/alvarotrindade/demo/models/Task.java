package com.alvarotrindade.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table( name = Task.TABLE_NAME)
public class Task {
    public static final String TABLE_NAME = "Task";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", unique = true )
    private long id;

    @ManyToOne
    @JoinColumn(name = "User_id", nullable = false, updatable = false)
    private User user;

     @Column (name = "description", length = 225, nullable = false)
     @NotNull
     @NotEmpty
     @Size(min = 1, max = 255)
    private String description;

    public Task(User user, long id, String description) {
        this.user = user;
        this.id = id;
        this.description = description;
    }

    public Task() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull @NotEmpty @Size(min = 1, max = 255) String getDescription() {
        return description;
    }

    public void setDescription(@NotNull @NotEmpty @Size(min = 1, max = 255) String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

