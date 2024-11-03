package com.kuzmins.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuzmins.model.BusTicket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Service
public class TicketDataReader {

    @Value("ticketData.txt")
    Resource resourceFile;

    public List<BusTicket> ticketsFromJSONDataFile() throws JsonProcessingException {
        List<String> stringList = getInput();
        List<BusTicket> listOfBusTickets = new ArrayList<>();
        for (String string : stringList) {
            BusTicket busTicket = new ObjectMapper().readValue(string, BusTicket.class);
            listOfBusTickets.add(busTicket);
        }
        return listOfBusTickets;
    }

    private List<String> getInput() {

        ArrayList<String> list = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceFile.getInputStream()))) {
            while (bufferedReader.ready()) {
                String newLine = bufferedReader.readLine().replace("“", "\"");
                list.add(newLine);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (!list.isEmpty()) {
            return list;
        } else {
            throw new IllegalArgumentException("List of Strings is empty");
        }
    }
}
