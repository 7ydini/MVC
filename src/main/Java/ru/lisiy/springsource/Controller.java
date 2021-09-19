package ru.lisiy.springsource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lisiy.springsource.dao.PersonDAO;
import ru.lisiy.springsource.dao.TicketDAO;

@org.springframework.stereotype.Controller
@RequestMapping("/tickets")
public class Controller {

    private final TicketDAO ticketDAO;
    private final PersonDAO personDAO;

    @Autowired
    public Controller(TicketDAO ticketDAO, PersonDAO personDAO) {
        this.ticketDAO = ticketDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("ticket", ticketDAO.index());
        return "ticket/index";
    }

    @GetMapping("/{id}}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("ticket", personDAO.show(id));
        return "person/show";
    }

}
