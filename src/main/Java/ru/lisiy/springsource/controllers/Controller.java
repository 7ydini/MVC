package ru.lisiy.springsource.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lisiy.springsource.dao.TicketDAO;

@org.springframework.stereotype.Controller
@RequestMapping("/tickets")
public class Controller {

    private final TicketDAO ticketDAO;

    @Autowired
    public Controller(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @GetMapping("/place")
    public String index(Model model){
        model.addAttribute("ticket", ticketDAO.index());
        return "ticket/index";
    }
}
