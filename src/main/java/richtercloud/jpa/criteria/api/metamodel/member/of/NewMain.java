/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package richtercloud.jpa.criteria.api.metamodel.member.of;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SetAttribute;

/**
 *
 * @author richter
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManager em = null;
        AUser user = null;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ConcreteEntity> criteria = cb.createQuery(ConcreteEntity.class);
        Root<ConcreteEntity> concreteRoot = criteria.from(ConcreteEntity.class);
        criteria.select(concreteRoot);
        SetAttribute<AbstractEntity, AUser> b = ConcreteEntity_.createdBy;
        PluralAttribute<ConcreteEntity, Set<AUser>, AUser> d = b;
        Expression<Set<AUser>> c = concreteRoot.get(d);
        criteria.where(cb.isNotMember(user, c));
        TypedQuery<ConcreteEntity> query = em.createQuery(criteria);
        List<ConcreteEntity> result = query.getResultList();
    }
    
}
