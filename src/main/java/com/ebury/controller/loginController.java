package com.ebury.controller;

import com.ebury.dao.EmpresaRepository;
import com.ebury.dao.UsuarioRepository;
import com.ebury.entity.EmpresaEntity;
import com.ebury.entity.RolEntity;
import com.ebury.entity.UsuarioEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class loginController {

    @Autowired
    protected UsuarioRepository userRepo;

    @Autowired
    protected EmpresaRepository empRepo;

    @GetMapping("/")
    public String doLogin(){
        return "entrada";
    }
    @PostMapping("/")
    public String getHome(Model model, @RequestParam("usuario") String user, @RequestParam("clave") String clave, HttpSession session){
        String urlTo = "redirect:/";
        UsuarioEntity usuario = this.userRepo.autenticar(user, clave);
        if(usuario!=null){
            RolEntity rol = usuario.getRolByRol();
            String rolname = rol.getNombre();
            session.setAttribute("usuario", usuario);
            switch (rolname){
                case "Cliente": //Usuario es cliente
                    urlTo+="cliente/";
                    break;
                case "AutorizadoEmpresa": //Usuario es empresa
                    urlTo+="empresaHome";
                    break;
                case "Gestor": //Usuario es gestor
                    urlTo+="gestorHome/";
                    break;
                case "Asistente": //Usuario es asistente
                    urlTo+="asistente";
                    break;
                case "SocioEmpresa": //Usuario es socio
                    urlTo="empresaHome";
                    break;
            }
        }else{
            System.out.println("Error");
        }
        return urlTo;
    }

    @PostMapping("/empresa")
    public String getEmpresa(Model model, @RequestParam("cif") Integer cif, @RequestParam("clave") String clave, HttpSession session){
        EmpresaEntity empresa = this.empRepo.findByCifAndContrasenya(cif,clave);
        if(empresa != null)
        {
            session.setAttribute("empresa",empresa);
            return ("redirect:/empresaHome/");
        }
        return "/";
    }

}
