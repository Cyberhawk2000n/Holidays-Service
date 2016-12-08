/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.happiness_service;

/**
 *
 * @author Cyberhawk
 */
public class Pack {
    public String name;
    public String[] names;

    public Pack() {
    }

    public Pack(String name, String[] names) {
        this.name = name;
        this.names = names;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }
    
}
