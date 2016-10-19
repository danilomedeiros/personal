package com.medeiros.personal.data;

import java.util.Collection;

import com.medeiros.personal.domain.Aluno;

/**
 * QuickTickets Dashboard backend API.
 */
public interface DataProvider {
    /**
     * @param count
     *            Number of transactions to fetch.
     * @return A Collection of most recent transactions.
     */
    Collection<Aluno> getAlunos();

    int getUnreadNotificationsCount();

}
