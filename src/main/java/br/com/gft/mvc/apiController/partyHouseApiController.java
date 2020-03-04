package br.com.gft.mvc.apiController;

import br.com.gft.mvc.apiController.dto.PartyHouseDto;
import br.com.gft.mvc.apiController.form.PartyHouseForm;
import br.com.gft.mvc.model.entity.PartyHouse;
import br.com.gft.mvc.model.repository.PartyHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/casas")
public class partyHouseApiController {
    @Autowired
    private PartyHouseRepository partyHouseRepository;

    @GetMapping
    public List<PartyHouseDto> partyHouseList() {
        List<PartyHouse> partyHouseList = partyHouseRepository.findAll();
        return PartyHouseDto.converter(partyHouseList);
    }

    @GetMapping("{id}")
    public PartyHouseDto findById(@PathVariable Long id) {
        Optional<PartyHouse> partyHouse = partyHouseRepository.findById(id);
        if (partyHouse.isPresent()) {
            return new PartyHouseDto(partyHouse.get());
        } else {
            return null;
        }
    }

    @GetMapping("/nome/{name}")
    public PartyHouseDto findByName(@PathVariable String name) {
        Optional<PartyHouse> partyHouse = partyHouseRepository.findByName(name);
        if (partyHouse.isPresent()) {
            return new PartyHouseDto(partyHouse.get());
        } else {
            return null;
        }
    }

    @GetMapping("/asc")
    public List<PartyHouseDto> findAllByAsc() {
        List<PartyHouse> partyHouse = partyHouseRepository.findAllByOrderByNameAsc();
        return PartyHouseDto.converter(partyHouse);
    }

    @GetMapping("/desc")
    public List<PartyHouseDto> findAllByDesc() {
        List<PartyHouse> partyHouse = partyHouseRepository.findAllByOrderByNameDesc();
        return PartyHouseDto.converter(partyHouse);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PartyHouseDto> save(@RequestBody @Valid PartyHouseForm partyHouseForm, UriComponentsBuilder uriBuilder) {
        PartyHouse partyHouse = partyHouseForm.converter();
        partyHouseRepository.save(partyHouse);
        URI uri = uriBuilder.path("{id}").buildAndExpand(partyHouse.getId()).toUri();
        return ResponseEntity.created(uri).body(new PartyHouseDto(partyHouse));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<PartyHouseDto> update(@PathVariable Long id, @RequestBody @Valid PartyHouseForm partyHouseForm) {
        PartyHouseDto partyHouseDto = partyHouseForm.update(id, partyHouseRepository);
        return ResponseEntity.ok(partyHouseDto);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<PartyHouse> partyHouse = partyHouseRepository.findById(id);
        if (partyHouse.isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
