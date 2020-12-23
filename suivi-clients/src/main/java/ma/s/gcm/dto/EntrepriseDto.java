package ma.s.gcm.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import ma.s.gcm.domain.Compte;
import ma.s.gcm.dto.views.UserView;

import javax.validation.constraints.NotNull;
import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EntrepriseDto {
    @JsonView(UserView.Basic.class)
    private Long id;
    @JsonView(UserView.Basic.class)
    private String nom;
    @JsonView(UserView.Basic.class)
    private String pattente;
    @JsonView(UserView.Basic.class)
    private Collection<Compte> comptes;
}
