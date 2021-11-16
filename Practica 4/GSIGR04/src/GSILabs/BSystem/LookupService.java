/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 16/17
 * ---
 * Nota: esta documento no contiene tildes ni caracteres castellanos.
 */
package GSILabs.BSystem;
import GSILabs.BModel.*;

/**
 * Interfaz de acceso inteligente a BSystem
 * @author Carlos Lopez (carlos.lopez@unavarra.es)
 * @version 1.0
 * @since 09.08.2016
 */
public interface LookupService {
    
    /**
     * Obtiene la valoración media de un local, o -1 si éste no existe.
     * @param l Local de interés.
     * @return La valoración media de las reviews asociadas, o 0 si no existen reviews.
     */
    public float obtenerValoracionMedia(Local l);
    
    /**
     * Obtiene la valoración media de un propietario, o -1 si éste no existe.
     * @param p El propietario a investigar
     * @return La valoración media de las reviews asociadas a sus locales, o 0 si no existen reviews.
     */
    public float obtenerValoracionMedia(Propietario p);
    
    /**
     * Obtiene la valoración media de un local realizadas por gente que,
     * en el momento de la valoración, estaba en un rango de edad determinado, 
     * o null si éste no existe, o -1 si éste no existe.
     * @param l Local de interés.
     * @param edadEntre Edad minima del rango (incluida)
     * @param edadHasta Edad maxima del rango (incluida)
     * @return 
     */
    public float obtenerValoracionMedia(Local l, int edadEntre, int edadHasta);
    
    
    /**
     * Obtiene los locales de una ciudad y provincia ordenados por su valoración media
     * en las reviews asociadas. Los locales que no tienen reviews serán valorados
     * con 0.
     * @param ciudad Ciudad de interés
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return Los locales ordenados por nota descendente
     */
    public Local[] obtenerLocalesOrdenados(String ciudad, String provincia);
    
    /**
     * Obtiene los locales de una provincia ordenados por su valoración media
     * en las reviews asociadas. Los locales que no tienen reviews serán valorados
     * con 0.
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return Los locales ordenados por nota descendente
     */
    public Local[] obtenerLocalesOrdenados(String provincia);
    
    /**
     * Obtiene los bares de una ciudad y provincia ordenados por su valoración media
     * en las reviews asociadas. Los locales que no tienen reviews serán valorados
     * con 0.
     * @param ciudad Ciudad de interés
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return Los bares ordenados por nota descendente
     */
    public Bar[] obtenerBaresOrdenados(String ciudad, String provincia);
    
    /**
     * Obtiene los restaurantes de una ciudad y provincia ordenados por su valoración media
     * en las reviews asociadas. Los locales que no tienen reviews serán valorados
     * con 0.
     * @param ciudad Ciudad de interés
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return Los restaurantes ordenados por nota descendente
     */
    public Restaurante[] obtenerRestaurantesOrdenados(String ciudad, String provincia);
    
    /**
     * Obtiene los pubs de una ciudad y provincia ordenados por su valoración media
     * en las reviews asociadas. Los locales que no tienen reviews serán valorados
     * con 0.
     * @param ciudad Ciudad de interés
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return Los pubs ordenados por nota descendente
     */
    public Pub[] obtenerPubOrdenados(String ciudad, String provincia);
    
}
