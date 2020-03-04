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

    @PostMapping
    @Transactional
    public ResponseEntity<PartyHouseDto> save(@RequestBody @Valid PartyHouseForm partyHouseForm, UriComponentsBuilder uriBuilder) {
        PartyHouse partyHouse = partyHouseForm.converter();
        System.out.println(partyHouse.getId() + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        partyHouseRepository.save(partyHouse);
        System.out.println(partyHouse.getId() + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        URI uri = uriBuilder.path("{id}").buildAndExpand(partyHouse.getId()).toUri();
        return ResponseEntity.created(uri).body(new PartyHouseDto(partyHouse));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<PartyHouseDto> update(@PathVariable Long id, @RequestBody @Valid PartyHouseForm partyHouseForm) {
        PartyHouseDto partyHouseDto = partyHouseForm.update(id, partyHouseRepository);
        return ResponseEntity.ok(partyHouseDto);
    }
}
