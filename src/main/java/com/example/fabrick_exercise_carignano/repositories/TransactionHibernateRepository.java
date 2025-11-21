package com.example.fabrick_exercise_carignano.repositories;

import com.example.fabrick_exercise_carignano.repositories.dao.Transaction;
import com.example.fabrick_exercise_carignano.repositories.filter.TransactionFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TransactionHibernateRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Transaction> findTransactionsByFilter(TransactionFilter filter) {
        String hql = "SELECT t FROM Transaction t WHERE t.amount BETWEEN :minAmount AND :maxAmount AND t.paymentDate BETWEEN :fromDate AND :toDate AND t.paymentMethod.circuitType = :circuit";
        return entityManager.createQuery(hql, Transaction.class)
                .setParameter("minAmount", filter.getMinAmount())
                .setParameter("maxAmount", filter.getMaxAmount())
                .setParameter("fromDate", filter.getFromDate())
                .setParameter("toDate", filter.getToDate())
                .setParameter("circuit", filter.getCircuit())
                .getResultList();
    }
}
