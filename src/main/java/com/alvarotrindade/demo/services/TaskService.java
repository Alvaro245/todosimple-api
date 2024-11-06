package com.alvarotrindade.demo.services;

import com.alvarotrindade.demo.models.Task;
import com.alvarotrindade.demo.models.User;
import com.alvarotrindade.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

     @Autowired
    private TaskRepository taskRepository;


      @Autowired
      private UserService userService;


      public Task findById (Long id) {
          Optional<Task> task = this.taskRepository.findById(id);
             return task.orElseThrow(() -> new RuntimeException("Tarefa não encontrada! Id: " + id + "Tipo" + User.class.getName()));

      }
        @Transactional
     public Task create (Task obj) {
          User user = this.userService.findById(obj.getUser().getId());
          obj.setId(null);
          obj.setUser(user);
          obj = this.taskRepository.save(obj);
           return obj;
     }

     public List<Task> findAllByUserId(Long userId) {
          List<Task> tasks = this.taskRepository.findByUser_Id(userId);
          return tasks;

    }





     @Transactional
      public Task update(Task obj) {
         Task newObj = findById(obj.getId());
         newObj.setDescription(obj.getDescription());
         return this.taskRepository.save(newObj);
     }


public void delete (Long id) {
   findById(id);
   try {
     this.taskRepository.deleteById(id);
   } catch (RuntimeException e) {
       throw new RuntimeException("Não é possivel excluir pois há entidades relacionadas");
   }

   }

}






