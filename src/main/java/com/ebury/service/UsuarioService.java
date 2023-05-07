package com.ebury.service;

import com.ebury.dao.*;
import com.ebury.dto.*;
import com.ebury.entity.*;
import com.ebury.ui.UsuarioWrapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    EstadoRepository estadoRepository;
    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    DivisaRepository divisaRepository;
    @Autowired
    SaldoRepository saldoRepository;
    @Autowired
    TransferenciasRepository transferenciasRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    CuentaService cuentaService;

    /**
     * @author Daniel
     */
    public List<UsuarioDTO> findUsuarios() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioEntity::toDTO).collect(Collectors.toList());
    }

    /**
     * @author Daniel
     */
    public UsuarioDTO findUsuarioById(int id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return null;
        } else {
            return usuario.toDTO();
        }
    }

    /***
     * @author Lucas Colbert Eastgate
     */
    public List<UsuarioDTO> findAllClientes() {
        List<UsuarioEntity> usuariosEntity = usuarioRepository.findAllClientes();
        return usuariosEntity.stream().map(UsuarioEntity::toDTO).collect(Collectors.toList());
    }

    /***
     * @author Lucas Colbert Eastgate
     */
    public List<UsuarioDTO> findClientesFiltrados(String filtroUsuario) {
        List<UsuarioEntity> usuariosEntity = usuarioRepository.findAllByRolByRolNombre(filtroUsuario);
        return usuariosEntity.stream().map(UsuarioEntity::toDTO).collect(Collectors.toList());
    }

    /***
     * @author Lucas Colbert Eastgate
     */
    public List<UsuarioDTO> findAllByAltaSolicitada() {
        List<UsuarioEntity> usuariosEntity = usuarioRepository.findAllByAltaSolicitada(true);
        return usuariosEntity.stream().map(UsuarioEntity::toDTO).collect(Collectors.toList());
    }

    /***
     * @author Lucas Colbert Eastgate
     */
    public void darDeAltaUsuario(Integer usuario) {
        UsuarioEntity usuarioEntity = (UsuarioEntity) usuarioRepository.findById(usuario).orElse(null);

        usuarioEntity.setAlta(true);
        usuarioEntity.setAltaSolicitada(false);

        CuentaEntity cuenta = new CuentaEntity();
        cuenta.setEstadoCuentaByEstado(estadoRepository.getReferenceById(1));
        cuenta.setUsuarioByDuenyo(usuarioEntity);
        String Iban = ("ES" + (Math.abs(new Random().nextInt())) + System.currentTimeMillis());
        Iban = Iban.substring(0, Math.min(Iban.length(), 23));
        cuenta.setIban(Iban);
        SaldoEntity saldo = new SaldoEntity();
        saldo.setCuentaByCuenta(cuenta);
        saldo.setCantidad(0.0);
        DivisaEntity divisa = (DivisaEntity) divisaRepository.findById(1).orElse(null);
        saldo.setDivisaByDivisa(divisa);

        usuarioRepository.save(usuarioEntity);
        cuentaRepository.save(cuenta);
        saldoRepository.save(saldo);
    }

    /***
     * @author Lucas Colbert Eastgate
     */
    public void denegarAltaUsuario(Integer usuario) {
        UsuarioEntity usuarioEntity = (UsuarioEntity) usuarioRepository.findById(usuario).orElse(null);

        usuarioEntity.setAltaSolicitada(false);

        usuarioRepository.save(usuarioEntity);
    }

    /***
     * @author Lucas Colbert Eastgate
     */
    public List<TransferenciaDTO> findAllTransferencias(Integer usuario) {
        List<TransferenciaEntity> transferenciaEntities = transferenciasRepository.findAllByUsuario(usuario);
        return transferenciaEntities.stream().map(TransferenciaEntity::toDTO).collect(Collectors.toList());
    }

    /**
     * @author Daniel
     */
    public List<UsuarioDTO> findUsuariosByRolNombre(String rolName) {
        return usuarioRepository.findAllByRolByRolNombre(rolName).stream().map(UsuarioEntity::toDTO).collect(Collectors.toList());
    }

    /**
     * @author Daniel
     */
    public UsuarioDTO autenticar(String nombreUsuario, String clave) {
        UsuarioEntity usuario = usuarioRepository.autenticar(nombreUsuario, clave);
        if (usuario == null) return null;
        return usuario.toDTO();
    }

    /**     Registra un socio nuevo en la empresa. Solo se puede llamar desde el rol FundadorEmpresa.
           @author Diego
       */
    public String makeRegister(UsuarioDTO u, int empresaId) {
        UsuarioEntity usuario = new UsuarioEntity();

        usuario.setPrimerNombre(u.getPrimerNombre());
        usuario.setSegundoNombre(u.getSegundoNombre());
        usuario.setPrimerApellido(u.getPrimerApellido());
        usuario.setSegundoApellido(u.getSegundoApellido());
        usuario.setContrasenya(u.getContrasenya());
        usuario.setEmail(u.getEmail());
        usuario.setNif(u.getNif());
        usuario.setFechaNacimiento(u.getFechaNacimiento());

        //EmpresaEntity empresa = this.empresaRepository.findById(empresaId).orElse(null);

        usuario.setRolByRol(this.rolRepository.findByNombre(u.getRolName()));
        //usuario.setEmpresaByEmpresa(empresa);
        usuario.setAlta(false);
        usuario.setAltaSolicitada(true);

        //List<UsuarioEntity> usuarioEntityList = (List<UsuarioEntity>) empresa.getUsuariosById();
        //usuarioEntityList.add(usuario);
        //empresa.setUsuariosById(usuarioEntityList);

        //this.empresaRepository.save(empresa);
        this.usuarioRepository.save(usuario);

        return ("redirect:/cliente/");

    }

    // @author Jaime
    public String makeEdit(UsuarioDTO u, HttpSession session) {
        UsuarioDTO userActual = (UsuarioDTO) session.getAttribute("usuario");

        UsuarioEntity usuario = this.usuarioRepository.findById(userActual.getId()).orElse(null);

        usuario.setPrimerNombre(u.getPrimerNombre());
        usuario.setSegundoNombre(u.getSegundoNombre());
        usuario.setPrimerApellido(u.getPrimerApellido());
        usuario.setSegundoApellido(u.getSegundoApellido());
        usuario.setContrasenya(u.getContrasenya());
        usuario.setEmail(u.getEmail());
        usuario.setNif(u.getNif());
        usuario.setFechaNacimiento(u.getFechaNacimiento());

        // Insertar
        this.usuarioRepository.save(usuario);

        return ("redirect:/logout");
    }

    /**     Devuelve al fundador y a todos los socios y autorizados de una empresa.
           @author Diego
           */
    public List<UsuarioDTO> findFundadorSociosAndAutorizadosByEmpresaId(int id) {
        List<UsuarioEntity> usuarios = (this.usuarioRepository.findAllFundadoresAndSociosAndAutorizadosByEmpresaByEmpresaId(id));
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();
        for (UsuarioEntity u : usuarios) {
            usuarioDTOS.add(u.toDTO());
        }
        return usuarioDTOS;
    }

    /**     Devuelve todos los socios y autorizados de una empresa.
           @author Diego
           */
    public List<UsuarioDTO> findSociosAndAutorizadosByEmpresaId(int id) {
        List<UsuarioEntity> usuarios = (this.usuarioRepository.findAllSociosAndAutorizadosByEmpresa(id));
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();
        for (UsuarioEntity u : usuarios) {
            usuarioDTOS.add(u.toDTO());
        }
        return usuarioDTOS;
    }

    /**
          Cambia el estado de todas las cuentas del usuario a bloqueada.
          @author Diego
     */
    public void bloquearUsuarioById(int id) {
        UsuarioEntity usuarioBloqueado = this.usuarioRepository.findById(id).orElse(null);

        List<CuentaEntity> cuentaEntities = this.cuentaRepository.findAllByUsuarioByDuenyo(usuarioBloqueado);

        EstadoCuentaEntity estadoBloqueado = this.estadoRepository.findById(2).orElse(null);

        List<CuentaEntity> cuentasBloqueadas = new ArrayList<>();

        for (CuentaEntity cuenta : cuentaEntities) {
            cuenta.setEstadoCuentaByEstado(estadoBloqueado);
            cuentasBloqueadas.add(cuenta);
            this.cuentaRepository.save(cuenta);
        }

        usuarioBloqueado.setCuentasById(cuentasBloqueadas);
        this.usuarioRepository.save(usuarioBloqueado);
    }

    /***
     * @author Lucas Colbert Eastgate
     */
    public List<UsuarioDTO> getUsuariosInactivos(){
        List<UsuarioEntity> inactivos = usuarioRepository.findAllUsuariosInactivos();
        List<UsuarioEntity> usuariosInactivosConCuentasActivadas = inactivos;
        boolean activo;

        for(int i = 0; i<inactivos.size(); i++){
            UsuarioEntity usuario = inactivos.get(i);
            activo = false;
            for(CuentaEntity cuenta : usuario.getCuentasById()){
                if(cuenta.getEstadoCuentaByEstado().getId() == 1){
                    activo = true;
                }
            }
            if(!activo){
                usuariosInactivosConCuentasActivadas.remove(i);
            }
        }
        return usuariosInactivosConCuentasActivadas.stream().map(UsuarioEntity::toDTO).collect(Collectors.toList());
    }

    public List<UsuarioDTO> findAllUsuariosQueParticipanEnChat() {
        return usuarioRepository.findAllUsuariosQueParticipanEnChat().stream().map(UsuarioEntity::toDTO).toList();
    }

    public void solicitarActivacion(Integer id) {
        cuentaService.solicitarDesbloqueoCuenta(id);
    }
}
