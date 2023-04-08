package backend.week8.domain.item.dto.response;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindItemsResponseDto {
    private List<ItemResponseDto> items;
}
