package ma.s.gcm.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import ma.s.gcm.dto.views.UserView;

@Data @AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class VilleDto {
    @JsonView(UserView.Basic.class)
    private Long id;
    @JsonView(UserView.Basic.class)
    private String nom;
}
