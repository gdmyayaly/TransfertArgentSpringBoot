package com.neldale.SpringBoot.controller;

import com.neldale.SpringBoot.dao.UserDao;
import com.neldale.SpringBoot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SystemeController {
    @Autowired
    UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;
    SimpleDateFormat formater = null;

    Date aujourdhui = new Date();
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajoutsysteme")

    public User ajoutuser(User user){
        String a=formater.format(aujourdhui);
        System.out.println(a);
        String b=formater.format(aujourdhui);
        int  c = (int)(Math.random() * 1000);
        System.out.println(c);
        String d=a+b+c;
        System.out.println(d);
        String  username= ""+user.getNom().charAt(0)+user.getNom().charAt(1)+user.getPrenom().charAt(0)+user.getPrenom().charAt(1)+d;
        User verification = userDao.findByUsername(username);
        while (verification!=null) {
            username= ""+user.getNom().charAt(0)+user.getNom().charAt(1)+user.getPrenom().charAt(0)+user.getPrenom().charAt(1)+d;
             verification = userDao.findByUsername(username);
        }
        user.setStatut("ACTIF");
        user.setPassword(bcryptEncoder.encode("welcome"));
        user.setUsername(username);
        userDao.save(user);
        return user;
    }
}
