/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Adam Škarda
 */
@Entity
@DiscriminatorValue(UserRoles.Values.REGULAR)
public class RegularUser extends User{

    private UserStatus status = UserStatus.ACTIVE;
    
    
    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
    
}
