package com.alvarotrindade.demo.services;


import com.alvarotrindade.demo.models.User;
import com.alvarotrindade.demo.repositories.TaskRepository;
import com.alvarotrindade.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@org.springframework.stereotype.Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;


    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
         return user.orElseThrow(() -> new RuntimeException("Usuário não encontrado! Id: " + id + ",Tipo: " + User.class.getName()));
    }


     @Transactional
    public User create(User obj) {
        obj.setId(null);
          obj = this.userRepository.save(obj);
          this.taskRepository.saveAll(obj.getTasks());
          return obj;
    }

     @Transactional
    public User update(User obj) {
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

     public void delete(Long id) {
        findById(id);
            try {
                this.userRepository.deleteById(id);
            } catch (Exception e) {
                throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
            }
     }



}




