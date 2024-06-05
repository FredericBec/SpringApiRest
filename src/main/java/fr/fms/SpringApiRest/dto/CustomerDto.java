package fr.fms.SpringApiRest.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CustomerDto {

    private String name;
    private String firstName;
    private String address;
    private String email;
    private String phone;

    public CustomerDto (String name, String firstName, String address, String email, String phone){
        this.name=name;
        this.firstName=firstName;
        this.address=address;
        this.email=email;
        this.phone=phone;
    }
}
