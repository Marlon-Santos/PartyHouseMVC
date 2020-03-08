package br.com.gft.mvc.apiController;

import br.com.gft.mvc.apiController.dto.HistoricDto;
import br.com.gft.mvc.apiController.dto.UserDto;
import br.com.gft.mvc.config.errorsHandle.BadRequest;
import br.com.gft.mvc.model.entity.Ticket;
import br.com.gft.mvc.model.entity.User;
import br.com.gft.mvc.model.repository.TicketRepository;
import br.com.gft.mvc.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendas")
public class HistoricApiController {
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping
    public ResponseEntity<List<HistoricDto>> listUsers() {
        List<Ticket> tickets = ticketRepository.findAll();
        if (tickets.size() > 0) {
            List<HistoricDto> historic = HistoricDto.converter(tickets);
            System.out.println(historic.size()+"tamanho da lista >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            return ResponseEntity.ok(historic);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> listUser(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            return ResponseEntity.ok(new HistoricDto(ticket.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
