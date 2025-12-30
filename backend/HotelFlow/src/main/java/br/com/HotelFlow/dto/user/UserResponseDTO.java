package br.com.HotelFlow.dto.user;

import br.com.HotelFlow.model.UserModel;

public record UserResponseDTO(
        Long id,
        String role,
        String name,
        String email,
        Boolean activate
) {
    public UserResponseDTO(UserModel user){
        this(
                user.getId(),
                (user.getRole().getName() != null) ? user.getRole().getName() : null,
                user.getName(),
                user.getEmail(),
                user.getActive()
        );
    }
}
