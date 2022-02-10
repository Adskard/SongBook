/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Adam Škarda
 */
@Entity
@DiscriminatorValue(UserRoles.Values.ADMIN)
@Table (name = "ADMINISTRATOR")
public class Admin extends User{
}
