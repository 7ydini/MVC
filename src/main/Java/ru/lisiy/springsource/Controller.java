package ru.lisiy.springsource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lisiy.springsource.dao.PersonDAO;
import ru.lisiy.springsource.dao.TicketDAO;
import ru.lisiy.springsource.models.Person;

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

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        if(personDAO.show(id).getId() == 0){
            return "redirect:/tickets/"+id+"/new";
        }
        model.addAttribute("person", personDAO.show(id));
        return "person/show";
    }

    @GetMapping("/{id}/new")
    public String newPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", new Person());
        model.addAttribute("id", id);
        return "person/create";
    }

    @PostMapping("/{id}/new")
    public String create(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        personDAO.save(person);
        ticketDAO.update(id, person);
        return "redirect:/tickets";
    }
}
