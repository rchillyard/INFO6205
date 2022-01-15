package edu.neu.coe.info6205;

import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TicketTest {

    @Test
    public void getTickets() {
        Random random = new Random(0L);
        List<Long> tickets = Ticket.getTickets(2, 1, random);
        assertEquals(Long.valueOf(311L), tickets.get(0));
        assertEquals(Long.valueOf(313L), tickets.get(1));
    }

    @Test
    public void getFactor() {
        Random random = new Random(0L);
        assertEquals(233, Ticket.getFactor(random));
    }
}