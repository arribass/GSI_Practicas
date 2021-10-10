/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 16/17
 * ---
 * Nota: esta documento no contiene tildes ni caracteres castellanos.
 */
package GSILabs.BSystem;

import GSILabs.BModel.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Interfaz de acceso a BSystem
 * @author Carlos Lopez (carlos.lopez@unavarra.es)
 * @version 1.0
 * @since 09.08.2016
 */
public interface LeisureOffice {
    
    /** Usuarios **/
    
    /**
     * Da de alta un usuario, en caso de que su informacion no incumpla las 
     * normas referentes al nick o edad.
     * @param u El nuevo usuario
     * @return Cierto si el usuario pudo ser añadido.
     */
    public boolean nuevoUsuario(Usuario u);
    
    /**
     * Elimina al usuario que se pase como argument.
     * @param u El usuario
     * @return True si y solo si el usuario existia y pudo ser eliminado.
     */
    public boolean eliminaUsuario(Usuario u);
    
    /**
     * Reemplaza en el sistema al usuario viejo por el nuevo. Para que esto suceda debe
     * Cumplirse que el usuario viejo exista y que el nuevo no incumpla normas
     * relativas a las politicas de Usuarios (nick y/o edad)
     * @param u El usuario
     * @param nuevoU El nuevo usuario
     * @return True si el usuario se encontro y pudo ser modificado
     */
    public boolean modificaUsuario(Usuario u, Usuario nuevoU);
    
    /**
     * Comprueba si existe algun usuario con ese mismo nick
     * @param nick
     * @return True si existe un usuario con ese nick
     */
    public boolean existeNick(String nick);
    
    /**
     * Recupera el usuario asociado a un nick, en caso de que exista.
     * @param nick
     * @return El usuario con el nick. Debe devolver null si existeNick(nick) es falso.
     */
    public Usuario obtenerUsuario(String nick);
 
    
    
    
    /** Reviews **/
    
    /**
     * Incorpora una nueva review al sistema, en caso de que sus datos (Usuario, 
     *  Local) sean correctos y no haya otra introducida para la misma fecha.
     * @param r La review a introducir al sistema.
     * @return True si y solo si la operacion fue completada.
     */
    public boolean nuevaReview(Review r);
    
    /**
     * Elimina una review del sistema, siempre y cuando exista y no tenga una 
     * contestacion asociada.
     * @param r Review a eliminar
     * @return True si y solo si la operacion fue completada.
     */
    public boolean eliminaReview(Review r);
    
    /**
     * Comprueba si la visita de un usuario a un local en una fecha dada ha sido 
     * comentada. En caso deque alguno de los datos sea incorrecto, o inexistente, el 
     * resultado sera false.
     * @param u el usuario
     * @param l el local visitadl
     * @param ld la fecha de visita
     * @return True si y solo si la review existe.
     */
    public boolean existeRewiew(Usuario u, Local l, LocalDate ld);
            
    
    
    
    
    /** Contestaciones **/
    
    /**
     * Añade una contestacion a una review, en caso de que la review exista y no este 
     * ya comentada.
     * @param c Contestacion a añadir
     * @param r Review
     * @return True si y solo si la operacion fue completada y se pudo añadir la review.
     */
    public boolean nuevaContestacion(Contestacion c, Review r);
    
    /**
     * Consulta la existencia de una contestacion para una review. Devolvera
     * falso si la contestacion no existe, o si la Review no esta registrada en
     * el sistema.
     * @param r Review a añadir
     * @return True si y solo si la Review existe y tiene contestacion
     */
    public boolean tieneContestacion(Review r);
    
    /**
     * Recupera la contestacion para una review dada, si esta existe.
     * @param r Review a consultar
     * @return La contestacion, o null si esta, o la propia review, no existen
     */
    public Contestacion obtenerContestacion(Review r);
    
    /**
     * Elimina la contestacion pasada como argumento
     * @param c Contestacion a eliminar
     * @return True si y solo si la operacion fue completada.
     */
    public boolean eliminaContestacion(Contestacion c);        
    
    /**
     * Elimina la contestacion asociada a una review
     * @param r La review cuya contestacion hay que elimnar
     * @return True si y solo si la operacion fue completada.
     */
    public boolean eliminaContestacion(Review r);      
    
    
    /** Locales **/
    
