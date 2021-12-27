package com.example.Backend.Rearrangments;

import com.example.Backend.Model.Email;

import java.util.*;

public interface ArrangeI {
    public PriorityQueue<Entry>  arrange(HashMap<UUID, Email> list);
}
