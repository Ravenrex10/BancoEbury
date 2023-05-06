package com.ebury.service;

import com.ebury.dao.CuentaRepository;
import com.ebury.dao.EstadoRepository;
import com.ebury.dao.RolRepository;
import com.ebury.dao.UsuarioRepository;
import com.ebury.dto.CuentaDTO;
import com.ebury.dto.TransferenciaDTO;
import com.ebury.dto.UsuarioDTO;
import com.ebury.entity.*;
import com.ebury.ui.FiltroTransferencias;
import com.ebury.ui.FiltroUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void desactivarCuentas(Integer usuario){
        UsuarioEntity usuarioEntity = usuarioRepository.getById(usuario);
        for(CuentaEntity cuenta : cuentaRepository.findAllByUsuarioByDuenyo(usuarioEntity)){
            cuenta.setEstadoCuentaByEstado(estadoRepository.getReferenceById(0));
            cuentaRepository.save(cuenta);
        }
    }

    public void desactivarCuenta(Integer cuenta){
        CuentaEntity cuentaEntity = cuentaRepository.getById(cuenta);
        cuentaEntity.setEstadoCuentaByEstado(estadoRepository.getReferenceById(0));
        cuentaRepository.save(cuentaEntity);
    }

    public List<CuentaDTO> getCuentasSospechosas(){
        List<CuentaEntity> cuentas = cuentaRepository.findAllByEstadoCuentaByEstado_Id(4);
        List<CuentaEntity> cuentasSospechosas = new ArrayList<CuentaEntity>();
        Boolean sospechosa;
        for(CuentaEntity cuenta : cuentaRepository.findAll()){
            sospechosa = false;
            for(TransferenciaEntity transferencia : cuenta.getTransferenciasById()){
                if(transferencia.getCuentaByCuentaDestino().getEstadoCuentaByEstado().getId()==4){
                    sospechosa = true;
                }
            }
            if(sospechosa && cuenta.getEstadoCuentaByEstado().getId()==1){
                cuentasSospechosas.add(cuenta);
            }
        }
        return cuentasSospechosas.stream().map(CuentaEntity::toDTO).collect(Collectors.toList());
    }

    public List<CuentaDTO> getCuentasBloqueadas(){
        List<CuentaEntity> cuentasBloqueadas = cuentaRepository.findAllByEstadoCuentaByEstado_Id(3);
        return cuentasBloqueadas.stream().map(CuentaEntity::toDTO).collect(Collectors.toList());
    }

    public void desbloquear(Integer idCuenta){
        CuentaEntity cuenta = cuentaRepository.getById(idCuenta);
        cuenta.setEstadoCuentaByEstado(estadoRepository.getReferenceById(1));
        cuentaRepository.save(cuenta);
    }
}