    /**
     * Añade un local al sistema, siempre que no exista otro en la misma direccion.
     * @param l El nuevo local
     * @return True si y solo si la operacion fue completada.
     */
    public boolean nuevoLocal(Local l);
    
    /**
     * Elimina un local determinado, si este existe como tal en el sistema.
     * @param l EL local a eliminar
     * @return True si y solo si la operacion fue completada.
     */
    public boolean eliminarLocal(Local l);
    
    /**
     * Obtiene los datos del local instalado en una determinada direccion fisica
     * @param d Direccion del local.
     * @return El local almacenado en el sistema, o null si no existe.
     */
    public Local obtenerLocal(Direccion d);
    
    /**
     * Asocia un local a un propietario, en caso de que ambos existan y no se haya llegado
     * al limite de Propietarios por local
     * @param l Local existente en en sistema
     * @param p Propietario existente en el sistema
     * @return True si y solo si la operacion fue completada.
     */
    public boolean asociarLocal(Local l, Propietario p);
    
    /**
     * Desliga un local de un propietario, en caso de que ambos existan y estén
     * ya relacionados
     * @param l Local existente en en sistema
     * @param p Propietario existente en el sistema
     * @return True si y solo si la operacion fue completada.
     */
    public boolean desasociarLocal(Local l, Propietario p);
    
    /**
     * 
     * @param viejoL
     * @param nuevoL
     * @return 
     */
    public boolean actualizarLocal(Local viejoL, Local nuevoL);
    
    /**
     * Ver las review asociadas a un local
     * @param l Local existente en en sistema
     * @return Lista de reviews del sistema. En caso de que el Local no exista, sera
     *  el valor null.
     */
    public Review[] verReviews(Local l);
 
    
    
    
    /** Locales **/
    
    /**
     * Anota una nueva reserva para un cliente dado, en un local reservable
     * para una fecha y hora concreta. El cliente y el local deben existir,
     * y la fecha y hora debe ser futura. El cliente no debe tener otra reserva para el
     * mismo local en la misma fecha.
     * @param c Cliente que hace la reserva
     * @param r Local donde se efectua la reserva
     * @param ld Fecha de la reserva
     * @param lt Hora de la reserva
     * @return True si y solo si la operacion fue completada.
     */
    public boolean nuevaReserva(Cliente c, Reservable r, LocalDate ld, LocalTime lt);
    
    /**
     * Obtiene todas las reservas (futuras y pasadas) del cliente.
     * @param c El cliente a consultar
     * @return La lista de las reservas, o null si el cliente no existe.
     */
    public Reserva[] obtenerReservas(Cliente c);
    
    /**
     * Obtiene todas las reservas (futuras y pasadas) del local.
     * @param r El local a consultar
     * @return La lista de las reservas, o null si el local no existe.
     */
    public Reserva[] obtenerReservas(Reservable r);
    
    /**
     * Obtiene todas las reservas del dia usado como argumento
     * @param ld la fecha a consultar
     * @return La lista de las reservas.
     */
    public Reserva[] obtenerReservas(LocalDate ld);
    
    /**
     * Elimina una reserva del sistema, en caso de que esta exista
     * @param r La reserva a eliminar.
     * @return True si y solo si la operacion fue completada.
     */
    public boolean eliminarReserva(Reserva r);
 
    
    
    
    /** Locales **/
    
    /**
     * Lista los bares en una ciudad dada
     * @param ciudad Ciudad de interes
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return La lista de locales, potencialmente de longitud 0.
     */
    public Local[] listarLocales(String ciudad, String provincia);
    
    /**
     * Lista los bares en una ciudad dada
     * @param ciudad Ciudad de interes
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return La lista de bares, potencialmente de longitud 0.
     */
    public Bar[] listarBares(String ciudad, String provincia);
    
    
    /**
     * Lista los bares en una ciudad dada
     * @param ciudad Ciudad de interes
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return La lista de bares, potencialmente de longitud 0.
     */
    public Restaurante[] listarRestaurantes(String ciudad, String provincia);
    

    
    /**
     * Lista los bares en una ciudad dada
     * @param ciudad Ciudad de interes
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return La lista de bares, potencialmente de longitud 0.
     */
    public Pub[] listarPubs(String ciudad, String provincia);
    
}
