/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package richtercloud.jpa.criteria.api.metamodel.member.of;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
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
        main0();
        main1();
    }

    private static void main0() {
        EntityManager em = null;
        AUser user = null;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ConcreteEntity> criteria = cb.createQuery(ConcreteEntity.class);
        Root<ConcreteEntity> concreteRoot = criteria.from(ConcreteEntity.class);
        criteria.select(concreteRoot);
        SetAttribute<AbstractEntity, AUser> b = ConcreteEntity_.createdBy;
        PluralAttribute<AbstractEntity, Set<AUser>, AUser> d = b;
        Expression<Set<AUser>> c = concreteRoot.get(d);
        criteria.where(cb.isNotMember(user, c));
        TypedQuery<ConcreteEntity> query = em.createQuery(criteria);
        List<ConcreteEntity> result = query.getResultList();
    }

    private static void main1() {
        MyEntityManager em = null;
        AUser user = null;
        MyCriteriaBuilder cb = em.getCriteriaBuilder();
        MyCriteriaQuery<ConcreteEntity> criteria = cb.createQuery(ConcreteEntity.class);
        MyRoot<ConcreteEntity> concreteRoot = criteria.from(ConcreteEntity.class);
        criteria.select(concreteRoot);
        SetAttribute<AbstractEntity, AUser> b = ConcreteEntity_.createdBy;
        PluralAttribute<AbstractEntity, Set<AUser>, AUser> d = b;
        MyExpression<Set<AUser>> c = concreteRoot.get(d);
        criteria.where(cb.isNotMember(user, c));
        MyTypedQuery<ConcreteEntity> query = em.createQuery(criteria);
        List<ConcreteEntity> result = query.getResultList();
    }

    private interface MyEntityManager {
        MyCriteriaBuilder getCriteriaBuilder();

        <T> MyTypedQuery<T> createQuery(MyCriteriaQuery<T> criteriaQuery);
    }

    private interface MyCriteriaBuilder {
        <T> MyCriteriaQuery<T> createQuery(Class<T> clazz);

        <E, C extends Collection<E>> Predicate isNotMember(E elem, MyExpression<C> collection);
    }

    private interface MyCriteriaQuery<T> {
        MyRoot<T> from(Class<T> clazz);

        MyCriteriaQuery<T> select(MyRoot<T> clazz);

        MyCriteriaQuery<T> where(MyExpression<Boolean> restriction);
    }

    private interface MyRoot<X> {
        //proposed change
        <E, C extends Collection<E>> MyExpression<C> get(PluralAttribute<? super X, C, E> collection);
        //original
//        <E, C extends java.util.Collection<E>> Expression<C> get(PluralAttribute<X, C, E> collection);
    }

    private interface MySetAttribute<X, E> extends PluralAttribute<X, java.util.Set<E>, E> {
    }

    private interface MyExpression<C> {
    }

    private interface MyTypedQuery<X> {
        List<X> getResultList();
    }
}
