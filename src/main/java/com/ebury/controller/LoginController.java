package com.ebury.controller;

import com.ebury.dao.UsuarioRepository;
import com.ebury.dto.EmpresaDTO;
import com.ebury.dto.UsuarioDTO;
import com.ebury.entity.RolEntity;
import com.ebury.entity.UsuarioEntity;
import com.ebury.service.EmpresaService;
import com.ebury.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected EmpresaService empresaService;

    @GetMapping("/")
    public String doLogin(){
        return "entrada";
    }
    @PostMapping("/")
    public String getHome(Model model, @RequestParam("usuario") String user, @RequestParam("clave") String clave, HttpSession session){
        String urlTo = "redirect:/";
        UsuarioDTO usuario = this.usuarioService.autenticar(user, clave);
        if(usuario!=null){
            String rolname = usuario.getRolName();
            session.setAttribute("usuario", usuario);
            switch (rolname){
                case "Cliente": //Usuario es cliente
                    urlTo+="cliente/";
                    break;
                case "AutorizadoEmpresa": //Usuario es empresa
                    urlTo+="autorizadoHome/";
                    break;
                case "FundadorEmpresa": // Usuario es fundador
                    urlTo+="fundadorHome/";
                    break;
                case "Gestor": //Usuario es gestor
                    urlTo+="gestorHome/";
                    break;
                case "Asistente": //Usuario es asistente
                    urlTo+="asistente";
                    break;
                case "SocioEmpresa": //Usuario es socio
                    urlTo+="socioHome/";
                    break;
            }
        }else{
            System.out.println("Error");
        }
        return urlTo;
    }

    @GetMapping("/logout")
    public String doLogout (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
