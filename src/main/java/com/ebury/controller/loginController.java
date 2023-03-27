package com.ebury.controller;

import com.ebury.dao.UsuarioRepository;
import com.ebury.entity.UsuarioEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class loginController {

    @Autowired
    protected UsuarioRepository userRepo;

    @GetMapping("/")
    public String doLogin(){
        return "entrada";
    }
    @PostMapping("/")
    public String getHome(Model model, @RequestParam("usuario") String user, @RequestParam("clave") String clave, HttpSession session){
        String urlTo = "redirect:/";
        UsuarioEntity usuario = this.userRepo.autenticar(user, clave);
        if(usuario!=null){
            int rol = usuario.getRol();
            session.setAttribute("usuario", usuario);
            switch (rol){
                case 1: //Usuario es cliente
                    urlTo+="usuario/";
                    break;
                case 2: //Usuario es empresa
                    urlTo+="empresa/";
                    break;
                case 3: //Usuario es gestor
                    urlTo+="gestor/";
                    break;
                case 4: //Usuario es asistente
                    urlTo+="asistente/";
                    break;
                case 5: //Usuario es socio
                    urlTo="socio/";
                    break;
            }
        }else{
            System.out.println("Error");
        }
        return urlTo;
    }

}
