package br.com.gft.mvc.apiController.form;

import br.com.gft.mvc.apiController.dto.PartyHouseDto;
import br.com.gft.mvc.model.entity.PartyHouse;
import br.com.gft.mvc.model.repository.PartyHouseRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

public class PartyHouseForm {
    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    private String address;

    public PartyHouse converter() {
        return new PartyHouse(name, address);
    }
    public PartyHouseDto update(Long id, PartyHouseRepository partyHouseRepository){
        Optional<PartyHouse> partyHouse = partyHouseRepository.findById(id);
        if(partyHouse.isPresent()){
            partyHouse.get().setName(name);
            partyHouse.get().setAddress(address);
            return new PartyHouseDto(partyHouse.get());
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartyHouseForm that = (PartyHouseForm) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
