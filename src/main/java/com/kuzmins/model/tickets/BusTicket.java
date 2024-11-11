package com.kuzmins.model.tickets;

import lombok.Data;

@Data
public class BusTicket {

  private String ticketClass;

  private String ticketType;

  private String startDate;

  private String price;
}
