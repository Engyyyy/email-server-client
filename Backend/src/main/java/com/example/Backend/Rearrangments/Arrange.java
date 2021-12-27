package com.example.Backend.Rearrangments;

import com.example.Backend.Model.Email;

import java.util.*;

public class Arrange /*implements ArrangeI*/ {

    /*public PriorityQueue<Entry> arrange(HashMap<UUID, Email> list) {
        PriorityQueue<Entry> q = new PriorityQueue<>((x, y) -> {
            return Integer.compare(y.getValue().getHeader().getPriority(), x.getValue().getHeader().getPriority());
        });
        for (HashMap.Entry<UUID, Email> emailEntry : list.entrySet()) {
            q.add(new Entry(emailEntry.getValue().getHeader().getPriority()+"", emailEntry.getValue()));
        }
        return q;
    }*/
   public PriorityQueue<Map.Entry<String, Email>> arrange(HashMap<UUID, Email> list) {
        PriorityQueue<Map.Entry<String, Email>> queue = new PriorityQueue<>((a, b)->(b.getValue().getHeader().getPriority()+"").compareTo(a.getValue().getHeader().getPriority()+""));
        for (HashMap.Entry<UUID, Email> emailEntry : list.entrySet()) {
            queue.add(new AbstractMap.SimpleEntry<>(emailEntry.getValue().getHeader().getPriority()+"", emailEntry.getValue()));
        }
        return queue;
    }
}
