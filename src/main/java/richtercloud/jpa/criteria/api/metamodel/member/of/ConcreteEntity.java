/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package richtercloud.jpa.criteria.api.metamodel.member.of;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author richter
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ConcreteEntity extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    private String property1;

    public ConcreteEntity() {
    }

    public ConcreteEntity(String property1) {
        this.property1 = property1;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }
}
