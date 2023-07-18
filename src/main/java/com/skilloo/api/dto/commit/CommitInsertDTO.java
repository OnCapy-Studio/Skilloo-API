package com.skilloo.api.dto.commit;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommitInsertDTO {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @PastOrPresent
    private LocalDateTime data;
}
