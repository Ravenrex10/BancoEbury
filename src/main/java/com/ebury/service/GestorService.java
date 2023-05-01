package com.ebury.service;

import com.ebury.dao.CuentaRepository;
import com.ebury.dao.EstadoRepository;
import com.ebury.dao.RolRepository;
import com.ebury.dao.UsuarioRepository;
import com.ebury.dto.TransferenciaDTO;
import com.ebury.dto.UsuarioDTO;
import com.ebury.entity.CuentaEntity;
import com.ebury.entity.RolEntity;
import com.ebury.entity.UsuarioEntity;
import com.ebury.ui.FiltroTransferencias;
import com.ebury.ui.FiltroUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GestorService {

    @Autowired
    RolRepository rolRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    TransferenciaService transferenciaService;

    //Autor Lucas Colbert Eastgate
    public List<String> getRoles(){
        List<RolEntity> roles = rolRepository.findAll();
        List<String> rolesNombres = new ArrayList<>();
        for(RolEntity rol : roles){
            rolesNombres.add(rol.getNombre());
        }
        return rolesNombres;
    }

    //Lucas Colbert Eastgate
    public List<UsuarioDTO> filtrar(FiltroUsuarios filtroUsuario){
        List<UsuarioDTO> usuarios;
        if(filtroUsuario.getFiltro().equals("0")){
            usuarios = usuarioService.findAllClientes();
        }else{
            String filtro = "";
            switch (filtroUsuario.getFiltro()){
                case "Cliente Particular": filtro="Cliente";
                    break;

                case "Fundador de empresa": filtro="FundadorEmpresa";
                    break;

                case "Autorizado de empresa": filtro="AutorizadoEmpresa";
                    break;

                case "Socio de empresa": filtro="SocioEmpresa";
            }
            usuarios = usuarioService.findClientesFiltrados(filtro);
        }
        return usuarios;
    }

    //Lucas Colbert Eastgate
    public List<TransferenciaDTO> filtrarTransferencias(FiltroTransferencias filtroTransferencias, Integer usuario){
        List<TransferenciaDTO> transferencias;
        switch (filtroTransferencias.getFiltro()){
            case "Recibidos": transferencias = transferenciaService.findAllByUsuarioDestino(usuario);
            System.out.println(usuarioService.findUsuarioById(usuario).getPrimerNombre());
                break;

            case "Enviados": transferencias = transferenciaService.findAllByUsuarioOrigen(usuario);
            System.out.println("enviados");
                break;

            default: transferencias = transferenciaService.findAllTransferencias(usuario);
                break;
        }
        return transferencias;
    }

    public void desactivarCuenta(Integer usuario){
        UsuarioEntity usuarioEntity = usuarioRepository.getById(usuario);
        for(CuentaEntity cuenta : cuentaRepository.findAllByUsuarioByDuenyo(usuarioEntity)){
            cuenta.setEstadoCuentaByEstado(estadoRepository.getReferenceById(0));
            cuentaRepository.save(cuenta);
        }
    }
}
