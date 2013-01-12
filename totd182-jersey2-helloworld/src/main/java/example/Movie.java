/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author arungup
 */
@XmlRootElement
public class Movie {
    private String name;
    private String star;

    public Movie() {
    }
    
    public Movie(String name, String star) {
        this.name = name;
        this.star = star;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
