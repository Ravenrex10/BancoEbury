package com.ebury.service;

import com.ebury.dao.*;
import com.ebury.dto.TransferenciaDTO;
import com.ebury.entity.*;
import com.ebury.exceptions.DivisaException;
import com.ebury.exceptions.NegativeImportException;
import com.ebury.exceptions.NoCoinsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {
    @Autowired
    protected TransferenciasRepository transferenciasRepository;
    @Autowired
    protected CuentaRepository cuentaRepository;
    @Autowired
    protected SaldoRepository saldoRepository;

    @Autowired
    protected DivisaRepository divisaRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    public List<TransferenciaDTO> findAllByUsuarioOrigen(Integer id){
        List<TransferenciaEntity> transferenciaEntities = transferenciasRepository.findAllByUsuarioOrigen(id);
        return transferenciaEntities.stream().map(TransferenciaEntity::toDTO).collect(Collectors.toList());
    }

    public List<TransferenciaDTO> findAllByUsuarioDestino(Integer id){
        List<TransferenciaEntity> transferenciaEntities = transferenciasRepository.findAllByUsuarioDestino(id);
        return transferenciaEntities.stream().map(TransferenciaEntity::toDTO).collect(Collectors.toList());
    }

    public List<TransferenciaDTO> findAllTransferencias(Integer usuario) {
        List<TransferenciaEntity> transferenciaEntities = transferenciasRepository.findAllByUsuario(usuario);
        return transferenciaEntities.stream().map(TransferenciaEntity::toDTO).collect(Collectors.toList());
    }

    public List<TransferenciaDTO> findAllTransferenciasOrdenadas(Integer usuario, String orden){
        List<TransferenciaEntity> transferenciaEntities;
        switch (orden){
            case "Fecha Ascendente": transferenciaEntities = transferenciasRepository.findAllByUsuarioOrderByFechaAscendente(usuario);
                break;

            case "Fecha Descendente": transferenciaEntities = transferenciasRepository.findAllByUsuarioOrderByFechaDescendente(usuario);
                break;

            case "Cantidad Ascendente": transferenciaEntities = transferenciasRepository.findAllByUsuarioOrderByCantidadAscendente(usuario);
                break;

            case "Cantidad Descendente": transferenciaEntities = transferenciasRepository.findAllByUsuarioOrderByCantidadDescendente(usuario);

            default: transferenciaEntities = transferenciasRepository.findAll();
        }

        return transferenciaEntities.stream().map(TransferenciaEntity::toDTO).collect(Collectors.toList());
    }

    /**
        Transfiere una cantidad de dinero desde una cuenta origen a una cuenta destino
        @author Diego
     */
    public void transferir(Integer origen, Integer destino,Double cantidad)
    {
        if(cantidad <= 0.0)
        {
            throw new NegativeImportException();
        }
        CuentaEntity cuentaOrigen = this.cuentaRepository.findById(origen).orElse(null);
        CuentaEntity cuentaDestino = this.cuentaRepository.findById(destino).orElse(null);

        SaldoEntity saldoOrigen = this.saldoRepository.findSaldoEntityByCuentaByCuenta_Id(cuentaOrigen.getId());
        SaldoEntity saldoDestino = this.saldoRepository.findSaldoEntityByCuentaByCuenta_Id(cuentaDestino.getId());

        DivisaEntity divisaOrigen = saldoOrigen.getDivisaByDivisa();
        DivisaEntity divisaDestino = saldoDestino.getDivisaByDivisa();

        if(!divisaOrigen.getNombre().equals(divisaDestino.getNombre()))
        {
            throw new DivisaException();
        }

        saldoOrigen.setCantidad(saldoOrigen.getCantidad() - cantidad);
        saldoDestino.setCantidad(saldoDestino.getCantidad() + cantidad);

        TransferenciaEntity newTransferencia = new TransferenciaEntity();
        newTransferencia.setCantidad(cantidad);
        newTransferencia.setFecha(new java.sql.Date(System.currentTimeMillis()));
        newTransferencia.setCuentaByCuentaOrigen(cuentaOrigen);
        newTransferencia.setCuentaByCuentaDestino(cuentaDestino);
        newTransferencia.setDivisaByDivisaOrigen(saldoOrigen.getDivisaByDivisa());
        newTransferencia.setDivisaByDivisaDestino(saldoDestino.getDivisaByDivisa());

        UsuarioEntity usuarioOrigen = this.usuarioRepository.findById(cuentaOrigen.getUsuarioByDuenyo().getId()).orElse(null);
        usuarioOrigen.setFechaUltimaOperacion(newTransferencia.getFecha());

        this.transferenciasRepository.save(newTransferencia);
        this.saldoRepository.save(saldoDestino);
        this.saldoRepository.save(saldoOrigen);
        this.cuentaRepository.save(cuentaOrigen);
        this.cuentaRepository.save(cuentaDestino);
        this.usuarioRepository.save(usuarioOrigen);

    }

    /**
     Transfiere una cantidad de dinero desde una cuenta origen a una cuenta null
     @author Juan Salmerón
     */
    public void sacarEfectivo(Integer origen, Double cantidad)
    {
        if(cantidad <= 0.0)
        {
            throw new NegativeImportException();
        } else if (cantidad%10 != 0){
            throw new NoCoinsException();
        }
        CuentaEntity cuentaOrigen = this.cuentaRepository.findById(origen).orElse(null);
        CuentaEntity cuentaDestino = null;

        SaldoEntity saldoOrigen = this.saldoRepository.findSaldoEntityByCuentaByCuenta_Id(cuentaOrigen.getId());

        DivisaEntity divisaOrigen = saldoOrigen.getDivisaByDivisa();

        saldoOrigen.setCantidad(saldoOrigen.getCantidad() - cantidad);

        TransferenciaEntity newTransferencia = new TransferenciaEntity();
        newTransferencia.setCantidad(cantidad);
        newTransferencia.setFecha(new java.sql.Date(System.currentTimeMillis()));
        newTransferencia.setCuentaByCuentaOrigen(cuentaOrigen);
        newTransferencia.setCuentaByCuentaDestino(cuentaDestino);
        newTransferencia.setDivisaByDivisaOrigen(divisaOrigen);
        newTransferencia.setDivisaByDivisaDestino(divisaOrigen);

        UsuarioEntity usuarioOrigen = this.usuarioRepository.findById(cuentaOrigen.getUsuarioByDuenyo().getId()).orElse(null);
        usuarioOrigen.setFechaUltimaOperacion(newTransferencia.getFecha());

        this.transferenciasRepository.save(newTransferencia);
        this.saldoRepository.save(saldoOrigen);
        this.cuentaRepository.save(cuentaOrigen);
        this.usuarioRepository.save(usuarioOrigen);

    }

    /**
        Devuelve todas las transferencias de una empresa en orden ascendente
        @author Diego
    */
    public List<TransferenciaDTO> findAllTransferenciasFromAEmpresa(Integer idEmpresa)
    {
        List<TransferenciaDTO> res = new ArrayList<>();
        List<TransferenciaEntity>  transferencias = this.transferenciasRepository.findAllByEmpresaId(idEmpresa);
        for(TransferenciaEntity t : transferencias)
        {
            res.add(t.toDTO());
        }
        return res;
    }

    public List<TransferenciaDTO> findAllTransferenciasFromAnUser(Integer idUser)
    {
        List<TransferenciaDTO> res = new ArrayList<>();
        //List<TransferenciaEntity>  transferencias = this.transferenciasRepository.findAllByEmpresaId(idEmpresa);
        List<TransferenciaEntity>  transferencias = this.transferenciasRepository.findAllByUsuario(idUser);
        for(TransferenciaEntity t : transferencias)
        {
            res.add(t.toDTO());
        }
        return res;
    }

    /**
        Devuelve todas las transferencias de una empresa en orden descendente
        @author Diego
     */
    public List<TransferenciaDTO> findAllTransferenciasFromAEmpresaOrderDesc(Integer idEmpresa) {
        List<TransferenciaDTO> res = new ArrayList<>();
        List<TransferenciaEntity>  transferencias = this.transferenciasRepository.findAllByEmpresaIdOrderByDesc(idEmpresa);
        for(TransferenciaEntity t : transferencias)
        {
            res.add(t.toDTO());
        }
        return res;
    }

     /**
        Devuelve todas las transferencias de una empresa cuya divisa y id de cuenta equivalen a los parámetros. Orden fecha descendente.
        @author Diego
     */

    public List<TransferenciaDTO> findAllTransferenciasFromAEmpresaByDivisaAndCuentaIdOrderDesc(Integer idUser, String divisa, Integer cuentaId) {
        List<TransferenciaDTO> res = new ArrayList<>();
        List<TransferenciaEntity>  transferencias = this.transferenciasRepository.findAllTransferenciasFromAEmpresaByDivisaAndUsuarioIdOrderDesc(idUser,divisa,cuentaId);
        for(TransferenciaEntity t : transferencias)
        {
            res.add(t.toDTO());
        }
        return res;
    }

    public List<TransferenciaDTO> findAllTransferenciasFromAnUserByDivisaAndCuentaIdOrderDesc(Integer idUser, String divisa, Integer cuentaId) {
        List<TransferenciaDTO> res = new ArrayList<>();
        List<TransferenciaEntity>  transferencias = this.transferenciasRepository.findAllTransferenciasFromAnUserByDivisaAndUsuarioIdOrderDesc(idUser,divisa,cuentaId);
        for(TransferenciaEntity t : transferencias)
        {
            res.add(t.toDTO());
        }
        return res;
    }
         /**
        Devuelve todas las transferencias de una empresa cuya divisa y id de cuenta equivalen a los parámetros. Orden fecha ascendente.
        @author Diego
     */

    public List<TransferenciaDTO> findAllTransferenciasFromAEmpresaByDivisaAndCuentaIdOrderAsc(Integer idEmpresa, String divisa, Integer cuentaId) {
        List<TransferenciaDTO> res = new ArrayList<>();
        List<TransferenciaEntity>  transferencias = this.transferenciasRepository.findAllTransferenciasFromAEmpresaByDivisaAndUsuarioIdOrderAsc(idEmpresa,divisa,cuentaId);
        for(TransferenciaEntity t : transferencias)
        {
            res.add(t.toDTO());
        }
        return res;
    }
    /**
   Devuelve todas las transferencias de una empresa cuya divisa equivalen a los parámetros. Orden fecha descendente.
   @author Diego
*/
    public List<TransferenciaDTO> findAllTransferenciasFromAEmpresaByDivisaOrderDesc(Integer idEmpresa, String divisa) {
        List<TransferenciaDTO> res = new ArrayList<>();
        List<TransferenciaEntity>  transferencias = this.transferenciasRepository.findAllTransferenciasFromAEmpresaByDivisaOrderDesc(idEmpresa,divisa);
        for(TransferenciaEntity t : transferencias)
        {
            res.add(t.toDTO());
        }
        return res;
    }

    public List<TransferenciaDTO> findAllTransferenciasFromAnUserByDivisaOrderDesc(Integer idUser, String divisa) {
        List<TransferenciaDTO> res = new ArrayList<>();
        List<TransferenciaEntity>  transferencias = this.transferenciasRepository.findAllTransferenciasFromAnUserByDivisaOrderDesc(idUser,divisa);
        for(TransferenciaEntity t : transferencias)
        {
            res.add(t.toDTO());
        }
        return res;
    }
    /**
   Devuelve todas las transferencias de una empresa cuya divisa equivalen a los parámetros. Orden fecha ascendente.
   @author Diego
*/
    public List<TransferenciaDTO> findAllTransferenciasFromAEmpresaByDivisaOrderAsc(Integer idEmpresa, String divisa) {
        List<TransferenciaDTO> res = new ArrayList<>();
        List<TransferenciaEntity>  transferencias = this.transferenciasRepository.findAllTransferenciasFromAEmpresaByDivisaOrderAsc(idEmpresa,divisa);
        for(TransferenciaEntity t : transferencias)
        {
            res.add(t.toDTO());
        }
        return res;
    }
    /**
   Devuelve todas las transferencias de una empresa cuyo id de cuenta equivalen a los parámetros. Orden fecha descendente.
   @author Diego
*/
    public List<TransferenciaDTO> findAllTransferenciasFromAEmpresaByCuentaIdOrderDesc(Integer idEmpresa, Integer cuentaId) {
        List<TransferenciaDTO> res = new ArrayList<>();
        List<TransferenciaEntity>  transferencias = this.transferenciasRepository.findAllTransferenciasFromAEmpresaByUsuarioIdOrderDesc(idEmpresa,cuentaId);
        for(TransferenciaEntity t : transferencias)
        {
            res.add(t.toDTO());
        }
        return res;
    }

    public List<TransferenciaDTO> findAllTransferenciasFromAnUserByCuentaIdOrderDesc(Integer idUser, Integer cuentaId) {
        List<TransferenciaDTO> res = new ArrayList<>();
        List<TransferenciaEntity>  transferencias = this.transferenciasRepository.findAllTransferenciasFromAnUserByUsuarioIdOrderDesc(idUser,cuentaId);
        for(TransferenciaEntity t : transferencias)
        {
            res.add(t.toDTO());
        }
        return res;
    }
    /**
   Devuelve todas las transferencias de una empresa cuyo id de cuenta equivalen a los parámetros. Orden fecha ascendente.
   @author Diego
*/
    public List<TransferenciaDTO> findAllTransferenciasFromAEmpresaByCuentaIdOrderAsc(Integer idEmpresa, Integer cuentaId) {
        List<TransferenciaDTO> res = new ArrayList<>();
        List<TransferenciaEntity>  transferencias = this.transferenciasRepository.findAllTransferenciasFromAEmpresaByUsuarioIdOrderAsc(idEmpresa,cuentaId);
        for(TransferenciaEntity t : transferencias)
        {
            res.add(t.toDTO());
        }
        return res;
    }

}
