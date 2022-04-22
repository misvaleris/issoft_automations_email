package my.test.shop.userData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserData {
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private String city;
    private String postCode;
}
