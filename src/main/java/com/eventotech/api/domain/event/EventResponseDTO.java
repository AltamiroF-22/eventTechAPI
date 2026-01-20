package com.eventotech.api.domain.event;

import java.util.UUID;
import java.util.Date;

public record EventResponseDTO(
    UUID            id,
    String          title,
    String          description,
    Date            date,
    String          city,
    String          state,
    Boolean         remote,
    String          eventUrl,
    String          imgUrl
) {
}

